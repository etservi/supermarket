package javaBeansClass;

import java.sql.Date;

public class Article {

	private int idArticle;
	private int idRayon;
	private int idCategoriee;
	private String raisonSociale;
	private int id;
	private String nomArticleNom;
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
	private double prixAvendre;

	public Article() {
		super();
	}

	public Article(int idArticle, String nomArticleNom, int qteStock, double prixUnitaire) {
		this.idArticle = idArticle;
		this.nomArticleNom = nomArticleNom;
		this.qteStock = qteStock;
		this.prixUnitaire = prixUnitaire;

	}
	
	//-------- Article Details Constructor
//	nomArticleNom`, `idRayon`, `idCategoriee`, `raisonSociale`, `codeBarre`, qteStock, prixUnitaire,prixTotal
	
	public Article(String nomArticleNom, int idRayon, int idCategoriee, String raisonSociale, String codeBarre,  int qteStock,
			double prixUnitaire, double prixAvendre) {
		super();
		this.nomArticleNom = nomArticleNom;
		this.idRayon = idRayon;
		this.idCategoriee = idCategoriee;
		this.raisonSociale = raisonSociale;
		this.codeBarre = codeBarre;
		this.qteStock = qteStock;
		this.prixUnitaire = prixUnitaire;
		this.prixAvendre = prixAvendre;
		
	}
	
	//-------------------------------------------

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

	public int getIdCategoriee() {
		return idCategoriee;
	}

	public void setIdCategoriee(int idCategoriee) {
		this.idCategoriee = idCategoriee;
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

	public String getNomArticleNom() {
		return nomArticleNom;
	}

	public void setNomArticleNom(String nomArticleNom) {
		this.nomArticleNom = nomArticleNom;
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

	public double getPrixAvendre() {
		return prixAvendre;
	}

	public void setPrixAvendre(double prixAvendre) {
		this.prixAvendre = prixAvendre;
	}

	public void sortie() {
		qteStock--;
	}

}
