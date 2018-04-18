import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment gameE;
    private GUI gui;
    private static int screenWidth = 700;
    private static int screenLength = 700;

    public void addCollidable(Collidable c) {
        if (this.gameE == null) {
            this.gameE = new GameEnvironment();
        }
        this.gameE.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        if (this.sprites == null) {
            this.sprites = new SpriteCollection();
        }
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.gui = new GUI("Arkanoid", screenWidth, screenLength);
        this.gameE = new GameEnvironment();
        ///initialize Blocks
        Rectangle rec1 = new Rectangle(new Point(50,150),100,100);
        Rectangle rec2 = new Rectangle(new Point(200,150),100,100);
        Rectangle rec3 = new Rectangle(new Point(350,150),100,100);
        Rectangle rec4 = new Rectangle(new Point(500,150),100,100);
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
            blocks.addToGame(this);
        }

        ///initialize Border
        int blockSize = 25;
        Rectangle blockDownR = new Rectangle(new Point(0, screenLength - blockSize), screenWidth, blockSize, Color.GRAY);
        Rectangle blockRightR = new Rectangle(new Point(screenWidth - blockSize, 0), blockSize, screenLength, Color.GRAY);
        Rectangle blockLeftR = new Rectangle(new Point(0, 0), blockSize, screenLength, Color.GRAY);
        Rectangle blockUpR = (new Rectangle(new Point(0, 0), screenWidth, blockSize, Color.GRAY));
        Block blockDown = new Block(blockDownR);
        Block blockRight = new Block(blockRightR);
        Block blockLeft = new Block(blockLeftR);
        Block blockUp = new Block(blockUpR);
        Block[] borderBlocks = new Block[4];
        borderBlocks[0] = blockDown;
        borderBlocks[1] = blockRight;
        borderBlocks[2] = blockLeft;
        borderBlocks[3] = blockUp;
        for (Block borders : borderBlocks) {
            borders.addToGame(this);
        }

        ///initialize Paddel
        int paddelWidth = 200;
        int paddelLength = 20;
        Point startLoc = new Point(screenLength / 2 - paddelWidth / 2, screenWidth - blockSize - paddelLength);
        Rectangle rec = new Rectangle(startLoc, paddelWidth, paddelLength);
        Paddle paddle = new Paddle(rec, gui.getKeyboardSensor());
        paddle.addToGame(this);

        ///initialize Balls
        Ball[] ballsArr = new Ball[16];
        ballsArr[0] = new Ball(new Point(400,500),5, Color.red);
        ballsArr[1] = new Ball(new Point(450,400),5, Color.black);
        ballsArr[2] = new Ball(new Point(500,450),5, Color.gray);
        ballsArr[3] = new Ball(new Point(390,500),5, Color.green);
        ballsArr[4] = new Ball(new Point(430,500),5, Color.blue);
        ballsArr[5] = new Ball(new Point(250,500),5, Color.red);
        ballsArr[6] = new Ball(new Point(400,500),5, Color.red);
        ballsArr[7] = new Ball(new Point(550,500),5, Color.red);
        ballsArr[8] = new Ball(new Point(100,500),5, Color.green);
        ballsArr[9] = new Ball(new Point(250,500),5, Color.green);
        ballsArr[10] = new Ball(new Point(400,500),5, Color.green);
        ballsArr[11] = new Ball(new Point(550,500),5, Color.green);
        ballsArr[12] = new Ball(new Point(100,500),5, Color.green);
        ballsArr[13] = new Ball(new Point(250,500),5, Color.green);
        ballsArr[14] = new Ball(new Point(400,500),5, Color.green);
        ballsArr[15] = new Ball(new Point(550,500),5, Color.green);

        int i = 1; //Random
        int j = 1;
        for (Ball balls : ballsArr) {
            balls.setBallGE(gameE);
            balls.setVelocity(-1,10);
            i += 1;
            balls.setColor(Color.green);
            balls.setRadius(10);
            balls.addToGame(this);
        }
        ballsArr[0].setVelocity(10,-1);
        ballsArr[1].setVelocity(10,-1);
        ballsArr[2].setVelocity(10,-1);
    }

    // Run the game -- start the animation loop.
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface drawS = gui.getDrawSurface();
            this.sprites.drawAllOn(drawS);
            gui.show(drawS);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        /*Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawS = gui.getDrawSurface();
            sprites.drawAllOn(drawS);
            sprites.notifyAllTimePassed();
            gui.show(drawS);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }*/
    }
}