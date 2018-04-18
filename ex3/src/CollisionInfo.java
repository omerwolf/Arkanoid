public class CollisionInfo {
    private Collidable collObj;
    private Point collPoint;

    public CollisionInfo(Collidable collObj1, Point collPoint1) {
        this.collObj = collObj1;
        this.collPoint = collPoint1;
    }
    // the point at which the collision occurs.
    public Point collisionPoint(){
        return this.collPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject(){
        return this.collObj;
    }
}