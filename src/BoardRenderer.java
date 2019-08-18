import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

public class BoardRenderer {

    private int dim;
    private static final int TILE_SIZE = 32;

    /**
     * @param dim: dimension of the game board
     */
    public void initialize(int dim) {
        this.dim = dim;
        StdDraw.setCanvasSize(dim * TILE_SIZE, dim * TILE_SIZE);
        StdDraw.setXscale(0, dim);
        StdDraw.setYscale(0, dim);
        StdDraw.clear(new Color(255, 255, 255));
        StdDraw.setPenColor(Color.BLACK);
        // Need to draw vertical and horizontal lines
        for (int i = 0; i < dim; i += 1) {
            StdDraw.line(i, dim, i, 0);
        }
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }
}
