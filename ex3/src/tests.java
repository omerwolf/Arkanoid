import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Arrays;
import java.util.Random;
import java.awt.Color;
import static java.lang.Double.max;
/**
 * A MultipleBouncingBallsAnimation class.
 *
 * @author Omer Wolf.
 */
public class tests {
    private static int width = 700;
    private static int length = 700;
    private GameEnvironment gameE;

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

        Random rand = new Random(); // create a random-number generator
        GameEnvironment gameE = new GameEnvironment();
        ///initialize Blocks
        Rectangle rec1 = new Rectangle(new Point(50,50),100,100);
        Rectangle rec2 = new Rectangle(new Point(200,50),100,100);
        Rectangle rec3 = new Rectangle(new Point(350,50),100,100);
        Rectangle rec4 = new Rectangle(new Point(500,50),100,100);
        Block b1 = new Block(rec1);
        Block b2 = new Block(rec2);
        Block b3 = new Block(rec3);
        Block b4 = new Block(rec4);
        Block[] blocksArr = new Block[4];
        blocksArr[0] = b1;
        blocksArr[1] = b2;
        blocksArr[2] = b3;
        blocksArr[3] = b4;
        for (Block blocks : blocksArr) {
            gameE.addCollidable(blocks);
       }

        ///initialize Border
        Block[] borderBlocks = new Block[4];
        int blockSize = 25;
        Rectangle blockDownR = new Rectangle(new Point(0, length - blockSize), width, blockSize, java.awt.Color.GRAY);
        Block blockDown = new Block(blockDownR);
        gameE.addCollidable(blockDown);
        Rectangle blockRightR = new Rectangle(new Point(width - blockSize, 0), blockSize, length, java.awt.Color.GRAY);
        Block blockRight = new Block(blockRightR);
        gameE.addCollidable(blockRight);
        Rectangle blockLeftR = new Rectangle(new Point(0, 0), blockSize, length, java.awt.Color.GRAY);
        Block blockLeft = new Block(blockLeftR);
        gameE.addCollidable(blockLeft);
        Rectangle blockUpR = (new Rectangle(new Point(0, 0), width, blockSize, java.awt.Color.GRAY));
        Block blockUp = new Block(blockUpR);
        gameE.addCollidable(blockUp);
        borderBlocks[0] = blockDown;
        borderBlocks[1] = blockRight;
        borderBlocks[2] = blockLeft;
        borderBlocks[3] = blockUp;

        ///initialize Balls
        Ball ball1 = new Ball(new Point(100,500),5, Color.BLACK);
        Ball ball2 = new Ball(new Point(250,500),5, Color.BLACK);
        Ball ball3 = new Ball(new Point(400,500),5, Color.BLACK);
        Ball ball4 = new Ball(new Point(550,500),5, Color.BLACK);
        Ball[] ballsArr = new Ball[4];
        ballsArr[0] = ball1;
        ballsArr[1] = ball2;
        ballsArr[2] = ball3;
        ballsArr[3] = ball4;
        int i = 20;
        for (Ball balls : ballsArr) {
            balls.setBallGE(gameE);
            balls.setVelocity(0 + i,-10);
            i++;
        }
        GUI gui = new GUI("MultipleBouncingBallsAnimation", width, length);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawS = gui.getDrawSurface();

            for (Block blocks : blocksArr) {
                blocks.drawOn(drawS);
            }
            for (Block borders : borderBlocks) {
                borders.drawOn(drawS);
            }
            for (Ball balls : ballsArr) {
                balls.drawOn(drawS);
                balls.moveOneStep();
            }
            gui.show(drawS);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
    public static Block[] initializeBorder(GameEnvironment ge) {
        Block[] retArr = new Block[4];
        int blockSize = 25;
        Rectangle blockDownR = new Rectangle(new Point(0, length - blockSize), width, blockSize, java.awt.Color.GRAY);
        Block blockDown = new Block(blockDownR);
        ge.addCollidable(blockDown);

        Rectangle blockRightR = new Rectangle(new Point(width - blockSize, 0), blockSize, length, java.awt.Color.GRAY);
        Block blockRight = new Block(blockRightR);
        ge.addCollidable(blockRight);

        Rectangle blockLeftR = new Rectangle(new Point(0, 0), blockSize, length, java.awt.Color.GRAY);
        Block blockLeft = new Block(blockLeftR);
        ge.addCollidable(blockLeft);

        Rectangle blockUpR = (new Rectangle(new Point(0, 0), width, blockSize, java.awt.Color.GRAY));
        Block blockUp = new Block(blockUpR);
        ge.addCollidable(blockUp);

        retArr[0] = blockDown;
        retArr[1] = blockRight;
        retArr[2] = blockLeft;
        retArr[3] = blockUp;
        return  retArr;
    }
}
