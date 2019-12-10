package fr.diginamic.props;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Set;

public class TestConfiguration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");
		System.out.println(driverName);
		
		Enumeration cles = monFichierConf.getKeys();
		while (cles.hasMoreElements()) {
		   String cle = (String)cles.nextElement();
		   String valeur = monFichierConf.getString(cle);
		   System.out.println("cle = " + cle +", " +   "valeur = " + valeur);
		}
		
		Set<String> keys=monFichierConf.keySet();
		for(String key:keys)
		{
			driverName=monFichierConf.getString(key);
			System.out.println(driverName);
		}
		
	}

}
