package app.tabs;

import app.Role;
import app.logic.POI;
import app.logic.POIHandler;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * MapTab with POI creation/editing/navigation integrated. Drop-in replacement for your existing
 * MapTab.
 */
public class MapTab extends JPanel {

  private BufferedImage img;
  private JLayeredPane layerPane;
  private JPanel POIPanel;
  private JPanel PopupPanel;
  private int imgWidth;
  private int imgHeight;
  private int popupWidth = 800;
  private int popupHeight = 500;

  private int poiX;
  private int poiY;
  private POI rootPOI;
  private POI currentPOI;
  private JLabel poiTitleLabel;
  private JTextArea poiDescriptionArea;

  // popup fields promoted so save listener can access them
  private JTextField titleField;
  private JTextField descField;
  private JTextField pathField;

  // edit-mode checkbox made a field so it can be referenced in helper methods
  private JCheckBox mapEditMode;

  // pending button & editing poi (used while popup is open)
  private JButton pendingButton = null;
  private POI editingPOI = null;

  private java.util.Stack<POI> navigationStack = new java.util.Stack<>();
  private JButton backButton;

  private void loadMapImage(String path) {
    try {
      img = ImageIO.read(new java.io.File(path));
    } catch (Exception ex) {
      ex.printStackTrace();
      img = null;
    }
  }

  private JPanel drawImage() {
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
    return imagePanel;
  }

