package app.tabs;

import javax.swing.*;
import app.Role;

public class InventoryTab extends JPanel {

    public InventoryTab(Role role) {

        if (role == Role.DM) {
            add(new JLabel("DM Inventory View"));
            add(new JButton("Add Loot"));
        } else {
            add(new JLabel("Player Inventory"));
            add(new JButton("Open Backpack"));
        }
    }
}

