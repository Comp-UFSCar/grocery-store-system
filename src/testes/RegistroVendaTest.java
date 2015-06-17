package testes;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import projetomercado.Cliente;
import projetomercado.Produto;
import projetomercado.GerenciadorClientes;
import projetomercado.GerenciadorProdutos;
import projetomercado.GerenciadorRegistrosVenda;
import projetomercado.RegistroVenda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RegistroVendaTest {

    public static String numero;
    public static String _cpf;
    public static String data;
    public static String produtosEQuantidades;
    public static RegistroVenda rv1, rv2;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        numero = "1";
        _cpf = "12647527504";
        data = "25/09/1993";
        produtosEQuantidades = "01; 1; 02; 2";

        GerenciadorProdutos.insereProduto
                (01, "produto um", 1.50f, 2.0f, "kg", 11.11f, Produto.ATIVO);
        GerenciadorProdutos.insereProduto
                (02, "produto dois", 1.50f, 2.0f, "kg", 22.22f, Produto.ATIVO);
        GerenciadorProdutos.insereProduto
                (03, "produto tres", 0.50f, 1.0f, "mm", 0f, Produto.INATIVO);
        GerenciadorClientes.insereCliente("Fabiane Ferrari", "12647527504", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
        GerenciadorRegistrosVenda.insereRegistroVenda(numero + ";" + _cpf + ";" + data + ";" + produtosEQuantidades);
        rv1 = new RegistroVenda();
        rv2 = new RegistroVenda("2;" + _cpf + ";" + data + ";" + produtosEQuantidades);
    }

    // get tests
    @Test
    public void testGetNumero() throws Exception {
        assertEquals(rv2.getNumero(), 2);
    }
    @Test
    public void testGetProdutos() throws Exception {
        assertNotNull(rv2.getProdutos());
    }
    @Test
    public void testGetQuantidades() throws Exception {
        assertNotNull(rv2.getQuantidades());
    }
    @Test
    public void testGetQuantidadeItens() throws Exception {
        assertEquals(rv2.getQuantidadeItens(), 2);
    }
    @Test
    public void testGetData() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        assertEquals(rv2.getData(), df.parse(data));
    }

    @Test
    public void testFaturamento() throws Exception {
        //System.out.println(rv2.faturamento());
        assertEquals(rv2.faturamento(), 1.5f, 0.0001f);
    }
    // setNumeroVenda tests
    // correct input
    @Test
    public void testSetNumeroVendaCorrectInput() throws Exception {
        rv1.setNumeroVenda("3");
    }
    // incorrect inputs
    @Test(expected = RuntimeException.class)
    public void testSetNumeroVendaNegative() throws Exception {
        rv1.setNumeroVenda("-1");
    }
    @Test(expected = RuntimeException.class)
    public void testSetNumeroVenda0() throws Exception {
        rv1.setNumeroVenda("0");
    }

    @Test(expected = RuntimeException.class)
    public void testSetNumeroRepeatedNumber() throws Exception {
        rv1.setNumeroVenda("1");
    }
    @Test(expected = RuntimeException.class)
    public void testSetNumeroNaN() throws Exception {
        rv1.setNumeroVenda("?");
    }

    //setData tests
    //correct input
    @Test
    public void testSetDataVendaCorrectInput() throws Exception {
        rv1.setDataVenda("25/09/2015");
    }
    //incorrect inputs
    @Test (expected = RuntimeException.class)
    public void testSetDataVendaInvalidDay0() throws Exception {
        rv1.setDataVenda("00/01/2015");
    }

    @Test (expected = RuntimeException.class)
    public void testSetDataVendaInvalidDay32() throws Exception {
        rv1.setDataVenda("32/01/2015");
    }

    @Test (expected = RuntimeException.class)
    public void testSetDataVendaInvalidDayFeb30th() throws Exception {
        rv1.setDataVenda("30/02/2015");
    }

    @Test (expected = RuntimeException.class)
    public void testSetDataVendaInvalidMonth0() throws Exception {
        rv1.setDataVenda("15/00/2015");
    }

    @Test (expected = RuntimeException.class)
    public void testSetDataVendaInvalidMonth13() throws Exception {
        rv1.setDataVenda("15/13/2015");
    }

    @Test (expected = RuntimeException.class)
    public void testSetDataVendaInvalidYear0() throws Exception {
        rv1.setDataVenda("15/1/0");
    }

    //SetQuantidadesEProdutos tests
    //correct input
    @Test
    public void testSetQuantidadesEProdutos() throws Exception {
        rv1.setQuantidadesEProdutos("1; 2; 2; 3");
    }

    //incorrect inputs
    @Test (expected = RuntimeException.class)
    public void testSetQuantidadesEProdutosProductNotFound() throws Exception {
        rv1.setQuantidadesEProdutos("4; 2");
    }
    @Test (expected = RuntimeException.class)
    public void testSetQuantidadesEProdutosNoQuantity() throws Exception {
        rv1.setQuantidadesEProdutos("1");
    }
    @Test (expected = RuntimeException.class)
    public void testSetQuantidadesEProdutosNoProducts() throws Exception {
        rv1.setQuantidadesEProdutos("");
    }
    @Test (expected = RuntimeException.class)
    public void testSetQuantidadesEProdutosProductNotEnoughQuantity() throws Exception {
        rv1.setQuantidadesEProdutos("2; 25.2f");
    }

    //setCliente Tests
    // correct input
    @Test
    public void testSetClienteCorrectInput() throws Exception {
        rv1.setCliente("12647527504");
    }

    // incorrect inputs
    @Test (expected = RuntimeException.class)
    public void testSetClienteCpfNotFound() throws Exception {
        rv1.setCliente("52361946440");
    }

    @Test (expected = RuntimeException.class)
    public void testSetClienteCpfIncorrectCpf() throws Exception {
        rv1.setCliente("523619464");
    }
} 
