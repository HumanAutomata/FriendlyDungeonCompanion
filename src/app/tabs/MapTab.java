package app.tabs;

import app.Role;
import app.MainFrame;
import app.pages.RoleSelectionPage;
import app.logic.POI;
import app.logic.POIHandler;
import app.logic.Map;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MapTab extends JPanel {

  private static final int PLACEHOLDER_WIDTH = 800;
  private static final int PLACEHOLDER_HEIGHT = 800;
  private static final int POPUP_WIDTH = 800;
  private static final int POPUP_HEIGHT = 500;
  private static final String WORLD_PATH = "./state/world/World.json";

  
  // poi tracking variables
  private POI rootPOI;
  private POI currentPOI;
  private JLabel poiTitleLabel;
  private JTextArea poiDescriptionArea;
  private int poiX;
  private int poiY;

  // stack variables so we can navigate through POI's
  private java.util.Stack<POI> navigationStack = new java.util.Stack<>();
  private JButton backButton;

  // pending button & editing poi (used while popup is open)
  private JButton pendingButton = null;
  private POI editingPOI = null;
  private Boolean editCurrentPOI = false;

  // popup panel variables
  private JLayeredPane layerPane;
  private JPanel poiPanel;
  private JPanel popupPanel;
  private JTextField titleField;
  private JTextField descField;
  private JTextField pathField;

  // edit-mode checkbox made a field so it can be referenced in helper methods
  private JCheckBox mapEditMode;
  
  // initalize map helper functions
  private Map mapHelper = new Map();
  private RoleSelectionPage rolePage = new RoleSelectionPage(null);

  // load image from filepath
  private BufferedImage loadMapImage(String path) {
    try {
      return ImageIO.read(new java.io.File(path)); 
    } catch (Exception ex) {
      //ex.printStackTrace();
      return null;
    }
  }

  // draws map image to pane
  private JPanel drawMap(BufferedImage image) {
    // if no map is found, handle accordingly
    if (image == null) {
      return drawNoMap();
    }
    JPanel imagePanel =
        new JPanel() {
          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // offset to draw in the middle
            int offsetWidth = (getWidth() - image.getWidth()) / 2;
            int offsetHeight = (getHeight() - image.getHeight()) / 2;
            g.drawImage(image, offsetWidth, offsetHeight, null);
          }

          // resizing
          @Override
          public Dimension getPreferredSize() {
            return new Dimension(image.getWidth(), image.getHeight());
          }
        };
    imagePanel.setOpaque(true);
    imagePanel.setBounds(0, 0, image.getWidth(), image.getHeight());
    return imagePanel;
  }

  // handles drawing maps when no map is found by providing placeholder
  private JPanel drawNoMap() {
  // create basic JPanel which simply says "No Map Loaded"
  JPanel placeholder = new JPanel() {
          @Override
          protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              g.setColor(Color.LIGHT_GRAY);
              g.fillRect(0, 0, getWidth(), getHeight());
              g.setColor(Color.DARK_GRAY);
              g.setFont(getFont().deriveFont(Font.BOLD, 24f));
              String msg = "No Map Loaded";
              FontMetrics fm = g.getFontMetrics();
              int x = (getWidth() - fm.stringWidth(msg)) / 2;
              int y = getHeight() / 2;
              g.drawString(msg, x, y);
          }
      };
      placeholder.setPreferredSize(new Dimension(PLACEHOLDER_WIDTH, PLACEHOLDER_HEIGHT));
      placeholder.setOpaque(true);
      placeholder.setBounds(0, 0, PLACEHOLDER_WIDTH, PLACEHOLDER_HEIGHT);
      return placeholder;
  }

  // handle logic when editing a POI depending on whether it exists or not
  private void editPOI(POI editingPOI, JButton pendingButton) {
  if (editingPOI != null && pendingButton != null) {
      // update existing POI
      editingPOI.title = titleField.getText();
      editingPOI.description = descField.getText();
      String copiedPath = mapHelper.copyImageToWorldFolder(pathField.getText());
      editingPOI.imagePath = copiedPath;
      // position unchanged (user edits metadata only)
    } else {
      // create new POI from pending coords
      String copiedPath = mapHelper.copyImageToWorldFolder(pathField.getText());
      POI newPOI =
          new POI(titleField.getText(), descField.getText(), copiedPath, poiX, poiY);
      currentPOI.children.add(newPOI);
      // attach POI to the pending button (if exists)
      if (pendingButton != null) {
        pendingButton.putClientProperty("poi", newPOI);
      }
    }
    // update World.json
    POIHandler.save(rootPOI, WORLD_PATH);
    // reset variables
    editingPOI = null;
    pendingButton = null;
  }

  // handle logic for editing the currently view POI
  private void editCurrentPOI(POI poi) {
    poi.title = titleField.getText();
    poi.description = descField.getText();
    String copiedPath = mapHelper.copyImageToWorldFolder(pathField.getText());
    poi.imagePath = copiedPath;
    POIHandler.save(rootPOI, WORLD_PATH);
  }

  private JScrollPane findScrollPane(Component c) {
    while (c != null) {
        if (c instanceof JScrollPane) {
            return (JScrollPane) c;
        }
        c = c.getParent();
    }
    return null;
  }

  // Get and draw all POIs for currently viewed POI
  private void drawPOIs() {
    if (poiPanel == null) 
      return;
    poiPanel.removeAll();
    for (POI child : currentPOI.children) {
      ImageIcon poiIcon = new ImageIcon("./assets/poi.png");
      Image scaled = poiIcon.getImage().getScaledInstance(28, 40, Image.SCALE_SMOOTH);
      JButton b = new JButton(new ImageIcon(scaled));
      b.setContentAreaFilled(false);
      b.setBorderPainted(false);
      b.setFocusPainted(false);
      b.setOpaque(false);
      b.setBounds(child.x - 12, child.y - 12, 28, 40);
      b.putClientProperty("poi", child);

      // small visual niceties
      b.setMargin(new Insets(0, 0, 0, 0));

      b.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              // add confirmation popup before deleting POI and delete POI accordingly
              if (SwingUtilities.isRightMouseButton(e)
                  && mapEditMode != null
                  && mapEditMode.isSelected()) {
                int confirm =
                    JOptionPane.showConfirmDialog(
                        MapTab.this, "Delete this POI?", "Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                  // remove from model, persist, redraw
                  currentPOI.children.remove(child);
                  POIHandler.save(rootPOI, WORLD_PATH);
                  drawPOIs();
                }
                return;
              }

              // either navigate to or create POI (if in edit mode)
              if (SwingUtilities.isLeftMouseButton(e)) {
                if (mapEditMode != null && mapEditMode.isSelected()) {
                  // if in edit mode, create POI
                  lockViewport(findScrollPane(poiPanel));
                  editingPOI = child;
                  pendingButton = b;
                  titleField.setText(child.title != null ? child.title : "");
                  descField.setText(child.description != null ? child.description : "");
                  pathField.setText(child.imagePath != null ? child.imagePath : "");
                  if (!layerPane.isAncestorOf(popupPanel)) {
                    popupPanel = setPopupSize(popupPanel, loadMapImage(currentPOI.imagePath), findScrollPane(poiPanel));
                    layerPane.add(popupPanel, JLayeredPane.POPUP_LAYER);
                  }
                  layerPane.repaint();
                } else {
                  // open this POI (navigate into it)
                  unlockViewport(findScrollPane(poiPanel));
                  navigationStack.push(currentPOI);
                  backButton.setEnabled(true);
                  backButton.setVisible(true);
                  openPOI(child);
                }
              }
            }
          });
      poiPanel.add(b);
    }
    poiPanel.revalidate();
    poiPanel.repaint();
  }

  // checks bounds of image so you can't create POIs outside of image
  private boolean isInsideImage(int x, int y, BufferedImage image, JPanel panel) {
    // calculate image bounds from centre of panel
    int offsetX = (panel.getWidth()- image.getWidth()) / 2;
    int offsetY = (panel.getHeight() - image.getHeight()) / 2;
    return x >= offsetX && x <= offsetX + image.getWidth() && y >= offsetY && y <= offsetY + image.getHeight();
  }

  // return the dimensions of the image as a dimension object
  private Dimension getImageDimensions(BufferedImage image) {
    if (image != null) 
      return new Dimension(image.getWidth(), image.getHeight());
    // if image is not found, return default size
    return new Dimension(PLACEHOLDER_WIDTH, PLACEHOLDER_HEIGHT);
  }

  // returns a popup panel which is located in the middle of the POI image
  private JPanel setPopupSize(JPanel panel, BufferedImage image, JScrollPane scrollPane) {
    int imageWidth = PLACEHOLDER_WIDTH;
        int imageHeight = PLACEHOLDER_HEIGHT;

        // If dimensions of image are too small, set to default size values
        if (image != null) {
            imageWidth = Math.max(image.getWidth(), PLACEHOLDER_WIDTH);
            imageHeight = Math.max(image.getHeight(), PLACEHOLDER_HEIGHT);
        }

        // Get the visible area of the scroll pane
        JViewport viewport = scrollPane.getViewport();
        Dimension viewportSize = viewport.getSize();
        Point viewPosition = viewport.getViewPosition();

        // Calculate the position for the popup in the center of the viewport
        int popupX = (viewportSize.width - POPUP_WIDTH) / 2 + viewPosition.x;
        int popupY = (viewportSize.height - POPUP_HEIGHT) / 2 + viewPosition.y;

        // Set the bounds for the popup panel
        panel.setBounds(popupX, popupY, POPUP_WIDTH, POPUP_HEIGHT);
        panel.setBackground(new Color(255, 255, 255, 240)); // Semi-transparent background
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        return panel;
    }

  // changes view to currently selected POI and draws its children POI
  private void openPOI(POI poi) {
    BufferedImage image = loadMapImage(poi.imagePath);
    try {
      currentPOI = poi;
      poiTitleLabel.setText(currentPOI.title);
      poiDescriptionArea.setText(currentPOI.description);
      image = loadMapImage(poi.imagePath);

      redraw(image);

      // reset poiPanel size and add back on top
      poiPanel = new JPanel(null);
      poiPanel.setOpaque(false);
      poiPanel.setBounds(0, 0, getImageDimensions(image).width, getImageDimensions(image).height);
      poiPanel.setPreferredSize(getImageDimensions(image));

      layerPane.setPreferredSize(getImageDimensions(image));
      layerPane.add(poiPanel, JLayeredPane.PALETTE_LAYER);

      // draw children of currentPOI
      drawPOIs();

      layerPane.revalidate();
      layerPane.repaint();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // rebuild the entirety of the panels
  private void redraw(BufferedImage image) {
    layerPane.removeAll();
    JPanel imagePanel = drawMap(image);
    layerPane.setPreferredSize(getImageDimensions(image));
    layerPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
  }

  // setup world file and initial POI at startup
  private void initializeWorld() {
    rootPOI = POIHandler.load(WORLD_PATH);
    // if we don't have an initial POI, create it and save it
    if (rootPOI == null) {
      rootPOI = new POI("World", "World Map", "", 0, 0);
      POIHandler.save(rootPOI, WORLD_PATH);
    }
    currentPOI = rootPOI;
  }
  
  // locks the viewport to the current orientation
  private void lockViewport(JScrollPane scrollPane) {
    scrollPane.setWheelScrollingEnabled(false);
    scrollPane.getHorizontalScrollBar().setEnabled(false);
    scrollPane.getVerticalScrollBar().setEnabled(false);
  }

  // unlocks the viewport to allow scrolling
  private void unlockViewport(JScrollPane scrollPane) {
    scrollPane.setWheelScrollingEnabled(true);
    scrollPane.getHorizontalScrollBar().setEnabled(true);
    scrollPane.getVerticalScrollBar().setEnabled(true);
  }

  public MapTab(Role role) {
    setLayout(new BorderLayout());

    // load POI and map image upon opening tab
    initializeWorld();

    // draw the image from the file
    JPanel imagePanel = drawMap(loadMapImage(currentPOI.imagePath));

    // draw the POI panel as a transparent layer above the image for display of POIs separate from image
    poiPanel = new JPanel(null);
    poiPanel.setOpaque(false);
    poiPanel.setBounds(
      0, 
      0, 
      getImageDimensions(loadMapImage(currentPOI.imagePath)).width, 
      getImageDimensions(loadMapImage(currentPOI.imagePath)).height
    );

    // assemble layered pane (whole image)
    layerPane = new JLayeredPane();
    layerPane.setPreferredSize(getImageDimensions(loadMapImage(currentPOI.imagePath)));
    layerPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
    layerPane.add(poiPanel, JLayeredPane.PALETTE_LAYER);

    // make the layer pane scrollable (resizable) and add it to the tab
    JScrollPane scroll = new JScrollPane(layerPane);
    scroll.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    scroll.setPreferredSize(new Dimension(1000, 1000));

/////////////////////////////////////// Popup Panel ///////////////////////////////////////////////////////////////
    popupPanel = new JPanel(new BorderLayout());
    popupPanel = setPopupSize(popupPanel, loadMapImage(currentPOI.imagePath), scroll);

    // title of POI
    JLabel popupTitle = new JLabel("Point of Interest");
    popupTitle.setFont(popupTitle.getFont().deriveFont(Font.BOLD, 24f));
    popupTitle.setHorizontalAlignment(SwingConstants.CENTER);
    popupPanel.add(popupTitle, BorderLayout.NORTH);

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

    popupPanel.add(content, BorderLayout.CENTER);

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
            if (!editCurrentPOI) {
              editPOI(editingPOI, pendingButton);
            } else {
              editCurrentPOI(currentPOI);;
              editCurrentPOI = false;
              openPOI(currentPOI);
            }
            layerPane.remove(popupPanel);
            unlockViewport(scroll);
            drawPOIs();
            layerPane.repaint();
          }
        });

    cancel.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            // if user selects cancel, remove pending placeholder button
            if (editCurrentPOI)
              editCurrentPOI = false;
            if (pendingButton != null && editingPOI == null) {
              poiPanel.remove(pendingButton);
              pendingButton = null;
            }
            editingPOI = null;
            layerPane.remove(popupPanel);
            unlockViewport(scroll);
            layerPane.repaint();
          }
        });

    buttons.add(save, BorderLayout.WEST);
    buttons.add(cancel, BorderLayout.EAST);
    popupPanel.add(buttons, BorderLayout.SOUTH);

