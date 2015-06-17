package testes;

import org.junit.Test;
import org.junit.BeforeClass;
import projetomercado.*;

import static org.junit.Assert.*;
public class GerenciadorRegistrosVendaTest {
    public static String numero;
    public static String _cpf;
    public static String data;
    public static String produtosEQuantidades;
    @BeforeClass
    public void before() throws Exception {
        numero = "1";
        _cpf = "12647527504";
        data = "25/09/1993";
        produtosEQuantidades = "01; 1; 02; 2";
        // insereProduto(codigo, descricao, precoCompra, precoVenda, unidade, estoque, status)
        GerenciadorProdutos.insereProduto(11111, "este eh o produto 1", 1.00, 5.99, "cm", 10.5, Produto.ATIVO);
        GerenciadorProdutos.insereProduto(22222, "e este eh o produto 2", 2.00, 7.99, "kg", 10.5, 1);
        GerenciadorProdutos.insereProduto(33333, "por fim o produto 3", 3.00, 10.99, "mm", 10.5, 1);
        GerenciadorRegistrosVenda.insereRegistroVenda(numero + ";" + _cpf + ";" + data + ";" + produtosEQuantidades);

        // insereCliente(nome, cpf,String endereco, telefone, email, status)
        GerenciadorClientes.insereCliente("Jose Silva", "52882174160", "Rua Episcopal 111", "(16)988886666", "email@email.com", Cliente.ATIVO);
    }

    @Test
    public void testInsereRegistroVendaCorrectInput() throws Exception {
        produtosEQuantidades = "3; 1.5";
        GerenciadorRegistrosVenda.insereRegistroVenda("2" + ";" + _cpf + ";" + data + ";" + produtosEQuantidades);
    }

    @Test (expected = RuntimeException.class)
    public void testInsereRegistroVendaSameNumber() throws Exception {
        GerenciadorRegistrosVenda.insereRegistroVenda(numero + ";" + _cpf + ";" + data + ";" + produtosEQuantidades);
    }


} 
