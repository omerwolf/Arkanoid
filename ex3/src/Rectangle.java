/**
 * Rectangle class.
 *
 * @author Omer Wolf.
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
class Rectangle {
    private Point location;                                  //The upper-left corner
    private double width;
    private double height;
    private Color color;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width1, double height1) {
        this.location = upperLeft;
        this.width = width1;
        this.height = height1;
        this.color = Color.BLACK;
    }
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width1, double height1, Color newColor) {
        this.location = upperLeft;
        this.width = width1;
        this.height = height1;
        this.color = newColor;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public List<Point> intersectionPoints(Line line) {
        double xp = location.getX();
        double yp = location.getY();
        Point pointUR = new Point(xp + width, yp);         //The upper-right corner
        Point pointDR = new Point(xp + width, yp + height);//The lower-right corner
        Point pointDL = new Point(xp, yp + height);        //The down-lower corner
        Line line1 = new Line(location, pointUR);          //The upper line
        Line line2 = new Line(pointUR, pointDR);            //The right line
        Line line3 = new Line(pointDR, pointDL);            //The lower line
        Line line4 = new Line(pointDL, location);           //The left line
        List<Line> listOfLines = new ArrayList<Line>();
        listOfLines.add(line1);
        listOfLines.add(line2);
        listOfLines.add(line3);
        listOfLines.add(line4);
        List<Point> listI = new ArrayList<Point>();
        for (int i = 0; i < 4; i++) {
            Point interPoint = line.intersectionWith(listOfLines.get(i));
            if (interPoint != null) {
                listI.add(interPoint);
            }
        }
        return listI;
    }

    // Return the width and height of the rectangle
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return location;
    }

    public Line getUpperLine() {
        double xp = location.getX();
        double yp = location.getY();
        Point pointUR = new Point(xp + width, yp);         //The upper-right corner
        return new Line(location, pointUR);
    }

    public Line getRightLine() {
        double xp = location.getX();
        double yp = location.getY();
        Point pointUR = new Point(xp + width, yp);         //The upper-right corner
        Point pointDR = new Point(xp + width, yp + height);//The lower-right corner
        return new Line(pointUR, pointDR);
    }
    public Line getLowerLine() {
        double xp = location.getX();
        double yp = location.getY();
        Point pointDR = new Point(xp + width, yp + height);//The lower-right corner
        Point pointDL = new Point(xp, yp + height);        //The down-lower corner
        return new Line(pointDR, pointDL);
    }
    public Line getLeftLine() {
        double xp = location.getX();
        double yp = location.getY();
        Point pointDL = new Point(xp, yp + height);        //The down-lower corner
        return new Line(pointDL, location);
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }

    public Color getColor() {
        return color;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}