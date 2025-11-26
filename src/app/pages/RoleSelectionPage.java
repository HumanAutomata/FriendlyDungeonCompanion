package app.pages;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import app.Role;
import app.pages.StylizedButton;
import app.MainFrame;

public class RoleSelectionPage extends JPanel {

    public RoleSelectionPage(Consumer<Role> callback) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        StylizedButton styleButton = new StylizedButton();

        JLabel title = new JLabel("Your Friendly Dungeon Companion");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 75));

        JLabel login = new JLabel("Please Select Your Role:");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setFont(new Font("Arial", Font.PLAIN, 50));

        JButton player = new JButton("Player");
        styleButton.makeRounded(player, 100, styleButton.APP_RED, 50, 40, 20);

        JButton dm = new JButton("Dungeon Master");
        styleButton.makeRounded(dm, 100, styleButton.APP_PURPLE, 50, 40, 20);

        player.addActionListener(e -> callback.accept(Role.PLAYER));
        dm.addActionListener(e -> callback.accept(Role.DM));

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

