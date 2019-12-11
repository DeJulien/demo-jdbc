package fr.diginamic.jdbc.test;

import java.util.List;

import fr.diginamic.jdbc.dao.ArticleDaoJdbc;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoArticle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Article> listArticle;
		ArticleDaoJdbc tempsArticle = new ArticleDaoJdbc();
		
		List<Fournisseur> listFournisseur;
		FournisseurDaoJdbc tempsFourni = new FournisseurDaoJdbc();
		Fournisseur fourni= new Fournisseur(4,"La Maison des Peinture");
		
		listArticle=tempsArticle .extraire();
		for (int i=0;i<listArticle.size();i++)
		{
			Article temp= listArticle.get(i);
			System.out.println(temp.getId()+" / "+temp.getRef()+" / "+temp.getDesignation()+" / "+temp.getPrix()+" / "+temp.getFournisseurId());
		}
		
		//moyenne
		double moyenne=tempsArticle .moyenne();
		System.out.println(moyenne);

		
		//ajout des articles
		
		/*
		tempsFourni.insert(fourni);
		
		Article a1= new Article(11,"G01","Peinture blanche 1L",12.5,4);
		Article a2= new Article(12,"G02","Peinture rouge mate 1L",15.5 ,4);
		Article a3= new Article(13,"G03","Peinture noire laquée 1L",17.8 ,4);
		Article a4 = new Article(14,"G04","Peinture bleue mate 1L ",15.5 ,4);
		
		tempsArticle .insert(a1);
		tempsArticle .insert(a2);
		tempsArticle .insert(a3);
		tempsArticle .insert(a4);
		listArticle=tempsArticle.extraire();
		
		for (int i=0;i<listArticle.size();i++)
		{
			Article temp= listArticle.get(i);
			System.out.println(temp.getId()+" / "+temp.getRef()+" / "+temp.getDesignation()+" / "+temp.getPrix()+" / "+temp.getFournisseurId());
		}
		*/
		
		//modification du prix
		
		/*tempsArticle.update("Peinture", 25);
		listArticle=tempsArticle.extraire();
		
		for (int i=0;i<listArticle.size();i++)
		{
			Article temp= listArticle.get(i);
			System.out.println(temp.getId()+" / "+temp.getRef()+" / "+temp.getDesignation()+" / "+temp.getPrix()+" / "+temp.getFournisseurId());
		}*/
		
		//suppression
		/*tempsArticle .deleteDesignation("Peinture");
		listArticle=tempsArticle .extraire();
		
		
		
		tempsFourni.delete(fourni);
		
		for (int i=0;i<listArticle.size();i++)
		{
			Article temp= listArticle.get(i);
			System.out.println(temp.getId()+" / "+temp.getRef()+" / "+temp.getDesignation()+" / "+temp.getPrix()+" / "+temp.getFournisseurId());
		}*/
		
	}

}
