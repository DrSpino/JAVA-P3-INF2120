
public class Plan {
	public Point w;
	public Point u;
	public Point v;
	public Point n;
	public double uv;
	public double vv;
	public double uu;
	public double den;
	
	public Plan( Point w, Point u, Point v, Point n ) {
		this.w = w;
		this.u = u;
		this.v = v;
		this.n = n;
		
		uv = u.produitScalaire( v );
		uu = u.produitScalaire( u );
		vv = v.produitScalaire( v );
		
		den = uv * uv - vv * uu;
	}
	
	public Point vers2D( Point x ) {
		Point xLocal = x.sub( w );
		double xv = xLocal.produitScalaire( v );
		double xu = xLocal.produitScalaire( u );
		double s = ( uv * xv - vv * xu ) / den;
		double t = ( uv * xu - uu * xv ) / den;
		
		return new Point( s, t, 0.0 );
	}
	
	
	public void rotation( double rollGamma, double pitchBeta, double yawAlpha ) {
		double cos_g = Math.cos( rollGamma );
		double cos_b = Math.cos( pitchBeta );
		double cos_a = Math.cos( yawAlpha );
		double sin_g = Math.sin( rollGamma );
		double sin_b = Math.sin( pitchBeta );
		double sin_a = Math.sin( yawAlpha );
		
		double cos_sin = cos_a * sin_g;
		double sin_sin = sin_a * sin_g;
		double cos_cos = cos_a * cos_g;
		double sin_cos = sin_a * cos_g;
		
		Point l1 = new Point( 
				cos_a * cos_b, 
				cos_sin * sin_b - sin_cos, 
				cos_cos * sin_b + sin_sin
				);
		Point l2 = new Point( 
				sin_a * cos_b, 
				sin_sin * sin_b + cos_cos, 
				sin_cos * sin_b - cos_sin
				);
		Point l3 = new Point(
				- sin_b,
				cos_b * sin_g,
				cos_b * cos_g
				);
		
		u = new Point( 
				u.produitScalaire( l1 ),
				u.produitScalaire( l2 ),
				u.produitScalaire( l3 )
				);
		v = new Point( 
				v.produitScalaire( l1 ),
				v.produitScalaire( l2 ),
				v.produitScalaire( l3 )
				);
		n = new Point( 
				n.produitScalaire( l1 ),
				n.produitScalaire( l2 ),
				n.produitScalaire( l3 )
				);
	}
}