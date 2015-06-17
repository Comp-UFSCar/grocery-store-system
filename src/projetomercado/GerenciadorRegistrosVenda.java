package projetomercado;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	/*
        
            Essas funções abaixo não sao necessárias devido ao uso de uma interface no momento e porque elas nao estao na especificação.
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
        */
	public static ArrayList<RegistroVenda> getRegistros() {
		return registros;
	}
	
	public static ArrayList<RegistroVenda> getRegistroDoMes (int mes)  {
		if ((mes <= 0) || (mes > 12))
			throw new RuntimeException("Mes invalido");
		
		ArrayList<RegistroVenda> regs = new ArrayList<RegistroVenda>(); 

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
				
		Date inicio, fim;
		try {
			inicio = df.parse("01/" + mes + "/2015");
			fim = df.parse("01/" + (mes + 1) + "/2015");
			
			for (RegistroVenda r : registros){
				if (r.getData().after(inicio) && r.getData().before(fim) || r.getData().equals(inicio)) {
					regs.add(r);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (regs.isEmpty())
			return null;
		return regs;
	}
	
}
