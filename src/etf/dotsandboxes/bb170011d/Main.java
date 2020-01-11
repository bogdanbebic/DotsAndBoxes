package etf.dotsandboxes.bb170011d;

import etf.dotsandboxes.bb170011d.engine.Game;
import etf.dotsandboxes.bb170011d.graphics.MainPanel;

import javax.swing.*;

public class Main {
    public static Game game = new Game();
    public static MainPanel mainPanel = new MainPanel();


    public static void main(String ... args) {
        System.out.println("Hello, World!");
        JFrame frame = new JFrame("Dots&Boxes");
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
