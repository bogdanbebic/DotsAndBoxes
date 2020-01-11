package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.exceptions.InvalidBoardDimensionsException;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private GameBoardObject [][] gameBoardObjects;

    public void setFilled(int row, int column, boolean filled) {
        this.gameBoardObjects[row][column].setFilled(filled);
    }

    public boolean isFilled(int row, int column) {
        return this.gameBoardObjects[row][column].isFilled();
    }

    public GameBoard(int numberOfVerticesInRow, int numberOfVerticesInColumn) throws InvalidBoardDimensionsException {
        if (numberOfVerticesInRow <= 0 || numberOfVerticesInColumn <= 0)
            throw new InvalidBoardDimensionsException();
        this.gameBoardObjects = new GameBoardObject[2 * numberOfVerticesInRow - 1][2 * numberOfVerticesInColumn - 1];
        this.initializeGameBoard(2 * numberOfVerticesInRow - 1, 2 * numberOfVerticesInColumn - 1);
    }

    private void initializeGameBoard(int numberOfRows, int numberOfColumns) {
        this.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    Vertex vertex = new Vertex();
                    this.gameBoardObjects[i][j] = vertex;
                    this.add(vertex);
                } else if (i % 2 == 1 && j % 2 == 1) {
                    PlayerNode playerNode = new PlayerNode();
                    this.gameBoardObjects[i][j] = playerNode;
                    this.add(playerNode);
                } else {
                    Edge edge = new Edge();
                    edge.setRowAndColumn(i, j);
                    if (i % 2 == 1)
                        edge.setOrientation(Edge.Orientation.VERTICAL);
                    else
                        edge.setOrientation(Edge.Orientation.HORIZONTAL);

                    this.gameBoardObjects[i][j] = edge;
                    this.add(edge);
                }
            }
        }
    }

    /**
     * @param i Rows index of the board matrix
     * @param j Cols index of the board matrix
     * @return String representing one move on the board
     */
    public static String getStringFromIndices(int i, int j) {
        String ret = "";
        char retChar = (char)(j / 2 + 'A' - (i % 2 == 0 || j % 2 == 0 ? 0 : 1));
        int retInt = i / 2 + j % 2 - (i % 2 == 0 ? 1 : 0);
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


    public static void main(String ... args) throws InvalidBoardDimensionsException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.add(new GameBoard(5, 8));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
