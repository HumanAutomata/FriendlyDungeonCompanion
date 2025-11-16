package app.tabs;

import javax.swing.*;
import app.Role;

public class MapTab extends JPanel {

    public MapTab(Role role) {

        add(new JLabel("Map"));

        //if (role == Role.DM) {
        //    add(new JLabel("NPC character sheet"));
        //    add(new JButton("Create a new NPC"));
        //} else {
        //    add(new JLabel("Your Character"));
        //    add(new JButton("Yay"));
        //}
    }
}



