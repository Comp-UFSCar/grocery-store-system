package projetomercado;

import java.util.ArrayList;

public class GerenciamentoProdutos {

    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public void insereProduto(int codigo, String descricao, double precoCompra, double precoVenda,
                              String unidade, double estoque, int status){
        try{
            if(!validaCodigo(codigo))
                throw new RuntimeException("Codigo ja existente no sistema: " + codigo);
            Produto p = new Produto(codigo, descricao, precoCompra, precoVenda, unidade, estoque, status);
            this.produtos.add(p);
        } catch (RuntimeException re) {
            System.err.println(re);
        }
    }

    public void alteraEstoque(int codigo, double estoque){
        try{
            Produto p = consultaProdutoPorCodigo(codigo);
            if(p == null)
                throw new RuntimeException("Produto de codigo " + codigo + " nao encontrado");
            p.setEstoque(estoque);
        } catch (RuntimeException re)
        {
            System.err.println(re);
        }
    }

    public Produto consultaProdutoPorCodigo(int codigo){
        for(Produto p : this.produtos){
            if(p.getCodigo() == codigo)
                return p;
        }
        return null;
    }
    
    public ArrayList<Integer> consultaPorDescricao(String descricao){
    	ArrayList<Integer> codigos = new ArrayList<Integer>();
    	
    	for(Produto p: this.produtos){
    		if(p.getDescricao().startsWith(descricao))
    			codigos.add(p.getCodigo());	
    	}
    	
    	if (codigos.isEmpty())
    		codigos.add(-1);
    	
    	return codigos;
    }
    
    
    public ArrayList<Produto> getProdutos(){
        return this.produtos;
    }

    public Produto getProduto(int codigo){
        return consultaProdutoPorCodigo(codigo);
    }

    private boolean validaCodigo(int codigo){
        for (Produto p : this.produtos){
            if(p.getCodigo() == codigo)
                return false;
        }
        return true;
    }

    public void listaProdutos(){
        for(Produto p : produtos)
            exibeProduto(p);
    }

    public void exibeProduto(Produto p){
        System.out.println(
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
