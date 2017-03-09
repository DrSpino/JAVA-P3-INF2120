import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
/*Classe pour gerer les rotations
 * Étend de JSlider.
 * */
public class Rotation extends JSlider{
	
	private static final long serialVersionUID = 1L;
	
	/*Variables de classes=
	 * */
	Scene s;
	Niveau n;
	JTextField txt;
	JButton buttonAdd;
	JButton buttonSub;
	JLabel label;
	
	/*Constructeur
	 * */
	public Rotation(Scene s,Niveau niveau, String axe){
		super(-180,180,0);
		this.s = s;
		this.n = niveau;
		txt = new JTextField("0",7);
		buttonAdd = new JButton(">");
		buttonSub = new JButton("<");
		label = new JLabel();
		
		/*Determine de quel position
		 * il s'agit grace au String axe.
		 * */
		if(axe.equals("YZ")){
		    YZ();
			setLabel("rotation Y Z");
		}else if(axe.equals("XZ")){
			XZ();
			setLabel("rotation X Z");
		}else if(axe.equals("XY")){
			XY();
			setLabel("rotation X Y");
		}
			
		
	}
	
	private void setLabel(String label){
		this.label.setText(label);
	}
	
	private double radian(double degre){
		return degre * Math.PI /180;
	}
	
	/*Méthode pour les rotations
	 * sur l'axe YZ.
	 * */
	private void YZ(){
		this.addChangeListener((e) -> {
			double rotationYZ = this.getValue();
			s.setRotationYZ(radian(rotationYZ));
			txt.setText(String.valueOf(rotationYZ));
			n.setNiveau();
			s.repaint();
		});
		
		txt.addActionListener((e) -> {
			double rotationYZ = Double.parseDouble(txt.getText());
			if(rotationYZ > -180 && rotationYZ < 180){
				s.setRotationYZ(radian(rotationYZ));
				this.setValue((int)rotationYZ);
			}else if(rotationYZ < -180){
				s.setRotationYZ(radian(this.getMinimum()));
				this.setValue(this.getMinimum());
				txt.setText(String.valueOf(this.getMinimum()));
			}else{
				s.setRotationYZ(radian(this.getMaximum()));
				this.setValue(this.getMaximum());
				txt.setText(String.valueOf(this.getMaximum()));
			}
			n.setNiveau();
			s.repaint();
		});
		
		buttonAdd.addActionListener((e) -> {
			double rotationYZ = this.getValue();
			if(rotationYZ < 180){
				rotationYZ ++;
			}
			s.setRotationYZ(radian(rotationYZ));
			this.setValue((int)rotationYZ);
			txt.setText(String.valueOf(rotationYZ));
			n.setNiveau();
			s.repaint();
		});
		
		buttonSub.addActionListener((e) -> {
			double rotationYZ = this.getValue();
			if(rotationYZ > -180){
				rotationYZ --;
			}
			s.setRotationYZ(radian(rotationYZ));
			this.setValue((int)rotationYZ);
			txt.setText(String.valueOf(rotationYZ));
			n.setNiveau();
			s.repaint();
		});
		
	}
	
	/*Méthode pour les rotations
	 * sur l'axe XZ.
	 * */
	private void XZ(){
		this.addChangeListener((e) -> {
			double rotationXZ = this.getValue();
			s.setRotationXZ(radian(rotationXZ));
			txt.setText(String.valueOf(rotationXZ));
			s.repaint();
		});
		
		txt.addActionListener((e) -> {
			double rotationXZ = Double.parseDouble(txt.getText());
			if(rotationXZ > -180 && rotationXZ < 180){
				s.setRotationXZ(radian(rotationXZ));
				this.setValue((int)rotationXZ);
			}else if(rotationXZ < -180){
				s.setRotationXZ(radian(this.getMinimum()));
				this.setValue(this.getMinimum());
				txt.setText(String.valueOf(this.getMinimum()));
			}else{
				s.setRotationXZ(radian(this.getMaximum()));
				this.setValue(this.getMaximum());
				txt.setText(String.valueOf(this.getMaximum()));
			}
			s.repaint();
		});
		
		buttonAdd.addActionListener((e) -> {
			double rotationXZ = this.getValue();
			if(rotationXZ < 180){
				rotationXZ ++;
			}
			s.setRotationXZ(radian(rotationXZ));
			this.setValue((int)rotationXZ);
			txt.setText(String.valueOf(rotationXZ));
			s.repaint();
		});
		
		buttonSub.addActionListener((e) -> {
			double rotationXZ = this.getValue();
			if(rotationXZ > -180){
				rotationXZ --;
			}
			s.setRotationXZ(radian(rotationXZ));
			this.setValue((int)rotationXZ);
			txt.setText(String.valueOf(rotationXZ));
			s.repaint();
		});
	}
	
	/*Méthode pour les rotations
	 * sur l'axe XY.
	 * */
	private void XY(){
		this.addChangeListener((e) -> {
			double rotationXY = this.getValue();
			s.setRotationXY(radian(rotationXY));
			txt.setText(String.valueOf(rotationXY));
			s.repaint();
		});
		
		txt.addActionListener((e) -> {
			double rotationXY = Double.parseDouble(txt.getText());
			if(rotationXY > -180 && rotationXY < 180){
				s.setRotationXY(radian(rotationXY));
				this.setValue((int)rotationXY);
			}else if(rotationXY < -180){
				s.setRotationXY(radian(this.getMinimum()));
				this.setValue(this.getMinimum());
				txt.setText(String.valueOf(this.getMinimum()));
			}else{
				s.setRotationXY(radian(this.getMaximum()));
				this.setValue(this.getMaximum());
				txt.setText(String.valueOf(this.getMaximum()));
			}
			s.repaint();
		});
		
		buttonAdd.addActionListener((e) -> {
			double rotationXY = this.getValue();
			if(rotationXY < 180){
				rotationXY ++;
			}
			s.setRotationXY(radian(rotationXY));
			this.setValue((int)rotationXY);
			txt.setText(String.valueOf(rotationXY));
			s.repaint();
		});
		
		buttonSub.addActionListener((e) -> {
			double rotationXY = this.getValue();
			if(rotationXY > -180){
				rotationXY --;
			}
			s.setRotationXY(radian(rotationXY));
			this.setValue((int)rotationXY);
			txt.setText(String.valueOf(rotationXY));
			s.repaint();
		});
	}
	
}
