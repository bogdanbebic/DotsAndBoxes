package etf.dotsandboxes.bb170011d.engine;

import etf.dotsandboxes.bb170011d.exceptions.InvalidBoardDimensionsException;

public class GameBoard {

    /**
     * @param i Rows index of the board matrix
     * @param j Cols index of the board matrix
     * @return String representing one move on the board
     */
    public static String getStringFromIndices(int i, int j) {
        String ret = "";
        char retChar = (char)(j / 2 + 'A' - (j % 2 == 0 ? 0 : 1));
        int retInt = i / 2 + j % 2;
        if (i % 2 == 0) {
            ret += retInt;
            ret += retChar;
        } else {
            ret += retChar;
            ret += retInt;
        }

        return ret;
    }

    /**
     * @param move The string (length 2) representing one move on the board
     * @return int representing the rows index in the board matrix
     */
    public static int getRowsIndexFromString(String move) {
        if (Character.isDigit(move.charAt(0)))
            return (move.charAt(0) - '0') * 2;

        return (move.charAt(1) - '0') * 2 + 1;
    }

    /**
     * @param move The string (length 2) representing one move on the board
     * @return int representing the cols index in the board matrix
     */
    public static int getColsIndexFromString(String move) {
        if (Character.isDigit(move.charAt(0)))
            return (move.charAt(1) - 'A') * 2 + 1;

        return (move.charAt(0) - 'A') * 2;
    }

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

    public static void main(String ... args) {
        int i = 7;
        int j = 4;
        System.out.println(getStringFromIndices(i, j));
        System.out.println(getRowsIndexFromString(getStringFromIndices(i, j)));
        System.out.println(getColsIndexFromString(getStringFromIndices(i, j)));

    }

}
