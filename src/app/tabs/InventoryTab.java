package app.tabs;

import app.Role;
import app.pages.StylizedButton;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class InventoryTab extends JPanel {

    private static final String INVENTORY_DIR = "state/inventory";
    private static final String INVENTORY_FILE = "state/inventory/inventory.json";

    private Map<String, JTextField[][]> inventory = new HashMap<>();
    private String[] tableNames = {
            "FUNDS", "WEAPONS", "WEARABLES", "SPECIAL", "CONSUMABLES",
            "COMPONENTS", "DOCUMENTS", "CONTAINERS", "AMMUNITION", "MAGIC ITEMS",
            "ATTUNED", "LOOT", "MISC"
    };

    public InventoryTab(Role role) {

        setLayout(new BorderLayout());

        StylizedButton sb = new StylizedButton();


        JPanel main = new JPanel();
        main.setLayout(new GridLayout(2, 7, 5, 5));

        String[] fundsNames = {"CP", "SP", "EP", "GP", "PP"};

        /// /////////////////////////////////////////////////////////////////////////////ADD ALL TABLES TO MAP

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
            saveInventory();
            JOptionPane.showMessageDialog(this, "Inventory saved!", "Saved", JOptionPane.INFORMATION_MESSAGE);
        });

        /// ///////////////////////////////////////////////////////////////////////////SAVE BUTTON PANEL

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(saveButton);

        add(bottomPanel, BorderLayout.SOUTH);
        add(main);

        // Load saved inventory data
        loadInventory();
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

    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////         SAVE / LOAD METHODS          //////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private void saveInventory() {
        // Convert JTextField[][] maps to String[][] maps
        Map<String, String[][]> stringMap = new HashMap<>();

        for (String key : inventory.keySet()) {
            JTextField[][] table = inventory.get(key);
            int rows = table.length;
            int cols = table[0].length;

            String[][] convertedTable = new String[rows][cols];

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    JTextField tf = table[r][c];
                    convertedTable[r][c] = tf != null ? tf.getText() : "";
                }
            }

            stringMap.put(key, convertedTable);
        }

        // Save to JSON file
        try {
            Files.createDirectories(Paths.get(INVENTORY_DIR));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(stringMap);
            Files.write(Paths.get(INVENTORY_FILE), json.getBytes());
            System.out.println("Successfully saved inventory to: " + INVENTORY_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving inventory");
        }
    }

    private void loadInventory() {
        try {
            Path filePath = Paths.get(INVENTORY_FILE);

            if (!Files.exists(filePath)) {
                System.out.println("No inventory file found, starting fresh");
                return;
            }

            String json = Files.readString(filePath);
            Gson gson = new Gson();
            Map<String, List<List<String>>> loadedData = gson.fromJson(json,
                new TypeToken<Map<String, List<List<String>>>>(){}.getType());

            if (loadedData == null) return;

            // Apply loaded data to UI components
            for (String key : loadedData.keySet()) {
                if (!inventory.containsKey(key)) continue;

                JTextField[][] table = inventory.get(key);
                List<List<String>> data = loadedData.get(key);

                for (int r = 0; r < Math.min(table.length, data.size()); r++) {
                    List<String> row = data.get(r);
                    for (int c = 0; c < Math.min(table[r].length, row.size()); c++) {
                        if (table[r][c] != null && row.get(c) != null) {
                            table[r][c].setText(row.get(c));
                        }
                    }
                }
            }

            System.out.println("Successfully loaded inventory from: " + INVENTORY_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading inventory");
        }
    }
}

