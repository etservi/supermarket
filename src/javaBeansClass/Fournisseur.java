package javaBeansClass;

public class Fournisseur {

	private String raisonSociale;
	private String sigle;
	private String telephone;
	private String adresse;
	private String email;

	public Fournisseur() {
		super();
	}

	public Fournisseur(String raisonSociale, String sigle, String telephone, String adresse, String email) {
		super();
		this.raisonSociale = raisonSociale;
		this.sigle = sigle;
		this.telephone = telephone;
		this.adresse = adresse;
		this.email = email;
	}
	
	// CONSTRUCTEUR COMBOXBOX AJOUT ARTICLE
	public Fournisseur(String raisonSociale) {
		super();
		this.raisonSociale = raisonSociale;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
