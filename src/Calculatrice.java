import javax.swing.JButton;
import javax.swing.JFrame;

public class Calculatrice extends JFrame {
	
	public Calculatrice(){
		super();
		
		JButton button1 = new JButton();
		button1.setText("0");
		button1.setVisible(true);
	}
	
	public static void main(String[]args){
		JFrame d = new JFrame();
		d.setVisible(true);
	}

}
