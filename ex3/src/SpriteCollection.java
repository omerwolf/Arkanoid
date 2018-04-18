import biuoop.DrawSurface;

import java.util.LinkedList;

public class SpriteCollection {
    private LinkedList<Sprite> spriteList;

    public SpriteCollection() {
        this.spriteList = new LinkedList<Sprite>();
    }
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }
    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
}