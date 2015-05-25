package projetomercado;

import java.util.ArrayList;

public class GerenciamentoClientes {
    private ArrayList<Cliente> clientes = new ArrayList<Produto>();
    public void insereCliente(String nome, String cpf, String endereco,
                              String telefone, String email, int status)
    {
        Cliente c = new Cliente(nome, cpf, endereco, telefone, email, status);

        if(buscaCliente(cpf) != null)
        {
            this.clientes.add(c);
        }
        throw new RuntimeException("Cliente ja existe no sistema: " + c.getNome());
    }

    private Cliente buscaCliente(String cpf)
    {
        for(Cliente c : this.clientes){
            if(c.getCpf() == cpf)
                return c;
        }
        return null;
    }
    public ArrayList <Cliente> getClientes(){
        return this.clientes;
    }
}
