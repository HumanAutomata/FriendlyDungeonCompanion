package app;

import javax.swing.*;
import java.awt.*;
import app.pages.*;
import app.Role;

public class MainFrame extends JFrame {

    private CardLayout layout = new CardLayout();
    private JPanel container = new JPanel(layout);

    public MainFrame() {
        super("My App");

        // first page
        container.add(new RoleSelectionPage(this::onRoleSelected), "role");

        add(container);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void onRoleSelected(Role role) {
        HomePage home = new HomePage(role);
        container.add(home, "home");
        layout.show(container, "home");
    }
}

