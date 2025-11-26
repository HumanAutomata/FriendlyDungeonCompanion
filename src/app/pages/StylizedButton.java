package app.pages;

import javax.swing.*;
import java.awt.*;

public class StylizedButton extends JPanel {

    public final Color APP_PURPLE = new Color(120, 80, 200);
    public final Color APP_RED = new Color(186,68,68);
    public final Color APP_GRAY = new Color(45, 43, 47);
    public final Color APP_LIGHT_PURPLE = new Color(253,226,255);

    // Makes a rounded button depending on height, width, color and font size
    public void makeRounded(JButton button, int radius, Color color, int fontSize, int borderWidth, int borderHeight) {
        button.setBorder(BorderFactory.createEmptyBorder(borderHeight, borderWidth, borderHeight, borderWidth));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // background
                g2d.setColor(button.getBackground());
                g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);

                // text
                super.paint(g, c);

                g2d.dispose();
            }
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, fontSize));
        button.setBackground(color);
        button.setForeground(Color.WHITE); 
    }

}