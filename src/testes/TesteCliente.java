package testes;

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

	//cpf tests
	@Test (expected = RuntimeException.class)
	public void cpfIsTooShort(){
		c.setCpf("1111111111");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsTooLong(){
		c.setCpf("11111111111");
	}
	@Test (expected = RuntimeException.class)
	public void cpfHasNonDigits(){
		c.setCpf("1111111111a");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsAll0s(){
		c.setCpf("00000000000");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsAll1s(){
		c.setCpf("11111111111");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsAll2s(){
		c.setCpf("22222222222");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsAll3s(){
		c.setCpf("33333333333");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsAll4s(){
		c.setCpf("44444444444");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsAll5s(){
		c.setCpf("55555555555");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsAll6s(){
		c.setCpf("66666666666");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsAll7s(){
		c.setCpf("77777777777");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsAll8s(){
		c.setCpf("88888888888");
	}
	@Test (expected = RuntimeException.class)
	public void cpfIsAll9s(){
		c.setCpf("99999999999");
	}
	@Test (expected = RuntimeException.class)
	public void cpfHas10thDigitWrong(){
		c.setCpf("15988150231");
	}
	@Test (expected = RuntimeException.class)
	public void cpfHas11thDigitWrong(){
		c.setCpf("15988150242");
	}
	@Test 
	public void cpfValid(){
		c.setCpf("15988150241");
	}
	
	//telefone tests
	@Test (expected = RuntimeException.class)
	public void telHasMoreThan10digits(){
		c.setTelefone("(14)12345678910");
	}
	
	@Test (expected = RuntimeException.class)
	public void telHasLessThan9digits(){
		c.setTelefone("(14)12345678");
	}
	
	@Test (expected = RuntimeException.class)
	public void telHasNoParenthesis(){
		c.setTelefone("14123456789");;
	}
	
	@Test (expected = RuntimeException.class)
	public void telHasDash(){
		c.setTelefone("(14)12345-6789");;
	}
	
	@Test
	public void correctInput(){
		c.setTelefone("(11)123456789");;
	}
	
	//e-mail tests
	
	@Test (expected = RuntimeException.class)
	public void emailHasNoUser() {
		c.setEmail("@a.com");
	}
	
	@Test (expected = RuntimeException.class)
	public void emailsHasNoDomain() {
		c.setEmail("a@.com");
	}
	@Test (expected = RuntimeException.class)
	public void emailHasNoAcceptableComplement1() {
		c.setEmail("a@a.com.uk");
	}
	@Test (expected = RuntimeException.class)
	public void emailHasNoAcceptableComplement2() {
		c.setEmail("a@a.co");
	}
	@Test (expected = RuntimeException.class)
	public void emailsUserStartsWithNumber() {
		c.setEmail("1a@a.com");
	}
	@Test (expected = RuntimeException.class)
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
}
