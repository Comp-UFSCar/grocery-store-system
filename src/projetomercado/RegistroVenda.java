package projetomercado;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class RegistroVenda {
	int codigoVenda;
	String dataVenda;
	int quantidadeTotal;
	// Fazer um Map com Produtos e Quantidades?
	ArrayList<Float> quantidades;
	ArrayList<Produto> produtos;
	Cliente cliente;
	
	private static int codigoUltimaVenda;
	private GerenciamentoProdutos gProdutos;
	private GerenciamentoClientes gClientes;
	
	RegistroVenda(String registroVenda, GerenciamentoProdutos gProdutos, 
			GerenciamentoClientes gClientes){
		this.gProdutos = gProdutos;
		this.gClientes = gClientes;
		
		String[] partes = registroVenda.split(";");
		String regCodigoVenda = partes[0];
		String regCpfCliente = partes[1];
		String regQuantidadeTotal = partes[2];
		String regDataVenda = partes[3];
		String regProdutosQuantidades = partes[4];
		
		if (validaRegistroVenda(regCodigoVenda, regCpfCliente, regQuantidadeTotal,
				regDataVenda, regProdutosQuantidades)){
			setCodigoVenda(regCodigoVenda);
			setDataVenda(regDataVenda);
			setQuantidadesEProdutos(regQuantidadeTotal, regProdutosQuantidades);
			setCliente(regCpfCliente);
		}
		else
			throw new RuntimeException();
	}

	/////////////////////////////////////////////////////////////////////
	///////////////////// Validação /////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	private boolean validaRegistroVenda(String codigoVenda, String cpfCliente, 
			String quantidadeTotal, String dataVenda, String produtosQuantidades){
		return (validaCodigoVenda(codigoVenda) ||
				validaCpfCliente(cpfCliente) ||
				validaQuantidadeTotal(quantidadeTotal) ||
				validaDataVenda(dataVenda) ||
				validaProdutosQuantidades(quantidadeTotal, produtosQuantidades));
	}

	private boolean validaCodigoVenda(String cod){
		// TODO verificar na lista de registros venda?
		int codigo = Integer.parseInt(cod);
		return (codigo > codigoUltimaVenda);
	}

	private boolean validaCpfCliente(String cpf){
		// TODO
		return false;
	}

	private boolean validaQuantidadeTotal(String tot){
		// parseInt joga exceçao se nao for um inteiro valido
		int total = Integer.parseInt(tot);
		return (total>0);
	}
	
	private boolean validaDataVenda(String data){
		// TODO: verificar se mes e dia sao validos no calendario
		return data.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
	}
	
	private boolean validaProdutosQuantidades(String total, String produtosQuantidades){
		return false;
	}
	
	/////////////////////////////////////////////////////////////////////
	///////////////////// Sets //////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////

	private void setCodigoVenda(String regCodigoVenda) {
		codigoVenda = Integer.parseInt(regCodigoVenda);
	}

	private void setDataVenda(String regDataVenda) {
		dataVenda = regDataVenda;
	}

	private void setQuantidadesEProdutos(String regQuantidadeTotal,
			String regProdutosQuantidades) {
		quantidadeTotal = Integer.parseInt(regProdutosQuantidades);
		
		String[] partes = regProdutosQuantidades.split(";");

		Produto produto;
		int cod;
		float qtd;
		
		// Separa string em codigo e quantidade
		// procura na lista de produtos
		for (int i = 0 ; i < quantidadeTotal; i+=2){
			cod = Integer.parseInt(partes[i]);
			qtd = Float.parseFloat((partes[i+1]));

			produto = gProdutos.consultaProdutoPorCodigo(cod);
			gProdutos.alteraEstoque(cod, qtd);
			produtos.add(produto);
			quantidades.add(qtd);
		}
		
	}
	
	private void setCliente(String regCpfCliente) {
		//cliente = gClientes.consultaClientePorCpf(regCpfCliente);
	}
	

}
