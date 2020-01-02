package etf.dotsandboxes.bb170011d.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel {
    public static final String MENU_PANEL = "Main Menu";
    public static final String GAME_PANEL = "Game";
    public static final String SETTINGS_PANEL = "Settings";

    private final String []panels = { MENU_PANEL, GAME_PANEL, SETTINGS_PANEL };

    public void setActivePanel(String nextActivePanel) {
        ((CardLayout)this.getLayout()).show(this, nextActivePanel);
    }

    {
        this.setLayout(new CardLayout());
        this.add(new MenuPanel(), MENU_PANEL);
        this.add(new JPanel(), GAME_PANEL); // TODO: change to game panel
        this.add(new SettingsPanel(), SETTINGS_PANEL);
    }

    public static void main(String ... args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        MainPanel panel = new MainPanel();
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ((CardLayout)panel.getLayout()).next(panel);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
