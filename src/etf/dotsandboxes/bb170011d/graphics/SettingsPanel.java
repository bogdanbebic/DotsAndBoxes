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
    private JTextField ai1DepthOfTreeText = new JTextField("10", 5);
    private JTextField ai2DepthOfTreeText = new JTextField("10", 5);
    private JButton aiPropertiesSelectionButton = new JButton("Select AI properties");

    // AI difficulty selection button action listener
    {
        this.aiPropertiesSelectionButton.addActionListener(e -> {
            String ai1Difficulty = (String)this.ai1DifficultySelector.getSelectedItem();
            String ai2Difficulty = (String)this.ai2DifficultySelector.getSelectedItem();
            // TODO: set AI difficulties to game
            System.out.println("AI1: " + ai1Difficulty);
            System.out.println("AI2: " + ai2Difficulty);

            try {
                Integer ai1DepthOfTree = Integer.parseInt(this.ai1DepthOfTreeText.getText());
                Integer ai2DepthOfTree = Integer.parseInt(this.ai1DepthOfTreeText.getText());
                // TODO: set AI depth of tree to game
                if (ai1DepthOfTree <= 0 || ai2DepthOfTree <= 0)
                    throw new Exception();

                System.out.println("AI1 tree depth: " + ai1DepthOfTree);
                System.out.println("AI2 tree depth: " + ai2DepthOfTree);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,"Invalid selection of depth of tree params, " +
                                "depth of tree must be a positive integer",
                        "Invalid selection", JOptionPane.ERROR_MESSAGE
                );
            }
        });
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
        this.playerTypeSelectionButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        playerTypeComponents.add(player1Type);
        playerTypeComponents.add(this.player1TypeSelector);
        playerTypeComponents.add(Box.createVerticalGlue());
        playerTypeComponents.add(player2Type);
        playerTypeComponents.add(this.player2TypeSelector);
        playerTypeComponents.add(Box.createVerticalGlue());
        playerTypeComponents.add(this.playerTypeSelectionButton);
        this.add(Box.createVerticalGlue());

        // lays out AI properties components
        JPanel aiComponents = new JPanel();
        aiComponents.setLayout(new BoxLayout(aiComponents, BoxLayout.Y_AXIS));

        final int textAreaHeight = 40;
        this.ai1DifficultySelector.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.ai1DepthOfTreeText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.ai1DepthOfTreeText.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAreaHeight));
        this.ai2DifficultySelector.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.ai2DepthOfTreeText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.ai2DepthOfTreeText.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAreaHeight));
        this.aiPropertiesSelectionButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel ai1Difficulty = new JLabel("AI 1 difficulty:");
        JLabel ai1DepthOfTree = new JLabel("AI 1 depth of tree:");
        JLabel ai2Difficulty = new JLabel("AI 2 difficulty:");
        JLabel ai2DepthOfTree = new JLabel("AI 2 depth of tree:");
        ai1Difficulty.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        ai1DepthOfTree.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        ai2Difficulty.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        ai2DepthOfTree.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        aiComponents.add(Box.createVerticalGlue());
        aiComponents.add(ai1Difficulty);
        aiComponents.add(this.ai1DifficultySelector);
        aiComponents.add(Box.createVerticalGlue());
        aiComponents.add(ai1DepthOfTree);
        aiComponents.add(this.ai1DepthOfTreeText);
        aiComponents.add(Box.createVerticalGlue());
        aiComponents.add(ai2Difficulty);
        aiComponents.add(this.ai2DifficultySelector);
        aiComponents.add(Box.createVerticalGlue());
        aiComponents.add(ai2DepthOfTree);
        aiComponents.add(this.ai2DepthOfTreeText);
        aiComponents.add(Box.createVerticalGlue());
        aiComponents.add(this.aiPropertiesSelectionButton);
        aiComponents.add(Box.createVerticalGlue());

        this.add(playerTypeComponents);
        this.add(aiComponents);
    }

    public static void main(String ... args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.add(new SettingsPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
