import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;


public class BallsTest1 {
    public static void main(String[] args) {
        GUI gui = new GUI("title",200,200);
        Sleeper sleeper = new Sleeper();
        Point screenSize = new Point(200,200);
        Ball ball = new Ball(0, 0, 30, java.awt.Color.BLACK,screenSize);
        ball.setVelocity(2, 2);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}