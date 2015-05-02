package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import projetomercado.Cliente;

public class TesteCliente {
	
	//To be done correctly
	@Test
	public void emailHasNoUser() {
		Cliente c = new Cliente("nome", "cpf", "endereco", "(14)1234-5678", "@a.com", 0);
	}
	@Test
	public void emailsHasNoDomain() {
		Cliente c = new Cliente("nome", "cpf", "endereco", "(14)1234-5678", "a@.com", 0);
	}
	@Test
	public void emailHasNotAcceptableComplement() {
		Cliente c = new Cliente("nome", "cpf", "endereco", "(14)1234-5678", "a@a.com.uk", 0);
	}
	@Test
	public void userStartsWithNumber() {
		Cliente c = new Cliente("nome", "cpf", "endereco", "(14)1234-5678", "1ab@a.com", 0);
	}
	@Test
	public void domainStartsWithNumber() {
		Cliente c = new Cliente("nome", "cpf", "endereco", "(14)1234-5678", "bla@1do.com", 0);
	}
	@Test
	public void acceptableEmail1() {
		Cliente c = new Cliente("nome", "cpf", "endereco", "(14)1234-5678", "a234@a.com.br", 0);
	}
	@Test
	public void acceptableEmail2() {
		Cliente c = new Cliente("nome", "cpf", "endereco", "(14)1234-5678", "a@a.com", 0);
	}
	
}
