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
	public String nome(){
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
		this.nome = nome;
	}
	
	public void setCpf(String cpf){
		this.cpf = cpf;
	}
	
	public void setEndereco(String endereco){
		this.endereco = endereco;
	}
	
	public void setTelefone(String telefone){
		this.telefone = telefone;
	}
	
	public void setEmail(String email){
		//corrigir
		//matches letter + alphanumeric(s) + @ + letter + alphanumeric(s) + ".com" (validating only if the following characters are ".br")
		try{
			if(!email.matches("([a-z])([a-z]|\\d)*@([a-z])([a-z]|\\d)*(.com)(?=.br)"))
				throw new RuntimeException("E-mail invalido " + email);
			
			this.email = email;
		}
		catch (RuntimeException re){
			System.err.println(re);
		}
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	public void alteraCliente(String nome, String CPF, String endereco, String telefone, 
			String email, int status){
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
		setTelefone(telefone);
		setEmail(email);
		setStatus(status);
	}
	
	
	
}
