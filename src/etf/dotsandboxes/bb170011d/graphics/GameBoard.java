package etf.dotsandboxes.bb170011d.graphics;


import etf.dotsandboxes.bb170011d.engine.GameBoardObject;
import etf.dotsandboxes.bb170011d.exceptions.InvalidBoardDimensionsException;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private static final int DEFAULT_BOARD_DIMENSIONS = 5;
    private GameBoardObject[][]board = new GameBoardObject[DEFAULT_BOARD_DIMENSIONS][DEFAULT_BOARD_DIMENSIONS];

    public GameBoard(int numberOfVerticesInRow, int numberOfVerticesInColumn) throws InvalidBoardDimensionsException {
        if (numberOfVerticesInRow <= 0 || numberOfVerticesInColumn <= 0)
            throw new InvalidBoardDimensionsException();
        this.initializeGameBoard(2 * numberOfVerticesInRow - 1, 2 * numberOfVerticesInColumn - 1);
    }

    private void initializeGameBoard(int numberOfRows, int numberOfColumns) {
        this.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        this.board = new GameBoardObject[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (i % 2 == 0 && j % 2 == 0)
                    this.board[i][j] = new Vertex();
                else if (i % 2 == 1 && j % 2 == 1)
                    this.board[i][j] = new PlayerNode();
                else {
                    this.board[i][j] = new Edge();
                    if (i % 2 == 1)
                        ((Edge)this.board[i][j]).setOrientation(Edge.Orientation.VERTICAL);
                    else
                        ((Edge)this.board[i][j]).setOrientation(Edge.Orientation.HORIZONTAL);
                }

                if (this.board[i][j] instanceof JComponent)
                    this.add((JComponent)this.board[i][j]);
            }
        }
    }

    public static void main(String ... args) throws InvalidBoardDimensionsException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.add(new GameBoard(5, 8));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
