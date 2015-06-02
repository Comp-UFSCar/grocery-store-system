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
	private String registro;
	
	
	public String getRegVenda(){
		String s = String.valueOf(numero);
		s += ";" + cliente.getCpf();
		s += ";" + data;
		s += ";" + String.valueOf(quantidadeItens);
		
		for (int i = 0; i < quantidadeItens; i++){
			s += ";" + getProdutos().get(i).getCodigo();
			s += ";" + String.valueOf(quantidades.get(i));
		}
		
		return s;
	}
	
	
	RegistroVenda(String registroVenda){		
		// Divide a string em 4 partes
		String partes[] = registroVenda.split(";",4);
		String regCodigoVenda = partes[0];
		String regCpfCliente = partes[1];
		String regDataVenda = partes[2];
		String regProdutosQuantidades = partes[3];
		
		// Validaçao
		validaCodigoVenda(regCodigoVenda);
		validaCpfCliente(regCpfCliente);
		validaDataVenda(regDataVenda);
		validaProdutosQuantidades(regProdutosQuantidades);
		
		// Sets
		setCodigoVenda(regCodigoVenda);
		setDataVenda(regDataVenda);
		setQuantidadesEProdutos(regProdutosQuantidades);
		setCliente(regCpfCliente);
		// String de entrada completa
		setRegistro(registroVenda);
	}

	/////////////////////////////////////////////////////////////////////
	///////////////////// Validação /////////////////////////////////////
	/////////////////////////////////////////////////////////////////////


	private void validaCodigoVenda(String cod){
		int codigo = Integer.parseInt(cod);
		
		if (codigo <= 0)
			throw new RuntimeException ("Codigo de registro de venda invalido: negativo ou zero");
		
		if (GerenciadorRegistrosVenda.getRegistros().isEmpty())
			return;
		
		for (RegistroVenda r : GerenciadorRegistrosVenda.getRegistros()){
			if (codigo == r.getCodigo())
				throw new RuntimeException ("Codigo de registro de venda invalido: repetido");
		}
		
	}

	private void validaCpfCliente(String cpf){
		Cliente c = GerenciadorClientes.consultaClientePorCpf(cpf);
		if (c == null)
			throw new RuntimeException ("CPF nao encontrado");
	}
	
	private void validaDataVenda(String data){
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			df.setLenient(false);
			df.parse(data);
		} catch (ParseException e){
			throw new RuntimeException("Data invalida");
		}
	}
	
	private boolean validaProdutosQuantidades(String regProdutosQuantidades){
		String[] partes = regProdutosQuantidades.split(";");
		int quantidadeItens = partes.length/2;

		Produto produto;
		int cod;
		Double qtd;
		
		// Separa string em codigo e quantidade
		// procura na lista de produtos e verifica se tem em estoque
		for (int i = 0 ; i < quantidadeItens; i+=2){
			cod = Integer.parseInt(partes[i]);
			qtd = Double.parseDouble(partes[i+1]);

			produto = GerenciadorProdutos.consultaProdutoPorCodigo(cod);
			if (produto == null){
				throw new RuntimeException("Produto codigo " + cod + " nao encontrado");
			}
			if (produto.getEstoque() < qtd)
				throw new RuntimeException("Produto codigo " + cod + " nao tem a quantidade pedida: " 
						+ qtd + ", estoque: " + produto.getEstoque());
		}
		return true;
	}
	
	/////////////////////////////////////////////////////////////////////
	///////////////////// Sets / Gets ///////////////////////////////////
	/////////////////////////////////////////////////////////////////////

	private void setCodigoVenda(String regCodigoVenda) {
		numero = Integer.parseInt(regCodigoVenda);
	}

	private void setDataVenda(String regDataVenda) {	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		try {
			data = df.parse(regDataVenda);
		} catch (ParseException e) {
			e.printStackTrace();
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
			produto.reduzEstoque(qtd);
			getProdutos().add(produto);
			quantidades.add(qtd);
		}
		
	}
	
	private void setCliente(String regCpfCliente) {
		cliente = GerenciadorClientes.consultaClientePorCpf(regCpfCliente);
	}
	
	
	public int getCodigo(){
		return numero;
	}

	
	public String getRegistro(){
		return registro;
	}
	
	private void setRegistro(String registroVenda) {
		registro = registroVenda;
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
	
	

}
