package app.pages;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import app.Role;

public class RoleSelectionPage extends JPanel {

    public RoleSelectionPage(Consumer<Role> callback) {
        setLayout(new GridBagLayout());

        JButton player = new JButton("Player");
        JButton dm = new JButton("Dungeon Master");

        player.addActionListener(e -> callback.accept(Role.PLAYER));
        dm.addActionListener(e -> callback.accept(Role.DM));

        JPanel row = new JPanel();
        row.add(player);
        row.add(dm);

        add(row);
    }
}

