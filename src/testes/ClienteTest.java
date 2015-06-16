package testes;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import projetomercado.Cliente;

public class ClienteTest {
	//Note: cpf generated from http://www.geradorcpf.com/
	//To be done correctly
	private String nome;
	private String cpf;
	private String endereco;
	private String tel;
	private String email;
	private int status;
	private Cliente c;

	@Before
	public void criaObjetoValido(){
		nome = "Fabiano Ferrari";
		cpf = "15988150241";
		endereco = "endereco";
		tel = "(14)123456789";
		email = "a@a.com";
		status = Cliente.ATIVO;
		c = new Cliente(nome, cpf, endereco, tel, email, status);
	}
	//nome tests

	//incorrect inputs
	@Test (expected = RuntimeException.class)
	public void nomeIsEmpty(){
		c.setNome("");
	}

	@Test (expected = RuntimeException.class)
	public void nameHasOnlyOneName(){
		c.setNome("LuBu");
	}

	@Test (expected = RuntimeException.class)
	public void nameHasOnlyOneCharacter(){
		c.setNome("L Bu");
	}

	@Test (expected = RuntimeException.class)
	public void lastNameHasOnlyOneCharacter(){
		c.setNome("Lu B");
	}

	@Test (expected = RuntimeException.class)
	public void nameHasNumbers(){
		c.setNome("Lu B1");
	}

	@Test (expected = RuntimeException.class)
	public void nameHasSpecialCharacter1(){
		c.setNome("Lu B$");
	}

	@Test (expected = RuntimeException.class)
	public void nameHasSpecialCharacter2(){
		c.setNome("Lu B@");
	}

	@Test (expected = RuntimeException.class)
	public void nameIsTooLong(){
		c.setNome("otorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista abcaoriqpdosta");
	}

	//correct inputs
	@Test
	public void nomeCorrectInputMinimum(){
		c.setNome("Lu Bu");
	}
	@Test
	public void nomeCorrectInputMaximum(){
		c.setNome("otorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista abcaoriqpdos");
	}

	//teste de getNome
	@Test
	public void testGetNome(){
		assertEquals(c.getNome(), nome);
		assertNotEquals(c.getNome(), "Outro nome");
	}

	//cpf tests
	//incorrect inputs
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

	//correct input
	@Test 
	public void cpfValid(){
		c.setCpf("15988150241");
	}

	//teste de getCpf
	@Test
	public void testGetCpf(){
		assertEquals(c.getCpf(), cpf);
		assertNotEquals(c.getCpf(), "52361946440");
	}

	//endereco tests
	//incorrect inputs
	@Test (expected = RuntimeException.class)
	public void enderecoIsEmpty(){
		c.setEndereco("");
	}
	@Test (expected = RuntimeException.class)
	public void enderecoHasSpecialCharacter(){
		c.setEndereco("?");
	}
	@Test (expected = RuntimeException.class)
	public void enderecoCorrectTooLong(){
		c.setEndereco("otorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista abcaoriqpdostotorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista abcaoriqpdosta");
	}

	//correct inputs
	@Test
	public void enderecoCorrectInputMinimumLimit(){
		c.setEndereco("a");
	}
	@Test
	public void enderecoCorrectInputWithNumber(){
		c.setEndereco("1  1");
	}
	@Test
	public void enderecoCorrectInputMaximumLimit(){
		c.setEndereco("otorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista abcaoriqpdostotorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista otorrinolaringologista abcaoriqpdost");
	}

	//teste de getEndereco
	@Test
	public void testGetEndereco(){
		assertEquals(c.getEndereco(), endereco);
		assertNotEquals(c.getEndereco(), "novo endereco");
	}

	//telefone tests
	//incorrect inputs
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
	public void telHasLetters(){
		c.setTelefone("(14)bls-6789");;
	}
	@Test (expected = RuntimeException.class)
	public void telHasDash(){
		c.setTelefone("(14)12345-6789");;
	}

	//correct input
	@Test
	public void telAccepted(){
		c.setTelefone("(11)123456789");;
	}

	//teste de getTelefone
	@Test
	public void testGetTelefone(){
		assertEquals(c.getTelefone(), tel);
		assertNotEquals(c.getTelefone(), "(98)76543210");
	}

