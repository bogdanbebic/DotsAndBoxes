package etf.dotsandboxes.bb170011d.engine;

import etf.dotsandboxes.bb170011d.graphics.GameBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a logical state of the board
 */
public class GameBoardState {
    private boolean [][]edges;
    private int boardRowCount;
    private int boardColCount;

    private int pointsPlayer1 = 0;
    private int pointsPlayer2 = 0;

    GameBoardState(GameBoard gameBoard) {
        this.boardRowCount = gameBoard.getRowCount();
        this.boardColCount = gameBoard.getColumnCount();

        this.edges = new boolean[boardRowCount][boardColCount];

        for (int i = 0; i < boardRowCount; i++)
            for (int j = 0; j < boardColCount; j++)
                this.edges[i][j] = gameBoard.isFilled(i, j);
    }

    /**
     * @return Heuristic function for the current game state
     */
    public int evaluate() {
        return 0;
    }

    /**
     * @param i row index of the board
     * @param j column index of the board
     * @return boolean representing if board[i][j] is filled
     */
    public boolean isFilled(int i, int j) {
        return this.edges[i][j];
    }

    /**
     * @param i row index of the board
     * @param j column index of the board
     * @param filled boolean representing if board[i][j] is filled
     */
    public void setFilled(int i, int j, boolean filled) {
        this.edges[i][j] = filled;
    }

    /**
     * @param i row index of the board
     * @param j column index of the board
     * @return boolean representing if the component on the indices is an Edge
     */
    private boolean isRealEdge(int i, int j) {
        return (i + j) % 2 == 1;
    }

    /**
     * Should only be called for player node indices
     * @param row row index of the board
     * @param column column index of the board
     * @return boolean representing if all adjacent edges except one are filled
     */
    private boolean areAdjacentFilled(int row, int column) {
        int adjacentFilled = 0;
        final int maxAdjacentFilled = 4;
        if (this.isFilled(row - 1, column))
            adjacentFilled++;
        if (this.isFilled(row + 1, column))
            adjacentFilled++;
        if (this.isFilled(row, column - 1))
            adjacentFilled++;
        if (this.isFilled(row, column + 1))
            adjacentFilled++;

        return adjacentFilled == maxAdjacentFilled - 1;
    }

    /**
     * @param i row index of the board
     * @param j column index of the board
     * @return boolean representing if move given by i and j is a move which results in points
     */
    public boolean isPointsMove(int i, int j) {
        boolean ret = false;
        if (i % 2 == 0) {
            // horizontal
            if (i > 0)
                ret = this.areAdjacentFilled(i - 1, j);
            if (i < this.boardRowCount - 1)
                ret |= this.areAdjacentFilled(i + 1, j);
        } else {
            // vertical
            if (j > 0)
                ret = this.areAdjacentFilled(i, j - 1);
            if (j < this.boardColCount - 1)
                ret |= this.areAdjacentFilled(i, j + 1);
        }

        return ret;
    }

    /**
     * @return optional possibly containing a points move if such exists on the curent game board state
     */
    public Optional<int []> getPointsMove() {
        return this.getPossibleMoves()
                .stream()
                .filter(move -> GameBoardState.this.isPointsMove(move[0], move[1]))
                .findAny();
    }

    /**
     * @return List of all possible moves for the current game board state
     */
    public List<int []> getPossibleMoves() {
        List<int []> possibleMoves = new ArrayList<>();
        for (int i = 0; i < boardRowCount; i++) {
            for (int j = 0; j < boardColCount; j++) {
                if (!this.isFilled(i, j) && this.isRealEdge(i, j)) {
                    possibleMoves.add(new int[]{i, j});
                }
            }
        }

        return possibleMoves;
    }

}
