package main;
import java.util.*;
import dao.DAOLibri;

public class Main_01
{
	public static void main(String[] args)
	{
		//TEST METODO READ DI DAOLIBRI
		System.out.println(DAOLibri.getInstance().leggiTutti().size());
		
		//TEST METODO READ DI DAOLIBRI
//		String ris = "Elenco libri\n";
//		for(Map<String, String> m : DAOLibri.getInstance().leggiTutti())
//			ris += m.toString() + "\n";
//		System.out.println(ris);
//		
//		//TEST METODO CREATE DI DAOLIBRI
//		Map<String, String> inserisci = new HashMap<String,String>();
//		inserisci.put("autore","Go Nagai");
//		inserisci.put("titolo","Devilman");
//		System.out.println("Inserimento: " + DAOLibri.getInstance().create(inserisci));
//		
//		//TEST METODO MODIFICA DI DAOLIBRI
//		Map<String, String> modifica = new HashMap<String,String>();
//		modifica.put("id","4");
//		modifica.put("autore","Go Nagai");
//		modifica.put("titolo","Il grande Mazinga");
//		System.out.println("Modifica: " + DAOLibri.getInstance().update(modifica));
//		
//		//TEST METODO ELIMINA DI DAOLIBRI
//		System.out.println("Elimina: " + DAOLibri.getInstance().delete(4));
	}
}