package dao;
import viste.GestoreTemplate;

public class Config {
	
	//La classe CONFIG avr� solo propriet� static final e servir� per creare un imbuto e far si che
	//tutti gli oggetti come GestoreTemplate siano gestiti da qui.
	//NOTA BENE: CAMBIATE IL PERCORSO IN BASE AL VOSTRO PC
//	/Users/utente/Documents/2024/_dev_mac/corso/java/DynamicWebProject_DAOLibri_Mappe/DynamicWebProject_DAOLibri_Mappe/WebContent/viste
	public static final String PERCORSOCARTELLAVISTE = "/Users/utente/Documents/2024/_dev_mac/corso/java/DynamicWebProject_DAOLibri_Mappe/DynamicWebProject_DAOLibri_Mappe/WebContent/viste";
	//GestoreTemplate viene creato concretamente solo all'interno della classe CONFIG.
	//Quindi invece di creare ogni volta un nuovo oggetto GestoreTemplate, quando avr� bisogno dei
	//metodi al suo interno mi baster� chiamare Config.GT per ad esempio leggere un file.
	//Il percorso della cartella viste viene definito solo qui come propriet� static final e quindi
	//non devo riprenderlo ogni volta.
	public static final GestoreTemplate GT = new GestoreTemplate(PERCORSOCARTELLAVISTE);
}