import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;
/**
 * AbstractArtDrawing class.
 *
 * @author Omer Wolf.
 */
public class AbstractArtDrawing {
    private final int radius = 3;
    private final int width = 800;
    private final int length = 600;
    private final int numOfLines = 10;
    private final Line[] lineArr;
    private final GUI gui;
    private final DrawSurface drawS;
  /**
    * AbstractArtDrawing constractor.
    *
    *
    **/
    public AbstractArtDrawing() {
        this.lineArr = new Line[10];
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        this.gui = new GUI("Random Lines", width, length);
        this.drawS = gui.getDrawSurface();
    }
   /**
    * this function create a new line by getting two random points.
    *
    *
    **/
    public void drawLines() {
        Random rand = new Random(); // create a random-number generator
        for (int i = 0; i < numOfLines; ++i) {
            int x1 = rand.nextInt(width) + 1; // get double in range 1-400
            int y1 = rand.nextInt(length) + 1; // get double in range 1-300
            int x2 = rand.nextInt(width) + 1; // get double in range 1-400
            int y2 = rand.nextInt(length) + 1; // get double in range 1-300
            lineArr[i] = new Line(x1, y1, x2, y2);
            drawS.setColor(Color.BLACK);
            drawS.drawLine(x1, y1, x2, y2);
        }
    }
   /**.
    * this function draw blue(middle) point for each line
    *
    *
    **/
    public void drawBluePoints() {
        for (int i = 0; i < numOfLines; ++i) {
            Point midP = lineArr[i].middle();
            int x = (int) midP.getX();
            int y = (int) midP.getY();
            drawS.setColor(Color.BLUE);
            drawS.fillCircle(x, y, radius);
        }
    }
    /**.
    * this function draw red(intersecting) point for each line
    *
    *
    **/
    public void drawRedPoint() {
        for (int i = 0; i < numOfLines; ++i) {
            for (int j = i + 1; j < numOfLines; ++j) {
                if (lineArr[i].isIntersecting(lineArr[j])) {
                    Point intersectP = lineArr[i].intersectionWith(lineArr[j]);
                    int x = (int) intersectP.getX();
                    int y = (int) intersectP.getY();
                    drawS.setColor(Color.RED);
                    drawS.fillCircle(x, y, radius);
                }
            }
        }
    }
     /**.
    * gui getter
    *
    *@return gui
    **/
    public GUI getGui() {
        return gui;
    }
     /**.
    * DrawSurface getter
    *
    *@return drawS
    **/
    public DrawSurface getDrawSurface() {
        return drawS;
    }
     /**
    * @param args command line arguments.
    *
    *the main function that creates a new AbstractArtDrawing class.
    */
    public static void main(String[] args) {
            AbstractArtDrawing artD = new AbstractArtDrawing();
            artD.drawLines();
            artD.drawBluePoints();
            artD.drawRedPoint();
            artD.getGui().show(artD.getDrawSurface());
    }

}



