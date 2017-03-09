import javax.swing.JLabel;
import javax.swing.JTextField;

/*Classe qui permet de gerer
 * les differentes échelles.
 * Étend de JTextField.
 * */
public class Echelle extends JTextField{
	
	private static final long serialVersionUID = 1L;
	/*Variables de classe
	 * */
	JLabel label;
	Niveau n;
	
	/*Constructeur
	 * */
	public Echelle(Scene s,Niveau n,String sVal,String type){
		super(sVal,10);
		
		this.n = n;
		label = new JLabel();
		
		/*Détermine quel type d'échelle
		 * il s'agit grace au String type.
		 * */
		if(type.equals("grille")){
			echelleGrille(s);
			setLabel("Échelle grille");
		}else if(type.equals("profondeur")){
			echelleProfondeur(s);
			setLabel("Échelle profondeur");
		}else if(type.equals("image")){
			echelleImage(s);
			setLabel("Échelle image");
		}
	}
	
	/*Méthode pour gerer
	 * l'échelle image.
	 * @params Scene s
	 * */
	private void echelleImage(Scene s){
		this.addActionListener( (e) -> {
			int val = s.getEchelleImage();
			String strVal = String.valueOf(val);
			
			String in = this.getText();
			int echelleImage = Integer.parseInt(in);
			
			if(echelleImage > 0){
				s.setEchelleImage(echelleImage);
			}else{
				this.setText(strVal);
			}
			s.repaint();
		});
	}
	
	/*Méthode pour gerer
	 * l'échelle grille.
	 * @params Scene s
	 * */
	public void echelleGrille(Scene s){
		this.addActionListener( (e) -> {
			double val = s.getEchelleGrille();
			String strVal = String.valueOf(val);
			
			String in = this.getText();
			double echelleGrille = Double.parseDouble(in);
			
			if(echelleGrille > 0){
				s.setEchelleGrille(echelleGrille);
			}else{
				this.setText(strVal);
			}
			n.setNiveau();
			s.repaint();
		});
	}
	
	/*Méthode pour gerer
	 * l'échelle profondeur.
	 * @params Scene s
	 * */
	public void echelleProfondeur(Scene s){
		this.addActionListener( (e) -> {
			double val = s.getEchelleProfondeur();
			String strVal = String.valueOf(val);
			
			String in = this.getText();
			double echelleProfondeur = Double.parseDouble(in);
			
			if(echelleProfondeur > 0){
				s.setEchelleProfondeur(echelleProfondeur);
			}else{
				this.setText(strVal);
			}
			n.setNiveau();
			s.repaint();
		});
	}
	
	private void setLabel(String label){
		this.label.setText(label);
	}
}