	//e-mail tests
	//incorrect inputs
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

	//correct inputs
	@Test
	public void emailAccepted1() {
		c.setEmail("a@a.com");
	}
	@Test
	public void emailAccepted2() {
		c.setEmail("a@a.com.br");
	}

	//teste de getEmail
	@Test
	public void testGetEmail(){
		assertEquals(c.getEmail(), email);
		assertNotEquals(c.getEmail(), "b@b.com");
	}

	//status tests
	//incorrect inputs
	@Test (expected = RuntimeException.class)
	public void statusIsGreaterThanOne() {
		c.setStatus(2);
	}
	@Test (expected = RuntimeException.class)
	public void statusIsLessThanZero() {
		c.setStatus(-1);
	}

	//correct inputs
	@Test
	public void correctInputStatus(){
		c.setStatus(0);
		c.setStatus(1);
		c.setStatus(Cliente.ATIVO);
		c.setStatus(Cliente.INATIVO);
	}

	//teste de getStatus
	@Test
	public void testGetStatus() {
		assertEquals(c.getStatus(), status);
		assertNotEquals(c.getStatus(), Cliente.INATIVO);
	}

	//teste de Construtor
	//incorrect input
	@Test (expected = RuntimeException.class)
	public void incorrectNameConstrutor(){
		Cliente c2 = new Cliente("LuBu", "52361946440", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
	}
	@Test (expected = RuntimeException.class)
	public void incorrectCpfConstrutor(){
		Cliente c2 = new Cliente("Lu Bu", "5236194644", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
	}
	@Test (expected = RuntimeException.class)
	public void incorrectEnderecoConstrutor(){
		Cliente c2 = new Cliente("Lu Bu", "52361946440", "??", "(14)123456789", "a@a.com", Cliente.ATIVO);
	}
	@Test (expected = RuntimeException.class)
	public void incorrectTelefoneConstrutor(){
		Cliente c2 = new Cliente("Lu Bu", "52361946440", "endereco", "(14123456789", "a@a.com", Cliente.ATIVO);
	}
	@Test (expected = RuntimeException.class)
	public void incorrectEmailConstrutor(){
		Cliente c2 = new Cliente("Lu Bu", "52361946440", "endereco", "(14)123456789", "a@.com", Cliente.ATIVO);
	}
	@Test (expected = RuntimeException.class)
	public void incorrectStatusConstrutor(){
		Cliente c2 = new Cliente("Lu Bu", "52361946440", "endereco", "(14)123456789", "a@a.com", 2);
	}
	//correct input
	@Test
	public void correctConstrutor(){
		Cliente c2 = new Cliente("Lu Bu", "52361946440", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
	}

	//teste de alteraCliente
	//incorrect input
	@Test (expected = RuntimeException.class)
	public void alteraClienteIncorrectName(){
		c.alteraCliente("LuBu", "52361946440", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
	}
	@Test (expected = RuntimeException.class)
	public void alteraClienteIncorrectCpf(){
		c.alteraCliente("Lu Bu", "5236194644", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
	}
	@Test (expected = RuntimeException.class)
	public void alteraClienteIncorrectEndereco(){
		c.alteraCliente("Lu Bu", "52361946440", "??", "(14)123456789", "a@a.com", Cliente.ATIVO);
	}
	@Test (expected = RuntimeException.class)
	public void alteraClienteIncorrectTelefone(){
		c.alteraCliente("Lu Bu", "52361946440", "endereco", "(14123456789", "a@a.com", Cliente.ATIVO);
	}
	@Test (expected = RuntimeException.class)
	public void alteraClienteIncorrectEmail(){
		c.alteraCliente("Lu Bu", "52361946440", "endereco", "(14)123456789", "a@.com", Cliente.ATIVO);
	}
	@Test (expected = RuntimeException.class)
	public void alteraClienteIncorrectStatus(){
		c.alteraCliente("Lu Bu", "52361946440", "endereco", "(14)123456789", "a@a.com", 2);
	}
	//correct input
	@Test
	public void alteraClienteCorrectInput(){
		c.alteraCliente("Lu Bu", "52361946440", "endereco", "(14)123456789", "a@a.com", Cliente.ATIVO);
	}
}
