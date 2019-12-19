package etf.dotsandboxes.bb170011d.engine;

import etf.dotsandboxes.bb170011d.exceptions.InvalidBoardDimensionsException;

public class GameBoard {
    private GameBoardObject[][]board = new GameBoardObject[5][5];

    public GameBoard(int numberOfVerticesInRow, int numberOfVerticesInColumn) throws InvalidBoardDimensionsException {
        if (numberOfVerticesInRow <= 0 || numberOfVerticesInColumn <= 0)
            throw new InvalidBoardDimensionsException();
        this.initializeGameBoard(2 * numberOfVerticesInRow - 1, 2 * numberOfVerticesInColumn - 1);
    }

    private void initializeGameBoard(int numberOfRows, int numberOfColumns) {
        this.board = new GameBoardObject[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (i % 2 == 0 && j % 2 == 0)
                    this.board[i][j] = new Vertex();
                else if (i % 2 == 1 && j % 2 == 1)
                    this.board[i][j] = new PlayerNode();
                else
                    this.board[i][j] = new Edge();
            }
        }
    }

}
