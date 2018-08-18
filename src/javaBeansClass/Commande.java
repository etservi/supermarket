package javaBeansClass;

import java.sql.Date;

public class Commande {

	private String numeroCom;
	private String nomCom;
	private Date dateCom;

	public Commande() {
		super();
	}

	public String getNumeroCom() {
		return numeroCom;
	}

	public void setNumeroCom(String numeroCom) {
		this.numeroCom = numeroCom;
	}

	public String getNomCom() {
		return nomCom;
	}

	public void setNomCom(String nomCom) {
		this.nomCom = nomCom;
	}

	public Date getDateCom() {
		return dateCom;
	}

	public void setDateCom(Date dateCom) {
		this.dateCom = dateCom;
	}
}
