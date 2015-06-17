package projetomercado;

import ui.MainUI;

import javax.swing.*;
import java.util.ArrayList;


public class Mercado  {

	static MainUI dialog;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}

		// Base de Dados Pre-definida
		GerenciadorProdutos.insereProduto(00001, "Produto Um", 1.50f, 2.50f, "kg", 100f, Produto.ATIVO);
		GerenciadorProdutos.insereProduto(00002, "Produto Dois", 10.00f, 11.15f, "mm", 100f, Produto.ATIVO);
		GerenciadorProdutos.insereProduto(00003, "Um produto qualquer", 9.25f, 16.30f, "ml", 0, Produto.INATIVO);
		GerenciadorProdutos.insereProduto(00004, "Outro produto", 0.50f, 1.00f, "kg", 100f, Produto.ATIVO);
		GerenciadorProdutos.insereProduto(00005, "Mais um produto", 120.13f, 141.90f, "kg", 100f, Produto.ATIVO);

		GerenciadorClientes.insereCliente(
				"Machado de Assis", "41567832270","Rua das Araucarias", "(19)134568795",  "machadoassis@brasil.com.br", Cliente.ATIVO);
		GerenciadorClientes.insereCliente(
				"Fernando Pessoa", "08853489820","Av Sao Carlos", "(16)526789112",  "fpessoa@ufscar.com", Cliente.ATIVO);
		GerenciadorClientes.insereCliente(
				"Henri Montherlant", "35412119387","Rua Carvalho", "(11)465238976",  "montherlant@cthulhu.com", Cliente.INATIVO);
		GerenciadorClientes.insereCliente(
				"Cesare Pavese", "16142848064","Av Sao Carlos", "(21)446223597",  "cesarpavese@brasil.com.br", Cliente.ATIVO);
		GerenciadorClientes.insereCliente(
				"John Milton", "70553523120","Rua Episcopal", "(14)465332335",  "miltonjohn@brasil.com.br", Cliente.ATIVO);

		GerenciadorRegistrosVenda.insereRegistroVenda("01 ; 41567832270 ; 10/05/2015 ; 1 ; 2");
		GerenciadorRegistrosVenda.insereRegistroVenda("02 ; 41567832270 ; 12/05/2015 ; 1 ; 5 ; 2 ; 1");
		GerenciadorRegistrosVenda.insereRegistroVenda("03 ; 08853489820 ; 20/05/2015 ; 2 ; 2 ; 4 ; 5; 5; 2");
		GerenciadorRegistrosVenda.insereRegistroVenda("04 ; 35412119387 ; 21/05/2015 ; 2 ; 1");
		GerenciadorRegistrosVenda.insereRegistroVenda("05 ; 70553523120 ; 25/05/2015 ; 1 ; 9.5; 2; 7.5; 4; 1; 5; 2.5");

		dialog = new MainUI();
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		System.exit(0);
	}

	public static double calculaFaturamento(int mes){
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

	public static void refreshTable(ArrayList<?> list){
		dialog.refreshTable(list);
	}
	public static void searchTable(Produto p){
		dialog.searchTable(p);
	}
	public static void searchTable(Cliente c){
		dialog.searchTable(c);
	}
	public static void searchTable(ArrayList<?> list)
	{
		dialog.searchTable(list);
	}

}
