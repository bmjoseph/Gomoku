public class Engine {
    public BoardRenderer board;
    private int dim;

    public Engine(int dimension) {
        board = new BoardRenderer();
        dim = dimension;
    }

    public void play() {
        board.initialize(dim);
    }
}
