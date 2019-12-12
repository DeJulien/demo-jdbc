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
	

	@Override
	public List<Article> extraire() throws RuntimeException{
		ArrayList<Article> listArticle = new ArrayList<>();
		Connection connection=null;
		PreparedStatement monStatement=null;
		ResultSet curseur=null;
		String tempo="SELECT ID, REF,DESIGNATION,PRIX,FOURNISSEUR_ID FROM ARTICLE";
		try {
			connection = getConnection();
			monStatement = connection.prepareStatement(tempo);
			curseur = monStatement.executeQuery();
			
			while (curseur.next()){
				
				Integer id = curseur.getInt("ID");
				String ref = curseur.getString("REF");
				String designation = curseur.getString("DESIGNATION");
				Integer prix = curseur.getInt("PRIX");
				Integer fournisseurId = curseur.getInt("FOURNISSEUR_ID");
				Article art = new Article(id,ref,designation,prix,fournisseurId );
				listArticle.add(art);
			
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
		
		
		return listArticle;
	}

	@Override
	public void insert(Article article) throws RuntimeException{

		int id=article.getId();
		String ref=article.getRef();
		String designation=article.getDesignation();
		double prix=article.getPrix();
		int fournisseurId=article.getFournisseurId();

		String tempo="INSERT INTO ARTICLE (ID,REf,DESIGNATION,PRIX,FOURNISSEUR_ID) VALUES (?,?,?,?,?)";
		Connection connection=null;
		PreparedStatement monStatement=null;
		
		
		try {
			connection = getConnection();
			
			monStatement = connection.prepareStatement(tempo);
			monStatement.setInt(1,id);
			monStatement.setString(2, ref);
			monStatement.setString(3,designation);
			monStatement.setDouble(4, prix);
			monStatement.setInt(5, fournisseurId);
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
	public int update(String designation, double pourcentage) throws RuntimeException{

		int stn=0;

		Connection connection=null;
		PreparedStatement monStatement=null;
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
	public boolean delete(Article article) throws RuntimeException {
		boolean test= false;
		int id=article.getId();

		Connection connection=null;
		PreparedStatement monStatement=null;
		String tempo="DELETE FROM ARTICLE WHERE ID=?";
		try {
			connection = getConnection();
			
			
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
	
	@Override
	public boolean deleteDesignation(String designation) throws RuntimeException {
		boolean test= false;
	
		Connection connection=null;
		PreparedStatement monStatement=null;
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
	
	
	
	
	public double moyenne()
	{
		double moy=0;

		Connection connection=null;
		Statement monStatement=null;
		ResultSet curseur=null;
		try {
			connection = getConnection();
			monStatement = connection.createStatement();
			curseur = monStatement.executeQuery("SELECT AVG(PRIX) FROM ARTICLE");
			
			while (curseur.next()){
				moy = curseur.getInt("AVG(PRIX)");
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

		return moy;
	}

}
