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
import javax.swing.JLayeredPane;

public class MapTab extends JPanel {

  private boolean showGrid = false;
  private BufferedImage img;
  private JLayeredPane panel;

  // @Override
  // void mouseMoved(MouseEvent e) {
  //   int x = e.getX();
  //   int y = e.getY();
  //   //System.out.println("X: " + x + ", Y: " + y);
  // }


  public MapTab(Role role /*BufferedImage image*/ ) {

    setLayout(new BorderLayout());

    JLayeredPane jLayeredPane = new JLayeredPane();

    try {
      img = ImageIO.read(new File("./state/baseMap.png"));

      // draw the image + optional grid
      panel = new JLayeredPane() {
        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          g.drawImage(img, (getWidth()  - img.getWidth())  / 2, (getHeight() - img.getHeight()) / 2, null);
        }

        // Scrooling
        @Override
        public Dimension getPreferredSize() {
          return new Dimension(img.getWidth(), img.getHeight());
        }
      };

      JScrollPane scroll = new JScrollPane(panel);
      scroll.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

      JButton importMap = new JButton("importMap");

      //add(importMap, BorderLayout.NORTH);
      jLayeredPane.add(scroll, JLayeredPane.DEFAULT_LAYER);
      scroll.setVisible(true);

      if (role == Role.DM) {
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
            pane.repaint();
          }
        });
  }

  public void toggleGrid() {
    showGrid = !showGrid;
    panel.repaint();
  }
  
}
