package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.Main;
import etf.dotsandboxes.bb170011d.engine.Game;
import etf.dotsandboxes.bb170011d.exceptions.InvalidBoardDimensionsException;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a panel for the game
 * Contains the relevant information for the current game and the GameBoard
 */
public class GamePanel extends JPanel {
    private static final String textPlayer1 = "Player1";
    private static final String textPlayer2 = "Player2";

    private static final String winMsg = "wins";
    private static final String loseMsg = "loses";
    private static final String drawMsg = "draws";

    private int points1 = 0;
    private int points2 = 0;

    private JPanel gameInfoPanel = new JPanel(new FlowLayout());
    private JLabel labelPlayer1 = new JLabel(textPlayer1 + ": " + this.points1);
    private JLabel labelPlayer2 = new JLabel(textPlayer2 + ": " + this.points2);
    private JLabel labelActivePlayer = new JLabel(textPlayer1);
    private JButton menuButton = new JButton("back to menu");
    private JButton saveGame = new JButton("save game");

    /**
     * Toggles the active player representation on the panel
     */
    public void toggleActivePlayer() {
        if (this.labelActivePlayer.getText().equals(textPlayer1))
            this.labelActivePlayer.setText(textPlayer2);
        else
            this.labelActivePlayer.setText(textPlayer1);
    }

    {
        this.menuButton.addActionListener(l -> {
            this.points1 = 0;
            this.points2 = 0;
            this.labelPlayer1.setText(textPlayer1 + ": " + this.points1);
            this.labelPlayer2.setText(textPlayer2 + ": " + this.points2);
            try {
                Main.board = new GameBoard(Main.verticesInRow, Main.verticesInCol);
            } catch (InvalidBoardDimensionsException ignored) {}
            this.removeAll();
            this.add(gameInfoPanel, BorderLayout.NORTH);
            this.add(Main.board, BorderLayout.CENTER);
            Main.game = new Game();
            Main.mainPanel.setActivePanel(MainPanel.MENU_PANEL);
        });
    }

    {
        this.saveGame.addActionListener(l -> {
            // TODO: maybe change to JFileChooser
            String filepath = JOptionPane.showInputDialog(Main.frame, "Filepath?");
            Main.game.save(filepath);
        });
    }

    {
        this.labelPlayer1.setForeground(Main.game.getColor1());
        this.labelPlayer2.setForeground(Main.game.getColor2());

        this.gameInfoPanel.add(this.menuButton);
        this.gameInfoPanel.add(this.saveGame);
        this.gameInfoPanel.add(this.labelPlayer1);
        this.gameInfoPanel.add(this.labelPlayer2);
        this.gameInfoPanel.add(new JLabel("Active:"));
        this.gameInfoPanel.add(this.labelActivePlayer);

        this.setLayout(new BorderLayout());
        this.add(gameInfoPanel, BorderLayout.NORTH);
        this.add(Main.board, BorderLayout.CENTER);
    }


    /**
     * Sets the points representation on the panel
     * @param pointsPlayer1 points for player 1
     * @param pointsPlayer2 points for player 1
     */
    public void setPoints(int pointsPlayer1, int pointsPlayer2) {
        this.points1 = pointsPlayer1;
        this.points2 = pointsPlayer2;
        this.labelPlayer1.setText(textPlayer1 + ": " + this.points1);
        this.labelPlayer2.setText(textPlayer2 + ": " + this.points2);
    }

    /**
     * Sets the endgame representation of the game panel information
     */
    public void endGame() {
        if (this.points1 > this.points2) {
            this.labelPlayer1.setText(this.labelPlayer1.getText() + " " + winMsg);
            this.labelPlayer2.setText(this.labelPlayer2.getText() + " " + loseMsg);
        } else if (this.points1 < this.points2) {
            this.labelPlayer1.setText(this.labelPlayer1.getText() + " " + loseMsg);
            this.labelPlayer2.setText(this.labelPlayer2.getText() + " " + winMsg);
        } else {
            this.labelPlayer1.setText(this.labelPlayer1.getText() + " " + drawMsg);
            this.labelPlayer2.setText(this.labelPlayer2.getText() + " " + drawMsg);
        }
    }
}
