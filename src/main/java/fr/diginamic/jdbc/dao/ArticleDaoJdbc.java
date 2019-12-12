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

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class ArticleDaoJdbc implements ArticleDao{
	
		private Connection connection=null;
		private PreparedStatement monStatement=null;

		private static ResourceBundle bundle = ResourceBundle.getBundle("database");
		// On créé le singleton 
		private static ArticleDaoJdbc instance = new ArticleDaoJdbc();
		// La classe DbMgr n'a qu'un seul attribut d'instance: le pool de connexions.
		private ComboPooledDataSource cpds;
		/**
		 * Constructeur privé appelé une seule fois lors de la création
		 * du singleton et qui initialise le pool de connexions.
		 */
		public ArticleDaoJdbc(){
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
		
		/** 
		 * Lance la deconnection
		 */
		public void deconnectionT()
		{
			try {
				monStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	@Override
	public List<Article> extraire() throws RuntimeException{
		ArrayList<Article> listArticle = new ArrayList<>();
		
		ResultSet curseur=null;
		String tempo="SELECT ID, REF,DESIGNATION,PRIX,FOURNISSEUR_ID FROM ARTICLE";
		try {
			connection = getConnection();
			monStatement = connection.prepareStatement(tempo);
			curseur = monStatement.executeQuery();
			
			while (curseur.next()){
				Article art = new Article(curseur.getInt("ID"),curseur.getString("REF"),curseur.getString("DESIGNATION"),curseur.getInt("PRIX"),curseur.getInt("FOURNISSEUR_ID") );
				listArticle.add(art);
			}
			
		} catch (SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				curseur.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listArticle;
	}

	@Override
	public void insert(Article article) throws RuntimeException{

		String tempo="INSERT INTO ARTICLE (ID,REf,DESIGNATION,PRIX,FOURNISSEUR_ID) VALUES (?,?,?,?,?)";

		try {
			connection = getConnection();
			
			monStatement = connection.prepareStatement(tempo);
			monStatement.setInt(1,article.getId());
			monStatement.setString(2, article.getRef());
			monStatement.setString(3,article.getDesignation());
			monStatement.setDouble(4,article.getPrix());
			monStatement.setInt(5,article.getFournisseurId());
			monStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int update(String designation, double pourcentage) throws RuntimeException{

		int stn=0;

		String tempo="UPDATE ARTICLE SET PRIX= PRIX-(PRIX*(?/100))  WHERE DESIGNATION like ?";
		
		try {
			connection = getConnection();
			monStatement = connection.prepareStatement(tempo);
			monStatement.setDouble(1,pourcentage);
			monStatement.setString(2,designation+"%");
			
			stn=monStatement.executeUpdate();
			
			connection.commit();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stn;
	}

	@Override
	public boolean delete(Article article) throws RuntimeException {
		boolean test= false;

		String tempo="DELETE FROM ARTICLE WHERE ID=?";
		try {
			connection = getConnection();
			
			
			monStatement = connection.prepareStatement(tempo);
			monStatement.setInt(1,article.getId());
			monStatement.executeUpdate();
			test=true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;
	}
	
	@Override
	public boolean deleteDesignation(String designation) throws RuntimeException {
		boolean test= false;

		String tempo="DELETE FROM ARTICLE WHERE DESIGNATION like ?";
		try {
			connection = getConnection();
			
			monStatement = connection.prepareStatement(tempo);
			monStatement.setString(1,designation+"%");
			monStatement.executeUpdate();
			test=true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;
	}
	
	
	
	
	public double moyenne()
	{
		double moy=0;
		String tempo="SELECT AVG(PRIX) FROM ARTICLE";

		ResultSet curseur=null;
		try {
			connection = getConnection();
			monStatement = connection.prepareStatement(tempo);
			curseur = monStatement.executeQuery();
			
			while (curseur.next()){
				moy = curseur.getInt("AVG(PRIX)");
			}
		} catch (SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				curseur.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return moy;
	}

}
