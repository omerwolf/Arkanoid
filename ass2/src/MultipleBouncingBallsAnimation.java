import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;
import static java.lang.Double.max;
/**
 * A MultipleBouncingBallsAnimation class.
 *
 * @author Omer Wolf.
 */
public class MultipleBouncingBallsAnimation {
    private static int width = 700;
    private static int length = 700;

    /**
     * @param str string from command line arguments.
     *
     * @return check if the args is number
     */
    public static boolean isNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
        /**
     * @param args command line arguments.
     *
     * the main function that creates an animation using the graphical package
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Please enter ball size!");
        }
        Random rand = new Random(); // create a random-number generator
        Point screenSize = new Point(width, length);
        Ball[] ballsArr = new Ball[args.length];
        int ballsArrLen = ballsArr.length;
        for (int i = 0; i < ballsArrLen; ++i) {
            if (!isNum(args[i]) || Integer.parseInt(args[i])  <= 0) {
                throw new RuntimeException("Please enter positive int radius only!");
            }
            int r = Integer.parseInt(args[i]);
            double x =  max((rand.nextInt(width) + 1 - r), r);
            double y = max((rand.nextInt(length) + 1 - r), r);
            Ball ball = new Ball(x, y, r, Color.BLACK, screenSize);
            double angle = 90 * rand.nextDouble();
            double speed = max(55 - r, 5);
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(velocity);
            ball.setMinMove(new Point(0, 0));
            ball.setMaxMove(new Point((double) width, (double) length));
            ballsArr[i] = ball;
        }
        GUI gui = new GUI("MultipleBouncingBallsAnimation", width, length);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawS = gui.getDrawSurface();
            for (Ball balls : ballsArr) {
                balls.drawOn(drawS);
                balls.moveOneStep();
            }
            gui.show(drawS);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}
