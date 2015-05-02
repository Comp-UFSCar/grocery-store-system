package projetomercado;

public class Produto {

	private int codigo;
	private String descricao;
	private double precoCompra;
	private double precoVenda;
	private String unidade;
	private double estoque;
	private int status;

	Produto (int codigo, String descricao, double precoCompra, double precoVenda,
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

	public void setCodigo(int codigo) {
		try {
			if(codigo <= 0)
				throw new RuntimeException("Codigo invalido " + codigo);

			this.codigo = codigo;
		}
		catch (RuntimeException re){
			System.err.println(re);
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		try{
			if(!(descricao.matches("[\\w|\\s]{3,64}")))
				throw new RuntimeException("Descricao invalida: " + descricao);

			this.descricao = descricao;
		}
		catch (RuntimeException re){
			System.err.println(re);
		}
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		try{
			if(precoCompra <= 0)
				throw new RuntimeException("Preco de compra invalido: " + precoCompra);

			this.precoCompra = precoCompra;
		} catch (RuntimeException re){
			System.err.println(re);
		}
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		try{
			if(precoVenda < this.precoCompra)
				throw new RuntimeException("Preco de venda invalido: " + precoVenda);

			this.precoVenda = precoVenda;
		} catch (RuntimeException re){
			System.err.println(re);
		}
		this.precoVenda = precoVenda;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		try{
			if(!(unidade.matches("[a-zA-Z]{2}")))
				throw new RuntimeException("Unidade invalida: " + unidade);

			this.unidade = unidade;
		}catch(RuntimeException re){
			System.err.println(re);
		}
	}

	public double getEstoque() {
		return estoque;
	}

	public void setEstoque(double estoque) {
		try{
			if(estoque < 0)
				throw new RuntimeException("Estoque invalido: " + estoque);

			this.estoque = estoque;
		} catch(RuntimeException re){
			System.err.println(re);
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		try {
			if (!(status == 1 || status == 0))
				throw new RuntimeException("Status - valor invalido: " + status);
			if (status == 0 && this.estoque > 0)
				throw new RuntimeException("Status - nao pode ser desativado, ainda ha estoque");

			this.status = status;
		} catch (RuntimeException re) {
			System.err.println(re);
		}
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
