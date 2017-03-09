import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/* Classe pour gerer le niveau.
 *Étend de JSlider.
 *
 * PS : Ressemble beaucoup à rotation
 * mais j'ai preferé la laisser
 * à par pour faire plus propre.
 * */
public class Niveau extends JSlider{
	
	private static final long serialVersionUID = 1L;
	
	/*Variable de classe
	 * */
	Scene s;
	JTextField txt;
	JButton buttonAdd;
	JButton buttonSub;
	JLabel label;
	
	/*Constructeur
	 * */
	public Niveau(Scene s){
		super(0,s.getNiveauMaximum(),s.getNiveau());
		this.s = s;
		txt = new JTextField("0",7);
		buttonAdd = new JButton(">");
		buttonSub = new JButton("<");
		label = new JLabel("niveau");
		
		niveau();
	}
	
	public void setNiveau(){
		this.setMaximum(s.getNiveauMaximum());
	}
	
	/*Méthode principale
	 * pour gerer le niveau.
	 * */
	private void niveau(){
		niveauSlider();
		
		niveauTextField();
		
		niveauButton();
	}
	
	/*Méthode qui s'occupe
	 * de gerer le slider 
	 * de niveau.
	 * */
	private void niveauSlider(){
		this.addChangeListener((e) -> {
			int niveau = this.getValue();
			s.setNiveau(niveau);
			txt.setText(String.valueOf(niveau));
			s.repaint();
		});
	}
	
	/*Méthode qui s'occupe
	 * de gerer le textField 
	 * de niveau.
	 * */
	private void niveauTextField(){
		txt.addActionListener((e) -> {
			int niveau = Integer.parseInt(txt.getText());
			if(niveau >= 0 && niveau < this.getMaximum()){
				s.setNiveau(niveau);;
				this.setValue(niveau);
			}else if(niveau < 0){
				s.setNiveau(this.getMinimum());
				this.setValue(this.getMinimum());
				txt.setText(String.valueOf(this.getMinimum()));
			}else{
				s.setNiveau(this.getMaximum());
				this.setValue(this.getMaximum());
				txt.setText(String.valueOf(this.getMaximum()));
			}
			s.repaint();
		});
	}
	
	/*Méthode qui s'occupe
	 * de gerer les bouttons 
	 * de niveau.
	 * */
	private void niveauButton(){
		buttonAdd.addActionListener((e) -> {
			int niveau = this.getValue();
			if(niveau < this.getMaximum()){
				niveau ++;
			}
			s.setNiveau(niveau);
			this.setValue(niveau);
			txt.setText(String.valueOf(niveau));
			s.repaint();
		});
		
		buttonSub.addActionListener((e) -> {
			int niveau = this.getValue();
			if(niveau > 0){
				niveau --;
			}
			s.setNiveau(niveau);
			this.setValue(niveau);
			txt.setText(String.valueOf(niveau));
			s.repaint();
		});
	}
	
}
