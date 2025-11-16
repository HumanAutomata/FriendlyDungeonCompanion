package app.pages;

import app.Role;
import app.tabs.*;
import java.awt.*;
import javax.swing.*;

public class HomePage extends JPanel {

  public HomePage(Role role) {
    setLayout(new java.awt.BorderLayout());

    JTabbedPane tabs = new JTabbedPane();
    tabs.add("Map", new MapTab(role));
    tabs.add("Notes", new NotesTab());
    if (role == Role.DM) {
      tabs.add("Tools", new ToolsTab(role));
      tabs.add("NPC Character Sheet", new NPCTab(role));
    } else {
      tabs.add("Character Sheet", new CharacterSheetTab(role));
      tabs.add("Inventory", new InventoryTab(role));
    }

    add(tabs, BorderLayout.CENTER);
  }
}
