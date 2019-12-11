package fr.diginamic.jdbc.test;

import java.util.List;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoFournisseur {

	public static void main(String[] args) {
		
		List<Fournisseur> listFournisseur;
		FournisseurDaoJdbc temps = new FournisseurDaoJdbc();
		listFournisseur=temps.extraire();
		for (int i=0;i<listFournisseur.size();i++)
		{
			Fournisseur temp= listFournisseur.get(i);
			System.out.println(temp.getId()+" : "+temp.getNom());
		}
		//Insert
		/*Fournisseur test= new Fournisseur(4,"La Maison de la Peinture");
		temps.insert(test);
		
		listFournisseur=temps.extraire();*/
		
		
		//Update
		/*temps.update("La Maison de la Peinture", "La Maison des Peintures");
		listFournisseur=temps.extraire();*/
		
		//delete
		/*Fournisseur test2= new Fournisseur(4,"La Maison des Peinture");
		temps.delete(test2);
		listFournisseur=temps.extraire();*/
	}

}
