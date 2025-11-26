package app.tabs;

import app.Role;
import app.pages.StylizedButton;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InventoryTab extends JPanel {

    public InventoryTab(Role role) {

        setLayout(new BorderLayout());

        StylizedButton sb = new StylizedButton();


        JPanel main = new JPanel();
        main.setLayout(new GridLayout(2, 7, 5, 5));

        String[] fundsNames = {"CP", "SP", "EP", "GP", "PP"};

        String[] tableNames = {
                "FUNDS", "WEAPONS", "WEARABLES", "SPECIAL", "CONSUMABLES",
                "COMPONENTS", "DOCUMENTS", "CONTAINERS", "AMMUNITION", "MAGIC ITEMS",
                "ATTUNED", "LOOT", "MISC"
        };

        /// /////////////////////////////////////////////////////////////////////////////ADD ALL TABLES TO MAP

        Map<String, JTextField[][]> inventory = new HashMap<>();

        JTextField[][] funds = new JTextField[5][1];
        inventory.put("FUNDS", funds);
        main.add(tableBuilderPanel("FUNDS", funds));

        for (String name : tableNames) {
            if (name.equals("FUNDS")) {continue;}
            if (name.equals("ATTUNED")) {
                JTextField[][] fields = new JTextField[3][2];
                inventory.put(name, fields);
                main.add(tableBuilderPanel(name, fields));
            }
            else {
                JTextField[][] fields = new JTextField[20][2];
                inventory.put(name, fields);
                main.add(tableBuilderPanel(name, fields));
            }
        }

        /// /////////////////////////////////////////////////////////////////////////////////////SAVE BUTTON

        JPanel savePanel = new JPanel(new BorderLayout());
        JButton saveButton = new JButton("SAVE");
        sb.makeRounded(saveButton,10, sb.APP_RED, 16, 10, 5);
        saveButton.addActionListener(e -> {

            System.out.println(inventory.get("LOOT")[5][1].getText());
            System.out.println(Arrays.deepToString(inventory.get("LOOT")));

            /// //////////// GET TEXT FROM FUNDS TABLE

            Map<String, String> fundsMap = new HashMap<>();
            for (String fundLable : fundsNames){
                int i = 0;
                fundsMap.put(fundLable, inventory.get("FUNDS")[i][0].getText());
                i++;
            }

            /// ///////////// GET TEXT FROM ALL OTHER TABLES

            Map<String, String[][]> stringMap = new HashMap<>();

            for (String key : inventory.keySet()) {

                JTextField[][] table = inventory.get(key);

                int rows = table.length;
                int cols = table[0].length;

                String[][] convertedTable = new String[rows][cols];

                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        JTextField tf = table[r][c];
                        convertedTable[r][c] = tf.getText();
                    }
                }

                stringMap.put(key, convertedTable);
            }

            for (Map.Entry<String, String> entry : fundsMap.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }

            for (String name : tableNames) {
                if (name.equals("FUNDS")) {continue;}
                System.out.println(Arrays.deepToString(stringMap.get(name)));
            }

        });

        /// ///////////////////////////////////////////////////////////////////////////SAVE BUTTON PANEL

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(saveButton);

        add(bottomPanel, BorderLayout.SOUTH);
        add(main);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////            TABLE BUILDER              //////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    JPanel tableBuilderPanel(String title, JTextField[][] table) {

        /// ////////////////////////////////////////////////////////////////TABLE TITLE

        JPanel outer = new JPanel(new BorderLayout(5,5));
        outer.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        outer.add(titleLabel, BorderLayout.NORTH);

        /// ///////////////////////////////////////////////////////////////////FUNDS TABLE

        if (title.equals("FUNDS")) {

            JPanel fundsNamePanel = new JPanel(new GridLayout(5, 1));
            JPanel fundsDataPanel = new JPanel(new GridLayout(5, 1));
            String[] fundsNames = {"CP", "SP", "EP", "GP", "PP"};

            for (String name : fundsNames) {
                JLabel fundNameLabel = new JLabel(name, SwingConstants.CENTER);
                fundNameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
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

        } else { /// //////////////////////////////////////////////////////////OTHER TABLES


            JPanel grid = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.fill = GridBagConstraints.BOTH;

            for (int r = 0; r < table.length; r++) {
                for (int c = 0; c < table[r].length; c++) {

                    table[r][c] = new JTextField();
                    table[r][c].setFont(new Font("Arial", Font.PLAIN, 18));

                    gbc.gridx = c;
                    gbc.gridy = r;
                    gbc.weighty = 2;

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

