package fr.diginamic.props;

import java.util.ResourceBundle;

public class TestConfiguration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");
		
		
		
		
		
	}

}
