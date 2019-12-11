package fr.diginamic.jdbc.entites;

public class Article {

	private int id;
	private String ref;
	private String designation;
	private double prix;
	private int fournisseurId;
	
	public Article(int id, String ref, String designation, double prix, int fournisseurId)
	{
		this.id= id;
		this.ref=ref;
		this.designation=designation;
		this.prix=prix;
		this.fournisseurId=fournisseurId;
	}

	/**Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**Getter
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**Setter
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**Getter
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**Setter
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**Getter
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**Setter
	 * @param prix the prix to set
	 */
	public void setPrix(int prix) {
		this.prix = prix;
	}

	/**Getter
	 * @return the fournisseurId
	 */
	public int getFournisseurId() {
		return fournisseurId;
	}

	/**Setter
	 * @param fournisseurId the fournisseurId to set
	 */
	public void setFournisseurId(int fournisseurId) {
		this.fournisseurId = fournisseurId;
	}
	
	
	
}
