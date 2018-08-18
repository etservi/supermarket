package javaBeansClass;

import java.sql.Date;

public class Caissier extends Utilisateur {

	// CONSTRUCTEUR PRENOM ET ID DE L'UTILISATEUR EST APPELE
//	public Caissier(String prenom, int idCaissier) {
//		super(idCaissier);
//	}

	// CONSTRUCTEUR UTILISATEUR EST APPLELE
	public Caissier(int idCaissier, String nom, String prenom, String adresse, String telephone, String login, String password,
			String email, byte[] image, Date date) {

		super(idCaissier, nom, prenom, adresse, telephone, login, password, email, image, date);
	}
}
