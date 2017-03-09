public class Point {
	public double x;
	public double y;
	public double z;
	
	public Point() {
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}
	
	public Point( double x, double y, double z ) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	

	public Point add( Point p2 ) {
		return new Point( x + p2.x, y + p2.y, z + p2.z );
	}
	
	public Point sub( Point p2 ) {
		return new Point( x - p2.x, y - p2.y, z - p2.z );
	}
	
	public Point mul( Point p2 ) {
		return new Point(
					y * p2.z - z * p2.y,
					z * p2.x - x * p2.z,
					x * p2.y - y * p2.y
				);
	}
	
	public Point agrandir( double s ) {
		return new Point( x * s, y * s, z * s );
	}
	
	public double produitScalaire( Point p2 ) {
		return x * p2.x + y * p2.y + z * p2.z;
	}
	
	@Override
	public String toString() {
		return "( " + x + ", " + y + ", " + z + " )";
	}
}