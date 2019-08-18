import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

public class Engine {
    public BoardRenderer board;
    private int dim;
    private int turn;
    public Color[] colors = new Color[] {Color.BLACK, Color.WHITE};

    public Engine(int dimension) {
        board = new BoardRenderer();
        dim = dimension;
        turn = 0;
    }

    private static Point getClickPoint() {
        while (true) {
            if (StdDraw.isMousePressed()) {
                return new Point(StdDraw.mouseX(), StdDraw.mouseY());
            }
        }
    }


    private void doTurn() {
        Point choice = getClickPoint();
        choice = Point.round(choice);
        Color currColor = colors[turn % 2];
        StdDraw.setPenColor(currColor);
        StdDraw.filledCircle(choice.x() + .5, choice.y() + .5, .5);
        StdDraw.show();
        try {
            Thread.sleep(500);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void play() {
        board.initialize(dim);
        while (true) {
            doTurn();
            turn += 1;
        }
    }
}