//////////////////////////////////////////////////////////////////////////////////////////////////////

  

    // POI info panel displayed on right-hand side of screen
    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
    infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    poiTitleLabel = new JLabel(currentPOI.title);
    poiTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
    poiTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    infoPanel.add(poiTitleLabel, BorderLayout.NORTH);
    infoPanel.add(Box.createVerticalStrut(20));

    poiDescriptionArea = new JTextArea(currentPOI.description);
    poiDescriptionArea.setEditable(false);
    poiDescriptionArea.setLineWrap(true);
    poiDescriptionArea.setWrapStyleWord(true);
    poiDescriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
    infoPanel.add(new JScrollPane(poiDescriptionArea), BorderLayout.CENTER);

    // Left side = map (2/3), Right side = info (1/3)
    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, infoPanel);
    split.setResizeWeight(0.66); // left (map) gets 2/3 space
    add(split, BorderLayout.CENTER);

    // toolbar at top of map tab
    JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JButton importButton = new JButton("Import World");
    JButton exportButton = new JButton("Export World");
    JButton editPOIButton = new JButton("Edit Current Layer");

    // initialize buttons with fancy UI
    rolePage.makeRounded(importButton, 20, rolePage.APP_RED, 18, 20, 10);
    rolePage.makeRounded(exportButton, 20, rolePage.APP_RED, 18, 20, 10);
    rolePage.makeRounded(editPOIButton, 20, rolePage.APP_RED, 18, 20, 10);

    topBar.add(importButton);
    topBar.add(exportButton);

    add(topBar, BorderLayout.NORTH);

    // IMPORT: choose a zip file and extract into ./state/
    importButton.addActionListener(
        e -> {
          JFileChooser chooser = new JFileChooser();
          chooser.setDialogTitle("Select World ZIP File");
          int result = chooser.showOpenDialog(MapTab.this);

          if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File zipFile = chooser.getSelectedFile();
            try {
              mapHelper.extractZip(zipFile.getAbsolutePath(), "./state/world");
              initializeWorld();
              openPOI(currentPOI); // reload current POI screen
            } catch (Exception ex) {
              ex.printStackTrace();
              JOptionPane.showMessageDialog(MapTab.this, "Import failed: " + ex.getMessage());
            }
          }
        });

    // EXPORT: zip the ./state/ folder
    exportButton.addActionListener(
        e -> {
          JFileChooser chooser = new JFileChooser();
          chooser.setDialogTitle("Save World ZIP");
          chooser.setSelectedFile(new java.io.File("world.zip"));

          int result = chooser.showSaveDialog(MapTab.this);
          if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File outputZip = chooser.getSelectedFile();
            try {
              mapHelper.zipFolder("./state/world", outputZip.getAbsolutePath());
              JOptionPane.showMessageDialog(
                  MapTab.this, "Exported to:\n" + outputZip.getAbsolutePath());
            } catch (Exception ex) {
              ex.printStackTrace();
              JOptionPane.showMessageDialog(MapTab.this, "Export failed: " + ex.getMessage());
            }
          }
        });
    
    editPOIButton.addActionListener(
      e -> {
        lockViewport(scroll);
        titleField.setText(currentPOI.title != null ? currentPOI.title : "");
        descField.setText(currentPOI.description != null ? currentPOI.description : "");
        pathField.setText(currentPOI.imagePath != null ? currentPOI.imagePath : "");
        editCurrentPOI = true; // set that we are editing a POI
        popupPanel = setPopupSize(popupPanel, loadMapImage(currentPOI.imagePath), scroll);
        layerPane.add(popupPanel, JLayeredPane.POPUP_LAYER);
        drawPOIs(); // redraw POIs once done
      }
    );

    mapEditMode = new JCheckBox("Edit Mode");
    mapEditMode.setFont(new Font("Arial", Font.PLAIN, 16));
    mapEditMode.setEnabled(false);
    mapEditMode.setVisible(false);

    add(mapEditMode, BorderLayout.SOUTH);

    // create bottom bar
    JPanel bottomBar = new JPanel(new BorderLayout());
    bottomBar.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // back button in bottom left
    backButton = new JButton("Back");
    rolePage.makeRounded(backButton, 20, rolePage.APP_GRAY, 16, 16, 8);
    backButton.setEnabled(false); // nothing to go back to yet, disable button
    backButton.setVisible(false); // set back button as invisible when we are at root POI
    bottomBar.add(backButton, BorderLayout.WEST);

    // edit mode checkbox
    bottomBar.add(mapEditMode, BorderLayout.EAST);

    add(bottomBar, BorderLayout.SOUTH);

    // back button logic
    backButton.addActionListener(
        e -> {
          if (!navigationStack.isEmpty()) {
            POI previous = navigationStack.pop();
            openPOI(previous);

            // disable if we are back at root
            if (previous == rootPOI) {
              backButton.setEnabled(false);
              backButton.setVisible(false);
            }
          }
        });

    // DM only actions (edit POIs)
    if (role == Role.DM) {
      // enable and set visible button to go into edit mode
      mapEditMode.setEnabled(true);
      mapEditMode.setVisible(true);
      topBar.add(editPOIButton);
      bottomBar.add(mapEditMode);

      // add mouse listener to layerPane for ctrl+left create
      layerPane.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              // Ctrl + Left Click to create a POI (and only if edit mode selected)
              if (e.isControlDown()
                  && SwingUtilities.isLeftMouseButton(e)
                  && mapEditMode.isSelected()) {

                // convert to poiPanel coords so placement lines up with overlay
                Point overlayPoint = SwingUtilities.convertPoint(layerPane, e.getPoint(), poiPanel);
                poiX = overlayPoint.x;
                poiY = overlayPoint.y;

                if (isInsideImage(poiX, poiY, loadMapImage(currentPOI.imagePath), poiPanel)) {
                  lockViewport(scroll);
                  // create a placeholder button at that location
                  ImageIcon poiIcon = new ImageIcon("./assets/poi.png");
                  Image scaled = poiIcon.getImage().getScaledInstance(28, 40, Image.SCALE_SMOOTH);
                  JButton b = new JButton(new ImageIcon(scaled));
                  b.setContentAreaFilled(false);
                  b.setBorderPainted(false);
                  b.setFocusPainted(false);
                  b.setOpaque(false);
                  b.setBounds(poiX - 12, poiY - 12, 28, 40);
                  // visually make it obvious
                  b.setMargin(new Insets(0, 0, 0, 0));
                  poiPanel.add(b);
                  poiPanel.repaint();

                  // store pending button reference so Save can attach a POI
                  pendingButton = b;
                  editingPOI = null;

                  // show empty popup for user to fill details
                  titleField.setText("");
                  descField.setText("");
                  pathField.setText("");

                  // if the popup panel doesn't exist yet, add it
                  if (!layerPane.isAncestorOf(popupPanel)) {
                    popupPanel = setPopupSize(popupPanel, loadMapImage(currentPOI.imagePath), scroll);
                    layerPane.add(popupPanel, JLayeredPane.POPUP_LAYER);
                  }
                  layerPane.repaint();
                }
              }
            }
          });
    } 
    // initial draw of existing POIs for currentPOI
    drawPOIs();
  }
}
