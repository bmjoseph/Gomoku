public class Point {
    private int xPos;
    private int yPos;

    public Point(int x, int y) {
        xPos = x;
        yPos = y;
    }

    /**
     * Alternative constructor that makes the point from the floor of the inputs.
     * Used for finding the coordinates from mouse clicks
     * @param x: X position of the point
     * @param y: Y position of the point
     */
    public Point(double x, double y) {
        xPos = (int) Math.floor(x);
        yPos = (int) Math.floor(y);
    }

    public int x() {
        return xPos;
    }

    public int y() {
        return yPos;
    }

    /**
     * @param p Point to check
     * @param dim Dimension of the game board
     * @return True if the point is on the board
     */
    public static boolean inBoardBounds(Point p, int dim) {
        boolean xOK = p.x() >= 0 && p.x() < dim;
        boolean yOK = p.y() >= 0 && p.y() < dim;
        return xOK && yOK;
    }

}
