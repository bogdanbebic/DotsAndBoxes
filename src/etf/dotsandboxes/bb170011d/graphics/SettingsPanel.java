package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.engine.PlayerType;

import javax.swing.*;

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

    // lays out components
    {
        this.add(new JLabel("Player1 type:"));
        this.add(this.player1TypeSelector);
        this.add(new JLabel("Player2 type:"));
        this.add(this.player2TypeSelector);
        this.add(this.playerTypeSelectionButton);
    }

    public static void main(String ... args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.add(new SettingsPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
