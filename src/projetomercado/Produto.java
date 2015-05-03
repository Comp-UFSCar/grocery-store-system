package projetomercado;

public class Produto {

	private int codigo;
	private String descricao;
	private double precoCompra;
	private double precoVenda;
	private String unidade;
	private double estoque;
	private int status;

	// variaveis de controle para status
	public static final int ativo = 1;
	public static final int inativo = 0;

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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) throws RuntimeException {
		// codigo eh inteiro e > 0
		if(codigo <= 0)
			throw new RuntimeException("Codigo invalido " + codigo);

		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) throws RuntimeException {
		// apenas alfanumericos entre 3 e 64 caracteres
		if(!(descricao.matches("[\\w|\\s]{3,64}")))
			throw new RuntimeException("Descricao invalida: " + descricao);

		this.descricao = descricao;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		// preco de compra > 0
		if(precoCompra <= 0)
			throw new RuntimeException("Preco de compra invalido: " + precoCompra);

		this.precoCompra = precoCompra;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		// preco de venda > preco de compra
		if(precoVenda < this.precoCompra)
			throw new RuntimeException("Preco de venda invalido: " + precoVenda);

		this.precoVenda = precoVenda;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		if(!(unidade.matches("[a-zA-Z]{2}")))
			throw new RuntimeException("Unidade invalida: " + unidade);

		this.unidade = unidade;
	}

	public double getEstoque() {
		return estoque;
	}

	public void setEstoque(double estoque) {
		// estoque >= 0
		if(estoque < 0)
			throw new RuntimeException("Estoque invalido: " + estoque);

		this.estoque = estoque;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		// status pode ser 'ativo' ou 'inativo' (1 ou 0)
		if (!(status == ativo || status == inativo))
			throw new RuntimeException("Status - valor invalido: " + status);
		// status soh pode ser inativo se estoque = 0
		if (status == inativo && this.estoque > 0)
			throw new RuntimeException("Status - nao pode ser desativado, ainda ha estoque");

		this.status = status;
	}

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
}
