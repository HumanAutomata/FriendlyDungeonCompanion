package app.pages;

import app.Role;
import app.tabs.*;
import java.awt.*;
import javax.swing.*;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

class ModernTabbedUI extends BasicTabbedPaneUI {

    private final Color activeColor = new Color(120, 80, 200);   // purple (active)
    private final Color inactiveColor = new Color(45, 43, 47);  // dark gray

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color fill = isSelected ? activeColor : inactiveColor;
        g2d.setColor(fill);
        int arc = 18;
        g2d.fillRoundRect(x + 4, y + 6, w - 8, h - 12, arc, arc);
        g2d.dispose();
    }

    // Get rid of button border
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement,int tabIndex, int x, int y, int w, int h, boolean isSelected) {}

    // Set custom height of tabs
    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        return 60;
    }

    // Set custom width of tabs
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 90;
    }
}


public class HomePage extends JPanel {

  public HomePage(Role role) {
    setLayout(new BorderLayout());

    JTabbedPane tabs = new JTabbedPane();
    tabs.setUI(new ModernTabbedUI());
    tabs.setFont(new Font("Arial", Font.PLAIN, 18));
    tabs.setForeground(Color.WHITE);

    // REMOVE annoying Swing focus blue border
    tabs.setFocusable(false);

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
