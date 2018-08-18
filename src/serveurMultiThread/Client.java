package serveurMultiThread;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		try {
			Socket s = new Socket("localhost", 1234);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();

			Scanner scanner = new Scanner(System.in);
			System.out.println("Donner un nombre :");
			int nb = scanner.nextInt();

			os.write(nb);
			int reponse = is.read();
			System.out.println("resultat = " + reponse);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
