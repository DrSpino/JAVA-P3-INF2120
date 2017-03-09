
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Scanner extends JFrame {
	private static final long serialVersionUID = 1L;

	public Scanner() {
		super( "Scanner" );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setLayout( new BorderLayout() );

		Scene s = new Scene( "B-747.obj" );
		add( s, BorderLayout.CENTER );		
		
		GridBagConstraints c = new GridBagConstraints();
		
		/*Niveau
		 * */
		Niveau niveau = new Niveau(s);
		
		JButton load = new JButton( "Charger" );
		JLabel nomFichier = new JLabel( "." );
		load.addActionListener( (e) -> {
		    JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter( "Fichier obj", "obj" );
		    chooser.setFileFilter( filter );
		    int returnVal = chooser.showOpenDialog( this );
		    if( returnVal == JFileChooser.APPROVE_OPTION ) {
		    	nomFichier.setText( chooser.getSelectedFile().getName() );
		    	s.chargerFichierObj( chooser.getSelectedFile() );
		    	
				niveau.setNiveau();
				s.repaint();
		    }
		} );
		
		/*Instanciations
		 * des objets du scanner.
		 * PS: niveau à été instancié
		 * plus haut.
		 * */
		
		/*Échelle grille
		 * */
		double valEGr = s.getEchelleGrille();
		String sValEGr = String.valueOf(valEGr);
		Echelle eGr = new Echelle(s,niveau,sValEGr,"grille");
		
		/*Échelle profondeur
		 * */
		double valEPr = s.getEchelleGrille();
		String sValEPr = String.valueOf(valEPr);
		Echelle ePr = new Echelle(s,niveau,sValEPr,"profondeur");
		
		/*Échelle profondeur
		 * */
		double valImg = s.getEchelleImage();
		String sValImg = String.valueOf(valImg);
		Echelle eIm = new Echelle(s,niveau,sValImg,"image");
		
		/*YZ
		 * */
		Rotation YZ = new Rotation(s,niveau,"YZ");
		/*XZ
		 * */
		Rotation XZ = new Rotation(s,niveau,"XZ");
		/*XY
		 * */
		Rotation XY = new Rotation(s,niveau,"XY");
		
		add(panneauNorth(c,load,nomFichier,YZ,XZ,XY),BorderLayout.NORTH);
		add(panneauSouth(c,niveau,eIm,eGr,ePr),BorderLayout.SOUTH);
		
		pack();
		setVisible( true );
		s.repaint();
	}
	
	/*Méthode panneauSouth
	 * Créer le panneau et place les
	 * objets grace aux grilles.
	 * */
	private JPanel panneauSouth(GridBagConstraints c,
			Niveau niveau,Echelle eIm,Echelle eGr,
			Echelle ePr){
		
		JPanel panneauSouth = new JPanel(new GridBagLayout());
		
		c.gridx = 0;
		c.gridy = 0;
		panneauSouth.add(niveau.label,c);
		c.gridx = 1;
		panneauSouth.add(niveau.buttonSub,c);
		c.gridx = 2;
		panneauSouth.add(niveau,c);
		c.gridx = 3;
		panneauSouth.add(niveau.txt,c);
		c.gridx = 4;
		panneauSouth.add(niveau.buttonAdd,c);
		
		c.gridx = 1;
		c.gridy = 1;
		panneauSouth.add(eIm.label,c);
		c.gridx = 2;
		panneauSouth.add(eIm,c);
		
		c.gridx = 1;
		c.gridy = 2;
		panneauSouth.add(eGr.label,c);
		c.gridx = 2;
		panneauSouth.add(eGr,c);
		
		c.gridx = 1;
		c.gridy = 3;
		panneauSouth.add(ePr.label,c);
		c.gridx = 2;
		panneauSouth.add(ePr,c);
		
		return panneauSouth;
	}
	/*Meme chose pour le North.
	 * */
	private JPanel panneauNorth(GridBagConstraints c,
			JButton load, JLabel nomFichier,
			Rotation YZ,Rotation XZ,Rotation XY){
		
		JPanel panneauNorth = new JPanel(new GridBagLayout());
		
		c.gridx = 0;
		c.gridy = 0;
		panneauNorth.add( load, c );
		c.gridx = 1;
		c.gridy = 0;
		panneauNorth.add( nomFichier, c );
		
		/*Rotation*/
		c.gridx = 0;
		c.gridy = 1;
		panneauNorth.add(YZ.label,c);
		c.gridx = 1;
		panneauNorth.add(YZ.buttonSub,c);
		c.gridx = 2;
		panneauNorth.add(YZ,c);
		c.gridx = 3;
		panneauNorth.add(YZ.txt,c);
		c.gridx = 4;
		panneauNorth.add(YZ.buttonAdd,c);
		
		c.gridx = 0;
		c.gridy = 2;
		panneauNorth.add(XZ.label,c);
		c.gridx = 1;
		panneauNorth.add(XZ.buttonSub,c);
		c.gridx = 2;
		panneauNorth.add(XZ,c);
		c.gridx = 3;
		panneauNorth.add(XZ.txt,c);
		c.gridx = 4;
		panneauNorth.add(XZ.buttonAdd,c);
		
		c.gridx = 0;
		c.gridy = 3;
		panneauNorth.add(XY.label,c);
		c.gridx = 1;
		panneauNorth.add(XY.buttonSub,c);
		c.gridx = 2;
		panneauNorth.add(XY,c);
		c.gridx = 3;
		panneauNorth.add(XY.txt,c);
		c.gridx = 4;
		panneauNorth.add(XY.buttonAdd,c);
		
		return panneauNorth;
	}
	public static void main( String [] argv ) {
		javax.swing.SwingUtilities.invokeLater( () -> new Scanner() );
	}
}