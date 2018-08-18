package serveurMultiThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

import javaBeansClass.Fournisseur;

public class Scockett2 {

	public static void main(String... args) throws IOException {
		ServerListener server = new ServerListener();
		server.start();

		@SuppressWarnings("resource")
		Socket socketToServer = new Socket("localhost", 15000);
		ObjectOutputStream outStream = new ObjectOutputStream(socketToServer.getOutputStream());

		for (int i = 1; i < 10; i++) {
			try {
				Thread.sleep((long) (Math.random() * 3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Objet envoyé au serveur ...");
			outStream.writeObject("teste message #" + i);
		}
		System.exit(0);
		System.out.println("Server ShutDown");
	}

	static class ServerListener extends Thread {

		private ServerSocket serverSocket;

		ServerListener() throws IOException {
			serverSocket = ServerSocketFactory.getDefault().createServerSocket(15000);
		}

		@Override
		public void run() {
			while (true) {
				try {
					final Socket socketToClient = serverSocket.accept();
					ClientHandler clientHandler = new ClientHandler(socketToClient);
					clientHandler.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class ClientHandler extends Thread {
		private Socket socket;

		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

		ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			try {
				Fournisseur user = (Fournisseur) inputStream.readObject();
				System.out.println(user.getRaisonSociale());
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (true) {
				try {
					Object o = inputStream.readObject();
					System.out.println("Objet lu: " + o);
				} catch (IOException e) {
					e.printStackTrace();

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
}