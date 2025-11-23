package app.pages;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import app.Role;

public class RoleSelectionPage extends JPanel {

    private void makeRounded(JButton button, int radius) {
        button.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
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
    }

    public RoleSelectionPage(Consumer<Role> callback) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Your Friendly Dungeon Companion");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 75));

        JLabel login = new JLabel("Please Select Your Role:");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setFont(new Font("Arial", Font.PLAIN, 50));

        JButton player = new JButton("Player");
        player.setAlignmentX(Component.CENTER_ALIGNMENT);
        player.setFont(new Font("Arial", Font.PLAIN, 50));
        player.setBackground(new Color(186, 68, 68));
        player.setForeground(Color.WHITE); 
        player.setOpaque(true);
        player.setContentAreaFilled(true);
        makeRounded(player, 100);

        JButton dm = new JButton("Dungeon Master");
        dm.setAlignmentX(Component.CENTER_ALIGNMENT);
        dm.setFont(new Font("Arial", Font.PLAIN, 50));
        dm.setBackground(new Color(118, 64, 205));
        dm.setForeground(Color.WHITE); 
        dm.setOpaque(true);
        dm.setContentAreaFilled(true);
        makeRounded(dm, 100);

        player.addActionListener(e -> callback.accept(Role.PLAYER));
        dm.addActionListener(e -> callback.accept(Role.DM));



        // Everything centered horizontally
        setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 150))); // spacing
        add(login);
        add(Box.createRigidArea(new Dimension(0, 50))); // spacing
        add(player);
        add(Box.createRigidArea(new Dimension(0, 50))); // spacing
        add(dm);
        add(Box.createVerticalGlue());

    }
}

