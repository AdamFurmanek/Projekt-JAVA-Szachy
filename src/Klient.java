import java.net.*;
import java.awt.EventQueue;
import java.io.*;
import java.util.Scanner;


public class Klient {
	public static void main(String[] args) throws IOException {
		Scanner scanner=new Scanner(System.in);
		//String ip = scanner.nextLine();
		//Socket s = new Socket(ip, 4999);
		System.out.println("klient");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Okno();
			}
		});
	}
}
