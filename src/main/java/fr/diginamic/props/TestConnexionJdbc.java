package fr.diginamic.props;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {

	public static void main(String[] args) {

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
			System.out.println("connection open");
			connection.close();
			System.out.println("connection close");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
}
