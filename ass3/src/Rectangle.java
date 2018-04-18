/**
 * Rectangle class.
 *
 * @author Omer Wolf.
 */
class Rectangle {
	private Point point; // The upper-left point
	private double width;
	private double height;
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width1, double height1){
    	this.point = upperLeft;
    	this.width = width1;
    	this.height = height1; 
    }
    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List intersectionPoints(Line line){
        Point pointUL = new Point()
    }

    // Return the width and height of the rectangle
    public double getWidth();
    public double getHeight();

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft();
}