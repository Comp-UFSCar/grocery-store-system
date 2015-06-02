package projetomercado;

import java.util.ArrayList;

public class GerenciadorClientes {
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public static void insereCliente(String nome, String cpf, String endereco,
            String telefone, String email, int status){

		try{
			if (!validaCpfUnico(cpf)){
			throw new RuntimeException("CPF invalido: " + cpf + " já está registrado em outro cliente");
			}
			
			Cliente c = new Cliente(nome, cpf, endereco, telefone, email, status);
			clientes.add(c);
		} catch (RuntimeException re){
			//System.err.println(re);
			re.printStackTrace(System.out);
		}
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
	
}
