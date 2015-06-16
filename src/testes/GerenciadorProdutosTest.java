package testes;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;
import projetomercado.GerenciadorProdutos;
import projetomercado.Produto;

import static org.junit.Assert.*;

public class GerenciadorProdutosTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // Insere produtos validos no ArrayList produtos
        GerenciadorProdutos.insereProduto
                (01, "produto um", 1.50f, 2.0f, "kg", 11.11f, Produto.ATIVO);
        GerenciadorProdutos.insereProduto
                (02, "produto dois", 1.50f, 2.0f, "kg", 22.22f, Produto.ATIVO);
        GerenciadorProdutos.insereProduto
                (03, "produto tres", 0.50f, 1.0f, "mm", 0f, Produto.INATIVO);
    }

    // Insere Produto
    @Test
    public void testInsereProdutoVal() throws Exception {
        GerenciadorProdutos.insereProduto
                (04, "produto quatro", 1.50f, 4.0f, "kg", 44.44f, Produto.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void testInsereProdutoInv() throws RuntimeException {
        // Produto com codigo repetido
        GerenciadorProdutos.insereProduto
                (01, "produto um diferente", 1.50f, 4.0f, "kg", 44.44f, Produto.ATIVO);
    }

    // Altera Estoque
    @Test
    public void testAlteraEstoqueVal() throws Exception {
        GerenciadorProdutos.alteraEstoque(01, 1);
    }

    @Test (expected = RuntimeException.class)
    public void testAlteraEstoqueInv01() throws RuntimeException {
        // codigo nao existente
        GerenciadorProdutos.alteraEstoque(10, 1);
    }

    @Test (expected = RuntimeException.class)
    public void testAlteraEstoqueInv02() throws RuntimeException {
        // estoque invalido
        GerenciadorProdutos.alteraEstoque(1, -0.01);
    }

    // Consulta Produto por Codigo
    @Test
    public void testConsultaProdutoPorCodigoVal() throws Exception {
        assertNotNull(GerenciadorProdutos.consultaProdutoPorCodigo(01));
    }

    @Test
    public void testConsultaProdutoPorCodigoInv01() throws Exception {
        // codigo inexistente
        assertNull(GerenciadorProdutos.consultaProdutoPorCodigo(10));
    }

    @Test (expected = RuntimeException.class)
    public void testConsultaProdutoPorCodigoInv02() throws RuntimeException {
        // codigo invalido
        GerenciadorProdutos.consultaProdutoPorCodigo(0);
    }

    // Consulta por Descricao
    @Test
    public void testConsultaPorDescricaoVal() throws Exception {
        assertNotNull(GerenciadorProdutos.consultaPorDescricao("produto "));
    }

    @Test
    public void testConsultaPorDescricaoInv01() throws Exception {
        // nenhum produto possui essa descricao
        assertEquals(-1 , GerenciadorProdutos.consultaPorDescricao("aaa").get(0), 0);
    }

    @Test (expected = RuntimeException.class)
    public void testConsultaPorDescricaoInv02() throws RuntimeException {
        // descricao invalida
        assertNull(GerenciadorProdutos.consultaPorDescricao("descricao $$$"));
    }

}