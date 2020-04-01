import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Panel extends JPanel implements MouseListener{

	private int myszX1, myszY1, myszX2, myszY2;
	private Pole[][] szachownica = new Pole[8][8];
	private ImageIcon pole_szachownicy;
	
	public Panel() {
		addMouseListener(this);
		inicjuj();
	}

	public void paint(Graphics g)
	{
		int i,j;

		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				
				if((i+j)%2==0) {
					pole_szachownicy = new ImageIcon("src/pole-p.png");
					pole_szachownicy.paintIcon(this, g, i*100, j*100);
				}
				else {
					pole_szachownicy = new ImageIcon("src/pole-z.png");
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
		//b - bialy, c - czarny, n - nic
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
			if(Klient.gracz=='c') {
				szachownica[i][0].setKolor('b');
				szachownica[i][1].setKolor('b');
				szachownica[i][6].setKolor('c');
				szachownica[i][7].setKolor('c');
			}
			if(Klient.gracz=='b') {
				szachownica[i][0].setKolor('c');
				szachownica[i][1].setKolor('c');
				szachownica[i][6].setKolor('b');
				szachownica[i][7].setKolor('b');
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		myszX1=e.getX()/100;
		myszY1=e.getY()/100;
		System.out.println(myszX1 + " " + myszY1);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		myszX2=e.getX()/100;
		myszY2=e.getY()/100;
		System.out.println(myszX2 + " " + myszY2);
		if(sprawdzenieRuchu()) {
			szachownica[myszX2][myszY2].setKolor(szachownica[myszX1][myszY1].getKolor());
			szachownica[myszX2][myszY2].setPionek(szachownica[myszX1][myszY1].getPionek());
			szachownica[myszX1][myszY1].setKolor('n');
			szachownica[myszX1][myszY1].setPionek('n');
			repaint();
			Klient.myszX1=myszX1;
			Klient.myszY1=myszY1;
			Klient.myszX2=myszX2;
			Klient.myszY2=myszY2;
			Klient.wysylka=1;
			
			if(Klient.tura=='b')
				Klient.tura='c';
			else if(Klient.tura=='c')
				Klient.tura='b';	
			

		}
	}
	
	public boolean sprawdzenieRuchu() {
		if(Klient.gracz==Klient.tura) { //czy tura wlasciwa? tak:
			if(szachownica[myszX1][myszY1].getKolor()==Klient.gracz) { //czy kolor pionka wlasciwy? tak:
				if(szachownica[myszX1][myszY1].getPionek()=='p') { //piony:
					if((myszY1==6)&&(myszY2==4)&&(myszX1==myszX2)&&(szachownica[myszX1][5].getKolor()=='n')&&(szachownica[myszX1][4].getKolor()=='n')) //pierwszy ruch od dwa pola
						return true;
					else if((myszY2==myszY1-1)&&(myszX1==myszX2)&&(szachownica[myszX2][myszY2].getKolor()=='n'))
						return true;
					else if((myszY2==myszY1-1)&&((myszX2==myszX1-1)||(myszX2==myszX1+1))&&(szachownica[myszX2][myszY2].getKolor()!='n')&&(szachownica[myszX2][myszY2].getKolor()!=Klient.gracz))
						return true;
					else
						return false;
				}
				else
					return true;

			} //czy kolor pionka wlasciwy? nie:
			else
				return false;
		}
		else //czy tura wlasciwa? nie:
			return false;
	}
	
	public void zmiana(){
		szachownica[Klient.myszX2][Klient.myszY2].setKolor(szachownica[Klient.myszX1][Klient.myszY1].getKolor());
		szachownica[Klient.myszX2][Klient.myszY2].setPionek(szachownica[Klient.myszX1][Klient.myszY1].getPionek());
		szachownica[Klient.myszX1][Klient.myszY1].setKolor('n');
		szachownica[Klient.myszX1][Klient.myszY1].setPionek('n');
	}
}