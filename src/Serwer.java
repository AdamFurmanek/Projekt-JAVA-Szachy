import java.net.*;
import java.io.*;
import java.net.InetAddress;

import javax.swing.JOptionPane;

public class Serwer {
	public static Socket s1, s2;
	public static PrintWriter pr1, pr2;
	public static void main(String[] args) throws IOException {
		InetAddress ip= InetAddress.getLocalHost();
		int input = JOptionPane.showConfirmDialog(null,"IP serwera: "+ip+"\nNaciœnij ok aby uruchomiæ serwer.", "Serwer", JOptionPane.OK_CANCEL_OPTION);
		if(input!=0) {
			System.exit(0);
		}
		ServerSocket ss = new ServerSocket(4999);

		s1 = new Socket();
		s2 = new Socket();
		
			s1 = ss.accept();
			pr1 = new PrintWriter(s1.getOutputStream());
			pr1.print('c');
			pr1.flush();

			s2 = ss.accept();
			pr2 = new PrintWriter(s2.getOutputStream());
			pr2.print('b');
			pr2.flush();
		
		BufferedReader bf1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
		BufferedReader bf2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
		try {
		while(true) {
			pr1.println(bf2.readLine());
			pr1.flush();
			pr2.println(bf1.readLine());
			pr2.flush();
		}
		}
		catch(IOException e){
			pr1.println("x");
			pr1.flush();
			pr2.println("x");
			pr2.flush();
			System.exit(0);
		}

	}
}
