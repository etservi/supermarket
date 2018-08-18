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
//------------------------------------------
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
