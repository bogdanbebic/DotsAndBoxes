package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.exceptions.InvalidBoardDimensionsException;

import javax.swing.*;

public class GamePanel extends JPanel {
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        try {
            this.add(new GameBoard(5, 5));
        } catch (InvalidBoardDimensionsException e) {
            e.printStackTrace();
        }
    }
}
