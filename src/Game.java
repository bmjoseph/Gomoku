public class Game {

    public int dim;
    public Boolean[][] blackTiles;
    public Boolean[][] whiteTiles;
    public BoardRenderer board;
    public boolean gameOver = false;

    public Game(int dimension) {
        dim = dimension;
        board = new BoardRenderer();
        blackTiles = new Boolean[dim][dim];
        whiteTiles = new Boolean[dim][dim];

        // Initially fill the arrays with false
        for (int i = 0; i < dim; i += 1) {
            for (int j = 0; j < dim; j += 1) {
                blackTiles[i][j] = false;
                whiteTiles[i][j] = false;
            }
        }
    }

    public void setUpBoard() {
        board.initialize(dim);
    }

}
