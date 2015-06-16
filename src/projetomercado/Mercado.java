package projetomercado;

import ui.MainUI;

import javax.swing.*;
import java.lang.reflect.Array;
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
		GerenciadorProdutos.insereProduto(00001, "Produto Um", 1.50f, 2.50f, "kg", 10f, Produto.ATIVO);
		GerenciadorProdutos.insereProduto(00002, "Produto Dois", 10.00f, 11.15f, "mm", 10f, Produto.ATIVO);
		GerenciadorProdutos.insereProduto(00003, "Um produto qualquer", 9.25f, 16.30f, "ml", 0, Produto.INATIVO);
		GerenciadorProdutos.insereProduto(00004, "Outro produto", 0.50f, 1.00f, "kg", 10f, Produto.ATIVO);
		GerenciadorProdutos.insereProduto(00005, "Mais um produto", 120.13f, 141.90f, "kg", 10f, Produto.ATIVO);

		dialog = new MainUI();
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		System.exit(0);
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

	public static void updateTableProduto(){
		dialog.relistProdutos();
	}

	public static void searchTableProduto(Produto p){
		dialog.searchProduto(p);
	}

	public static void searchTableProduto(ArrayList<Integer> codigos){
		ArrayList<Produto> ps = new ArrayList<Produto>();
		for(int c : codigos )
			ps.add(GerenciadorProdutos.consultaProdutoPorCodigo(c));
		dialog.searchProduto(ps);
	}
}
