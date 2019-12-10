package fr.diginamic.props;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class TestConnexionJdbc {

	public static void main(String[] args) {
		
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");
		String url=monFichierConf.getString("database.url");
		String user=monFichierConf.getString("database.user");
		String password="";
		
		
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println(connection);
			

			
			
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
}
