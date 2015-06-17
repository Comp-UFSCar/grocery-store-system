package testes;

import org.junit.Test;
import org.junit.BeforeClass;
import projetomercado.Cliente;
import projetomercado.GerenciadorClientes;
import static org.junit.Assert.*;

public class GerenciadorClientesTest {

    public static Cliente c;
    @BeforeClass
    static public void before() throws Exception {
        GerenciadorClientes.insereCliente("Fabiane Ferrari", "12647527504", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
        c = new Cliente("Fabiane Ferrari", "12647527504", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }

    // Incorrect inputs
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectName() throws Exception {
        GerenciadorClientes.insereCliente("Lu B", "52361946440", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectCpf() throws Exception {
        GerenciadorClientes.insereCliente("Lu Bu", "5236194644", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectEndereco() throws Exception {
        GerenciadorClientes.insereCliente("Lu Bu", "52361946440", "Rua Santos Dumont - 10", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectTelefone() throws Exception {
        GerenciadorClientes.insereCliente("Lu Bu", "52361946440", "endereco", "14123456789", "a@a.com", Cliente.ATIVO);
    }
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectEmail() throws Exception {
        GerenciadorClientes.insereCliente("Lu Bu", "52361946440", "endereco", "(14)123456789", "a@.com", Cliente.ATIVO);
    }
    @Test (expected = RuntimeException.class)
    public void insereClienteFullIncorrectStatus() throws Exception {
        GerenciadorClientes.insereCliente("Lu Bu", "52361946440", "endereco", "(14)123456789", "a@a.com", 2);
    }

    @Test
    public void insereClienteFullcorrectInput() throws Exception {
        GerenciadorClientes.insereCliente("Lu Bu", "52361946440", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void insereClientesameCpfInput() throws Exception {
        GerenciadorClientes.insereCliente("Fabiane Ferrari", "12647527504", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }

    @Test (expected = RuntimeException.class)
    public void insereClienteFullsameCpf() throws Exception {
        GerenciadorClientes.insereCliente("Fabiane Ferrari", "12647527504", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
    }

    @Test
    public void testConsultaClientePorCpf() throws Exception {
        assertNotNull(GerenciadorClientes.consultaClientePorCpf("12647527504"));
        assertNull(GerenciadorClientes.consultaClientePorCpf("52361946440"));
        assertNull(GerenciadorClientes.consultaClientePorCpf("1264752750"));
    }

    @Test
    public void testGetClientes(){
        assertNotNull(GerenciadorClientes.getClientes());
    }

} 
