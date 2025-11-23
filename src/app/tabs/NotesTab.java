package app.tabs;

import app.logic.Notes;
import app.pages.RoleSelectionPage;
import java.awt.*;
import java.nio.file.*;
import javax.swing.*;

public class NotesTab extends JPanel {

  RoleSelectionPage rolePage = new RoleSelectionPage(null);

  public NotesTab() {

    Notes note = new Notes();

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    JTextArea textArea = new JTextArea("Insert text here!");
    textArea.setText(note.load());
    JScrollPane textContainer = new JScrollPane(textArea);
    textArea.setFont(new Font("Arial", Font.PLAIN, 18));

    textContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    textArea.setBackground(rolePage.APP_LIGHT_PURPLE);
    textArea.setBorder(BorderFactory.createLineBorder(rolePage.APP_LIGHT_PURPLE, 16, true));

    add(textContainer);

    JButton save = new JButton("Save");
    rolePage.makeRounded(save, 20, rolePage.APP_PURPLE, 32, 45,15);
    save.addActionListener(e -> note.save(textArea.getText()));
    add(save);
    add(Box.createVerticalStrut(32));
  }
}
