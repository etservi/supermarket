package javaBeansClass;

public class Mail {

	private String adress_envoi;
	private String adresse_reception;
	private String ip_server;
	private String password;
	private String message;

	public Mail(String adress_envoi, String adresse_reception, String password, String message) {
		super();
		this.adress_envoi = adress_envoi;
		this.adresse_reception = adresse_reception;
		this.password = password;
		this.message = message;
	}

	public String getAdress_envoi() {
		return adress_envoi;
	}

	public void setAdress_envoi(String adress_envoi) {
		this.adress_envoi = adress_envoi;
	}

	public String getAdresse_reception() {
		return adresse_reception;
	}

	public void setAdresse_reception(String adresse_reception) {
		this.adresse_reception = adresse_reception;
	}

	public String getIp_server() {
		return ip_server;
	}

	public void setIp_server(String ip_server) {
		this.ip_server = ip_server;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
