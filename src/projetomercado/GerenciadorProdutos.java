package projetomercado;

import java.util.ArrayList;

public class GerenciadorProdutos {

	static ArrayList<Produto> produtos = new ArrayList<Produto>();

    public static void insereProduto(int codigo, String descricao, double precoCompra, double precoVenda,
            String unidade, double estoque, int status)
    {
        // Verifica se o código é repetido no array produtos
        if(!validaCodigo(codigo))
            throw new RuntimeException("Codigo ja existente no sistema: " + codigo);

        Produto p = new Produto(codigo, descricao, precoCompra, precoVenda, unidade, estoque, status);
        produtos.add(p);
	}
    
    public static void alteraEstoque(int cod, double estoque) {
        Produto p = consultaProdutoPorCodigo(cod);
        if (p != null)
            p.setEstoque(estoque);
        else
            throw new RuntimeException("Codigo nao existente no sistema: " + cod);
    }

    public static Produto consultaProdutoPorCodigo(int codigo){
        if(codigo <= 0)
            throw new RuntimeException("Consulta por codigo invalido " + codigo);

        for(Produto p : produtos){
            if(p.getCodigo() == codigo)
                return p;
        }
        return null;
    }
    
    public static ArrayList<Integer> consultaPorDescricao(String descricao){
        if(!(descricao.matches("[\\w|\\s]{3,64}")))
            throw new RuntimeException("Consulta por descricao invalida: " + descricao);

        ArrayList<Integer> codigos = new ArrayList<Integer>();
    	
    	for(Produto p: produtos){
    		if(p.getDescricao().startsWith(descricao))
    			codigos.add(p.getCodigo());	
    	}
    	
    	if (codigos.isEmpty())
    		codigos.add(-1);
    	
    	return codigos;
    }
    
    private static boolean validaCodigo(int codigo){
        for (Produto p : produtos){
            if(p.getCodigo() == codigo)
                return false;
        }
        return true;
    }

    public static void listaProdutos(){
        for(Produto p : produtos)
            exibeProduto(p);
    }

    public static void exibeProduto(Produto p){
        System.out.println(
        	"----------------------------------------\n" +
			"Produto\n" +
			"----------------------------------------\n" +
            "Codigo: " + p.getCodigo() + "\n" +
            "Descricao: " + p.getDescricao() + "\n" +
            "Preco de compra: " + p.getPrecoCompra() + "\n" +
            "Preco de venda: " + p.getPrecoVenda() + "\n" +
            "Unidade: " + p.getUnidade() + "\n" +
            "Estoque: " + p.getEstoque() + "\n" +
            "Status: " + p.getStatus()
        );
    }
    
    public static void exibeProduto(int codigo){
    	Produto p = consultaProdutoPorCodigo(codigo);
        exibeProduto(p);
    }

    public static ArrayList<Produto> getProdutos(){
        return produtos;
    }
}
