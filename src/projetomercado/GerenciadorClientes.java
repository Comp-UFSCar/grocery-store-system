package projetomercado;

import java.util.ArrayList;

public class GerenciadorClientes {
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public static void insereCliente(String nome, String cpf, String endereco,
            String telefone, String email, int status){
		if (!validaCpfUnico(cpf))
			throw new RuntimeException("CPF invalido: " + cpf + " já está registrado em outro cliente");

		Cliente c = new Cliente(nome, cpf, endereco, telefone, email, status);
		clientes.add(c);
	}
	
    private static boolean validaCpfUnico(String cpf){
    	for (Cliente c : clientes){
    		if (cpf.equals(c.getCpf()))
    			return false;
    	}
    	return true;
    }
    
    public static Cliente consultaClientePorCpf(String cpf){
    	for (Cliente c : clientes){
    		if (cpf.equals(c.getCpf())){
    			return c;
    		}
    	}
    	return null;
    }

	public static ArrayList<Cliente> getClientes(){
		return clientes;
	}

	public static void listaClientes(){
		for(Cliente c : clientes)
			exibeCliente(c);
	}

	public static void exibeCliente(Cliente c){
		System.out.println(
				"----------------------------------------\n" +
						"Cliente\n" +
						"----------------------------------------\n" +
						"Nome: " + c.getNome() + "\n" +
						"CPF: " + c.getCpf() + "\n" +
						"Endereco: " + c.getEndereco() + "\n" +
						"Telefone: " + c.getTelefone() + "\n" +
						"E-mail: " + c.getEmail() + "\n" +
						"Status: " + c.getStatus()
		);
	}

	public static void exibeCliente(String cpf){
		Cliente c = consultaClientePorCpf(cpf);
		exibeCliente(c);
	}
	
}
