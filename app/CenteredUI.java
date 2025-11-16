import javax.swing.*;
import java.awt.*;

public class CenteredUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Friendly Dungeon Companion");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Everything centered horizontally
            panel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel title = new JLabel("Your Friendly Dungeon Companion");
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel login = new JLabel("Please Select Your Role:");
            login.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton player = new JButton("Player");
            player.setAlignmentX(Component.CENTER_ALIGNMENT);
            player.setFont(new Font("Arial", Font.PLAIN, 50));
            //btn1.setPreferredSize(new Dimension(100, 100));;

            JButton dm = new JButton("Dungeon Master");
            dm.setAlignmentX(Component.CENTER_ALIGNMENT);
            dm.setFont(new Font("Arial", Font.PLAIN, 50));

            // Add spacing + centering
            panel.add(Box.createVerticalGlue());
            panel.add(title);
            //panel.add(Box.createVerticalGlue());
            panel.add(Box.createRigidArea(new Dimension(0, 150))); // spacing
            panel.add(login);
            panel.add(Box.createRigidArea(new Dimension(0, 50))); // spacing
            panel.add(player);
            panel.add(Box.createRigidArea(new Dimension(0, 50))); // spacing
            panel.add(dm);
            panel.add(Box.createVerticalGlue());

            frame.add(panel);

            frame.setLocationRelativeTo(null);

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Sets window default to fullscreen
            frame.setMinimumSize(new Dimension(640, 480)); // Specify the minimum panel size
            frame.setUndecorated(false);
            frame.setVisible(true);
        });
    }
}