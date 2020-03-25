import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Panel extends JPanel {

	private Pole[][] szachownica = new Pole[8][8];
	private ImageIcon pole_szachownicy;
	public Panel() {
		
	}

	public void paint(Graphics g)
	{
		inicjuj();
		int i,j;

		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				
				if((i+j)%2==0) {
					pole_szachownicy = new ImageIcon("src/pole-p.png");
					pole_szachownicy.paintIcon(this, g, i*100, j*100);
				}
				
				if(szachownica[i][j].getKolor()=='b') {
					if(szachownica[i][j].getPionek()=='k')
						pole_szachownicy = new ImageIcon("src/k-b.png");
					if(szachownica[i][j].getPionek()=='h')
						pole_szachownicy = new ImageIcon("src/h-b.png");
					if(szachownica[i][j].getPionek()=='w')
						pole_szachownicy = new ImageIcon("src/w-b.png");
					if(szachownica[i][j].getPionek()=='g')
						pole_szachownicy = new ImageIcon("src/g-b.png");
					if(szachownica[i][j].getPionek()=='s')
						pole_szachownicy = new ImageIcon("src/s-b.png");
					if(szachownica[i][j].getPionek()=='p')
						pole_szachownicy = new ImageIcon("src/p-b.png");
					
					pole_szachownicy.paintIcon(this, g, i*100, j*100);
				}
				else if(szachownica[i][j].getKolor()=='c') {
					if(szachownica[i][j].getPionek()=='k')
						pole_szachownicy = new ImageIcon("src/k-c.png");
					if(szachownica[i][j].getPionek()=='h')
						pole_szachownicy = new ImageIcon("src/h-c.png");
					if(szachownica[i][j].getPionek()=='w')
						pole_szachownicy = new ImageIcon("src/w-c.png");
					if(szachownica[i][j].getPionek()=='g')
						pole_szachownicy = new ImageIcon("src/g-c.png");
					if(szachownica[i][j].getPionek()=='s')
						pole_szachownicy = new ImageIcon("src/s-c.png");
					if(szachownica[i][j].getPionek()=='p')
						pole_szachownicy = new ImageIcon("src/p-c.png");
					
					pole_szachownicy.paintIcon(this, g, i*100, j*100);
				}
			}
		}
	}
	
	public void inicjuj(){
		//zmienna pionek:
		//k - król, h - hetman, w - wie¿a, g - gonie, s - skoczek, p - pion, n - nic
		//zmienna kolor:
		char[][] rozstawienie = 
			{{'w','s','g','h','k','g','s','w'},
			{'p','p','p','p','p','p','p','p'},
			{'n','n','n','n','n','n','n','n'},
			{'n','n','n','n','n','n','n','n'},
			{'n','n','n','n','n','n','n','n'},
			{'n','n','n','n','n','n','n','n'},
			{'p','p','p','p','p','p','p','p'},
			{'w','s','g','h','k','g','s','w'}};
		
		int i,j;
		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				szachownica[i][j] = new Pole();
				szachownica[i][j].setPionek(rozstawienie[j][i]);
				szachownica[i][j].setKolor('n');
			}
			szachownica[i][0].setKolor('b');
			szachownica[i][1].setKolor('b');
			szachownica[i][6].setKolor('c');
			szachownica[i][7].setKolor('c');
		}
	}
}