import javax.swing.JPanel;

import javax.swing.ImageIcon;

import java.awt.Cursor;
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
		if(Klient.tura!=Klient.gracz)
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
	}

	public void paint(Graphics g)
	{
		int i,j;

		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				
				if((i+j)%2==0) {
					pole_szachownicy = new ImageIcon("png/pole-p.png");
					pole_szachownicy.paintIcon(this, g, i*100, j*100);
				}
				else {
					pole_szachownicy = new ImageIcon("png/pole-z.png");
					pole_szachownicy.paintIcon(this, g, i*100, j*100);
				}
				
				if(szachownica[i][j].getKolor()=='b') {
					if(szachownica[i][j].getPionek()=='k')
						pole_szachownicy = new ImageIcon("png/k-b.png");
					if(szachownica[i][j].getPionek()=='h')
						pole_szachownicy = new ImageIcon("png/h-b.png");
					if(szachownica[i][j].getPionek()=='w')
						pole_szachownicy = new ImageIcon("png/w-b.png");
					if(szachownica[i][j].getPionek()=='g')
						pole_szachownicy = new ImageIcon("png/g-b.png");
					if(szachownica[i][j].getPionek()=='s')
						pole_szachownicy = new ImageIcon("png/s-b.png");
					if(szachownica[i][j].getPionek()=='p')
						pole_szachownicy = new ImageIcon("png/p-b.png");
					
					pole_szachownicy.paintIcon(this, g, i*100, j*100);
				}
				else if(szachownica[i][j].getKolor()=='c') {
					if(szachownica[i][j].getPionek()=='k')
						pole_szachownicy = new ImageIcon("png/k-c.png");
					if(szachownica[i][j].getPionek()=='h')
						pole_szachownicy = new ImageIcon("png/h-c.png");
					if(szachownica[i][j].getPionek()=='w')
						pole_szachownicy = new ImageIcon("png/w-c.png");
					if(szachownica[i][j].getPionek()=='g')
						pole_szachownicy = new ImageIcon("png/g-c.png");
					if(szachownica[i][j].getPionek()=='s')
						pole_szachownicy = new ImageIcon("png/s-c.png");
					if(szachownica[i][j].getPionek()=='p')
						pole_szachownicy = new ImageIcon("png/p-c.png");
					
					pole_szachownicy.paintIcon(this, g, i*100, j*100);
				}
			}
		}
	}
	
	public void inicjuj(){
		//zmienna pionek:
		//k - król, h - hetman, w - wie¿a, g - goniec, s - skoczek, p - pion, n - nic
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
			wygrana();
			premia();
				
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
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
		}
	}
	
	public boolean sprawdzenieRuchu() {
 //czy tura wlasciwa? czy wybrano inne miejsce? czy nie stajesz na wlasnym pionku? czy kolor pionka wlasciwy?
			if((Klient.gracz==Klient.tura)&&(szachownica[myszX1][myszY1].getKolor()==Klient.gracz)&&(myszX1!=myszX2||myszY1!=myszY2)&&(szachownica[myszX2][myszY2].getKolor()!=Klient.gracz)) {
					if(szachownica[myszX1][myszY1].getPionek()=='p') { //pion:
						if((myszY1==6)&&(myszY2==4)&&(myszX1==myszX2)&&(szachownica[myszX1][5].getKolor()=='n')&&(szachownica[myszX1][4].getKolor()=='n')) //pierwszy ruch od dwa pola
							return true;
						else if((myszY2==myszY1-1)&&(myszX1==myszX2)&&(szachownica[myszX2][myszY2].getKolor()=='n'))
							return true;
						else if((myszY2==myszY1-1)&&((myszX2==myszX1-1)||(myszX2==myszX1+1))&&(szachownica[myszX2][myszY2].getKolor()!='n')&&(szachownica[myszX2][myszY2].getKolor()!=Klient.gracz))
							return true;
						else
							return false;
					}
					else if(szachownica[myszX1][myszY1].getPionek()=='w') { //wieza:
						if((myszX1==myszX2)) { //poruszanie w pionie
							if(myszY2>myszY1) { //ruch w dol
								int i=myszY1+1;
										while(i!=myszY2) {
											if(szachownica[myszX2][i].getKolor()!='n')
												return false;
											i++;
										}
								return true;
							}
							else { //ruch w gore
								int i=myszY2+1;
										while(i!=myszY1) {
											if(szachownica[myszX2][i].getKolor()!='n')
												return false;
											i++;
										}
								return true;
							}
						}
						else if ((myszY1==myszY2)) { //poruszanie w poziomie
							if(myszX2>myszX1) {
								int i=myszX1+1;
										while(i!=myszX2) {
											if(szachownica[i][myszY2].getKolor()!='n')
												return false;
											i++;
										}
								return true;
							}
							else {
								int i=myszX2+1;
										while(i!=myszX1) {
											if(szachownica[i][myszY2].getKolor()!='n')
												return false;
											i++;
										}
								return true;
							}
						}
						else
							return false;
					}
					else if(szachownica[myszX1][myszY1].getPionek()=='g') { //goniec
						if(myszX2-myszX1==myszY2-myszY1||myszX2-myszX1==-(myszY2-myszY1)) {
							if(myszX2>myszX1) { //prawo-gora lub prawo-dol
								if(myszY2>myszY1) { //prawo-dol
									int i = myszX1+1;
									int j = myszY1+1;
									while(i!=myszX2) {
										if(szachownica[i][j].getKolor()!='n')
											return false;
										i++;
										j++;
									}
									return true;
								}
								else {//prawo-gora
									int i = myszX1+1;
									int j = myszY1-1;
									while(i!=myszX2) {
										if(szachownica[i][j].getKolor()!='n')
											return false;
										i++;
										j--;
									}
									return true;
								}
							}
							else { //lewo-gora lub lewo-dol
								if(myszY2>myszY1) { //prawo-dol
									int i = myszX1-1;
									int j = myszY1+1;
									while(i!=myszX2) {
										if(szachownica[i][j].getKolor()!='n')
											return false;
										i--;
										j++;
									}
									return true;
								}
								else {//prawo-gora
									int i = myszX1-1;
									int j = myszY1-1;
									while(i!=myszX2) {
										if(szachownica[i][j].getKolor()!='n')
											return false;
										i--;
										j--;
									}
									return true;
								}
							}
						}

						else
							return false;
					}
					else if(szachownica[myszX1][myszY1].getPionek()=='h') {
						if(myszX2-myszX1==myszY2-myszY1||myszX2-myszX1==-(myszY2-myszY1)) {
							if(myszX2>myszX1) { //prawo-gora lub prawo-dol
								if(myszY2>myszY1) { //prawo-dol
									int i = myszX1+1;
									int j = myszY1+1;
									while(i!=myszX2) {
										if(szachownica[i][j].getKolor()!='n')
											return false;
										i++;
										j++;
									}
									return true;
								}
								else {//prawo-gora
									int i = myszX1+1;
									int j = myszY1-1;
									while(i!=myszX2) {
										if(szachownica[i][j].getKolor()!='n')
											return false;
										i++;
										j--;
									}
									return true;
								}
							}
							else { //lewo-gora lub lewo-dol
								if(myszY2>myszY1) { //prawo-dol
									int i = myszX1-1;
									int j = myszY1+1;
									while(i!=myszX2) {
										if(szachownica[i][j].getKolor()!='n')
											return false;
										i--;
										j++;
									}
									return true;
								}
								else {//prawo-gora
									int i = myszX1-1;
									int j = myszY1-1;
									while(i!=myszX2) {
										if(szachownica[i][j].getKolor()!='n')
											return false;
										i--;
										j--;
									}
									return true;
								}
							}
						}
						else if((myszX1==myszX2)) { //poruszanie w pionie
							if(myszY2>myszY1) { //ruch w dol
								int i=myszY1+1;
										while(i!=myszY2) {
											if(szachownica[myszX2][i].getKolor()!='n')
												return false;
											i++;
										}
								return true;
							}
							else { //ruch w gore
								int i=myszY2+1;
										while(i!=myszY1) {
											if(szachownica[myszX2][i].getKolor()!='n')
												return false;
											i++;
										}
								return true;
							}
						}
						else if ((myszY1==myszY2)) { //poruszanie w poziomie
							if(myszX2>myszX1) {
								int i=myszX1+1;
										while(i!=myszX2) {
											if(szachownica[i][myszY2].getKolor()!='n')
												return false;
											i++;
										}
								return true;
							}
							else {
								int i=myszX2+1;
										while(i!=myszX1) {
											if(szachownica[i][myszY2].getKolor()!='n')
												return false;
											i++;
										}
								return true;
							}
						}

						else
							return false;
					}
					else if(szachownica[myszX1][myszY1].getPionek()=='s') {
						if(((myszX2-myszX1==-1||myszX2-myszX1==1)&&(myszY2-myszY1==-2||myszY2-myszY1==2))||((myszX2-myszX1==-2||myszX2-myszX1==2)&&(myszY2-myszY1==-1||myszY2-myszY1==1)))
							return true;
						else
							return false;
					}
					else if(szachownica[myszX1][myszY1].getPionek()=='k') {
						if((myszX2-myszX1>=-1)&&(myszX2-myszX1<=1)&&(myszY2-myszY1>=-1)&&(myszY2-myszY1<=1))
							return true;
						else if(myszX1==4&&myszY1==7&&myszX2==2&&myszY2==7&&szachownica[0][7].getPionek()=='w'&&szachownica[0][7].getKolor()==Klient.gracz&&szachownica[1][7].getKolor()=='n'&&szachownica[2][7].getKolor()=='n'&&szachownica[3][7].getPionek()=='n') {
							szachownica[0][7].setPionek('n');
							szachownica[0][7].setKolor('n');
							szachownica[3][7].setPionek('w');
							szachownica[3][7].setKolor(Klient.gracz);
							return true;
						}
						else if(myszX1==4&&myszY1==7&&myszX2==6&&myszY2==7&&szachownica[5][7].getKolor()=='n'&&szachownica[6][7].getKolor()=='n'&&szachownica[7][7].getPionek()=='w'&&szachownica[7][7].getKolor()==Klient.gracz) {
							szachownica[7][7].setPionek('n');
							szachownica[7][7].setKolor('n');
							szachownica[5][7].setPionek('w');
							szachownica[5][7].setKolor(Klient.gracz);
							return true;
						}
						else
							return false;
					}
					else
						return true;
				}
				else
					return false;
	}
	
	public void zmiana(){
		if(Klient.myszX1==4&&Klient.myszY1==0&&Klient.myszX2==2&&Klient.myszY2==0&&szachownica[0][0].getPionek()=='w'&&szachownica[0][0].getKolor()!=Klient.gracz&&szachownica[1][0].getKolor()=='n'&&szachownica[2][0].getKolor()=='n'&&szachownica[3][0].getPionek()=='n') {
			szachownica[0][0].setPionek('n');
			szachownica[0][0].setKolor('n');
			szachownica[3][0].setPionek('w');
			if(Klient.gracz=='b')
				szachownica[3][0].setKolor('c');
			else
				szachownica[3][0].setKolor('b');
		}
		if(Klient.myszX1==4&&Klient.myszY1==0&&Klient.myszX2==6&&Klient.myszY2==0&&szachownica[5][0].getKolor()=='n'&&szachownica[6][0].getKolor()=='n'&&szachownica[7][0].getPionek()=='w'&&szachownica[7][0].getKolor()!=Klient.gracz) {
			szachownica[7][0].setPionek('n');
			szachownica[7][0].setKolor('n');
			szachownica[5][0].setPionek('w');
			if(Klient.gracz=='b')
				szachownica[5][0].setKolor('c');
			else
				szachownica[5][0].setKolor('b');
		}
			
		szachownica[Klient.myszX2][Klient.myszY2].setKolor(szachownica[Klient.myszX1][Klient.myszY1].getKolor());
		szachownica[Klient.myszX2][Klient.myszY2].setPionek(szachownica[Klient.myszX1][Klient.myszY1].getPionek());
		szachownica[Klient.myszX1][Klient.myszY1].setKolor('n');
		szachownica[Klient.myszX1][Klient.myszY1].setPionek('n');
		wygrana();
		premia();
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void wygrana() {
		int k=0;
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(szachownica[i][j].getPionek()=='k')
					k++;
			}
		}
		if(k!=2) {
			inicjuj();
		}
	}
	public void premia() {
		for(int i=0;i<8;i++) {
			if(szachownica[i][0].getPionek()=='p')
				szachownica[i][0].setPionek('h');
			if(szachownica[i][7].getPionek()=='p')
				szachownica[i][7].setPionek('h');
		}
	}
}