import javax.swing.JFrame;
import javax.swing.JPanel;

public class Okno extends JFrame{

		public Okno() {
			super("Szachy");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setResizable(false);
			setSize(816, 839);
			
			JPanel panel=new Panel();
			add(panel);
			//pack();

		}
}
