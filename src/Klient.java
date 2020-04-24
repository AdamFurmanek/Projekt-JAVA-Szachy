import java.net.*;
import java.awt.EventQueue;
import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Klient {
	
	public static char tura='b';
	public static char gracz;
	public static int wysylka = 0;
	public static int myszX1, myszY1, myszX2, myszY2;
	public static Socket s;
	public static BufferedReader bf;
	
	public static void main(String[] args) throws IOException {

		String ip = (String)JOptionPane.showInputDialog(null,"Podaj IP serwera", "IP serwera", JOptionPane.INFORMATION_MESSAGE, null, null, "localhost");
		if(ip==null)
			System.exit(0);
		
		try {
		s = new Socket(ip, 4999);
		bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
		gracz=(char)bf.read();
		}
		catch(IOException e){
			JOptionPane.showConfirmDialog(null,"Nie mo¿na po³¹czyæ z serwerem o podanym IP.", "Brak po³¹czenia", JOptionPane.DEFAULT_OPTION);
			System.exit(0);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Okno();
			}
		});
		PrintWriter pr = new PrintWriter(s.getOutputStream());
		
		String wspolrzedne;
		
		while(true) {
			if(tura==gracz) {
				System.out.print("");
			}
			else if(tura!=gracz&&wysylka==0) {
				
				wspolrzedne=bf.readLine();
				
				if(wspolrzedne.charAt(0)=='x') {
					JOptionPane.showConfirmDialog(null,"Przeciwnik siê roz³¹czy³.", "Koniec gry", JOptionPane.DEFAULT_OPTION);
					System.exit(0);
				}
				
				myszX1=((int)wspolrzedne.charAt(0))-48;
				myszY1=((int)wspolrzedne.charAt(1))-48;
				myszX2=((int)wspolrzedne.charAt(2))-48;
				myszY2=((int)wspolrzedne.charAt(3))-48;
				
				int i, j;
				for(i=0,j=7;i<8;i++,j--) {
					if (myszY1==i) {
						myszY1=j;
						break;
					}
				}
				for(i=0,j=7;i<8;i++,j--) {
					if(myszY2==i) {
						myszY2=j;
						break;
					}
				} //odwrocenie planszy
				
				Okno.panel.zmiana();
				Okno.panel.repaint();
				
				if(Klient.tura=='b')
					Klient.tura='c';
				else if(Klient.tura=='c')
					Klient.tura='b';	
				
			}
			else if(tura!=gracz&&wysylka==1) {
				pr.println(myszX1+""+myszY1+""+myszX2+""+myszY2);
				pr.flush();
				wysylka=0;
			}
		}
	}
}
