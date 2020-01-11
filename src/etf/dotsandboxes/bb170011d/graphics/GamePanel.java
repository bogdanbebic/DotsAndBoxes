package etf.dotsandboxes.bb170011d.graphics;

import etf.dotsandboxes.bb170011d.Main;

import javax.swing.*;

public class GamePanel extends JPanel {
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Main.board);
    }
}
