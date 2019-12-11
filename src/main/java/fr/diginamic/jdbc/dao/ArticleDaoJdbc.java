package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class ArticleDaoJdbc implements ArticleDao{

	@Override
	public List<Article> extraire() throws RuntimeException{
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");
		String url=monFichierConf.getString("database.url");
		String user=monFichierConf.getString("database.user");
		String password="";
		ArrayList<Article> listArticle = new ArrayList<>();
		
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
			curseur = monStatement.executeQuery("SELECT ID, REF,DESIGNATION,PRIX,FOURNISSEUR_ID FROM ARTICLE");
			
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
		String tempo="INSERT INTO ARTICLE (ID,REf,DESIGNATION,PRIX,FOURNISSEUR_ID) VALUES ('"+id+"','"+ref+"','"+designation+"','"+prix+"','"+fournisseurId+"')";
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
	public int update(String designation, double pourcentage) throws RuntimeException{
		
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
			String temp="PRIX-(PRIX*("+pourcentage+"/100))";
			
			stn=monStatement.executeUpdate("UPDATE ARTICLE SET PRIX="+temp+"  WHERE DESIGNATION like'"+designation+"%'");
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
			monStatement.executeUpdate("DELETE FROM ARTICLE WHERE ID='"+id+"'");
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
			
			monStatement = connection.createStatement();
			
			monStatement.executeUpdate("DELETE FROM ARTICLE WHERE DESIGNATION like'"+designation+"%'");
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
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");
		String url=monFichierConf.getString("database.url");
		String user=monFichierConf.getString("database.user");
		String password="";
		double moy=0;
	
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
