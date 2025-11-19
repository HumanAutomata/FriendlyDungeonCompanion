package app.tabs;

import app.Role;
import app.logic.Map;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

public class MapTab extends JPanel {

  private boolean showGrid = false;
  private BufferedImage img;
  private JPanel panel;

  // @Override
  // void mouseMoved(MouseEvent e) {
  //   int x = e.getX();
  //   int y = e.getY();
  //   //System.out.println("X: " + x + ", Y: " + y);
  // }


  public MapTab(Role role /*BufferedImage image*/ ) {

    setLayout(new BorderLayout());

    try {
      img = ImageIO.read(new File("./state/baseMap.png"));

      JLayeredPane poiLayer = new JLayeredPane();
      poiLayer.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));

      // draw the image + optional grid
      panel =
          new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
              super.paintComponent(g);

              g.drawImage(img, (getWidth()  - img.getWidth())  / 2, (getHeight() - img.getHeight()) / 2, null);

              if (showGrid) {
                g.setColor(new Color(255, 0, 0, 120));
                int cell = 25;
                for (int x = (getWidth() - img.getWidth())  / 2; x < (img.getWidth() + getWidth()) / 2; x += cell) 
                  g.drawLine(x, (getHeight() - img.getHeight()) / 2, x, (img.getHeight() + getHeight()) / 2);
                for (int y = (getHeight() - img.getHeight()) / 2; y < (img.getHeight() + getHeight()) / 2; y += cell) 
                  g.drawLine( (getWidth() - img.getWidth())  / 2, y, (img.getWidth() + getWidth()) / 2, y);
              }
            }

            // Scrooling
            @Override
            public Dimension getPreferredSize() {
              return new Dimension(img.getWidth(), img.getHeight());
            }
          };

      
      JButton gridButton = new JButton("Toggle Grid");
      gridButton.addActionListener(e -> toggleGrid());

      JScrollPane scroll = new JScrollPane(panel);
      scroll.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

      JButton importMap = new JButton("importMap");

      add(importMap, BorderLayout.NORTH);
      add(scroll, BorderLayout.CENTER);

      if (role == Role.DM) {
        add(gridButton, BorderLayout.SOUTH);
        add(poiLayer);
        addPOI(poiLayer);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addPOI(JLayeredPane pane)  {
    pane.addMouseListener(new MouseAdapter() {
          @Override 
          public void mousePressed(MouseEvent e) {
            System.out.println(e.getX() + "," + e.getY());
            JButton button = new JButton("Button");
            button.setBounds(e.getX(), e.getY(), 30, 30);
            add(button);
            pane.setOpaque(false);
            pane.repaint();
          }
        });
  }

  public void toggleGrid() {
    showGrid = !showGrid;
    panel.repaint();
  }
  
}
