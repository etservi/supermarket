package javaBeansClass;

public class DomaineCategorie {

	private int idCategoriee;
	private String libCategorie;
	private int idRayon;

	// CONSTRUCTEUR COMBOXBOX
	public DomaineCategorie(int idCategoriee) {
		this.idCategoriee = idCategoriee;
	}

	// CONSTRUCTEUR CHEZ LE CAISSIER - VOIRE LISTE CATEGORIE RAYON
	public DomaineCategorie(String libCategorie, int idRayon) {
		super();
		this.libCategorie = libCategorie;
		this.idRayon = idRayon;
	}
//----------------------------------------------------------------
	public int getIdCategoriee() {
		return idCategoriee;
	}

	public void setIdCategoriee(int idCategoriee) {
		this.idCategoriee = idCategoriee;
	}

	public String getLibCategorie() {
		return libCategorie;
	}

	public void setLibCategorie(String libCategorie) {
		this.libCategorie = libCategorie;
	}

	public int getIdRayon() {
		return idRayon;
	}

	public void setIdRayon(int idRayon) {
		this.idRayon = idRayon;
	}

}
