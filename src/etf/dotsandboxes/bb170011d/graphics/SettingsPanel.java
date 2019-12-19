package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.engine.PlayerType;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    private JComboBox<PlayerType> player1TypeSelector = new JComboBox<>(PlayerType.values());
    private JComboBox<PlayerType> player2TypeSelector = new JComboBox<>(PlayerType.values());
    private JButton playerTypeSelectionButton = new JButton("Select Player Types");

    // player type selection button action listener
    {
        this.playerTypeSelectionButton.addActionListener(e -> {
            PlayerType player1Type = (PlayerType)this.player1TypeSelector.getSelectedItem();
            PlayerType player2Type = (PlayerType)this.player2TypeSelector.getSelectedItem();
            if (player1Type == PlayerType.SERVER && player2Type == PlayerType.SERVER) {
                JOptionPane.showMessageDialog(
                        this,"Two servers cannot play on the same machine",
                        "Invalid selection", JOptionPane.ERROR_MESSAGE
                );
            } else {
                // TODO: set player types to game
                System.out.println("P1: " + player1Type);
                System.out.println("P2: " + player2Type);
            }
        });
    }

    private String [] aiDifficulties = { "Beginner", "Advanced", "Competitive" };

    private JComboBox<String> ai1DifficultySelector = new JComboBox<>(this.aiDifficulties);
    private JComboBox<String> ai2DifficultySelector = new JComboBox<>(this.aiDifficulties);
    private JButton aiDifficultySelectionButton = new JButton("Select AI Difficulties");

    // AI difficulty selection button action listener
    {
        this.aiDifficultySelectionButton.addActionListener(e -> {
            String ai1Difficulty = (String)this.ai1DifficultySelector.getSelectedItem();
            String ai2Difficulty = (String)this.ai2DifficultySelector.getSelectedItem();
            // TODO: set AI difficulties to game
            System.out.println("AI1: " + ai1Difficulty);
            System.out.println("AI2: " + ai2Difficulty);
        });
    }

    // lays out components
    {
        this.setLayout(new GridLayout(2, 1));

        JPanel playerTypeComponents = new JPanel();
        // lays out player type components
        playerTypeComponents.add(new JLabel("Player1 type:"));
        playerTypeComponents.add(this.player1TypeSelector);
        playerTypeComponents.add(new JLabel("Player2 type:"));
        playerTypeComponents.add(this.player2TypeSelector);
        playerTypeComponents.add(this.playerTypeSelectionButton);

        // lays out AI difficulty components
        JPanel aiDifficultiesComponents = new JPanel();
        aiDifficultiesComponents.add(new JLabel("AI 1 difficulty:"));
        aiDifficultiesComponents.add(this.ai1DifficultySelector);
        aiDifficultiesComponents.add(new JLabel("AI 2 difficulty:"));
        aiDifficultiesComponents.add(this.ai2DifficultySelector);
        aiDifficultiesComponents.add(this.aiDifficultySelectionButton);

        this.add(playerTypeComponents);
        this.add(aiDifficultiesComponents);
    }

    public static void main(String ... args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.add(new SettingsPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
