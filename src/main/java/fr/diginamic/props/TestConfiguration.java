package fr.diginamic.props;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class TestConfiguration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");

		Enumeration cles = monFichierConf.getKeys();
		while (cles.hasMoreElements()) {
		   String cle = (String)cles.nextElement();
		   String valeur = monFichierConf.getString(cle);
		   System.out.println("cle = " + cle +", " +   "valeur = " + valeur);
		}
		
		
	}

}
