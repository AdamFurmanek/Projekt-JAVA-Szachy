import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Panel extends JPanel {

	private Pole[][] tablica = new Pole[8][8];
	
	public Panel() {
		
	}

	public void paint(Graphics g)
	{
		int i,j;
		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				tablica[i][j]=new Pole("java.jpg");
				tablica[i][j].paintIcon(this, g, i*100, j*100);
			}
		}
	}
}