import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class Paddle implements Sprite, Collidable {
    private Rectangle shape;
    private KeyboardSensor keyboard;

    public Paddle(Rectangle rect, KeyboardSensor keyboardS) {
        this.shape = rect;
        this.keyboard = keyboardS;
    }
    public void moveLeft() {
        int limit = 25;    //Screen start point + size of the left border
        if (this.shape.getUpperLeft().getX() >= limit) {
            int sizeOfMove = 5;
            Point location = shape.getUpperLeft();
            Point newLocation = new Point(location.getX() - sizeOfMove, location.getY());
            this.shape.setLocation(newLocation);
        }
    }
    public void moveRight() {
        int limit = 700 - 25 - 200;    //Screen end point - size of the right border - paddel width
        if (this.shape.getUpperLeft().getX() <= limit) {
            int sizeOfMove = 5;
            Point location = shape.getUpperLeft();
            Point newLocation = new Point(location.getX() + sizeOfMove, location.getY());
            this.shape.setLocation(newLocation);
        }
    }

    /**implements Sprite
     *  this method call all of the Sprites to move.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
        return;
    }
    public void drawOn(DrawSurface drawS) {
        drawS.setColor(this.shape.getColor());
        drawS.fillRectangle((int) this.shape.getUpperLeft().getX(),(int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }
    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double paddalSize = this.shape.getWidth();
        double collXpoint = collisionPoint.getX();
        double paddleXPoint = this.shape.getUpperLeft().getX();

        double region1 = paddleXPoint + paddalSize / 5;        //Left-most region.
        double region2 = paddleXPoint + paddalSize * 2 /5;
        double region3 = paddleXPoint + paddalSize * 3 /5;
        double region4 = paddleXPoint + paddalSize * 4 /5;
        double region5 = paddleXPoint + paddalSize;            //Right-most region.

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        Line upperLine = this.shape.getUpperLine();
        Line lowerLine = this.shape.getLowerLine();
        Line leftLine = this.shape.getLeftLine();
        Line rightLine = this.shape.getRightLine();
        if ( upperLine.pointOnLine(collisionPoint,null)) {
            if (collXpoint <= region1) {
                double angel = 300;
                Velocity retVel = currentVelocity.fromAngleAndSpeed(angel, speed);
                return retVel;
            }
            if (region1 < collXpoint && collXpoint <= region2) {
                double angel = 330;
                Velocity retVel = currentVelocity.fromAngleAndSpeed(angel, speed);
                return retVel;
            }
            if (region2 < collXpoint && collXpoint <= region3) {
                Velocity retVel =  new Velocity(dx, -dy);
                return retVel;
            }
            if (region3 < collXpoint && collXpoint <= region4) {
                double angel = 30;
                Velocity retVel = currentVelocity.fromAngleAndSpeed(angel, speed);
                return retVel;
            }
            if (region4 < collXpoint && collXpoint <= region5) {
                double angel = 60;
                Velocity retVel = currentVelocity.fromAngleAndSpeed(angel, speed);
                return retVel;
            }
        }
        if (leftLine.pointOnLine(collisionPoint,null) || rightLine.pointOnLine(collisionPoint, null)) {
            Velocity retVel = new Velocity(-dx, dy);
            return retVel;
        }
        if (lowerLine.pointOnLine(collisionPoint,null)) {
            Velocity retVel = new Velocity(dx, -dy);
            return retVel;
        }
        return currentVelocity;
        }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}