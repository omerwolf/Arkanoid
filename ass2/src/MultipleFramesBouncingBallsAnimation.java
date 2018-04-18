import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

import static java.lang.Double.max;
/**
 * A MultipleFramesBouncingBallsAnimation class.
 *
 * @author Omer Wolf.
 */
public class MultipleFramesBouncingBallsAnimation {
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
        Ball[] yellowBallsArr = new Ball[args.length / 2];
        Ball[] greyBallsArr = new Ball[args.length - args.length / 2];
        int yellowBallsArrLen = yellowBallsArr.length;
        int greyBallsArrLen = greyBallsArr.length;

        for (int i = 0; i < yellowBallsArrLen; ++i) {
            if (!isNum(args[i]) || Integer.parseInt(args[i])  <= 0) {
                throw new RuntimeException("Please enter positive int radius only!");
            }
            int r = Integer.parseInt(args[i]);
            int minPos = 450 +  r;
            int maxPos = 600 -  r;
            double x =  ((rand.nextInt(maxPos - minPos) + 1) + minPos);
            while (x > maxPos || x < minPos) {
                x =  ((rand.nextInt(maxPos - minPos) + 1) + minPos);
            }
            double y =  ((rand.nextInt(maxPos - minPos) + 1) + minPos);
            while (y > maxPos || y < minPos) {
                y =  ((rand.nextInt(maxPos - minPos) + 1) + minPos);
            }
            Point minP = new Point(minPos - r, minPos - r);
            Point maxP = new Point(maxPos + r, maxPos + r);
            Ball ball = new Ball(x, y, r, Color.BLACK, screenSize);
            ball.setMaxMove(maxP);
            ball.setMinMove(minP);
            double angle = 90 * rand.nextDouble();
            double speed = (max(55 - r, 5)) / 3;
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(velocity);
            yellowBallsArr[i] = ball;
        }
        for (int i = 0; i < greyBallsArrLen; ++i) {

            if (!isNum(args[i + yellowBallsArrLen]) || Integer.parseInt(args[i + yellowBallsArrLen])  <= 0) {
                throw new RuntimeException("Please enter positive int radius only!");
            }
            int r = Integer.parseInt(args[i + yellowBallsArrLen]);
            int minPos = 50 + r;
            int maxPos = 500 - r;
            double x =  ((rand.nextInt(maxPos - minPos) + 1) + minPos);
            while (x > maxPos || x < minPos) {
                x =  ((rand.nextInt(maxPos - minPos) + 1) + minPos);
            }
            double y =  ((rand.nextInt(maxPos - minPos) + 1) + minPos);
            while (y > maxPos || y < minPos) {
                y =  ((rand.nextInt(maxPos - minPos) + 1) + minPos);
            }
            Point minP = new Point(minPos - r, minPos - r);
            Point maxP = new Point(maxPos + r, maxPos + r);
            Ball ball = new Ball(x, y, r, Color.BLACK, screenSize);
            ball.setMaxMove(maxP);
            ball.setMinMove(minP);
            double angle = 90 * rand.nextDouble();
            double speed = (max(55 - r, 5)) / 3;
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(velocity);
            greyBallsArr[i] = ball;
        }
        GUI gui = new GUI("MultipleBouncingBallsAnimation", width, length);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawS = gui.getDrawSurface();
            drawS.setColor(Color.GRAY);
            drawS.fillRectangle(50, 50, 450, 450);
            for (Ball balls : greyBallsArr) {
                balls.moveOneStep();
                balls.drawOn(drawS);
            }
            drawS.setColor(Color.YELLOW);
            drawS.fillRectangle(450, 450, 150, 150);
            for (Ball balls : yellowBallsArr) {
                balls.moveOneStep();
                balls.drawOn(drawS);
            }
            gui.show(drawS);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }


}

