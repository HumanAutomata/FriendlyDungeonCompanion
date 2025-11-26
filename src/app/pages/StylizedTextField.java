package app.pages;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class StylizedTextField {

    public final Color APP_PURPLE = new Color(120, 80, 200);
    public final Color APP_GRAY = new Color(45, 43, 47);
    public final Color BORDER_COLOR = new Color(180, 180, 180);
    public final Color LABEL_BG = new Color(238, 238, 238); // Match typical panel background

    /**
     * Creates a styled text field panel with a floating label that overlaps the border.
     *
     * @param textField The JTextField to style
     * @param labelText The label text to display
     * @param width Preferred width (-1 for default)
     * @param labelBgColor Background color for the label (to match parent panel)
     * @return A JPanel containing the styled field with label
     */
    public JPanel createLabeledField(JTextField textField, String labelText, int width, Color labelBgColor) {
        final Color bgColor = labelBgColor != null ? labelBgColor : LABEL_BG;
        final int fieldWidth = width > 0 ? width : 150;

        // Style the text field - use wrapper panel to paint rounded background
        textField.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setOpaque(false);

        // Wrapper panel that paints rounded background behind the text field
        JPanel fieldWrapper = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fill rounded background
                g2d.setColor(textField.getBackground());
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

                // Draw rounded border
                g2d.setColor(BORDER_COLOR);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 10, 10);

                g2d.dispose();
            }
        };
        fieldWrapper.setOpaque(false);
        fieldWrapper.add(textField, BorderLayout.CENTER);

        // Create the floating label with split background - top matches panel, bottom matches field
        JLabel label = new JLabel(" " + labelText + " ") {
            @Override
            protected void paintComponent(Graphics g) {
                int h = getHeight();
                int w = getWidth();
                // Top half - panel background
                g.setColor(bgColor);
                g.fillRect(0, 0, w, h / 2);
                // Bottom half - text field background (white)
                g.setColor(textField.getBackground());
                g.fillRect(0, h / 2, w, h - h / 2);
                super.paintComponent(g);
            }
        };
        label.setFont(new Font("Arial", Font.PLAIN, 11));
        label.setForeground(new Color(100, 100, 100));
        label.setOpaque(false); // We handle painting ourselves

        // Use JLayeredPane for proper z-ordering
        JLayeredPane layeredPane = new JLayeredPane() {
            @Override
            public void doLayout() {
                int w = getWidth();
                int h = getHeight();

                FontMetrics fm = label.getFontMetrics(label.getFont());
                int labelWidth = fm.stringWidth(label.getText()) + 4;
                int labelHeight = fm.getHeight();
                int labelOffset = labelHeight / 2;

                // Position field wrapper to fill remaining space
                fieldWrapper.setBounds(0, labelOffset, w, h - labelOffset);

                // Position label at top-left, overlapping border
                label.setBounds(12, 0, labelWidth, labelHeight);
            }

            @Override
            public Dimension getPreferredSize() {
                FontMetrics fm = label.getFontMetrics(label.getFont());
                int labelWidth = fm.stringWidth(label.getText()) + 4 + 12;
                int labelHeight = fm.getHeight();
                int minWidth = Math.max(fieldWidth, labelWidth);
                return new Dimension(minWidth, 35 + labelHeight / 2);
            }

            @Override
            public Dimension getMinimumSize() {
                return getPreferredSize();
            }
        };
        layeredPane.setOpaque(false);

        // Add components to layers - higher number = on top
        layeredPane.add(fieldWrapper, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(label, JLayeredPane.PALETTE_LAYER);

        // Wrap in a panel for proper sizing in layouts
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.add(layeredPane, BorderLayout.CENTER);

        return container;
    }

    /**
     * Creates a styled text field panel with default width and background.
     */
    public JPanel createLabeledField(JTextField textField, String labelText) {
        return createLabeledField(textField, labelText, -1, null);
    }

    /**
     * Creates a styled text field panel with specified width.
     */
    public JPanel createLabeledField(JTextField textField, String labelText, int width) {
        return createLabeledField(textField, labelText, width, null);
    }

    /**
     * Applies rounded border styling to an existing text field (without label).
     */
    public void styleTextField(JTextField textField, int radius) {
        textField.setBorder(new RoundedBorder(radius, BORDER_COLOR, 2));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    /**
     * Custom rounded border for text fields - only draws the border outline.
     */
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color borderColor;
        private final int thickness;

        public RoundedBorder(int radius, Color borderColor, int thickness) {
            this.radius = radius;
            this.borderColor = borderColor;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Only draw the rounded border outline
            g2d.setColor(borderColor);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRoundRect(x + thickness/2, y + thickness/2,
                             width - thickness, height - thickness,
                             radius, radius);

            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(8, 12, 8, 12);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = 12;
            insets.top = 8;
            insets.right = 12;
            insets.bottom = 8;
            return insets;
        }
    }

    /**
     * Creates a styled text area panel with a floating label.
     */
    public JPanel createLabeledTextArea(JTextArea textArea, String labelText, int width, int height, Color labelBgColor) {
        final Color bgColor = labelBgColor != null ? labelBgColor : LABEL_BG;
        final int fieldWidth = width > 0 ? width : 200;
        final int fieldHeight = height;

        // Create scroll pane for text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);

        // Wrapper panel that paints rounded background behind the scroll pane
        JPanel scrollWrapper = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fill rounded background
                g2d.setColor(textArea.getBackground());
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

                // Draw rounded border
                g2d.setColor(BORDER_COLOR);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 10, 10);

                g2d.dispose();
            }
        };
        scrollWrapper.setOpaque(false);
        scrollWrapper.add(scrollPane, BorderLayout.CENTER);

        // Create the floating label with split background - top matches panel, bottom matches field
        JLabel label = new JLabel(" " + labelText + " ") {
            @Override
            protected void paintComponent(Graphics g) {
                int h = getHeight();
                int w = getWidth();
                // Top half - panel background
                g.setColor(bgColor);
                g.fillRect(0, 0, w, h / 2);
                // Bottom half - text area background (white)
                g.setColor(textArea.getBackground());
                g.fillRect(0, h / 2, w, h - h / 2);
                super.paintComponent(g);
            }
        };
        label.setFont(new Font("Arial", Font.PLAIN, 11));
        label.setForeground(new Color(100, 100, 100));
        label.setOpaque(false); // We handle painting ourselves

        // Use JLayeredPane for proper z-ordering
        JLayeredPane layeredPane = new JLayeredPane() {
            @Override
            public void doLayout() {
                int w = getWidth();
                int h = getHeight();

                FontMetrics fm = label.getFontMetrics(label.getFont());
                int labelWidth = fm.stringWidth(label.getText()) + 4;
                int labelHeight = fm.getHeight();
                int labelOffset = labelHeight / 2;

                // Position scroll wrapper to fill remaining space
                scrollWrapper.setBounds(0, labelOffset, w, h - labelOffset);

                // Position label at top-left, overlapping border
                label.setBounds(12, 0, labelWidth, labelHeight);
            }

            @Override
            public Dimension getPreferredSize() {
                FontMetrics fm = label.getFontMetrics(label.getFont());
                int labelWidth = fm.stringWidth(label.getText()) + 4 + 12;
                int labelHeight = fm.getHeight();
                int minWidth = Math.max(fieldWidth, labelWidth);
                return new Dimension(minWidth, fieldHeight + labelHeight / 2);
            }

            @Override
            public Dimension getMinimumSize() {
                return getPreferredSize();
            }
        };
        layeredPane.setOpaque(false);

        // Add components to layers - higher number = on top
        layeredPane.add(scrollWrapper, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(label, JLayeredPane.PALETTE_LAYER);

        // Wrap in a panel for proper sizing in layouts
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.add(layeredPane, BorderLayout.CENTER);

        return container;
    }
}
