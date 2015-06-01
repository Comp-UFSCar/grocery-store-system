package projetomercado;

import java.util.ArrayList;

public class Produto {

	private int codigo;
	private String descricao;
	private double precoCompra;
	private double precoVenda;
	private String unidade;
	private double estoque;
	private int status; 

	static ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	// variaveis de controle para status
	public static final int ATIVO = 1;
	public static final int INATIVO = 0;

	// construtor completo
	public Produto (int codigo, String descricao, double precoCompra, double precoVenda,
			 String unidade, double estoque, int status){

		setCodigo(codigo);
		setDescricao(descricao);
		setPrecoCompra(precoCompra);
		setPrecoVenda(precoVenda);
		setUnidade(unidade);
		setEstoque(estoque);
		setStatus(status);
	}
	
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

    // Sets
    
	public void setCodigo(int codigo) throws RuntimeException {
		// codigo eh inteiro e > 0
		if(codigo <= 0)
			throw new RuntimeException("Codigo invalido " + codigo);

		this.codigo = codigo;
	}

	public void setDescricao(String descricao) throws RuntimeException {
		// apenas alfanumericos entre 3 e 64 caracteres
		if(!(descricao.matches("[\\w|\\s]{3,64}")))
			throw new RuntimeException("Descricao invalida: " + descricao);

		this.descricao = descricao;
	}

	public void setPrecoCompra(double precoCompra) {
		// preco de compra > 0
		if(precoCompra <= 0)
			throw new RuntimeException("Preco de compra invalido: " + precoCompra);

		this.precoCompra = precoCompra;
	}

	public void setPrecoVenda(double precoVenda) {
		// preco de venda > preco de compra
		if(precoVenda < this.precoCompra)
			throw new RuntimeException("Preco de venda invalido: " + precoVenda);

		this.precoVenda = precoVenda;
	}

	public void setUnidade(String unidade) {
		if(!(unidade.matches("[a-zA-Z]{2}")))
			throw new RuntimeException("Unidade invalida: " + unidade);

		this.unidade = unidade;
	}

	public void setEstoque(double estoque) {
		// estoque >= 0
		if(estoque < 0)
			throw new RuntimeException("Estoque invalido: " + estoque);

		this.estoque = estoque;
	}

	public void setStatus(int status) {
		// status pode ser 'ATIVO' ou 'INATIVO' (1 ou 0)
		if (!(status == ATIVO || status == INATIVO))
			throw new RuntimeException("Status - valor invalido: " + status);
		// status soh pode ser INATIVO se estoque = 0
		if (status == INATIVO && this.estoque > 0)
			throw new RuntimeException("Status - nao pode ser desativado, ainda ha estoque");

		this.status = status;
	}
	
	// Gets
	
	public int getCodigo(){
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public String getUnidade() {
		return unidade;
	}

	public double getEstoque(){
		return estoque;
	}
	
	public int getStatus() {
		return status;
	}


	// Metodos
	
	public void alteraProduto (int codigo, String descricao, double precoCompra, double precoVenda,
							   String unidade, double estoque, int status){
		setCodigo(codigo);
		setDescricao(descricao);
		setPrecoCompra(precoCompra);
		setPrecoVenda(precoVenda);
		setUnidade(unidade);
		setEstoque(estoque);
		setStatus(status);
	}

	// Reduz estoque do proprio produto
	public void reduzEstoque (double qtd) throws RuntimeException {
		if(qtd > estoque)
			throw new RuntimeException("A quantidade " + qtd + 
					" nao esta disponivel em estoque (" + estoque + ")");

		this.setEstoque(estoque - qtd);
		
	}
	
	// Altera o estoque do produto com o codigo	(esse metodo está na especificaçao)
	public void alteraEstoque(int codigo, double estoque) {
        try{
            Produto p = consultaProdutoPorCodigo(codigo);
            p.setEstoque(estoque);
        } catch (RuntimeException re)
        {
            System.err.println(re);
        }
    }
    
	// Reduz o estoque do produto com o codigo
    public static void reduzEstoque(int codigo, double reducao) {
        try{
            Produto p = consultaProdutoPorCodigo(codigo);
            p.reduzEstoque(reducao);
        } catch (RuntimeException re)
        {
            System.err.println(re);
        }
    }

    // Esse metodo está na especificaçao
    public static Produto consultaProdutoPorCodigo(int codigo){
        for(Produto p : produtos){
            if(p.codigo == codigo)
                return p;
        }
        return null;
    }
    
    public static ArrayList<Integer> consultaPorDescricao(String descricao){
    	ArrayList<Integer> codigos = new ArrayList<Integer>();
    	
    	for(Produto p: produtos){
    		if(p.descricao.startsWith(descricao))
    			codigos.add(p.codigo);	
    	}
    	
    	if (codigos.isEmpty())
    		codigos.add(-1);
    	
    	return codigos;
    }
    
    private static boolean validaCodigo(int codigo){
        for (Produto p : produtos){
            if(p.codigo == codigo)
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
            "Codigo: " + p.codigo + "\n" +
            "Descricao: " + p.descricao + "\n" +
            "Preco de compra: " + p.precoCompra + "\n" +
            "Preco de venda: " + p.precoVenda + "\n" +
            "Unidade: " + p.unidade + "\n" +
            "Estoque: " + p.estoque + "\n" +
            "Status: " + p.status
        );
    }
    
    public static void exibeProduto(int codigo){
    	Produto p = consultaProdutoPorCodigo(codigo);
        System.out.println(
            "----------------------------------------\n" +
	        "Codigo: " + p.codigo + "\n" +
	        "Descricao: " + p.descricao + "\n" +
	        "Preco de compra: " + p.precoCompra + "\n" +
	        "Preco de venda: " + p.precoVenda + "\n" +
	        "Unidade: " + p.unidade + "\n" +
	        "Estoque: " + p.estoque + "\n" +
	        "Status: " + p.status
        );
    }
	
}
