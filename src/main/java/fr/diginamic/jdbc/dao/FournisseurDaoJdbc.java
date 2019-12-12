package fr.diginamic.jdbc.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import fr.diginamic.jdbc.entites.Fournisseur;


public class FournisseurDaoJdbc implements FournisseurDao {
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("database");
	// On créé le singleton 
	private static FournisseurDaoJdbc instance = new FournisseurDaoJdbc();
	// La classe DbMgr n'a qu'un seul attribut d'instance: le pool de connexions.
	private ComboPooledDataSource cpds;
	/**
	 * Constructeur privé appelé une seule fois lors de la création
	 * du singleton et qui initialise le pool de connexions.
	 */
	public FournisseurDaoJdbc(){
		try {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass(bundle.getString("database.driver"));        
			cpds.setJdbcUrl(bundle.getString("database.url"));
			cpds.setUser(bundle.getString("database.user"));                                  
			cpds.setPassword(bundle.getString("database.password"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException("Impossible de se connecter à la base de données.");
		}           
	}
	/** Récupère une connexion
	 * @return Connection
	 */
	public static Connection getConnection(){
		try {
			return instance.cpds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de récupérer une nouvelle connexion sur la base de données.");
		}
	}
	
	
	

	@Override
	public List<Fournisseur> extraire() throws RuntimeException{
		ArrayList<Fournisseur> listFournisseur = new ArrayList<>();
		
		Connection connection=null;
		Statement monStatement=null;
		ResultSet curseur=null;
		
		try {
			connection = getConnection();
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

		String tempo="INSERT INTO FOURNISSEUR (ID,NOM) VALUES (?,?)";
		Connection connection=null;
		PreparedStatement monStatement=null;
		
		
		try {
			connection = getConnection();
			
			monStatement = connection.prepareStatement(tempo);
			monStatement.setInt(1,id);
			monStatement.setString(2, nom);
			monStatement.executeUpdate();
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
		int stn=0;

		Connection connection=null;
		PreparedStatement monStatement=null;
		String tempo="UPDATE FOURNISSEUR SET NOM=?  WHERE NOM=?";
		
		try {
			connection = getConnection();
			monStatement = connection.prepareStatement(tempo);
			monStatement.setString(1,nouveauNom);
			monStatement.setString(2, ancienNom);
			stn=monStatement.executeUpdate();
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

		Connection connection=null;
		PreparedStatement monStatement=null;
		String tempo="DELETE FROM FOURNISSEUR WHERE ID=?";
		
		try {
			connection = getConnection();
			System.out.println(connection);
			
			monStatement = connection.prepareStatement(tempo);
			monStatement.setInt(1,id);
			monStatement.executeUpdate();
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
