package app;

import javax.swing.*;
import java.awt.*;
import app.pages.*;
import app.Role;

public class MainFrame extends JFrame {

    private CardLayout layout = new CardLayout();
    private JPanel container = new JPanel(layout);

    public MainFrame() {
        super("Your Friendly Dungeon Companion");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // first page
        container.add(new RoleSelectionPage(this::onRoleSelected), "role");

        add(container);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Sets window default to fullscreen
        setMinimumSize(new Dimension(1280, 720)); // Specify the minimum panel size
        setUndecorated(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void onRoleSelected(Role role) {
        HomePage home = new HomePage(role);
        container.add(home, "home");
        layout.show(container, "home");
    }
}

