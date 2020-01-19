package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.Main;

import javax.swing.*;

/**
 * Represents a menu panel for the game
 * Contains options for changing the current active panel
 */
public class MenuPanel extends JPanel {
    private JButton newGameButton   = new JButton("New Game");
    private JButton loadGameButton  = new JButton("Load Game");
    private JButton settingsButton  = new JButton("Settings");

    // menu options action listeners
    {
        this.newGameButton.addActionListener(e -> {
            Main.mainPanel.setActivePanel(MainPanel.GAME_PANEL);
            Main.game.setPlayer1(Main.player1);
            Main.game.setPlayer2(Main.player2);
            Main.game.start();
        });

        this.loadGameButton.addActionListener(e -> {
            Main.mainPanel.setActivePanel(MainPanel.GAME_PANEL);
            // TODO: maybe change to JFileChooser
            String filepath = JOptionPane.showInputDialog("Filepath?");
            Main.game.setPlayer1(Main.player1);
            Main.game.setPlayer2(Main.player2);
            Main.game.load(filepath);
            Main.game.start();
        });

        this.settingsButton.addActionListener(e -> {
            Main.mainPanel.setActivePanel(MainPanel.SETTINGS_PANEL);
        });
    }

    // add menu options to gui
    {
        this.newGameButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.loadGameButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.settingsButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(this.newGameButton);
        this.add(Box.createVerticalGlue());
        this.add(this.loadGameButton);
        this.add(Box.createVerticalGlue());
        this.add(this.settingsButton);
        this.add(Box.createVerticalGlue());
    }

    public static void main(String ... args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.add(new MenuPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
