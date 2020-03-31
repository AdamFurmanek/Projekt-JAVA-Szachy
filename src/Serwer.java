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
		
		PrintWriter pr1 = new PrintWriter(s1.getOutputStream());
		pr1.print('b');
		pr1.flush();
		
		Socket s2 = ss.accept();
		System.out.println("Polaczono z drugim klientem");
		
		PrintWriter pr2 = new PrintWriter(s2.getOutputStream());
		pr2.print('c');
		pr2.flush();
		
		InputStreamReader in1 = new InputStreamReader(s1.getInputStream());
		BufferedReader bf1 = new BufferedReader(in1);
		
		InputStreamReader in2 = new InputStreamReader(s2.getInputStream());
		BufferedReader bf2 = new BufferedReader(in2);
		
		while(true) {
			//System.out.println("no to czekam na pierwsze dostanie elo");
			
			pr2.println(bf1.readLine());
			pr2.flush();
			
			pr1.println(bf2.readLine());
			pr1.flush();
		}
		
	}
}
