package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.engine.GameBoardObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Edge extends JComponent implements GameBoardObject {
    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    private Orientation orientation = Orientation.HORIZONTAL;

    private boolean isFilled = false;
    private boolean isPeeked = false;
    private Color playerColor = Color.blue;
    private Color playerPeekColor = Color.gray;

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public void setPlayerPeekColor(Color playerPeekColor) {
        this.playerPeekColor = playerPeekColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.isFilled) {
            g.setColor(this.playerColor);
        } else if (this.isPeeked) {
            g.setColor(this.playerPeekColor);
        } else {
            return;
        }

        if (this.orientation == Orientation.HORIZONTAL) {
            g.fillRect(0, this.getHeight() / 3, this.getWidth() -1, this.getHeight() / 3);
        } else {
            g.fillRect(this.getWidth() / 3, 0, this.getWidth() / 3, this.getHeight() - 1);
        }
    }

    {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Edge.this.isFilled = true;
                Edge.this.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Edge.this.isPeeked = true;
                Edge.this.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Edge.this.isPeeked = false;
                Edge.this.repaint();
            }
        });
    }

    public static void main(String ... args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        Edge edge = new Edge();
        edge.setPlayerColor(Color.red);
        edge.setPlayerPeekColor(Color.pink);
        edge.setOrientation(Orientation.VERTICAL);
        frame.add(edge);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
