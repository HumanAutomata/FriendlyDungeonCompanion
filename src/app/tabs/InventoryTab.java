package app.tabs;

import javax.swing.*;
import app.Role;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class InventoryTab extends JPanel {

    public InventoryTab(Role role) {

        setLayout(new BorderLayout());


        JPanel main = new JPanel();
        main.setLayout(new GridLayout(2, 7, 5, 5));

        String[] tableNames = {
                "WEAPONS", "WEARABLES", "SPECIAL / PERSONAL", "CONSUMABLES",
                "COMPONENTS", "DOCUMENTS", "BAGS / CONTAINERS", "AMMUNITION", "MAGIC ITEMS",
                "ATTUNED", "LOOT", "MISCELLANEOUS"
        };


        Map<String, JTextField[][]> inventory = new HashMap<>();

        JTextField[][] funds = new JTextField[5][1];
        inventory.put("FUNDS", funds);
        main.add(tableBuilderPanel("FUNDS", funds));

        for (String name : tableNames) {
            JTextField[][] fields = new JTextField[20][2];
            inventory.put(name, fields);
            main.add(tableBuilderPanel(name, fields));
        }

        JPanel savePanel = new JPanel(new BorderLayout());
        JButton saveButton = new JButton("SAVE");
        saveButton.addActionListener(e -> {

            // SAVE BUTTON CODE
           

        });
        savePanel.add(saveButton, BorderLayout.NORTH);
        main.add(savePanel);

        add(main);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////            TABLE BUILDER              //////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    JPanel tableBuilderPanel(String title, JTextField[][] table) {

        JPanel outer = new JPanel(new BorderLayout());
        outer.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        outer.add(titleLabel, BorderLayout.NORTH);

        if (title == "FUNDS") {

            JPanel fundsNamePanel = new JPanel(new GridLayout(5, 1));
            JPanel fundsDataPanel = new JPanel(new GridLayout(5, 1));
            String[] fundsNames = {"CP", "SP", "EP", "GP", "PP"};

            for (String name : fundsNames) {
                JLabel fundNameLabel = new JLabel(name, SwingConstants.CENTER);
                fundNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                fundsNamePanel.add(fundNameLabel);
            }

            for (int i = 0; i < 5; i++) {
                table[i][0] = new JTextField();
                table[i][0].setHorizontalAlignment(JTextField.CENTER);
                table[i][0].setFont(new Font("Arial", Font.PLAIN, 24));
                fundsDataPanel.add(table[i][0]);
            }

            outer.add(fundsNamePanel, BorderLayout.WEST);
            outer.add(fundsDataPanel, BorderLayout.CENTER);


            return outer;

        } else {


            JPanel grid = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(0, 0, 0, 0);
            gbc.fill = GridBagConstraints.BOTH;

            for (int r = 0; r < table.length; r++) {
                for (int c = 0; c < table[r].length; c++) {

                    table[r][c] = new JTextField();

                    gbc.gridx = c;
                    gbc.gridy = r;

                    if (c == 0) {
                        gbc.weightx = 1.0;   // LEFT COLUMN SIZE
                    } else {
                        gbc.weightx = 0.25;  //RIGHT COLUMN SIZE
                    }

                    grid.add(table[r][c], gbc);
                }
            }

            JScrollPane scrollPane = new JScrollPane(grid);
            scrollPane.setBorder(null);

            outer.add(scrollPane, BorderLayout.CENTER);
            return outer;

        }
    }
}

