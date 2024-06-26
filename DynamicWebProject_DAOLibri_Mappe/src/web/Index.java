package web;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.Config;
import dao.DAOLibri;

//questa struttura con lo switch per la servlet è definito FRONT CONTROLLER (soluzione generica a un proglema ricorrente)


@WebServlet("/Index")
public class Index extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		//all'interno dell'url tramite il parametro request qua nella firma del metodo, cerchiamo il parametro comando
		String comando = request.getParameter("comando");
		
		if(comando == null)
			comando = "home";
		
		String ris = "";
		
		switch(comando.toLowerCase())
		{
			case "home"	:
				//Se andiamo al case "home" vogliamo che il programma legga il file html e lo restituisca.
				//Per far questo chiamiamo in causa un oggetto di tipo GestoreTemplate dalla classe Config.
				//Al GestoreTemplate chiediamo di graficare il file "home.html" all'interno della cartella viste.
				ris = Config.GT.leggiHTML("home.html");
				break;
			case "elencolibri"	:
				//Ora che abbiamo i metodi per graficare le mappe provenienti dal db le richiediamo come prima
				//tramite leggiTutti() e le passiamo come parametro al metodo graficaLibri() del GestoreTemplate.
				//ris diventa quindi il file elencoLibri.html con i segnaposto aggiornati.
				//Il contenuto saranno i libri graficati singolarmente.
				ris = Config.GT.graficaLibri(DAOLibri.getInstance().leggiTutti());
				break;
			case "formnuovolibro"	:
				//Questo case serve SOLO a mandare l'utente alla form per l'inserimento di un nuovo libro
				ris = Config.GT.leggiHTML("formnuovolibro.html");
				break;
			case "nuovolibro"	:
				//A questo case si arriva dopo aver cliccato il pulsante SALVA in formnuovolibro.html
				//La form manda alla Index un URL simile a questo:
				//http://localhost:8080/MappeSqlDynamic/Index?comando=nuovolibro&titolo=Mirrorshades&autore=Sterling
				//Il valore di comando � nuovolibro e quindi arriviamo a questo CASE.
				//Ogni input ha ora associato un valore che � quello inserito dall'utente nella form.
				//Recuperiamo i valori dalla REQUEST
				String titoloForm = request.getParameter("titolo");
				String autoreForm = request.getParameter("autore");
				//Con i valori recuperati creiamo una mappa
				Map<String,String> nuovo = new HashMap<String,String>();
				nuovo.put("titolo", titoloForm);
				nuovo.put("autore", autoreForm);
				//Ora inviamo la mappa al metodo create di DaoLibri per inserire il nuovo record sul DB.
				if(DAOLibri.getInstance().create(nuovo) == true) {
					System.out.println("Inserimento riuscito");
					
				}else {
					
					System.out.println("Errore nell'inserimento del libro.");
				}
				ris = Config.GT.leggiHTML("home.html");
				break;
			case "eliminalibro"	:
				int idLibroElimina = Integer.parseInt(request.getParameter("id"));
				if(DAOLibri.getInstance().delete(idLibroElimina))
					System.out.println("Eliminazione avvenuta con successo");
				else
					System.out.println("Errore nell'eliminazione del record");
				ris = Config.GT.leggiHTML("home.html");
				break;
			case "formmodificalibro" :
				//Recuperiamo dalla request il valore di id e cerchiamo nel db il record con quell'id.
				int idLibroModifica = Integer.parseInt(request.getParameter("id"));
				//Richiamo il metodo cercaPerId per ottenere dal db la mappa corrispondente al libro cercato
				Map<String, String> mappaModifica = DAOLibri.getInstance().cercaPerId(idLibroModifica);
				//Leggo tutto il file della form per la modifica del libro e lo trasformo in STRING.
				ris = Config.GT.leggiHTML("formmodificalibro.html");
				//A questo punto sostituisco i segnaposto [] nel file con i valori della mappa cercata
				ris	=	ris.replace("[id]", mappaModifica.get("id"))
						.replace("[titolo]", mappaModifica.get("titolo"))
						.replace("[autore]", mappaModifica.get("autore"));
				//Alla fine ritorniamo ris ovvero la pagina della formmodificalibro.html con i valori del db invece
				//dei segnaposto. Quando l'utente cliccher� su AGGIORNA verr� rimandato al case della index
				//che si occuper� EFFETTIVAMENTE DELL'AGGIORNAMENTO. Questo case si occupa SOLO di graficare
				//la pagina web per la modifica dei dati del libro lato utente.
				break;
			case "modificalibro"	:
				//http://localhost:8080/MappeSqlDynamic/Index?comando=modificalibro&id=7&titolo=Sandman+collection&autore=Gaiman&genere=Graphic+novel&prezzo=56&qta=3
				//Come per nuovolibro recuperiamo i valori dalla request e li assegno alla mappa che passer�
				//come parametro al metodo update. DEVO RICORDARMI DI PASSARE ANCHE L'ID.
				Map<String,String> modificaLibro = new HashMap<String,String>();
				modificaLibro.put("titolo", request.getParameter("titolo"));
				modificaLibro.put("autore", request.getParameter("autore"));
				modificaLibro.put("id", request.getParameter("id"));
				//Ora inviamo la mappa al metodo update di DaoLibri per modificare il record sul DB.
				if(DAOLibri.getInstance().update(modificaLibro) == true)
					System.out.println("Modifica riuscita");
				else
					System.out.println("Errore nella modifica del libro.");
				ris = Config.GT.leggiHTML("home.html");
				break;
			default	:
				ris = Config.GT.leggiHTML("filenotfound.html");
				break;
		}
		//	System.out.println(ris);
		response.getWriter().append(ris);
	}//Fine di doGet()
	
	
	//metodo per gestire le richieste POST in modo che non siano visibili nell'url ecc
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	
}

