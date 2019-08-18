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

    private void doTurn() {
        if (StdDraw.isMousePressed()) {
            double mouseX = StdDraw.mouseX();
            double mouseY = StdDraw.mouseY();
            Color currColor = colors[turn % 2];
            System.out.print(mouseX);
            System.out.print(",");
            System.out.println(mouseY);
            //StdDraw.filledCircle();
            try {
                Thread.sleep(1000);      
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void play() {
        board.initialize(dim);
        while (true) {
            doTurn();
        }
    }
}
