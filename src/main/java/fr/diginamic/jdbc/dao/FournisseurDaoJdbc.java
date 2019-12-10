package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;


public class FournisseurDaoJdbc implements FournisseurDao {
	

	@Override
	public List<Fournisseur> extraire() throws RuntimeException{
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
		Connection connection=null;
		Statement monStatement=null;
		ResultSet curseur=null;
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			monStatement = connection.createStatement();
			curseur = monStatement.executeQuery("SELECT ID, NOM FROM FOURNISSEUR");
			
			while (curseur.next()){
				
				Integer id = curseur.getInt("ID");
				String nom = curseur.getString("NOM");
				Fournisseur fournisseur = new Fournisseur(id, nom);
				listFournisseur.add(fournisseur);
			
			}
			
		} catch (SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				curseur.close();
				monStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return listFournisseur;
	}

	@Override
	public void insert(Fournisseur fournisseur) throws RuntimeException {

		int id=fournisseur.getId();
		String nom=fournisseur.getNom();
		
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
		String tempo="INSERT INTO FOURNISSEUR (ID,NOM) VALUES ('"+id+"','"+nom+"')";
		Connection connection=null;
		Statement monStatement=null;
		
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			
			monStatement = connection.createStatement();
			monStatement.executeUpdate(tempo);
			connection.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				monStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public int update(String ancienNom, String nouveauNom) throws RuntimeException {
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");
		String url=monFichierConf.getString("database.url");
		String user=monFichierConf.getString("database.user");
		String password="";
		int stn=0;
		
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection connection=null;
		Statement monStatement=null;
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			monStatement = connection.createStatement();
			
			
			stn=monStatement.executeUpdate("UPDATE FOURNISSEUR SET NOM='"+nouveauNom+"'  WHERE NOM='"+ancienNom+"'");
			connection.commit();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				monStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		return stn;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) throws RuntimeException{
		boolean test= false;
		int id=fournisseur.getId();
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
		
		Connection connection=null;
		Statement monStatement=null;
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println(connection);
			
			monStatement = connection.createStatement();
			monStatement.executeUpdate("DELETE FROM FOURNISSEUR WHERE ID='"+id+"'");
			test=true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				monStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return test;
	}

}
