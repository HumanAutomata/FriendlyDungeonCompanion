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
  private JPanel POIPanel;
  private JPanel PopupPanel;
  private int imgWidth;
  private int imgHeight;

  public MapTab(Role role) {
    setLayout(new BorderLayout());

    try {

      // draw the image from the file
      img = ImageIO.read(new File("./state/baseMap.png"));
      imgWidth = img.getWidth();
      imgHeight = img.getHeight();
      JPanel imagePanel =
          new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              int offsetWidth = (getWidth() - img.getWidth()) / 2;
              int offsetHeight = (getHeight() - img.getHeight()) / 2;
              g.drawImage(img, offsetWidth, offsetHeight, null);
            }

            @Override
            public Dimension getPreferredSize() {
              return new Dimension(imgWidth, imgHeight);
            }
          };
      imagePanel.setOpaque(true);
      imagePanel.setBounds(0, 0, imgWidth, imgHeight);

      // draw the POIs
      POIPanel = new JPanel(null);
      POIPanel.setOpaque(false);
      POIPanel.setPreferredSize(new Dimension(imgWidth, imgHeight));
      POIPanel.setBounds(0, 0, imgWidth, imgHeight);

      // draw the Popup
      PopupPanel = new JPanel();

      // add the Map, POIs, and Popup to the layer pane
      layerPane = new JLayeredPane();
      layerPane.setPreferredSize(new Dimension(imgWidth, imgHeight));
      layerPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
      layerPane.add(POIPanel, JLayeredPane.PALETTE_LAYER);

      // make the layer pane scrollable (resizable) and add it to the tab
      JScrollPane scroll = new JScrollPane(layerPane);
      scroll.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
      add(scroll, BorderLayout.CENTER);

      // other buttons
      JButton importMap = new JButton("importMap");
      // importMap.setBounds(25, 25, 25, 25); //this to make it smaller, like an actual button
      add(importMap, BorderLayout.NORTH);

      // allow the DM to manage POIs
      if (role == Role.DM) {

        // add button on mouse click
        layerPane.addMouseListener(
            new MouseAdapter() {
              @Override
              public void mousePressed(MouseEvent e) {

                if (SwingUtilities.isLeftMouseButton(e)) {

                  // button
                  JButton b = new JButton("X");
                  b.setBounds(e.getX() - 10, e.getY() - 10, 25, 25);
                  POIPanel.add(b);
                  POIPanel.repaint();

                  // do stuff when you click the button
                  b.addMouseListener(
                      new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                          // delete if right-clicked
                          if (SwingUtilities.isRightMouseButton(e)) {
                            POIPanel.remove(b);
                            POIPanel.repaint();
                          } else if (SwingUtilities.isLeftMouseButton(e)) {
                            System.out.println("create popup");
                          }
                        }
                      });
                }
              }
            });
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
