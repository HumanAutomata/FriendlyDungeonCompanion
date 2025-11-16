package app.tabs;

import javax.swing.*;
import app.Role;

public class ToolsTab extends JPanel {

    public ToolsTab(Role role) {

        add(new JLabel("npc list"));
        add(new JLabel("quests"));
        add(new JButton("create npc"));

        //if (role == Role.DM) {
        //    add(new JLabel("NPC character sheet"));
        //    add(new JButton("Create a new NPC"));
        //} else {
        //    add(new JLabel("Your Character"));
        //    add(new JButton("Yay"));
        //}
    }
}




