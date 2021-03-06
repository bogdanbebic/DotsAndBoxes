package etf.dotsandboxes.bb170011d;

import etf.dotsandboxes.bb170011d.engine.Game;
import etf.dotsandboxes.bb170011d.exceptions.InvalidBoardDimensionsException;
import etf.dotsandboxes.bb170011d.graphics.GameBoard;
import etf.dotsandboxes.bb170011d.graphics.GamePanel;
import etf.dotsandboxes.bb170011d.graphics.MainPanel;
import etf.dotsandboxes.bb170011d.player.AbstractPlayer;
import etf.dotsandboxes.bb170011d.player.Player;

import javax.swing.*;

/**
 * Represents the main class of the Dots&Boxes game.
 * Contains the singleton elements required for the game
 */
public class Main {
    public static int verticesInRow = 5;
    public static int verticesInCol = 5;

    public static AbstractPlayer player1 = new Player();
    public static AbstractPlayer player2 = new Player();

    public static GameBoard board;
    static {
        try {
            board = new GameBoard(verticesInRow, verticesInCol);
        } catch (InvalidBoardDimensionsException ignored) {}
    }

    public static Game game = new Game();

    public static GamePanel gamePanel = new GamePanel();
    public static MainPanel mainPanel = new MainPanel();

    public static JFrame frame = new JFrame("Dots&Boxes");

    public static void main(String ... args) {
        System.out.println("Hello, World!");
        try {
            ImageIcon icon = new ImageIcon("gold_sheep.png");
            frame.setIconImage(icon.getImage());
        } catch (Exception ignored) {}
        frame.setSize(600, 600);
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
