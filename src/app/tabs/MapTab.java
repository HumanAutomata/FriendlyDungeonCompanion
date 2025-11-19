package app.tabs;

import app.Role;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MapTab extends JPanel {

  private BufferedImage img;
  private JLayeredPane layerPane;
  private JPanel overlay;

  public MapTab(Role role) {
    setLayout(new BorderLayout());

    try {
      img = ImageIO.read(new File("./state/baseMap.png"));

      JPanel imagePanel =
          new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              int x = (getWidth() - img.getWidth()) / 2;
              int y = (getHeight() - img.getHeight()) / 2;
              g.drawImage(img, x, y, null);
            }

            @Override
            public Dimension getPreferredSize() {
              return new Dimension(img.getWidth(), img.getHeight());
            }
          };
      imagePanel.setOpaque(true);

      overlay = new JPanel(null); // absolute positioning
      overlay.setOpaque(false);
      overlay.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));

      layerPane = new JLayeredPane();
      layerPane.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));

      imagePanel.setBounds(0, 0, img.getWidth(), img.getHeight());
      overlay.setBounds(0, 0, img.getWidth(), img.getHeight());

      layerPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
      layerPane.add(overlay, JLayeredPane.PALETTE_LAYER);

      JScrollPane scroll = new JScrollPane(layerPane);
      scroll.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

      add(scroll, BorderLayout.CENTER);

      JButton importMap = new JButton("importMap");
      //importMap.setBounds(25, 25, 25, 25); //this to make it smaller, like an actual button
      add(importMap, BorderLayout.NORTH);

      if (role == Role.DM) {

        // add button on mouse click
        layerPane.addMouseListener(
            new MouseAdapter() {
              @Override
              public void mousePressed(MouseEvent e) {

                // button
                JButton b = new JButton("X");
                b.setBounds(e.getX() - 10, e.getY() - 10, 25, 25);
                overlay.add(b);
                overlay.repaint();

                // delete if right-clicked
                b.addMouseListener(
                    new MouseAdapter() {
                      @Override
                      public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                          overlay.remove(b);
                          overlay.repaint();
                        }
                      }
                    });
              }
            });
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
