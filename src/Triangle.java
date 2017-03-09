import java.util.ArrayList;

public class Triangle {
	public int indiceP0;
	public int indiceP1;
	public int indiceP2;
	
	public Triangle( int indiceP0, int indiceP1, int indiceP2 ) {
		this.indiceP0 = indiceP0 - 1;
		this.indiceP1 = indiceP1 - 1;
		this.indiceP2 = indiceP2 - 1;
	}
	
	private void intersection( ArrayList<Point> r, Point a, Point c, Plan p ) {
		Point dir = c.sub( a );
		double den = p.n.produitScalaire( dir );
		Point temp = p.w.sub( a );
		double num = p.n.produitScalaire( temp );
		
		if( 0.0 == den && num == 0.0 ) {
			r.add( a );
			r.add( c );
		}
		if( 0.0 != den ) {
			double t = num / den;
			if( 0.0 <= t && t <= 1.0 ) {
				Point tt = dir.agrandir( t );
				r.add( a.add( tt ) );
			}
		}
	}
	
	public ArrayList<Point> trouverIntersections( Plan pt, ArrayList<Point> points ) {
		ArrayList<Point> resultat = new ArrayList<>();
		
		intersection( resultat, points.get( indiceP0 ), points.get( indiceP1 ), pt );
		intersection( resultat, points.get( indiceP0 ), points.get( indiceP2 ), pt );
		intersection( resultat, points.get( indiceP1 ), points.get( indiceP2 ), pt );
		
		return resultat;
	}
}