package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public interface FournisseurDao 
{
	/**
	 * Affichage de la base de donnée fournisseur
	 * @return
	 */
	List<Fournisseur> extraire();
	
	/**
	 * Insertion d'un nouveau fournisseur dans la base de donnée
	 * @param fournisseur
	 */
	void insert(Fournisseur fournisseur);
	
	/**
	 * Mise a jour d'un nouveau nom pour le fournisseur
	 * @param ancienNom
	 * @param nouveauNom
	 * @return
	 */
	int update(String ancienNom, String nouveauNom);
	
	/**
	 * Suppression d'un fournisseur en fonction d'un fournisseur
	 * @param fournisseur
	 * @return
	 */
	boolean delete(Fournisseur fournisseur);
	
	
}
