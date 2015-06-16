package projetomercado;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistroVenda {
	private int numero;
	private Date data;
	private int quantidadeItens;
	private ArrayList<Double> quantidades = new ArrayList<Double>();
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	private Cliente cliente;
	
	RegistroVenda(String registroVenda){		
		// Divide a string em 4 partes
		String partes[] = registroVenda.split(";",4);
		String regCodigoVenda = partes[0];
		String regCpfCliente = partes[1];
		String regDataVenda = partes[2];
		String regProdutosQuantidades = partes[3];
		
		// Sets
		setCodigoVenda(regCodigoVenda);
		setDataVenda(regDataVenda);
		setQuantidadesEProdutos(regProdutosQuantidades);
		setCliente(regCpfCliente);

	}

	/////////////////////////////////////////////////////////////////////
	///////////////////// Sets / Gets ///////////////////////////////////
	/////////////////////////////////////////////////////////////////////

	private void setCodigoVenda(String regCodigoVenda) {
		int codigo = Integer.parseInt(regCodigoVenda);
		
		if (codigo <= 0)
			throw new RuntimeException ("Codigo de registro de venda invalido: negativo ou zero");
		
		for (RegistroVenda r : GerenciadorRegistrosVenda.getRegistros()){
			if (codigo == r.getCodigo())
				throw new RuntimeException ("Codigo de registro de venda invalido: repetido");
		}
		
		this.numero = codigo;
	}

	private void setDataVenda(String regDataVenda) {	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		try {
			data = df.parse(regDataVenda);
		} catch (ParseException e) {
			throw new RuntimeException("Data invalida");
		}
	}

	private void setQuantidadesEProdutos(String regProdutosQuantidades) {
		String[] partes = regProdutosQuantidades.split(";");
		quantidadeItens = partes.length/2;

		Produto produto;
		int cod;
		double qtd;
		
		// Separa string em codigo e quantidade
		// reduz estoque e adiciona nos arrays
		
		// Obs: se já chegou aqui é pq passou na validação (produto encontrado e qtd>estoque)
		for (int i = 0 ; i <= quantidadeItens; i+=2){
			cod = Integer.parseInt(partes[i]);
			qtd = Double.parseDouble(partes[i+1]);

			produto = GerenciadorProdutos.consultaProdutoPorCodigo(cod);
			if (produto == null){
				throw new RuntimeException("Produto codigo " + cod + " nao encontrado");
			}
			if (produto.getEstoque() < qtd)
				throw new RuntimeException("Produto codigo " + cod + " nao tem a quantidade pedida: " 
						+ qtd + ", estoque: " + produto.getEstoque());
			
			GerenciadorProdutos.alteraEstoque(produto.getCodigo(), produto.getEstoque() - qtd);
			getProdutos().add(produto);
			quantidades.add(qtd);
		}
		
	}
	
	private void setCliente(String regCpfCliente) {
		Cliente c = GerenciadorClientes.consultaClientePorCpf(regCpfCliente);
		if (c == null)
			throw new RuntimeException ("CPF nao encontrado");
		
		cliente = c;
	}
	
	
	public int getCodigo(){
		return numero;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public ArrayList<Double> getQuantidades() {
		return quantidades;
	}

	public int getQuantidadeItens() {
		return quantidadeItens;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public Date getData(){
		return data;
	}
	
	// outros metodos
	
	public double faturamento(){
		double fat = 0;
		
		for (int i = 0; i < quantidadeItens; i++){
			Produto p = produtos.get(i);
			double qtd = quantidades.get(i);
			double precoVenda = p.getPrecoVenda() * qtd;
			double precoCompra = p.getPrecoCompra() * qtd;
			fat += (precoVenda - precoCompra);
		}
		
		return fat;
	}

}
