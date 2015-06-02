package projetomercado;

public class Mercado {
	
	//private static ArrayList<Produto> produtos = Produto.produtos;
	//private static ArrayList<Cliente> clientes = Cliente.clientes;
	//private static ArrayList<RegistroVenda> registrosVenda = RegistroVenda.registros;
	
	public static void main(String args[])
	{
		
		// insereProduto(codigo, descricao, precoCompra, precoVenda, unidade, estoque, status)
		GerenciadorProdutos.insereProduto(11111, "este eh o produto 1", 1.00, 5.99, "cm", 10.5, Produto.ATIVO);
		GerenciadorProdutos.insereProduto(22222, "e este eh o produto 2", 2.00, 7.99, "kg", 10.5, 1);
		GerenciadorProdutos.insereProduto(33333, "por fim o produto 3", 3.00, 10.99, "mm", 10.5, 1);
		
		// insereCliente(nome, cpf,String endereco, telefone, email, status)
		GerenciadorClientes.insereCliente("Jose Silva", "52882174160", "Rua Episcopal 111", "(16)988886666", "email@email.com", Cliente.ATIVO);
		
		GerenciadorProdutos.exibeProduto(GerenciadorProdutos.consultaProdutoPorCodigo(11111));
		
		{
			String numeroRegVenda = "1";
			String clienteCpf = "52882174160";
			String dataVenda = "01/11/2015";
			String codProduto1 = "11111";		// Testar com 11111 e 11112 (nao existe)
			String qtdProduto1 = "1.0"; 		// Testar com 1.0 e 100.0 (nao tem no estoque)
			String codProduto2 = "22222";
			String qtdProduto2 = "0.1f";
			
			String registroVenda = numeroRegVenda + ";" + 
					clienteCpf + ";" + 
					dataVenda + ";" +
					codProduto1 + ";" + 
					qtdProduto1 + ";" + 
					codProduto2 + ";" +
					qtdProduto2;
			
			GerenciadorRegistrosVenda.insereRegistroVenda(registroVenda);
		}
		
		GerenciadorRegistrosVenda.printRegistro(1);
		GerenciadorProdutos.exibeProduto(GerenciadorProdutos.consultaProdutoPorCodigo(11111));
		
		{ 
			String numeroRegVenda = "2";
			String clienteCpf = "52882174160";
			String dataVenda = "01/06/2015";
			String codProduto1 = "11111";		// Testar com 11111 e 11112 (nao existe)
			String qtdProduto1 = "5.0"; 		// Testar com 1.0 e 100.0 (nao tem no estoque)
			String codProduto2 = "22222";
			String qtdProduto2 = "0.3f";
			
			String registroVenda = numeroRegVenda + ";" + 
					clienteCpf + ";" + 
					dataVenda + ";" +
					codProduto1 + ";" + 
					qtdProduto1 + ";" + 
					codProduto2 + ";" +
					qtdProduto2;
			
			GerenciadorRegistrosVenda.insereRegistroVenda(registroVenda);
		}
		
		GerenciadorRegistrosVenda.printRegistro(2);
		GerenciadorProdutos.exibeProduto(GerenciadorProdutos.consultaProdutoPorCodigo(11111));
		

		
		
		
		
		/*
		//Produto.listaProdutos();
		//Produto.alteraEstoque(11111, 0);
		//Produto.listaProdutos();
		try{
			gProdutos.getProduto(11111).setCodigo(-1);
		}
		catch (Throwable t)
		{
			System.err.println(t);
		}
		try{
			gProdutos.getProduto(11111).setDescricao("ab");
		} catch (Throwable t)
		{
			System.err.println(t);
		}
		*/
	}

	public double calculaFaturamento(int mes){
		return 0.0f;
	}

	public int[] consultaProdutos(String descricao){
		return null;
	}

}
