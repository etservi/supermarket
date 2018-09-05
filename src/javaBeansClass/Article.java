package javaBeansClass;

import java.sql.Date;
import java.sql.Timestamp;

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
	private double prixAvendre;
	private double montantVerse;
	private double montantRendu;
	private String codeBarre;
	private Date dateAjoutee;
	private Timestamp dateVendu;
	private int Livrer0nonLivrer1;
	private byte[] facture;

	public Article(String nomArticleNom, String codeBarre) {
		super();
		this.nomArticleNom = nomArticleNom;
		this.codeBarre = codeBarre;
	}

	public Article(String nomArticleNom, String codeBarre, int qteStock, double prixUnitaire) {

		this.nomArticleNom = nomArticleNom;
		this.codeBarre = codeBarre;
		this.qteStock = qteStock;
		this.prixUnitaire = prixUnitaire;

	}
//======================================================================================================================
	// -------- Article Details Constructor
//	nomArticleNom`, `idRayon`, `idCategoriee`, `raisonSociale`, `codeBarre`, qteStock, prixUnitaire,prixTotal

	public Article(String nomArticleNom, int idRayon, int idCategoriee, String raisonSociale, String codeBarre, int qteStock, double prixUnitaire, double prixAvendre) {
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
//======================================================================================================================
	// ----------------------- CONSTRUCTION ARTICLE VENDU CAISSIER - nomArticleNom,
	// codeBarre, dateVendu, qteStock, prixAvendre, montantVerse, montantRendu

	public Article(String nomArticleNom, String codeBarre, Timestamp dateVendu, int qteStock, double prixAvendre, double montantVerse, double montantRendu) {
		super();
		this.nomArticleNom = nomArticleNom;
		this.codeBarre = codeBarre;
		this.dateVendu = dateVendu;
		this.qteStock = qteStock;
		this.prixAvendre = prixAvendre;
		this.montantVerse = montantVerse;
		this.montantRendu = montantRendu;

	}
//======================================================================================================================
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

	public Timestamp getDateVendu() {
		return dateVendu;
	}

	public void setDateVendu(Timestamp dateVendu) {
		this.dateVendu = dateVendu;
	}

	public double getPrixAvendre() {
		return prixAvendre;
	}

	public void setPrixAvendre(double prixAvendre) {
		this.prixAvendre = prixAvendre;
	}

	public int getLivrer0nonLivrer1() {
		return Livrer0nonLivrer1;
	}

	public void setLivrer0nonLivrer1(int livrer0nonLivrer1) {
		Livrer0nonLivrer1 = livrer0nonLivrer1;
	}

	public byte[] getFacture() {
		return facture;
	}

	public void setFacture(byte[] facture) {
		this.facture = facture;
	}

}
