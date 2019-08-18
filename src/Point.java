public class Point {
    private double xPos;
    private double yPos;

    public Point(double x, double y) {
        xPos = x;
        yPos = y;
    }

    public double x() {
        return xPos;
    }

    public double y() {
        return yPos;
    }

    public static Point round(Point p) {
        return new Point(Math.floor(p.x()), Math.floor(p.y()));
    }
}
