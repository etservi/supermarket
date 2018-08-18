package javaBeansClass;

import java.sql.Date;

public class Admin extends Utilisateur {

	protected String val;

	// CONSTRUCTEUR PRENOM ET ID DE L'UTILISATEUR EST APPELE
	public Admin(String prenom, int idAdmin) {
		super(idAdmin, prenom);
	}

	// CONSTRUCTEUR UTILISATEUR EST APPLELE
	public Admin(int idAdmin, String nom, String prenom, String adresse, String telephone, String login, String password,
			String email, byte[] image, Date date) {

		super(idAdmin, nom, prenom, adresse, telephone, login, password, email, image, date);
	}
	
	// CONSTRUCTEUR PRENOM ET ID DE L'UTILISATEUR EST APPELE
		public Admin(String prenom, int id, String val) {
			super(id, prenom);
			this.val = val;
		}
}
