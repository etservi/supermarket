package javaBeansClass;

import java.sql.Date;

public class Article {

	private int idArticle;
	private int idRayon;
	private int idCategorie;
	private String raisonSociale;
	private int id;
	private String nomArticle;
	private int qteStock;
	private int nombreArticle;
	private double prixUnitaire;
	private double prixVente;
	private double montantVerse;
	private double montantRendu;
	private String codeBarre;
	private Date dateAjoutee;
	private Date dateVendu;
	private int limteAlert;
	private double prixTotal;

	public Article() {
		super();
	}

	public Article(int idArticle, String nomArticle, int qteStock, double prixUnitaire) {
		this.idArticle = idArticle;
		this.prixUnitaire = prixUnitaire;
		this.qteStock = qteStock;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public int getIdRayon() {
		return idRayon;
	}

	public void setIdRayon(int idRayon) {
		this.idRayon = idRayon;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public int getQteStock() {
		return qteStock;
	}

	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}

	public int getNombreArticle() {
		return nombreArticle;
	}

	public void setNombreArticle(int nombreArticle) {
		this.nombreArticle = nombreArticle;
	}

	public double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}

	public double getMontantVerse() {
		return montantVerse;
	}

	public void setMontantVerse(double montantVerse) {
		this.montantVerse = montantVerse;
	}

	public double getMontantRendu() {
		return montantRendu;
	}

	public void setMontantRendu(double montantRendu) {
		this.montantRendu = montantRendu;
	}

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	public Date getDateAjoutee() {
		return dateAjoutee;
	}

	public void setDateAjoutee(Date dateAjoutee) {
		this.dateAjoutee = dateAjoutee;
	}

	public Date getDateVendu() {
		return dateVendu;
	}

	public void setDateVendu(Date dateVendu) {
		this.dateVendu = dateVendu;
	}

	public int getLimteAlert() {
		return limteAlert;
	}

	public void setLimteAlert(int limteAlert) {
		this.limteAlert = limteAlert;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public void sortie() {
		qteStock--;
	}

}
