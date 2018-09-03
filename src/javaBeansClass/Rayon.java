package javaBeansClass;

public class Rayon {

	private int idRayon;
	private String domaine;

	public Rayon() {
		super();
	}

	// COCNSTRUCTEUR COMBOBOX // DANS ARTICLE
	public Rayon(int idRayon) {
		this.idRayon = idRayon;
	}
//----------------------- 	CONSTRUCTEUR CHEZ CAISSIER VOIR LISTE RAYON & CATEGORIE
	
	public Rayon(int idRayon, String domaine) {
		super();
		this.idRayon = idRayon;
		this.domaine = domaine;
	}
//====================================
	
	public int getIdRayon() {
		return idRayon;
	}

	

	public void setIdRayon(int idRayon) {
		this.idRayon = idRayon;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
}
