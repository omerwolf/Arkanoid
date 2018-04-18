import biuoop.DrawSurface;

import java.awt.*;

public class Block implements Collidable, Sprite{
    private Rectangle shape;
    private int hitsCount;

    public  Block(Rectangle rec){
        this.shape = rec;
        this.hitsCount = 2;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    /**
     * @param currentVelocity - the current velocity.
     * @param collisionPoint - the collision point.
     * @return the new velocity depend on the location of the collision point.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        this.decreaseHitsCount();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Line upperLine = this.shape.getUpperLine();
        Line lowerLine = this.shape.getLowerLine();
        Line leftLine = this.shape.getLeftLine();
        Line rightLine = this.shape.getRightLine();
        if ( upperLine.pointOnLine(collisionPoint,null) || lowerLine.pointOnLine(collisionPoint,null)) {
            Velocity retVel = new Velocity(dx, -dy);
            return retVel;
        }
        if (leftLine.pointOnLine(collisionPoint,null) || rightLine.pointOnLine(collisionPoint, null)) {
            Velocity retVel = new Velocity(-dx, dy);
            return retVel;
        }
        return currentVelocity;
    }
    /**
     * @param drawS the given DrawSurface to draw the ball on it
     */
    public void drawOn(DrawSurface drawS) {
        drawS.setColor(this.shape.getColor());
        drawS.fillRectangle((int) this.shape.getUpperLeft().getX(),(int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        int midX = (int) (this.shape.getUpperLeft().getX() + this.shape.getWidth() / 2);
        int midY = (int) (this.shape.getUpperLeft().getY() + this.shape.getHeight() / 2);
        String hitsNum;
        int sizeOfText = 20;
        if (this.hitsCount < 1) {
            hitsNum = "X";
        } else {
            hitsNum = String.valueOf(this.hitsCount);
        }
        drawS.setColor(Color.WHITE);
        drawS.drawText(midX, midY, hitsNum, sizeOfText);
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    public void decreaseHitsCount() {
        this.hitsCount--;
    }
}
