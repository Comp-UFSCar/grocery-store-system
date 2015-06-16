package testes;

import org.junit.Before;
import org.junit.Test;
import projetomercado.Produto;

import static org.junit.Assert.*;

public class ProdutoTest {

    private int codigo;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    private String unidade;
    private double estoque;
    private int status;
    private Produto p;
    
    @Before
    public void criaObjetoValido(){
        codigo = 11111;
        descricao = "este eh o produto 1";
        precoCompra = 1.00;
        precoVenda = 5.99;
        unidade = "cm";
        estoque = 10.5;
        status = Produto.ATIVO;

        p = new Produto(codigo, descricao, precoCompra, precoVenda, unidade, estoque, status);
    }

    // Codigo
    @Test
    public void testGetCodigo() throws Exception {
        assertEquals(codigo, p.getCodigo(), 0);
    }

    @Test (expected = RuntimeException.class)
    public void testSetCodigoInv() throws RuntimeException {
        p.setCodigo(0);
    }

    @Test
    public void testSetCodigoVal() throws Exception {
        p.setCodigo(1);
    }

    // Descricao
    @Test
    public void testGetDescricao() throws Exception {
        assertEquals(descricao, p.getDescricao());
    }

    @Test (expected = RuntimeException.class)
    public void testSetDescricaoInv01() throws RuntimeException {
        // alfanumerico < 3
        p.setDescricao("ab");
    }

    @Test (expected = RuntimeException.class)
    public void testSetDescricaoInv02() throws RuntimeException {
        // alfanumerico > 64
        p.setDescricao("ababababababababababababababababababababababababababababababababc");
    }

    @Test (expected = RuntimeException.class)
    public void testSetDescricaoInv03() throws RuntimeException {
        // nao alfanumerico
        p.setDescricao("abc$#%ef");
    }

    @Test
    public void testSetDescricaoVal() throws Exception {
        p.setDescricao("Descricao do produto aqui");
    }

    // Preco Compra
    @Test
    public void testGetPrecoCompra() throws Exception {
        assertEquals(precoCompra, p.getPrecoCompra(), 0);
    }

    @Test (expected = RuntimeException.class)
    public void testSetPrecoCompraInv() throws RuntimeException {
        // preco compra < 0 - precisao de 2 casas decimais
        p.setPrecoCompra(-0.01);
    }

    @Test
    public void testSetPrecoCompraVal() throws Exception {
        p.setPrecoCompra(10.10);
    }

    // Preco Venda
    @Test
    public void testGetPrecoVenda() throws Exception {
        assertEquals(precoVenda, p.getPrecoVenda(), 0);
    }

    @Test (expected = RuntimeException.class)
    public void testSetPrecoVendaInv() throws RuntimeException {
        // preco venda < preco compra - precisao de 2 casas decimais
        p.setPrecoVenda(precoCompra - 0.01);
    }

    @Test
    public void testSetPrecoVendaVal() throws Exception {
        p.setPrecoVenda(precoCompra + 0.01);
    }

    // Unidade
    @Test
    public void testGetUnidade() throws Exception {
        assertEquals(unidade, p.getUnidade());
    }

    @Test (expected = RuntimeException.class)
    public void testSetUnidadeInv01() throws RuntimeException {
        // 1 caractere
        p.setUnidade("a");
    }

    @Test (expected = RuntimeException.class)
    public void testSetUnidadeInv02() throws RuntimeException {
        // 3 caracteres
        p.setUnidade("abc");
    }

    @Test (expected = RuntimeException.class)
    public void testSetUnidadeInv03() throws RuntimeException {
        // ![a-zA-Z]
        p.setUnidade("a1");
    }

    @Test
    public void testSetUnidadeVal() throws Exception {
        p.setUnidade("ml");
    }

    // Estoque
    @Test
    public void testGetEstoque() throws Exception {
        assertEquals(estoque, p.getEstoque(), 0);
    }

    @Test (expected = RuntimeException.class)
    public void testSetEstoqueInv() throws RuntimeException {
        // estoque < 0 - precisao 2 casas decimais
        p.setEstoque(-0.01);
    }

    @Test
    public void testSetEstoqueVal() throws Exception {
        p.setEstoque(0);
    }

    // Status
    @Test
    public void testGetStatus() throws Exception {
        assertEquals(status, p.getStatus());
    }

    @Test (expected = RuntimeException.class)
    public void testSetStatusInv() throws RuntimeException {
        // setStatus para INATIVO com estoque > 0
        p.setStatus(Produto.INATIVO);
    }

    @Test
    public void testSetStatusVal() throws Exception {
        p.setEstoque(0);
        p.setStatus(Produto.INATIVO);
    }

    // AlteraProduto
    @Test
    public void testAlteraProdutoVal() throws Exception {
        p.alteraProduto(11111, "altera a descricao", 2.50, 3.00, "mm", 50.0, Produto.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void testAlteraProdutoInv01() throws RuntimeException {
        // codigo invalido
        p.alteraProduto(-1, "altera a descricao", 2.50, 3.00, "mm", 50.0, Produto.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void testAlteraProdutoInv02() throws RuntimeException {
        // descricao invalida
        p.alteraProduto(11111, "altera a descricao $#%@", 2.50, 3.00, "mm", 50.0, Produto.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void testAlteraProdutoInv03() throws RuntimeException {
        // preco compra invalido
        p.alteraProduto(11111, "altera a descricao", -0.01, 3.00, "mm", 50.0, Produto.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void testAlteraProdutoInv04() throws RuntimeException {
        // preco venda invalido
        p.alteraProduto(11111, "altera a descricao", 2.50, 2.49, "mm", 50.0, Produto.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void testAlteraProdutoInv05() throws RuntimeException {
        // unidade invalida
        p.alteraProduto(11111, "altera a descricao", 2.50, 3.00, "mmm", 50.0, Produto.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void testAlteraProdutoInv06() throws RuntimeException {
        // estoque invalido
        p.alteraProduto(11111, "altera a descricao", 2.50, 3.00, "mm", -0.01, Produto.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void testAlteraProdutoInv07() throws RuntimeException {
        // status invalido
        p.alteraProduto(11111, "altera a descricao", 2.50, 3.00, "mm", 50.0, Produto.INATIVO);
    }
}