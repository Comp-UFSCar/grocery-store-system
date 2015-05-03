package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import projetomercado.Cliente;

public class TesteCliente {
	//Note: cpf generated from http://www.geradorcpf.com/
	static Cliente c = new Cliente("Lu Bu", "15988150241", "endereco", "(14)123456789", "a@a.com", 0);
	//To be done correctly
	
	//nome tests
	
	@Test
	public void nomeIsEmpty(){
		c.setNome("");
	}
	@Test
	public void nomeIsTooShort(){
		c.setNome("Monty Python");
	}

	/*
	//cpf tests
	@Test
	public void cpfIsTooShort(){
		c.setCpf("1111111111");
	}
	@Test
	public void cpfIsTooLong(){
		c.setCpf("11111111111");
	}
	@Test
	public void cpfHasNonDigits(){
		c.setCpf("1111111111a");
	}
	@Test
	public void cpfIsAll0s(){
		c.setCpf("00000000000");
	}
	@Test
	public void cpfIsAll1s(){
		c.setCpf("11111111111");
	}
	@Test
	public void cpfIsAll2s(){
		c.setCpf("22222222222");
	}
	@Test
	public void cpfIsAll3s(){
		c.setCpf("33333333333");
	}
	@Test
	public void cpfIsAll4s(){
		c.setCpf("44444444444");
	}
	@Test
	public void cpfIsAll5s(){
		c.setCpf("55555555555");
	}
	@Test
	public void cpfIsAll6s(){
		c.setCpf("66666666666");
	}
	@Test
	public void cpfIsAll7s(){
		c.setCpf("77777777777");
	}
	@Test
	public void cpfIsAll8s(){
		c.setCpf("88888888888");
	}
	@Test
	public void cpfIsAll9s(){
		c.setCpf("99999999999");
	}
	@Test
	public void cpfHas10thDigitWrong(){
		c.setCpf("15988150231");
	}
	@Test
	public void cpfHas11thDigitWrong(){
		c.setCpf("15988150242");
	}
	@Test
	public void cpfValid(){
		c.setCpf("15988150241");
	}
	
	//telefone tests
	@Test
	public void telHasMoreThan10digits(){
		c.setTelefone("(14)12345678910");
	}
	
	@Test
	public void telHasLessThan9digits(){
		c.setTelefone("(14)12345678");
	}
	
	@Test
	public void telHasNoParenthesis(){
		c.setTelefone("14123456789");;
	}
	
	@Test
	public void telHasDash(){
		c.setTelefone("(14)12345-6789");;
	}
	
	@Test
	public void correctInput(){
		c.setTelefone("(11)123456789");;
	}
	
	//e-mail tests
	
	@Test
	public void emailHasNoUser() {
		c.setEmail("@a.com");
	}
	
	@Test
	public void emailsHasNoDomain() {
		c.setEmail("a@.com");
	}
	@Test
	public void emailHasNoAcceptableComplement1() {
		c.setEmail("a@a.com.uk");
	}
	@Test
	public void emailHasNoAcceptableComplement2() {
		c.setEmail("a@a.co");
	}
	@Test
	public void emailsUserStartsWithNumber() {
		c.setEmail("1a@a.com");
	}
	@Test
	public void emailsDomainStartsWithNumber() {
		c.setEmail("a@1a.com");
	}
	@Test
	public void emailAccepted1() {
		c.setEmail("a@a.com");
	}
	@Test
	public void emailAccepted2() {
		c.setEmail("a@a.com.br");
	}
	*/
}
