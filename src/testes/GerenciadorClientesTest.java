package testes;

import org.junit.Test;
import org.junit.BeforeClass;
import projetomercado.Cliente;
import projetomercado.GerenciadorClientes;
import static org.junit.Assert.*;

public class GerenciadorClientesTest {

    @BeforeClass
    static public void before() throws Exception {
        GerenciadorClientes.insereCliente("Fabiane Ferrari", "12647527504", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);

    }

    // Incorrect inputs
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectName() throws Exception {
        GerenciadorClientes.insereCliente("Lu B", "52361946440", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectCpf() throws Exception {
        GerenciadorClientes.insereCliente("Lu B", "5236194644", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectEndereco() throws Exception {
        GerenciadorClientes.insereCliente("Lu B", "52361946440", "--", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectTelefone() throws Exception {
        GerenciadorClientes.insereCliente("Lu B", "52361946440", "endereco", "14123456789", "a@a.com", Cliente.ATIVO);
    }
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectEmail() throws Exception {
        GerenciadorClientes.insereCliente("Lu B", "52361946440", "endereco", "(14)123456789", "a@.com", Cliente.ATIVO);
    }
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectStatus() throws Exception {
        GerenciadorClientes.insereCliente("Lu B", "52361946440", "endereco", "(14)123456789", "a@a.com", 2);
    }

    @Test
    public void insereClienteFullcorrectInput() throws Exception {
        GerenciadorClientes.insereCliente("Lu Bu", "52361946440", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void insereClienteFullsameCpf() throws Exception {
        GerenciadorClientes.insereCliente("Fabiane Ferrari", "12647527504", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }

    @Test
    public void testInsereClienteWithObjectcorrectInput() throws Exception {
        Cliente c = new Cliente("Fabiano Ferrari", "15988150241", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
        GerenciadorClientes.insereCliente(c);
    }

    @Test
    public void testConsultaClientePorCpf() throws Exception {
        //TODO: Test goes here...
        GerenciadorClientes.consultaClientePorCpf("12647527504");
    }
    @Test
    public void testcpfInserido() throws Exception {
        assertTrue(GerenciadorClientes.cpfInserido("12647527504"));
        assertFalse(GerenciadorClientes.cpfInserido("49762871650"));
    }

} 
