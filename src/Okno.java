import javax.swing.JFrame;
import javax.swing.JPanel;

public class Okno extends JFrame{
	
	public static Panel panel;
	
		public Okno() {
			
			super("Szachy");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setResizable(false);
			setSize(800, 823);
			
			panel = new Panel();
			add(panel);
			//pack();

		}
}
