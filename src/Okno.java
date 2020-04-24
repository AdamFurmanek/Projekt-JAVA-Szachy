import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.Cursor;

public class Okno extends JFrame{
	
	public static Panel panel;
	
		public Okno() {
			
			super("Szachy - gracz " +(Klient.gracz=='b'?"bia³y":"czarny"));
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setResizable(false);
			setSize(800, 823);
			panel = new Panel();
			add(panel);

		}
}
