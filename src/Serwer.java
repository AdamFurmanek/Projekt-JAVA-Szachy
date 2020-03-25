import java.net.*;
import java.io.*;
import java.net.InetAddress;

public class Serwer {
	public static void main(String[] args) throws IOException {
		InetAddress ip= InetAddress.getLocalHost();
		System.out.println(ip);
		ServerSocket ss = new ServerSocket(4999);
		Socket s1 = ss.accept();
		System.out.println("Polaczono z pierwszym klientem");
		Socket s2 = ss.accept();
		System.out.println("Polaczono z drugim klientem");
	}
}
