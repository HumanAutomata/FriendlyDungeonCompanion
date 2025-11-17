package app.tabs;

import java.awt.*;
import java.nio.file.*;
import javax.swing.*;
import app.Role;
import app.logic.Notes;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NotesTab extends JPanel {

    //public Notes note;

    public NotesTab() {
        //note.load();
        Notes note = new Notes();

        setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea("Insert text here!");
        JScrollPane textContainer = new JScrollPane(textArea);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 18));
        textContainer.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        add(textContainer, BorderLayout.CENTER);

        JButton save = new JButton("Save");
        save.addActionListener(e -> note.save(textArea.getText()));
        add(save, BorderLayout.SOUTH);
    }
}
