package projetomercado;

import java.util.ArrayList;

public class GerenciadorProdutos {

	static ArrayList<Produto> produtos = new ArrayList<Produto>();

    public static void insereProduto(int codigo, String descricao, double precoCompra, double precoVenda,
            String unidade, double estoque, int status){
		try {
			// Verifica se o código é repetido no array produtos
			if(!validaCodigo(codigo))
				throw new RuntimeException("Codigo ja existente no sistema: " + codigo);
			
			Produto p = new Produto(codigo, descricao, precoCompra, precoVenda, unidade, estoque, status);
			produtos.add(p);
		} catch (RuntimeException re) {
			//System.err.println(re);
			re.printStackTrace(System.out);
		}
	}
    
    public static void reduzEstoque(int cod, double reducao) {
        try{
            Produto p = consultaProdutoPorCodigo(cod);
            p.reduzEstoque(reducao);
        } catch (RuntimeException re)
        {
            System.err.println(re);
        }
    }

    public static Produto consultaProdutoPorCodigo(int codigo){
        for(Produto p : produtos){
            if(p.getCodigo() == codigo)
                return p;
        }
        return null;
    }
    
    // Esse metodo está na especificaçao
    public static ArrayList<Integer> consultaPorDescricao(String descricao){
    	// TODO: verificar se entrada segue:
    	// alfanumerico, tamanho mınimo 3 caracteres, tamanho
    	//  maximo 64 caracteres
    	
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
}