  public MapTab(Role role) {
    setLayout(new BorderLayout()); // divide tab into center and 4 quadrants

    // load root POI structure
    rootPOI = POIHandler.load("./state/world/World.json");
    if (rootPOI == null) {
      rootPOI = new POI("World", "World Map", "./state/baseMap.png", 0, 0);
    }
    currentPOI = rootPOI;

    // load current map image
    loadMapImage(currentPOI.imagePath);

    // draw the image from the file
    JPanel imagePanel = drawImage();

    // draw the POI panel (transparent overlay)
    POIPanel = new JPanel(null);
    POIPanel.setOpaque(false);
    POIPanel.setBounds(0, 0, imgWidth, imgHeight);

    // build the Popup panel (reused for create/edit)
    PopupPanel = new JPanel(new BorderLayout());
    setPopupSize(PopupPanel);

    // title
    JLabel popupTitle = new JLabel("Point of Interest");
    popupTitle.setFont(popupTitle.getFont().deriveFont(Font.BOLD, 24f));
    popupTitle.setHorizontalAlignment(SwingConstants.CENTER);
    PopupPanel.add(popupTitle, BorderLayout.NORTH);

    // center content (vertical)
    JPanel content = new JPanel();
    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

    JLabel titleLabel = new JLabel("Title");
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    titleField = new JTextField();
    titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

    JLabel descLabel = new JLabel("Description");
    descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    descField = new JTextField();
    descField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

    JLabel pathLabel = new JLabel("New Image Path (please use the full path!)");
    pathLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    pathField = new JTextField();
    pathField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

    JButton fileButton = new JButton("Choose Image File");
    fileButton.addActionListener(
        e -> {
          JFileChooser chooser = new JFileChooser();
          chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
          int imagePath = chooser.showOpenDialog(MapTab.this);
          if (imagePath == JFileChooser.APPROVE_OPTION) {
            pathField.setText(chooser.getSelectedFile().getAbsolutePath());
          }
        });

    JPanel pathRow = new JPanel();
    pathRow.setLayout(new BorderLayout());
    pathRow.add(pathField, BorderLayout.CENTER);
    pathRow.add(fileButton, BorderLayout.EAST);

    content.add(titleLabel);
    content.add(titleField);
    content.add(Box.createVerticalStrut(8));
    content.add(descLabel);
    content.add(descField);
    content.add(Box.createVerticalStrut(8));
    content.add(pathLabel);
    content.add(pathRow);

    PopupPanel.add(content, BorderLayout.CENTER);

    // Save / Cancel buttons
    JPanel buttons = new JPanel(new BorderLayout());
    buttons.setOpaque(false);
    JButton save = new JButton("Save");
    JButton cancel = new JButton("Cancel");

    save.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            // If editing an existing POI, update it; otherwise create a new POI
            if (editingPOI != null && pendingButton != null) {
              // update existing POI
              editingPOI.title = titleField.getText();
              editingPOI.description = descField.getText();
              String copiedPath = copyImageToWorldFolder(pathField.getText());
              editingPOI.imagePath = copiedPath;
              // position unchanged (user edits metadata only)
            } else {
              // create new POI from pending coords
              String copiedPath = copyImageToWorldFolder(pathField.getText());
              POI newPOI =
                  new POI(
                      titleField.getText(), descField.getText(), copiedPath, poiX, poiY);
              currentPOI.children.add(newPOI);

              // attach POI to the pending button (if exists)
              if (pendingButton != null) {
                pendingButton.putClientProperty("poi", newPOI);
              }
            }

            // persist entire tree
            POIHandler.save(rootPOI, "./state/world/World.json");

            // cleanup
            editingPOI = null;
            pendingButton = null;
            layerPane.remove(PopupPanel);
            drawPOIs();
            layerPane.repaint();
          }
        });

    cancel.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            // if user cancelled creation, remove pending placeholder button
            if (pendingButton != null && editingPOI == null) {
              POIPanel.remove(pendingButton);
              pendingButton = null;
            }
            editingPOI = null;
            layerPane.remove(PopupPanel);
            layerPane.repaint();
          }
        });

    buttons.add(save, BorderLayout.WEST);
    buttons.add(cancel, BorderLayout.EAST);
    PopupPanel.add(buttons, BorderLayout.SOUTH);

    // assemble layered pane
    layerPane = new JLayeredPane();
    layerPane.setPreferredSize(new Dimension(imgWidth, imgHeight));
    layerPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
    layerPane.add(POIPanel, JLayeredPane.PALETTE_LAYER);

    // make the layer pane scrollable (resizable) and add it to the tab
    JScrollPane scroll = new JScrollPane(layerPane);
    scroll.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    scroll.setPreferredSize(new Dimension(1000, 1000));

    // --- Right-side POI info panel ---
    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BorderLayout());
    infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    poiTitleLabel = new JLabel(currentPOI.title);
    poiTitleLabel.setFont(poiTitleLabel.getFont().deriveFont(Font.BOLD, 22f));
    infoPanel.add(poiTitleLabel, BorderLayout.NORTH);

    poiDescriptionArea = new JTextArea(currentPOI.description);
    poiDescriptionArea.setEditable(false);
    poiDescriptionArea.setLineWrap(true);
    poiDescriptionArea.setWrapStyleWord(true);
    poiDescriptionArea.setFont(new Font("Serif", Font.PLAIN, 16));
    infoPanel.add(new JScrollPane(poiDescriptionArea), BorderLayout.CENTER);

    // Left side = map (2/3), Right side = info (1/3)
    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, infoPanel);
    split.setResizeWeight(0.66); // left (map) gets 2/3 space
    add(split, BorderLayout.CENTER);

    // top import button (unchanged)
    JButton importMap = new JButton("importMap");
    add(importMap, BorderLayout.NORTH);

    mapEditMode = new JCheckBox("Edit Map");
    mapEditMode.setEnabled(false);

    add(mapEditMode, BorderLayout.SOUTH);

    // create bottom bar
    JPanel bottomBar = new JPanel(new BorderLayout());
    bottomBar.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // Back button (left)
    backButton = new JButton("Back");
    backButton.setEnabled(false); // nothing to go back to yet
    bottomBar.add(backButton, BorderLayout.WEST);

    // Edit Map checkbox (right)
    bottomBar.add(mapEditMode, BorderLayout.EAST);

    add(bottomBar, BorderLayout.SOUTH);

    // --- Back button action ---
    backButton.addActionListener(
        e -> {
          if (!navigationStack.isEmpty()) {
            POI previous = navigationStack.pop();
            openPOI(previous);

            // disable if we are back at root
            if (previous == rootPOI) {
              backButton.setEnabled(false);
            }
          }
        });

    // allow the DM to manage POIs
    if (role == Role.DM) {
      // enable button to go into edit mode
      mapEditMode.setEnabled(true);

      // add mouse listener to layerPane for ctrl+left create
      layerPane.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              // Ctrl + Left Click to create a POI (and only if edit mode selected)
              if (e.isControlDown()
                  && SwingUtilities.isLeftMouseButton(e)
                  && mapEditMode.isSelected()) {

                // convert to POIPanel coords so placement lines up with overlay
                Point overlayPoint = SwingUtilities.convertPoint(layerPane, e.getPoint(), POIPanel);
                poiX = overlayPoint.x;
                poiY = overlayPoint.y;

                if (isInsideImage(poiX, poiY)) {

                  // create a placeholder button at that location
                  JButton b = new JButton("X");
                  b.setBounds(poiX - 10, poiY - 10, 25, 25);
                  // visually make it obvious
                  b.setMargin(new Insets(0, 0, 0, 0));
                  POIPanel.add(b);
                  POIPanel.repaint();

                  // store pending button reference so Save can attach a POI
                  pendingButton = b;
                  editingPOI = null;

                  // show empty popup for user to fill details
                  titleField.setText("");
                  descField.setText("");
                  pathField.setText("");

                  if (!layerPane.isAncestorOf(PopupPanel)) {

                    setPopupSize(PopupPanel);
                    layerPane.add(PopupPanel, JLayeredPane.POPUP_LAYER);
                  }
                  layerPane.repaint();
                }
              }
            }
          });
    } // initial draw of existing POIs for currentPOI

    drawPOIs();
  }

  private String copyImageToWorldFolder(String originalPath) {
    try {
      java.io.File src = new java.io.File(originalPath);
      if (!src.exists()) return originalPath;

      // Create ./state/world directory if missing
      java.io.File worldDir = new java.io.File("./state/world");
      if (!worldDir.exists()) worldDir.mkdirs();

      // Build destination path
      java.io.File dest = new java.io.File(worldDir, src.getName());

      // Copy file
      java.nio.file.Files.copy(
          src.toPath(), dest.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

      // Return path you want stored in JSON
      return "./state/world/" + src.getName();

    } catch (Exception ex) {
      ex.printStackTrace();
      return originalPath; // fallback
    }
  }

  /** Redraws all POI buttons for currentPOI. */
  private void drawPOIs() {
    if (POIPanel == null) return;

    POIPanel.removeAll();

    for (POI child : currentPOI.children) {
      JButton b = new JButton("X");
      b.setBounds(child.x - 10, child.y - 10, 25, 25);
      b.putClientProperty("poi", child);

      // small visual niceties
      b.setMargin(new Insets(0, 0, 0, 0));

      // clicks on this button:
      b.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              // Right-click in edit mode = delete this POI
              if (SwingUtilities.isRightMouseButton(e)
                  && mapEditMode != null
                  && mapEditMode.isSelected()) {
                int confirm =
                    JOptionPane.showConfirmDialog(
                        MapTab.this, "Delete this POI?", "Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                  // remove from model, persist, redraw
                  currentPOI.children.remove(child);
                  POIHandler.save(rootPOI, "./state/world/World.json");
                  drawPOIs();
                }
                return;
              }

              // Left-click: if in edit mode -> edit metadata; otherwise open POI map
              if (SwingUtilities.isLeftMouseButton(e)) {
                if (mapEditMode != null && mapEditMode.isSelected()) {
                  // edit this POI: show popup pre-filled
                  editingPOI = child;
                  pendingButton = b;
                  titleField.setText(child.title != null ? child.title : "");
                  descField.setText(child.description != null ? child.description : "");
                  pathField.setText(child.imagePath != null ? child.imagePath : "");
                  if (!layerPane.isAncestorOf(PopupPanel)) {
                    setPopupSize(PopupPanel);
                    layerPane.add(PopupPanel, JLayeredPane.POPUP_LAYER);
                  }
                  layerPane.repaint();
                } else {
                  // open this POI (navigate into it)
                  navigationStack.push(currentPOI);
                  backButton.setEnabled(true);
                  openPOI(child);
                }
              }
            }
          });

      POIPanel.add(b);
    }

    POIPanel.revalidate();
    POIPanel.repaint();
  }

  private boolean isInsideImage(int x, int y) {
    // imagePanel size
    int panelW = POIPanel.getWidth();
    int panelH = POIPanel.getHeight();

    int imgW = img.getWidth();
    int imgH = img.getHeight();

    // image is centered — compute top-left corner
    int offsetX = (panelW - imgW) / 2;
    int offsetY = (panelH - imgH) / 2;

    return x >= offsetX && x <= offsetX + imgW && y >= offsetY && y <= offsetY + imgH;
  }

  private void setPopupSize(JPanel panel) {
    int offsetWidth = (imgWidth - popupWidth) / 2;
    int offsetHeight = (imgHeight - popupHeight) / 2;
    panel.setBounds(offsetWidth, offsetHeight, popupWidth, popupHeight);
    panel.setBackground(new Color(255, 255, 255, 240));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  /** Switches the current view to the given POI (loads its image and children). */
  private void openPOI(POI poi) {
    try {
      currentPOI = poi;
      poiTitleLabel.setText(currentPOI.title);
      poiDescriptionArea.setText(currentPOI.description);
      loadMapImage(poi.imagePath);

      // recalc sizes and rebuild image layer
      imgWidth = img.getWidth();
      imgHeight = img.getHeight();

      // rebuild image panel
      layerPane.removeAll();
      JPanel imagePanel = drawImage();
      layerPane.setPreferredSize(new Dimension(imgWidth, imgHeight));

      layerPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);

      // reset POIPanel size and add back on top
      POIPanel = new JPanel(null);
      POIPanel.setOpaque(false);
      POIPanel.setBounds(0, 0, imgWidth, imgHeight);
      POIPanel.setPreferredSize(new Dimension(imgWidth, imgHeight));

      layerPane.setPreferredSize(new Dimension(imgWidth, imgHeight));
      layerPane.add(POIPanel, JLayeredPane.PALETTE_LAYER);

      // draw children of the new currentPOI
      drawPOIs();

      layerPane.revalidate();
      layerPane.repaint();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
