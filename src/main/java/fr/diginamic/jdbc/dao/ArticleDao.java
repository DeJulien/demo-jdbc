package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.util.List;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public interface ArticleDao {
	
	/**
	 * Affichage de la base de donnée article
	 * @return
	 */
	List<Article> extraire();
	
	/**
	 * Insertion d'un nouvelle article dans la base de donnée
	 * @param article
	 */
	void insert(Article article);
	
	/**
	 * Mise a jour d'un prix d'un article avec un pourcentage
	 * @param designation
	 * @param pourcentage
	 * @return un int de nombre de ligne modifier
	 */
	int update(String designation, double pourcentage);
	
	/**
	 * Suppression d'un article en fonction d'un article
	 * @param article
	 * @return un boolean qui confirme la suppression
	 */
	boolean delete(Article article);
	
	/**
	 * Moyenne total des article
	 * @return double 
	 */
	double moyenne();
	
	/**
	 * suppression d'un article en fonction d'une designation
	 * @param designation
	 * @return
	 */
	boolean deleteDesignation(String designation);
	

}
