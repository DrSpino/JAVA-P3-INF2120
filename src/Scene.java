import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Scene extends Canvas {
	Color FOND    = new Color( 0xFFE4AA );
	Color GRILLE  = new Color( 0xD4CA6A );
	Color GRILLE4 = new Color( 0x807515 );
	Color AXES    = new Color( 0xC01500 );
	Color CRAYON  = new Color( 0x061439 );

	private static final long serialVersionUID = 1L;
	
	private ArrayList< Point > points = new ArrayList<>();
	private ArrayList< Triangle > triangles = new ArrayList<>();
	
	private double echelleGrille = 1.0;
	private double echelleProfondeur = 1.0;
	private int echelleImage = 7;
	
	private Plan scanner = new Plan( new Point( 0.0, 0.0, 0.0 ),
			new Point( echelleGrille, 0.0, 0.0 ),
			new Point( 0.0, 0.0, - echelleGrille ),
			new Point( 0.0, - echelleGrille, 0.0 )
		);
	
	private double rotationYZ = 0.0;
	private double rotationXZ = 0.0;
	private double rotationXY = 0.0;
	
	private double minX = 0.0;
	private double maxX = 0.0;
	private double minY = 0.0;
	private double maxY = 0.0;
	private double minZ = 0.0;
	private double maxZ = 0.0;
	
	private int niveau = 0;
	private int niveauMaximum = 0;
	
	private int taille_x = 0;
	private int taille_y = 0;
	
	public void chargerFichierObj( File fichier ) {
		points = new ArrayList< Point >();
		triangles = new ArrayList< Triangle >();

		//File fichier = new File( nomFichierObj );
		try {
			Scanner scanner = new Scanner( fichier );
			
			while( scanner.hasNextLine() ) {
				Scanner ligne = new Scanner( scanner.nextLine() );

				try {
					switch( ligne.next() ) {
					case "v":
						double [] d = { 0.0, 0.0, 0.0 };
						for( int j = 0; j < 3; ++j ) {
							d[j] = Double.valueOf( ligne.next() );
						}
						points.add( new Point( d[0], d[1], d[2] ) );
						break;
					case "f":
						int [] p = {0, 0, 0};
						for( int i = 0; i < 3; ++i ) {
							Scanner v = new Scanner( ligne.next() );
							v.useDelimiter( "/" );
							p[i] = v.nextInt();
							v.close();
						}
						triangles.add( new Triangle( p[0], p[1], p[2] ) );
						break;
					default :
						break;
					}
				} catch( NoSuchElementException e ) {
				}
			
				ligne.close();
			}
//			System.out.println( points.size() );
//			System.out.println( triangles.size() );
			scanner.close();
		} catch( FileNotFoundException e ) {
			System.err.println( "Impossible d'ouvrir le fichier." );
			System.exit( -1 );
		}
		ajusterPlan();
	}
	
	public Scene( String nomFichierObj ) {
		
	//	chargerFichierObj( nomFichierObj );

	}
	
	private void ajusterNiveauMaximum() {
		niveauMaximum = (int) ( ( maxZ - minZ ) / echelleProfondeur );
	}
	
	private void ajusterPlan() {
		scanner = new Plan( new Point( 0.0, 0.0, 0.0 ),
				new Point( echelleGrille, 0.0, 0.0 ),
				new Point( 0.0, 0.0, - echelleGrille ),
				new Point( 0.0, - echelleGrille, 0.0 )
			);
		scanner.rotation( rotationYZ, rotationXZ, rotationXY );
		calculerMinima();
	}
	
	private void ajusterTaille() {
		taille_x = (int)( maxX - minX + 1 ) * echelleImage;
		taille_y = (int)( maxY - minY + 1 ) * echelleImage;
		setSize( taille_x, taille_y );
	}
	
	private void calculerMinima() {
		minX = 0.0;
		maxX = 0.0;
		minY = 0.0;
		maxY = 0.0;
		minZ = 0.0;
		maxZ = 0.0;
		
		double den = scanner.n.produitScalaire( scanner.n );
		
		for( Point point : points ) {
			Point temp = scanner.w.sub( point );
			double num = scanner.n.produitScalaire( temp );
			
			double t = den != 0.0 ? num / den : 0.0 ;
			Point tt = scanner.n.agrandir( t );
			Point collision = point.add( tt );
			
			Point w = collision.sub( scanner.w );
			double wv = w.produitScalaire( scanner.v );
			double wu = w.produitScalaire( scanner.u );
			double s0 = ( scanner.uv * wv - scanner.vv * wu ) / scanner.den;
			double t0 = ( scanner.uv * wu - scanner.uu * wv ) / scanner.den;
			
			minX = minX < s0 ? minX : s0;
			maxX = maxX > s0 ? maxX : s0;
			
			minY = minY < t0 ? minY : t0;
			maxY = maxY > t0 ? maxY : t0;
			
			minZ = minZ < t ? minZ : t;
			maxZ = maxZ > t ? maxZ : t;
		}
		
		ajusterNiveauMaximum();
		ajusterTaille();

	}
	
	public double getMaxX() {
		return maxX;
	}
	
	public double getMinX() {
		return minX;
	}
	
	public double getMaxY() {
		return maxY;
	}
	
	public double getMinY() {
		return minY;
	}
	
	public double getMaxZ() {
		return maxZ;
	}
	
	public double getMinZ() {
		return minZ;
	}
	
	public void setRotationYZ( double r ) {
		rotationYZ = r;
		
		ajusterPlan();
	}
	
	public double getRotationYZ() {
		return rotationYZ;
	}
	
	public void setRotationXZ( double r ) {
		rotationXZ = r;
		
		ajusterPlan();
	}
	
	public double getRotationXZ() {
		return rotationXZ;
	}
	
	public void setRotationXY( double r ) {
		rotationXY = r;
		
		ajusterPlan();
	}
	
	public double getRotationXY() {
		return rotationXY;
	}
	
	public double getEchelleGrille() {
		return echelleGrille;
	}
	
	public void setEchelleGrille( double echelle ) {
		echelleGrille = echelle;
		
		ajusterPlan();
	}
	
	public double getEchelleProfondeur() {
		return echelleProfondeur;
	}
	
	public void setEchelleProfondeur( double echelle ) {
		echelleProfondeur = echelle;
		
		ajusterNiveauMaximum();
	}
	
	public int getEchelleImage() {
		return echelleImage;
	}
	
	public void setEchelleImage( int echelle ) {
		echelleImage = echelle;
		
		ajusterTaille();
	}
	
	public int getNiveau() {
		return niveau;
	}
	
	public void setNiveau( int n ) {
		niveau = n;
	}
	
	public int getNiveauMaximum() {
		return niveauMaximum;
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor( FOND );
		g.fillRect( 0, 0, taille_x, taille_y );

		Point d = new Point( minX * echelleImage, minY * echelleImage, 0.0 );

		for( int x = (int)minX; x <= (int)maxX; ++ x ) {
			if( x == 0 ) {
				g.setColor( AXES );
			} else if( x % 4 == 0 ) {
				g.setColor( GRILLE4 );
			} else {
				g.setColor( GRILLE );
			}
			g.drawLine( (int)( x * echelleImage - d.x ), 
						(int)( minY * echelleImage - d.y ), 
						(int)( x * echelleImage - d.x ),
						(int)( maxY * echelleImage - d.y ) );
		}
		for( int y = (int)minY; y <= (int)maxY; ++ y ) {
			if( y == 0 ) {
				g.setColor( AXES );
			} else if( y % 4 == 0 ) {
				g.setColor( GRILLE4 );
			} else {
				g.setColor( GRILLE );
			}
			g.drawLine( (int)( minX * echelleImage - d.x ), 
						(int)( y * echelleImage - d.y ), 
						(int)( maxX * echelleImage - d.x ),
						(int)( y * echelleImage - d.y ) );
		}
		
		g.setColor( CRAYON );
		
		double profondeur = minZ + echelleProfondeur * niveau;
		Point deplacement = scanner.n.agrandir( - profondeur );

		Plan pt = new Plan( scanner.w.add( deplacement ), scanner.u, scanner.v, scanner.n );
		
		for( Triangle triangle : triangles ) {
			ArrayList< Point > intersections = triangle.trouverIntersections( pt, points );
			
			while( intersections.size() >= 2 ) {				
				Point debut = pt.vers2D( intersections.get( 0 ) ).agrandir( echelleImage ).sub( d );
				Point fin   = pt.vers2D( intersections.get( 1 ) ).agrandir( echelleImage ).sub( d );
				
				g.drawLine( (int)debut.x, (int)debut.y,	(int)fin.x, (int)fin.y );
				
				intersections.remove( 0 );
				intersections.remove( 0 );
			}
		}
	}

	@Override
	public void update(Graphics g)
	{
		paint( g );
	}
}