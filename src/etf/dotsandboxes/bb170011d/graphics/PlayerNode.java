package etf.dotsandboxes.bb170011d.graphics;

import javax.swing.*;
import java.awt.*;

public class PlayerNode extends JComponent implements GameBoardObject {
    private boolean filled = false;
    private Color playerColor = Color.blue;

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public void setFilled(boolean isFilled) {
        this.filled = isFilled;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.filled) {
            g.setColor(this.playerColor);
            g.fillOval(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
        }
    }

    public static void main(String ... args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        PlayerNode playerNode = new PlayerNode();
        playerNode.setPlayerColor(Color.cyan);
        frame.add(playerNode);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread.sleep(2000);
        playerNode.setFilled(true);
    }

    @Override
    public boolean isFilled() {
        return this.filled;
    }
}
