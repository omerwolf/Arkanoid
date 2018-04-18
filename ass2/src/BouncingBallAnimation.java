import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * A BouncingBallAnimation class.
 *
 * @author Omer Wolf.
 */
public class BouncingBallAnimation {
    /**
     * @param args command line arguments.
     *
     * the main function that creates an animation using the graphical package.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("BouncingBallAnimation", 400, 300);
        Sleeper sleeper = new Sleeper();
        Point screenSize = new Point(400, 300);
        Ball ball = new Ball(50, 50, 30, java.awt.Color.BLACK, screenSize);
        ball.setVelocity(5, 4);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
