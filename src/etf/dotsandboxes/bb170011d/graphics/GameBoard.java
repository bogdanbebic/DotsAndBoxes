package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.Main;
import etf.dotsandboxes.bb170011d.exceptions.InvalidBoardDimensionsException;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private GameBoardObject [][] gameBoardObjects;

    public int getRowCount() {
        return this.gameBoardObjects.length;
    }

    public int getColumnCount() {
        return this.gameBoardObjects[0].length;
    }

    public int getNumberOfPlayerNodes() {
        int numberOfPlayerNodes = 0;
        for (GameBoardObject[] gameBoardObject : this.gameBoardObjects)
            for (int j = 0; j < this.gameBoardObjects[0].length; j++)
                if (gameBoardObject[j] instanceof PlayerNode)
                    numberOfPlayerNodes++;

        return numberOfPlayerNodes;
    }

    private boolean areAdjacentFilled(int row, int column) {
        int adjacentFilled = 0;
        final int maxAdjacentFilled = 4;
        if (this.gameBoardObjects[row - 1][column].isFilled())
            adjacentFilled++;
        if (this.gameBoardObjects[row + 1][column].isFilled())
            adjacentFilled++;
        if (this.gameBoardObjects[row][column - 1].isFilled())
            adjacentFilled++;
        if (this.gameBoardObjects[row][column + 1].isFilled())
            adjacentFilled++;

        return adjacentFilled == maxAdjacentFilled;
    }

    public int setFilled(int row, int column, boolean filled) {
        this.gameBoardObjects[row][column].setFilled(filled);
        int ret = 0;
        if (row % 2 == 0) {
            // horizontal
            if (row > 0) {
                if (this.areAdjacentFilled(row - 1, column)) {
                    ret++;
                    PlayerNode node = ((PlayerNode)this.gameBoardObjects[row - 1][column]);
                    node.setPlayerColor(Main.game.getActiveColor());
                    node.setFilled(true);
                }
            }
            if (row < this.gameBoardObjects.length - 1) {
                if (this.areAdjacentFilled(row + 1, column)) {
                    ret++;
                    PlayerNode node = ((PlayerNode)this.gameBoardObjects[row + 1][column]);
                    node.setPlayerColor(Main.game.getActiveColor());
                    node.setFilled(true);
                }
            }
        } else {
            // vertical
            if (column > 0) {
                if (this.areAdjacentFilled(row, column - 1)) {
                    ret++;
                    PlayerNode node = ((PlayerNode)this.gameBoardObjects[row][column - 1]);
                    node.setPlayerColor(Main.game.getActiveColor());
                    node.setFilled(true);
                }
            }
            if (column < this.gameBoardObjects[0].length - 1) {
                if (this.areAdjacentFilled(row, column + 1)) {
                    ret++;
                    PlayerNode node = ((PlayerNode)this.gameBoardObjects[row][column + 1]);
                    node.setPlayerColor(Main.game.getActiveColor());
                    node.setFilled(true);
                }
            }
        }

        return ret;
    }

    public void setColor(int row, int column, Color color) {
        GameBoardObject gameBoardObject = this.gameBoardObjects[row][column];
        if (gameBoardObject instanceof Edge) {
            ((Edge)gameBoardObject).setPlayerColor(color);
        } else if (gameBoardObject instanceof PlayerNode) {
            ((PlayerNode)gameBoardObject).setPlayerColor(color);
        }
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
        this.removeAll();
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
