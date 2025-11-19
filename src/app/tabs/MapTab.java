package app.tabs;

import app.Role;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MapTab extends JPanel {

  private boolean showGrid = false;

  public MapTab(Role role) {

    setLayout(new BorderLayout());

    try {
      // Load image into the container
      BufferedImage img = ImageIO.read(new File("./state/baseMap.png"));
      ImageIcon icon = new ImageIcon(img);
      JLabel label = new JLabel(icon);
      JScrollPane scroll = new JScrollPane(label);
      scroll.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

      add(scroll, BorderLayout.CENTER);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

// JButton importMap = new JButton("importMap");
// JButton noMapFound = new JButton("No map found :(");

// if (role == Role.DM) {
//    add(new JLabel("NPC character sheet"));
//    add(new JButton("Create a new NPC"));
// } else {
//    add(new JLabel("Your Character"));
//    add(new JButton("Yay"));
// }
