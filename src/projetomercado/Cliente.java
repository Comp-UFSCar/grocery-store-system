package projetomercado;

public class Cliente {
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
	private String email;
	private int status;
	
	public static final int ATIVO = 1;
	public static final int INATIVO = 0;
	
	public Cliente(String nome, String cpf, String endereco, String telefone, 
			String email, int status){
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
		setTelefone(telefone);
		setEmail(email);
		setStatus(status);
	}
	
	//getters
	public String getNome(){
		return this.nome;
	}
	
	public String getCpf(){
		return this.cpf;
	}
	
	public String getEndereco(){
		return this.endereco;
	}
	
	public String getTelefone(){
		return this.telefone;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public int getStatus(){
		return this.status;
	}
	
	//setters
	public void setNome(String nome){
		/*
		if(!telefone.matches("[a-zA-Z\\-'\\s]+"))
			throw new RuntimeException("Nome invalido " + nome);
		*/
		this.nome = nome;
	}
	
	public void setCpf(String cpf){
		//CPF calculation
		if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
			cpf.equals("22222222222") || cpf.equals("33333333333") ||
			cpf.equals("44444444444") || cpf.equals("55555555555") ||
			cpf.equals("66666666666") || cpf.equals("77777777777") ||
			cpf.equals("88888888888") || cpf.equals("99999999999") ||
			(cpf.length() != 11))
			throw new RuntimeException("CPF invalido: " + cpf);

		int dig10, dig11, sum = 0;
		//10th digit verification
		for(int i = 0; i < 9; i++){
			sum += Character.getNumericValue( cpf.charAt(i) ) * (10-i);
		}
		dig10 = 11 - sum % 11;

		if (dig10 == 10 || dig10 == 11){
			dig10 = 0;
		}

		if( Character.getNumericValue( cpf.charAt(9) ) != dig10){
			throw new RuntimeException("CPF invalido: " + cpf);
		}

		//11th digit verification
		sum = 0;
		for(int i = 0; i < 10; i++){
			sum += Character.getNumericValue( cpf.charAt(i) ) * (11-i);
		}
		dig11 = 11 - sum % 11;

		if (dig11 == 10 || dig11 == 11){
			dig11 = 0;
		}
		if( Character.getNumericValue( cpf.charAt(10) ) != dig11){
			throw new RuntimeException("CPF invalido: " + cpf);
		}
		this.cpf = cpf;
	}
	
	public void setEndereco(String endereco){

		this.endereco = endereco;
	}
	
	public void setTelefone(String telefone){
		if(!telefone.matches("\\(\\d{2}\\)\\d{9}"))
			throw new RuntimeException("telefone invalido " + telefone);

		this.telefone = telefone;
	}
	
	public void setEmail(String email){
		if(!email.matches("([a-z])([a-z]|\\d)*@([a-z])([a-z]|\\d)*.com(.br){0,1}"))
			throw new RuntimeException("E-mail invalido " + email);

		this.email = email;
	}
	
	public void setStatus(int status){
		if(!(status == Cliente.ATIVO || status == Cliente.INATIVO))
			throw new RuntimeException("Status invalido " + status);
		this.status = status;
	}
	
	public void alteraCliente(String nome, String cpf, String endereco,
							  String telefone, String email, int status){
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
		setTelefone(telefone);
		setEmail(email);
		setStatus(status);
	}
	
	
	
}
