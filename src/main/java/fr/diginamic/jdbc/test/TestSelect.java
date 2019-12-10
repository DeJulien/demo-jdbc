package fr.diginamic.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");
		String url=monFichierConf.getString("database.url");
		String user=monFichierConf.getString("database.user");
		String password="";
		ArrayList<Fournisseur> listFournisseur = new ArrayList<>();
		
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println(connection);
			
			Statement monStatement = connection.createStatement();
			ResultSet curseur = monStatement.executeQuery("SELECT ID, NOM FROM FOURNISSEUR");
			
			while (curseur.next()){
				
			Integer id = curseur.getInt("ID");
			String nom = curseur.getString("NOM");
			Fournisseur fournisseur = new Fournisseur(id, nom);
			listFournisseur.add(fournisseur);
			
			}
			
			curseur.close();
			monStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i=0;i<listFournisseur.size();i++)
		{
			Fournisseur temp= listFournisseur.get(i);
			System.out.println(temp.getId()+" : "+temp.getNom());
		}

	}

}
