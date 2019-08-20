import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

public class BoardRenderer {

    private int dim;
    public static final int TILE_SIZE = 32;
    private static final Color BOARD_COLOR = new Color(222,184,135);
    private static final Color LINE_COLOR = new Color(139,69,19);

    /**
     * @param dim: dimension of the game board
     */
    public void initialize(int dim) {
        this.dim = dim;
        StdDraw.setCanvasSize(dim * TILE_SIZE, dim * TILE_SIZE);
        StdDraw.setXscale(0, dim);
        StdDraw.setYscale(0, dim);
        StdDraw.clear(BOARD_COLOR);
        StdDraw.setPenColor(LINE_COLOR);
        // Need to draw vertical and horizontal lines
        for (int i = 0; i < dim; i += 1) {
            StdDraw.line(i, dim, i, 0);
        }
        for (int j = 0; j < dim; j += 1) {
            StdDraw.line(0, j, dim, j);
        }
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    /**
     * Place a stone on the board.
     * @param p Point to place the stone on the board
     * @param col Color for the stone
     */
    public void printStone(Color col, Point p) {
        StdDraw.setPenColor(col);
        StdDraw.filledCircle(p.x() + .5, p.y() + .5, .4);
        StdDraw.show();
    }

}
