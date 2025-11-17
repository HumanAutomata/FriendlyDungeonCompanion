package app.tabs;

import app.Role;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MapTab extends JPanel {

  public MapTab(Role role) {

    setLayout(new BorderLayout());
    JButton importMap = new JButton("importMap");
    JButton noMapFound = new JButton("No map found :(");

    try {
      // Load the image
      Image img = ImageIO.read(new File("./state/baseMap.png"));
      JLabel label = new JLabel(new ImageIcon(img));
      add(label, BorderLayout.CENTER);
    } catch (Exception e) {
      add(noMapFound, BorderLayout.CENTER);
      e.printStackTrace();
    }

    add(importMap, BorderLayout.SOUTH);
  }
}
