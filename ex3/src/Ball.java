import java.awt.Color;
import biuoop.DrawSurface;
/**
 * Ball class.
 *
 * @author Omer Wolf.
 **/
public class Ball implements Sprite{
    private int radius;
    private Color color;
    private Point location;
    private Velocity velocity;
    private GameEnvironment ballGE;

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
        this.ballGE = new GameEnvironment();
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

    }
    /**
     *this method cause the ball to move one step, but make sure it's not go
     * out of the screen.
     **/
    public void moveOneStep() {
        double epsilon = 0.001;
        Point endTajectory = this.getVelocity().applyToPoint(this.location);
        Line trajectory = new Line(this.location,endTajectory);
        CollisionInfo collInfo = this.ballGE.getClosestCollision(trajectory);
        //No collision case.
        if (collInfo == null) {
            this.location = endTajectory;
        } else {
            double dx = this.velocity.getDx();
            double dy = this.velocity.getDy();
            Collidable collObj = collInfo.collisionObject();
            Point collPoint = collInfo.collisionPoint();
            this.setLocation(collPoint.getX() - (dx / (Math.abs(dx) + epsilon) * epsilon),
                    collPoint.getY() - (dy / (Math.abs(dy) + epsilon) * epsilon));
            this.setVelocity(collObj.hit(collPoint, this.velocity));

            if(collObj instanceof Paddle) {
                double upperXOfPaddle = collObj.getCollisionRectangle().getUpperLeft().getX();
                double upperYOfPaddle = collObj.getCollisionRectangle().getUpperLeft().getY();
                double widthOfPaddle = collObj.getCollisionRectangle().getWidth();
                double heightOfPaddle = collObj.getCollisionRectangle().getHeight();
                if (this.location.getX() >= upperXOfPaddle
                        && this.location.getX() <= upperXOfPaddle + widthOfPaddle && this.location.getY() >= upperYOfPaddle
                        && this.location.getY() <= upperYOfPaddle + heightOfPaddle) {
                    this.location = new Point (350,350);
                }
            }
            }
        }

    /**
     *
     * @param drawS - the given DrawSurface to draw the ball on.
     **/
    @Override
    public void drawOn(DrawSurface drawS) {
        int x = (int) location.getX();
        int y = (int) location.getY();
        drawS.setColor(color);
        drawS.fillCircle(x, y, radius);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * @param g a game. add the ball to the sprite list .
     */
    @Override
    public void addToGame(Game g) {
       g.addSprite(this);
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
     * @param ballGE1 - the game enviroment.
     *
     **/
    public void setBallGE(GameEnvironment ballGE1) {
        this.ballGE = ballGE1;
    }

    public void setRadius(int radius1) {
        this.radius = radius1;
    }

    public void setColor(Color color1) {
        this.color = color1;
    }

    public void setLocation(double x, double y) {
        this.location.setX(x);
        this.location.setY(y);
    }

}