import javax.swing.JFrame;
import javax.swing.JPanel;

public class Okno extends JFrame{
	
	public static Panel panel;
	
		public Okno() {
			
			super("Szachy");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setResizable(false);
			setSize(816, 839);
			
<<<<<<< HEAD
			panel = new Panel();
=======
			JPanel panel = new Panel();
>>>>>>> 810af2d7a8a94df399a22b1c0bb3a547ef11f91f
			add(panel);
			//pack();

		}
}
