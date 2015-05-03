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
        status = Produto.ativo;

        p = new Produto(codigo, descricao, precoCompra, precoVenda, unidade, estoque, status);
    }

    @Test
    public void testGetCodigo() throws Exception {
        assertEquals(codigo, p.getCodigo(), 0);
    }

    @Test (expected = RuntimeException.class)
    public void testSetCodigo() throws RuntimeException{
        p.setCodigo(0);
    }

    @Test
    public void testGetDescricao() throws Exception {
        assertEquals(descricao, p.getDescricao());
    }

    @Test (expected = RuntimeException.class)
    public void testSetDescricao() throws RuntimeException {
        p.setDescricao("ab");
    }

    @Test
    public void testGetPrecoCompra() throws Exception {
        assertEquals(precoCompra, p.getPrecoCompra(), 0);
    }

    @Test (expected = RuntimeException.class)
    public void testSetPrecoCompra() throws Exception {
        p.setPrecoCompra(-0.01);
    }

    @Test
    public void testGetPrecoVenda() throws Exception {
        assertEquals(precoVenda, p.getPrecoVenda(), 0);
    }

    @Test (expected = RuntimeException.class)
    public void testSetPrecoVenda() throws Exception {
        p.setPrecoVenda(precoCompra - 0.01);
    }

    @Test
    public void testGetUnidade() throws Exception {
        assertEquals(unidade, p.getUnidade());
    }

    @Test (expected = RuntimeException.class)
    public void testSetUnidade() throws Exception {
        p.setUnidade("a");
    }

    @Test
    public void testGetEstoque() throws Exception {
        assertEquals(estoque, p.getEstoque(), 0);
    }

    @Test (expected = RuntimeException.class)
    public void testSetEstoque() throws Exception {
        p.setEstoque(-0.1);
    }

    @Test
    public void testGetStatus() throws Exception {
        assertEquals(status, p.getStatus());
    }

    @Test (expected = RuntimeException.class)
    public void testSetStatus() throws Exception {
        p.setStatus(Produto.inativo);
    }

    @Test
    public void testAlteraProduto() throws Exception {
        p.alteraProduto(11111, "altera a descricao", 2.50, 3.00, "mm", 50.0, 1);
    }
}