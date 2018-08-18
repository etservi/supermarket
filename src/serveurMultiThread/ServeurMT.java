package serveurMultiThread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMT extends Thread {

	int nbClients; // PAR DEFAUT IL EST EGALE A ZERO

	@Override
	public void run() {

		try {
			ServerSocket ss = new ServerSocket(234);
			while (true) {
				Socket s = ss.accept();
				++nbClients;
				new Conversation(s, nbClients).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------------
	public class Conversation extends Thread {
		private Socket socket;
		private int numeroClient;

		public Conversation(Socket socket, int numeroClient) {
			super();
			this.socket = socket;
			this.numeroClient = numeroClient;
		}

		@Override
		public void run() {
			try {
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);

				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os, true);

				String Ip = socket.getRemoteSocketAddress().toString();

				System.out.println("Connexion du client Numéro" + numeroClient + "IP " + Ip);
				System.out.println("Bienvenue vous etes le client " + numeroClient);

				while (true) {
					String requette = br.readLine();
					System.out.println(Ip + " a envoyé " + requette);
					if (requette != null) {
						String reponse = "Size = " + requette.length();
						pw.println(reponse);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ---------------------------------------------
	public static void main(String[] args) {
		new ServeurMT().start();
	}
}
