package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public interface ArticleDao {
	
	List<Article> extraire();
	void insert(Article article);
	int update(String designation, double newPrix);
	boolean delete(Article article);
	double moyenne();
	boolean deleteDesignation(String designation);
}
