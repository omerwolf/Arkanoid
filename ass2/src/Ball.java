import java.awt.Color;
import biuoop.DrawSurface;
/**
 * Ball class.
 *
 * @author Omer Wolf.
 **/
public class Ball {
    private int radius;
    private Color color;
    private Point location;
    private Velocity velocity;
    private Point maxMove;
    private Point minMove;
    /**
     * constructor create a ball by given the center point, the radius and the
     * color.
     *
     * @param center the center point of the ball.
     * @param r the radius of the ball.
     * @param color1 the color of the ball.
     **/
    public Ball(Point center, int r, java.awt.Color color1) {
        this.radius = r;
        this.color = color1;
        this.location = center;
        this.velocity = new Velocity(0, 0);
    }
    /**
     * constructor create ball given the x and y coordinates of the center , the
     * radius and the color.
     *.
     * @param x the x coordinate of the center point of the ball.
     * @param y the y coordinate of the center point of the ball.
     * @param r the radius of the ball.
     * @param color1 the color of the ball.
     * @param screenSize the top Point of the screen.
     **/
    public Ball(double x, double y, int r, java.awt.Color color1, Point screenSize) {
        this.radius = r;
        this.color = color1;
        this.location = new Point(x, y);
        this.velocity = new Velocity(0, 0);
        this.maxMove = screenSize;
        this.minMove = new Point(0, 0);
    }
    /**
     *this method cause the ball to move one step, but make sure it's not go
     * out of the screen.
     **/
    public void moveOneStep() {
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        double currentX = this.location.getX();
        double currentY = this.location.getY();
        double maxX = this.maxMove.getX();
        double maxY = this.maxMove.getY();
        double minX = this.minMove.getX();
        double minY = this.minMove.getY();
        if ((currentX + radius + dx > maxX) || (currentX + radius + dx < 2 * radius + minX)) {
            this.velocity.setDx(-dx);
        }
        if ((currentY + radius + dy > maxY) || (currentY + radius + dy < 2 * radius + minY)) {
            this.velocity.setDy(-dy);
        }
        this.location = this.velocity.applyToPoint(this.location);
        }
    /**
    *
    * @param drawS - the given DrawSurface to draw the ball on.
     **/
    public void drawOn(DrawSurface drawS) {
        int x = (int) location.getX();
        int y = (int) location.getY();
        drawS.setColor(color);
        drawS.fillCircle(x, y, radius);
    }
    /**
     * @return x the x coordinate of the center point of the ball
     **/
    public int getX() {
        return (int) location.getX();
    }
    /**
     * @return y the y coordinate of the center point of the ball
     **/
    public int getY() {
        return (int) location.getY();
    }
    /**
     * @return radius the x radius of the ball
     **/
    public int getSize() {
        return radius;
    }
    /**
     * @return color the color of the ball.
     **/
    public java.awt.Color getColor() {
        return color;
    }
    /**
     * @param v -  the velocity of the ball.
     **/
    public void setVelocity(Velocity v) {
         this.velocity = v;
    }
    /**
     * build the dx and dy coordinates of the ball's velocity,
     * by given the x and y coordinates of the velocity
     *.
     * @param  dx - the dx coordinate of the velocity
     * @param  dy - the dy coordinate of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);

    }
    /**
     * @return velocity the velocity of the ball.
     **/
    public Velocity getVelocity() {
        return velocity;
    }
   /**
     * @param maxMove1 - the high edge of the screen.
     *
     **/
    public void setMaxMove(Point maxMove1) {
        this.maxMove = maxMove1;
    }
   /**
     * @param minMove1 - the low edge of the screen.
     *
     **/
    public void setMinMove(Point minMove1) {
        this.minMove = minMove1;
    }
}