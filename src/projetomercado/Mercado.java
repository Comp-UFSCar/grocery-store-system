package projetomercado;

import java.util.ArrayList;

public class Mercado {
	
	public static void main(String args[])
	{
		
		// insereProduto(codigo, descricao, precoCompra, precoVenda, unidade, estoque, status)
		GerenciadorProdutos.insereProduto(11111, "este eh o produto 1", 1.00, 5.99, "cm", 10.5, Produto.ATIVO);
		GerenciadorProdutos.insereProduto(22222, "e este eh o produto 2", 2.00, 7.99, "kg", 10.5, 1);
		GerenciadorProdutos.insereProduto(33333, "por fim o produto 3", 3.00, 10.99, "mm", 10.5, 1);
		
		// insereCliente(nome, cpf,String endereco, telefone, email, status)
		GerenciadorClientes.insereCliente("Jose Silva", "52882174160", "Rua Episcopal 111", "(16)988886666", "email@email.com", Cliente.ATIVO);
		
		GerenciadorProdutos.listaProdutos();
		
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
		GerenciadorRegistrosVenda.buscaPorNumero(1);
		GerenciadorRegistrosVenda.printRegistro(1);
		System.out.println("*faturamento: "+GerenciadorRegistrosVenda.buscaPorNumero(1).faturamento());
		
		GerenciadorProdutos.listaProdutos();
		
		{ 
			String numeroRegVenda = "2";
			String clienteCpf = "52882174160";
			String dataVenda = "01/06/2015";
			String codProduto1 = "11111";		// Testar com 11111 e 11112 (nao existe)
			String qtdProduto1 = "5.0"; 		// Testar com 1.0 e 100.0 (nao tem no estoque)
			String codProduto2 = "33333";
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
		System.out.println("*faturamento: " + GerenciadorRegistrosVenda.buscaPorNumero(2).faturamento());
		GerenciadorProdutos.listaProdutos();

		System.out.println("\n\nFaturamento de junho: " + calculaFaturamento(6));
	}

	private static double calculaFaturamento(int mes){
		ArrayList<RegistroVenda> registros;
		double fat = 0;
		
		try {
			registros = GerenciadorRegistrosVenda.getRegistroDoMes(mes);
			if (registros == null)
				return 0;
			for (RegistroVenda r : registros){
				fat += r.faturamento();
			}		
		} 
		catch (RuntimeException re){
			re.printStackTrace(System.out);
		}
		return fat;
	}

	public ArrayList<Integer> consultaProdutos(String descricao){
		return GerenciadorProdutos.consultaPorDescricao(descricao);
	}

}
