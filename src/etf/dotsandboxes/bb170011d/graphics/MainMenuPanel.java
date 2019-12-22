package etf.dotsandboxes.bb170011d.graphics;

import javax.swing.*;

public class MainMenuPanel extends JPanel {
    private JButton newGameButton   = new JButton("New Game");
    private JButton loadGameButton  = new JButton("Load Game");
    private JButton settingsButton  = new JButton("Settings");

    // menu options action listeners
    {
        this.newGameButton.addActionListener(e -> {
            // TODO: start new game
            System.out.println("NEW GAME");
        });

        this.loadGameButton.addActionListener(e -> {
            // TODO: load game
            System.out.println("LOAD GAME");
        });

        this.settingsButton.addActionListener(e -> {
            // TODO: show settings
            System.out.println("SETTINGS");
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
        frame.add(new MainMenuPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
