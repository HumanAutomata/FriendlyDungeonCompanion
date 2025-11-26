import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BasicApp {
  public static void main(String[] args) {
    // Create and set up the window.
    JFrame frame = new JFrame("Friendly Dungeon Companion");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);

    // Create a button
    JButton button = new JButton("Click me");
    button.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "Button clicked!");
          }
        });
    button.setBounds(40, 100, 100, 60);

    // Add the button to the frame
    JLabel text = new JLabel("Text");
    text.setText("Test");
    text.setBounds((frame.getWidth()) / 2, 20, 50, 20);
    frame.add(text);
    frame.getContentPane().add(button, BorderLayout.NORTH);

    // Display the window.
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Sets window default to fullscreen
    frame.setMinimumSize(new Dimension(640, 480)); // Specify the minimum panel size
    frame.setUndecorated(false);
    frame.setVisible(true);
  }
}
