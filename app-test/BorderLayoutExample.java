import javax.swing.*;
import java.awt.*;
 
public class BorderLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Border Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JButton northButton = new JButton("North");
        JButton southButton = new JButton("South");
        JButton eastButton = new JButton("East");
        JButton westButton = new JButton("West");
        JButton centerButton = new JButton("Center");
 
        frame.getContentPane().add(northButton, BorderLayout.NORTH);
        frame.getContentPane().add(southButton, BorderLayout.SOUTH);
        frame.getContentPane().add(eastButton, BorderLayout.EAST);
        frame.getContentPane().add(eastButton, BorderLayout.EAST);
        frame.getContentPane().add(westButton, BorderLayout.WEST);
        frame.getContentPane().add(centerButton, BorderLayout.CENTER);
 
        //frame.pack();
        frame.setSize(2000,1200);
        frame.setVisible(true);
    }
}
