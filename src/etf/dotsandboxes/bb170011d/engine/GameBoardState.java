package etf.dotsandboxes.bb170011d.engine;

import etf.dotsandboxes.bb170011d.graphics.GameBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameBoardState {
    private boolean [][]edges;
    private int boardRowCount;
    private int boardColCount;

    public GameBoardState(GameBoard gameBoard) {
        this.boardRowCount = gameBoard.getRowCount();
        this.boardColCount = gameBoard.getColumnCount();

        this.edges = new boolean[boardRowCount][boardColCount];

        for (int i = 0; i < boardRowCount; i++)
            for (int j = 0; j < boardColCount; j++)
                this.edges[i][j] = gameBoard.isFilled(i, j);
    }

    public boolean isFilled(int i, int j) {
        return this.edges[i][j];
    }

    public void setFilled(int i, int j, boolean filled) {
        this.edges[i][j] = filled;
    }

    private boolean isRealEdge(int i, int j) {
        return (i + j) % 2 == 1;
    }

    private boolean isPointsMove(int i, int j) {
        // TODO: implement
        return true;
    }

    public Optional<int []> getPointsMove() {
        return this.getPossibleMoves()
                .stream()
                .filter(move -> GameBoardState.this.isPointsMove(move[0], move[1]))
                .findAny();
    }

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
