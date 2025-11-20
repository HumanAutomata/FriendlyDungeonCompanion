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
  private int popupWidth = 800;
  private int popupHeight = 1000;

  public MapTab(Role role) {
    setLayout(new BorderLayout()); // divide tab into  center and 4 quadrants

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
              // offset to draw in the middle
              int offsetWidth = (getWidth() - img.getWidth()) / 2;
              int offsetHeight = (getHeight() - img.getHeight()) / 2;
              g.drawImage(img, offsetWidth, offsetHeight, null);
            }

            // resizing
            @Override
            public Dimension getPreferredSize() {
              return new Dimension(imgWidth, imgHeight);
            }
          };
      imagePanel.setOpaque(true);
      imagePanel.setBounds(0, 0, imgWidth, imgHeight);

      // draw the POI panel
      POIPanel = new JPanel(null);
      POIPanel.setOpaque(false);
      POIPanel.setPreferredSize(new Dimension(imgWidth, imgHeight));
      POIPanel.setBounds(0, 0, imgWidth, imgHeight);

      // draw the Popup panel
      PopupPanel = new JPanel(new BorderLayout());
      int offsetWidth = (imgWidth - popupWidth) / 2;
      int offsetHeight = (imgHeight - popupHeight) / 2;
      PopupPanel.setBounds(offsetWidth, offsetHeight, popupWidth, popupHeight);
      PopupPanel.setBackground(new Color(255, 255, 255, 0));
      PopupPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      // title
      JLabel popupTitle = new JLabel("Point of Interest");
      popupTitle.setFont(popupTitle.getFont().deriveFont(Font.BOLD, 56f));
      popupTitle.setHorizontalAlignment(SwingConstants.CENTER);
      PopupPanel.add(popupTitle, BorderLayout.NORTH);

      // put contents in a single row
      JPanel content = new JPanel();
      content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

      JLabel title = new JLabel("Title");
      title.setAlignmentX(Component.CENTER_ALIGNMENT);

      JTextField titleField = new JTextField();
      titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

      JLabel desc = new JLabel("Description");
      desc.setAlignmentX(Component.CENTER_ALIGNMENT);

      JTextField descField = new JTextField();
      descField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

      JLabel path = new JLabel("New Image Path (please use the full path!)");
      path.setAlignmentX(Component.CENTER_ALIGNMENT);

      JTextField pathField = new JTextField();
      pathField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));


      // add all components to content
      content.add(title);
      content.add(titleField);
      content.add(Box.createVerticalStrut(8));
      content.add(desc);
      content.add(descField);
      content.add(Box.createVerticalStrut(8));
      content.add(path);
      content.add(pathField);

      PopupPanel.add(content, BorderLayout.CENTER);

      JPanel buttons = new JPanel(new BorderLayout());

      JButton save = new JButton("Save");
      JButton cancel = new JButton("Cancel");

      save.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              System.out.println("saved!");
            }
          });
      cancel.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              System.out.println("Deleted");
              layerPane.remove(PopupPanel);
              layerPane.repaint();
            }
          });
      buttons.add(save, BorderLayout.WEST);
      buttons.add(cancel, BorderLayout.EAST);

      PopupPanel.add(buttons, BorderLayout.SOUTH);

      // add the Map, POIs, and Popup to the layer pane
      layerPane = new JLayeredPane();
      layerPane.setPreferredSize(new Dimension(imgWidth, imgHeight));
      layerPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
      layerPane.add(POIPanel, JLayeredPane.PALETTE_LAYER);

      // make the layer pane scrollable (resizable) and add it to the tab
      JScrollPane scroll = new JScrollPane(layerPane);
      scroll.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
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

                // Ctrl + Left Click to create a POI
                if (e.isControlDown() && SwingUtilities.isLeftMouseButton(e)) {

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
                            layerPane.add(PopupPanel, JLayeredPane.POPUP_LAYER);
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
