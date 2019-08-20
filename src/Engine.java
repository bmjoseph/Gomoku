import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;

public class Engine {

    private int dim;
    private int turn;
    public Color[] colors = new Color[] {Color.BLACK, Color.WHITE};
    public Game game;


    /**
     * Initialize a new instance of the game.
     * @param dimension Number of desired squares on each side of the board.
     */
    public Engine(int dimension) {
        dim = dimension;
        turn = 0;
        game = new Game(dimension);
    }


    /**
     * Get the color for the current player of the game
     */
    private Color getColor() {
        return colors[turn % 2];
    }

    private Color getWaitingColor() {
        return colors[(turn + 1) % 2];
    }

    /**
     * Book keep the game state internally
     * @param p Point to place the stone in the arrays
     */
    private void placeStone(Game game, Point p) {
        if (getColor().equals(Color.BLACK)) {
            game.blackTiles[p.x()][p.y()] = true;
        } else {
            game.whiteTiles[p.x()][p.y()] = true;
        }
    }

    /**
     * Place a stone on the board.
     * @param p Point to place the stone on the board
     * @param game Game to place the stone into
     */
    private void drawStone(Game game, Point p) {
        Color currCol = getColor();
        game.board.printStone(currCol, p);
    }

    /**
     * Decide who just won the game and display a win message.
     */
    private void showWinScreen() {
        Color winnerColor = getColor();
        StdDraw.setPenColor(winnerColor);
        String winner = "Black";
        if (winnerColor.equals(Color.WHITE)) {
            winner = "White";
        }
        Font font = new Font("Monaco", Font.BOLD, BoardRenderer.TILE_SIZE - 4);
        StdDraw.setFont(font);
        StdDraw.text(dim / 2, dim - .66, "Game Over: " + winner + " wins!");
        StdDraw.show();
        }

    /**
     * Handles the logic of a single turn.
     * Elicits a valid move, draws it on the board, and checks if the game is over.
     * @param game The game being played.
     */
    private void doTurn(Game game) {
        Point choice = elicitMove(game);
        drawStone(game, choice);
        placeStone(game, choice);
        if (checkWin(game, choice)) {
            showWinScreen();
            game.gameOver = true;
        }
        turn += 1;
    }

    /**
     * Set up a new game and wait for moves.
     */
    public void play() {
        game = new Game(dim);
        game.setUpBoard();
        while (!game.gameOver) {
            doTurn(game);
            try {
                Thread.sleep(500);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * @return The location of a mouse click on the game board.
     */
    private static Point elicitMove(Game game) {
        while (true) {
            Point attemptedMove = getClickPoint();
            if (isValidMove(game, attemptedMove)) {
                return attemptedMove;
            }
        }
    }

    /**
     * Check if the most recent move caused game to be won.
     * @param game The current game.
     * @param move The point of the most recent move.
     * @return True if the game has been won.
     */
    private boolean checkWin(Game game, Point move) {
        Boolean[][] currTileset = game.whiteTiles;
        if (turn % 2 == 0) {
            currTileset = game.blackTiles;
        }
        if (checkHorizWin(currTileset, move)) {
            return true;
        } else if (checkVerticalWin(currTileset, move)) {
            return true;
        } else if (checkDiagonalWin(currTileset, move, 1) ||
                checkDiagonalWin(currTileset, move, -1)) {
            return true;
        }

        return false;

    }

    /**
     * Decide if the tile just placed results in 5 in a row horizontally.
     * @param tileSet The current player's owned tile array.
     * @param move The most recent move position.
     */
    private boolean checkHorizWin(Boolean[][] tileSet, Point move) {
        // Start either at 0 or 4 to the left of move
        int farthestLeft = Math.max(0, move.x() - 4);
        int farthestRight = Math.min(dim - 1, move.x() + 4);
        int conseq = 0;
        for (int i = farthestLeft; i <= farthestRight; i += 1) {
            if (tileSet[i][move.y()]) {
                conseq += 1;
                if (conseq == 5) {
                    return true;
                }
            } else {
                conseq = 0;
            }
        }
        return false;
    }

    /**
     * Decide if the tile just placed results in 5 in a row horizontally.
     * @param tileSet The current player's owned tile array.
     * @param move The most recent move position.
     */
    private boolean checkVerticalWin(Boolean[][] tileSet, Point move) {
        // Start either at 0 or 4 below the move
        int farthestDown = Math.max(0, move.y() - 4);
        int farthestUp = Math.min(dim - 1, move.y() + 4);
        int conseq = 0;
        for (int i = farthestDown; i <= farthestUp; i += 1) {
            if (tileSet[move.x()][i]) {
                conseq += 1;
                if (conseq == 5) {
                    return true;
                }
            } else {
                conseq = 0;
            }
        }
        return false;
    }

    /**
     * Decide if the tile just placed results in 5 in a row diagonally.
     * @param tileSet The current player's owned tile array.
     * @param move The most recent move position.
     * @param direc 1 to go from upper left to bottom right or -1 to go from upper right to bottom left.
     * @return True if the game has been won.
     */
    private boolean checkDiagonalWin(Boolean[][] tileSet, Point move, int direc) {
        // Check upper left to bottom right direction
        int currOffset = -4 * direc;
        int conseq = 0;
        for (int i = 0; i < 9; i += 1) { // Need to check 9 total tiles
            Point testPoint = new Point(move.x() + currOffset, move.y() - (direc * currOffset));
            // If the point can be checked, check it as usual.
            if (Point.inBoardBounds(testPoint, dim)) {
                if (tileSet[testPoint.x()][testPoint.y()]) {
                    conseq += 1;
                    if (conseq == 5) {
                        return true;
                    }
                }
            } else {
                conseq = 0;
            }
            currOffset += direc;
        }
        return false;
    }


    /**
     * Decide whether an attempted move is legal.
     *  A legal move is one that is in the bounds of the game board and not already taken.
     * @param p Point to try to move
     * @return True if the move is legal.
     */
    private static boolean isValidMove(Game game, Point p) {
        boolean stillValid = true;
        // Check if the move in bounds
        if (p.x() >= (game.dim) | p.y() >= (game.dim)) {
            stillValid = false;
        }
        // Check if the space is taken by black
        else if (game.blackTiles[p.x()][p.y()]) {
            stillValid = false;
        }
        // Check if the space is taken by white
        else if (game.whiteTiles[p.x()][p.y()]) {
            stillValid = false;
        }
        return stillValid;
    }

    /**
     * @return The location of a mouse click on the game board.
     */
    private static Point getClickPoint() {
        while (true) {
            if (StdDraw.isMousePressed()) {
                return new Point(StdDraw.mouseX(), StdDraw.mouseY());
            }
        }
    }



}
