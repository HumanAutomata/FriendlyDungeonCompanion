import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class BasicApp {
    public static void main(String[] args) {
        // Create and set up the window.
        JFrame frame = new JFrame("Friendly Dungeon Companion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // Create a button
        JButton button = new JButton("Click me");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Button clicked!");
            }
        });
 
        // Add the button to the frame
        frame.getContentPane().add(button);
 
        // Display the window.
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(false);
        frame.setVisible(true);
    }
}
