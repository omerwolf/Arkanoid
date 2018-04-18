/**
 * A Velocity class.
 *
 * @author Omer Wolf.
 */
public class Velocity {
    private double dx;
    private double dy;

        /**
     * constructor of the ball's velocity by given the dx and dy coordinates.
     *
     * @param dx1 the x coordinate.
     * @param dy1 the y coordinate.
     */
    public Velocity(double dx1, double dy1) {
        this.dx = dx1;
        this.dy = dy1;
    }
    /**
     * @param p the point that we want to apply the new position on.
     * @return the new point with position (x+dx,y+dy).
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + dx;
        double newY = p.getY() + dy;
        Point newP = new Point(newX, newY);
        return newP;
    }
        /**
     * @param angle the angle of the velocity.
     * @param speed the speed of the velocity.
     * @return a new velocity with the dx and dy coordinates
     * we build from the angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx1 = Math.cos(Math.toRadians(angle)) * speed;
        double dy1 = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx1, dy1);
    }
           /**
     * @return the dx of the velocity.
     */
    public double getDx() {
        return dx;
    }
    /**
     * @param  dx1 - new dx.
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }
    /**
     * @return the dy of the velocity.
     */
    public double getDy() {
        return dy;
    }
    /**
     * @param  dy1 - new dy.
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }
}