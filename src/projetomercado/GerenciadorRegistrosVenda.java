package projetomercado;

import java.util.ArrayList;

public class GerenciadorRegistrosVenda {

	private static ArrayList<RegistroVenda> registros = new ArrayList<RegistroVenda>();
	
	public static void insereRegistroVenda (String str){
		try {
			RegistroVenda regVenda = new RegistroVenda(str);		
			registros.add(regVenda);
		}
		catch (RuntimeException re)
        {
    		//System.err.println(re);
            re.printStackTrace(System.out);
        }		
	}
	
	public static RegistroVenda buscaPorNumero(int numero){
		for (RegistroVenda r : registros){
			if (r.getNumero() == numero)
				return r;
		}
		throw new RuntimeException("Registro nao encontrado");
	}
	
	public static void printRegistro(int codigo){
		
		RegistroVenda r = buscaPorNumero(codigo);
				
        System.out.println(
        		"----------------------------------------\n" +
        		" Registro Venda\n" +
   				"----------------------------------------\n" +
    	        "Numero: " + r.getNumero() + "\n" +
    	        "Data: " + r.getData() + "\n" + 
    	        "Quantidade de itens: " + r.getQuantidadeItens() + "\n\n" +
    	        "Codigo \tDescricao \t\tQuantidade"
        );
        for (int i = 0; i < r.getQuantidadeItens() ; i++){
        	int cod = r.getProdutos().get(i).getCodigo();
        	String descri = r.getProdutos().get(i).getDescricao();
        	double qtd = r.getQuantidades().get(i);
        	String str = String.valueOf(cod) + "\t" + descri + "\t" + 
        			String.valueOf(qtd);
        	System.out.println(str);
        }
            
	}

	public static ArrayList<RegistroVenda> getRegistros() {
		return registros;
	}
	
}
