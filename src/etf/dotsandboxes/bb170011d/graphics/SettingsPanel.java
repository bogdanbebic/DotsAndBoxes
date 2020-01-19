package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.Main;
import etf.dotsandboxes.bb170011d.engine.PlayerType;
import etf.dotsandboxes.bb170011d.player.AbstractPlayer;
import etf.dotsandboxes.bb170011d.player.Player;
import etf.dotsandboxes.bb170011d.player.ai.Advanced;
import etf.dotsandboxes.bb170011d.player.ai.Beginner;
import etf.dotsandboxes.bb170011d.player.ai.Competitive;
import etf.dotsandboxes.bb170011d.player.ai.MinimaxPlayer;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a panel for the Settings of the game
 * Sets the parameters of the game when active panel is changed
 */
public class SettingsPanel extends JPanel {

    private JComboBox<PlayerType> player1TypeSelector = new JComboBox<>(PlayerType.values());
    private JComboBox<PlayerType> player2TypeSelector = new JComboBox<>(PlayerType.values());
    private JButton backToMenuButton = new JButton("back to menu");

    private void setPlayersToGame(PlayerType player1Type, PlayerType player2Type) {
        if (player1Type != null) {
            switch (player1Type) {
                case PLAYER:
                    Main.game.setPlayer1(new Player());
                    break;
                case BEGINNER:
                    Main.game.setPlayer1(new Beginner());
                    break;
                case ADVANCED:
                    Main.game.setPlayer1(new Advanced());
                    break;
                case COMPETITIVE:
                    Main.game.setPlayer1(new Competitive());
                    break;
            }
        }

        if (player2Type != null) {
            switch (player2Type) {
                case PLAYER:
                    Main.game.setPlayer2(new Player());
                    break;
                case BEGINNER:
                    Main.game.setPlayer2(new Beginner());
                    break;
                case ADVANCED:
                    Main.game.setPlayer2(new Advanced());
                    break;
                case COMPETITIVE:
                    Main.game.setPlayer2(new Competitive());
                    break;
            }
        }
    }

    // player type selection action listener
    {
        this.backToMenuButton.addActionListener(e -> {
            PlayerType player1Type = (PlayerType)this.player1TypeSelector.getSelectedItem();
            PlayerType player2Type = (PlayerType)this.player2TypeSelector.getSelectedItem();
            if (player1Type == PlayerType.SERVER || player2Type == PlayerType.SERVER) {
                JOptionPane.showMessageDialog(
                        this,"Not yet implemented",
                        "Oopsy woopsy", JOptionPane.ERROR_MESSAGE
                );
            } else {
                setPlayersToGame(player1Type, player2Type);
            }
        });
    }

    private JTextField ai1DepthOfTreeText = new JTextField("5", 5);
    private JTextField ai2DepthOfTreeText = new JTextField("5", 5);

    // AI difficulty selection action listener
    {
        this.backToMenuButton.addActionListener(e -> {
            try {
                Integer ai1DepthOfTree = Integer.parseInt(this.ai1DepthOfTreeText.getText());
                Integer ai2DepthOfTree = Integer.parseInt(this.ai2DepthOfTreeText.getText());

                if (ai1DepthOfTree <= 0 || ai2DepthOfTree <= 0)
                    throw new Exception();

                AbstractPlayer player1 = Main.game.getPlayer1();
                if (player1 instanceof MinimaxPlayer)
                    ((MinimaxPlayer)player1).setDepthOfTree(ai1DepthOfTree);

                AbstractPlayer player2 = Main.game.getPlayer2();
                if (player2 instanceof MinimaxPlayer)
                    ((MinimaxPlayer)player2).setDepthOfTree(ai1DepthOfTree);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,"Invalid selection of depth of tree params, " +
                                "depth of tree must be a positive integer",
                        "Invalid selection", JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    private JTextField numRowsText = new JTextField("5", 5);
    private JTextField numColsText = new JTextField("5", 5);

    // game board size
    {
        this.backToMenuButton.addActionListener(e -> {
            try {
                Integer numRows = Integer.parseInt(this.numRowsText.getText());
                Integer numCols = Integer.parseInt(this.numColsText.getText());

                if (numRows <= 0 || numCols <= 0 || numRows > 8 || numCols > 8)
                    throw new Exception();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this, "Invalid game board dimensions",
                        "Invalid selection", JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    // back to menu action listener
    {
        this.backToMenuButton.addActionListener(e -> Main.mainPanel.setActivePanel(MainPanel.MENU_PANEL));
    }


    // lays out components
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // lays out player type components
        JPanel playerTypeComponents = new JPanel();
        playerTypeComponents.setLayout(new BoxLayout(playerTypeComponents, BoxLayout.Y_AXIS));

        JLabel player1Type = new JLabel("Player1 type:");
        JLabel player2Type = new JLabel("Player2 type:");
        player1Type.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        player2Type.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        this.player1TypeSelector.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.player2TypeSelector.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        playerTypeComponents.add(player1Type);
        playerTypeComponents.add(this.player1TypeSelector);
        playerTypeComponents.add(Box.createVerticalGlue());
        playerTypeComponents.add(player2Type);
        playerTypeComponents.add(this.player2TypeSelector);
        playerTypeComponents.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());

        // lays out AI properties components
        JPanel aiComponents = new JPanel();
        aiComponents.setLayout(new BoxLayout(aiComponents, BoxLayout.Y_AXIS));

        final int textAreaHeight = 40;
        this.ai1DepthOfTreeText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.ai1DepthOfTreeText.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAreaHeight));
        this.ai2DepthOfTreeText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.ai2DepthOfTreeText.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAreaHeight));

        JLabel ai1DepthOfTree = new JLabel("AI 1 depth of tree:");
        JLabel ai2DepthOfTree = new JLabel("AI 2 depth of tree:");
        ai1DepthOfTree.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        ai2DepthOfTree.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        aiComponents.add(Box.createVerticalGlue());
        aiComponents.add(ai1DepthOfTree);
        aiComponents.add(this.ai1DepthOfTreeText);
        aiComponents.add(Box.createVerticalGlue());
        aiComponents.add(ai2DepthOfTree);
        aiComponents.add(this.ai2DepthOfTreeText);
        aiComponents.add(Box.createVerticalGlue());

        JPanel gameSettingsPanel = new JPanel();

        JLabel rowsLabel = new JLabel("Number of rows:");
        rowsLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAreaHeight));
        rowsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        JLabel colsLabel = new JLabel("Number of columns:");
        colsLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAreaHeight));
        colsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        this.numRowsText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.numColsText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.numRowsText.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAreaHeight));
        this.numColsText.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAreaHeight));

        gameSettingsPanel.setLayout(new BoxLayout(gameSettingsPanel, BoxLayout.Y_AXIS));
        gameSettingsPanel.add(Box.createVerticalGlue());
        gameSettingsPanel.add(rowsLabel);
        gameSettingsPanel.add(Box.createVerticalGlue());
        gameSettingsPanel.add(this.numRowsText);
        gameSettingsPanel.add(Box.createVerticalGlue());
        gameSettingsPanel.add(colsLabel);
        gameSettingsPanel.add(Box.createVerticalGlue());
        gameSettingsPanel.add(this.numColsText);
        gameSettingsPanel.add(Box.createVerticalGlue());

        this.add(playerTypeComponents);
        this.add(aiComponents);
        this.add(gameSettingsPanel);


        this.backToMenuButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(this.backToMenuButton);
    }

    public static void main(String ... args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.add(new SettingsPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
