package javaBeansClass;

import java.sql.Date;

public class Utilisateur {

	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private String login;
	private String password;
	private String email;
	private byte[] image;
	private Date date;
	private String role;
	
	public Utilisateur() {
		
	}
// CONSTRUCTEUR TABLEAU - AJOUTER UTILISATEUR
	public Utilisateur(String prenom, String nom ) {
		this.prenom = prenom;
		this.nom = nom;
	}
	
	
// CONSTRUCTEUR VOIR LISTE UTILISATEUR - PARAMETRE 
	
	public Utilisateur(String nom, String prenom, String role, String login) {
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
		this.login = login;
	}
//-------------------------------------------------------------
	public Utilisateur(int id, String nom, String prenom, String adresse, String telephone, String login,
			String password, String email, byte[] image, Date date, String role) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.login = login;
		this.password = password;
		this.email = email;
		this.image = image;
		this.date = date;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
