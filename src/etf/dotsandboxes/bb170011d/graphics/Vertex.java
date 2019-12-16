package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.engine.GameBoardObject;

import javax.swing.*;
import java.awt.*;

public class Vertex extends JComponent implements GameBoardObject {
    private static final int ARC_WIDTH = 16, ARC_HEIGHT = 16;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        int rectX = this.getWidth() / 3;
        int rectY = this.getHeight() / 3;
        int rectWidth = this.getWidth() / 3;
        int rectHeight = this.getHeight() / 3;
        g.fillRoundRect(rectX, rectY, rectWidth, rectHeight, ARC_WIDTH, ARC_HEIGHT);
    }

    public static void main(String ... args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        JPanel panel = new JPanel(new BorderLayout());
        Vertex vertex = new Vertex();
        panel.add(vertex);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
