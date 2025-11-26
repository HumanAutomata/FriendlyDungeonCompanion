package app.tabs;

import app.Role;
import app.logic.PlayerCharacterSheet;
import app.pages.StylizedButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterSheetTab extends JPanel {

    public PlayerCharacterSheet characterSheet = new PlayerCharacterSheet();

    public StylizedButton sb = new StylizedButton();
    
    public JScrollPane scrollPane = new JScrollPane();

    ////////////////////////////////////////////////////////////////////////////////////////////CHARACTER INFO
    private JTextField name = new JTextField();
    private JTextField race = new JTextField();
    private JTextField characterClass = new JTextField();
    private JTextField level = new JTextField();
    private JTextField background= new JTextField();
    private JTextField alignment = new JTextField();
    // character info map - Gabe likes his maps
    Map<String, JTextField> characterInfo = new HashMap<String, JTextField>() {{
        put("name", name);
        put("race", race);
        put("characterClass", characterClass);
        put("level", level);
        put("background", background);
        put("alignment", alignment);
    }};

    ////////////////////////////////////////////////////////////////////////////////////////////SPELL INFO
    private JTextField spellCastingAbility = new JTextField();
    private JTextField spellSaveDC = new JTextField();
    private JTextField spellAttackBonus = new JTextField();
    private JTextField sorcererPoints = new JTextField();
    // spell info map - Gabe likes his maps
    Map<String, JTextField> spellStats = new HashMap<String, JTextField>() {{
        put("spellCastingAbility", spellCastingAbility);
        put("spellSaveDC", spellSaveDC);
        put("spellAttackBonus", spellAttackBonus);
        put("sorcererPoints", sorcererPoints);
    }};

    ////////////////////////////////////////////////////////////////////////////////////////////ABILITIES
    private JTextField strength = new JTextField();
    private JTextField altStrength = new JTextField(5);
    private JTextField dexterity = new JTextField();
    private JTextField altDexterity = new JTextField();
    private JTextField constitution = new JTextField();
    private JTextField altConstitution = new JTextField();
    private JTextField intelligence = new JTextField();
    private JTextField altIntelligence = new JTextField();
    private JTextField wisdom = new JTextField();
    private JTextField altWisdom = new JTextField();
    private JTextField charisma = new JTextField();
    private JTextField altCharisma = new JTextField();
    // abilities maps - Gabe likes his maps
    private Map<String, JTextField> abilities = new HashMap<String, JTextField>() {{
        put("strength", strength);
        put("dexterity", dexterity);
        put("constitution", constitution);
        put("intelligence", intelligence);
        put("wisdom", wisdom);
        put("charisma", charisma);
    }};
    private Map<String, JTextField> abilitiesMod = new HashMap<String, JTextField>() {{
        put("strength", altStrength);
        put("dexterity", altDexterity);
        put("constitution", altConstitution);
        put("intelligence", altIntelligence);
        put("wisdom", altWisdom);
        put("charisma", altCharisma);
    }};

    ////////////////////////////////////////////////////////////////////////////////////////////SKILLS
    private JTextField inspiration = new JTextField(5);
    private JTextField proficiencyBonus = new JTextField(5);
    private JTextField passiveWisdom = new JTextField(5);
    // bonuses map - Gabe likes his maps
    Map<String, JTextField> bonuses = new HashMap<String, JTextField>() {{
        put("inspiration", inspiration);
        put("proficiencyBonus", proficiencyBonus);
        put("passiveWisdom", passiveWisdom);
    }};
    

    private JTextField acrobatics     = new JTextField();
    private JTextField animalHandling = new JTextField();
    private JTextField arcana         = new JTextField();
    private JTextField athletics      = new JTextField();
    private JTextField deception      = new JTextField();
    private JTextField history        = new JTextField();
    private JTextField insight        = new JTextField();
    private JTextField intimidation   = new JTextField();
    private JTextField investigation  = new JTextField();
    private JTextField medicine       = new JTextField();
    private JTextField nature         = new JTextField();
    private JTextField perception     = new JTextField();
    private JTextField performance    = new JTextField();
    private JTextField persuasion     = new JTextField();
    private JTextField sleight        = new JTextField();
    private JTextField religion       = new JTextField();
    private JTextField stealth        = new JTextField();
    private JTextField survival       = new JTextField();
    // skills map - Gabe likes his maps
    private Map<String, JTextField> skills = new HashMap<String, JTextField>() {{
        put("acrobatics", acrobatics);
        put("animalHandling", animalHandling);
        put("arcana", arcana);
        put("athletics", athletics);
        put("deception", deception);
        put("history ", history);
        put("insight", insight);
        put("intimidation", intimidation);
        put("investigation", investigation);
        put("medicine", medicine);
        put("nature", nature);
        put("perception", perception);
        put("performance", performance);
        put("persuasion", persuasion);
        put("sleight", sleight);
        put("religion", religion);
        put("stealth", stealth);
        put("survival", survival);
    }};

    private JCheckBox acrobaticsCB     = new JCheckBox();
    private JCheckBox animalHandlingCB = new JCheckBox();
    private JCheckBox arcanaCB         = new JCheckBox();
    private JCheckBox athleticsCB      = new JCheckBox();
    private JCheckBox deceptionCB      = new JCheckBox();
    private JCheckBox historyCB        = new JCheckBox();
    private JCheckBox insightCB        = new JCheckBox();
    private JCheckBox intimidationCB   = new JCheckBox();
    private JCheckBox investigationCB  = new JCheckBox();
    private JCheckBox medicineCB       = new JCheckBox();
    private JCheckBox natureCB         = new JCheckBox();
    private JCheckBox perceptionCB     = new JCheckBox();
    private JCheckBox performanceCB    = new JCheckBox();
    private JCheckBox persuasionCB     = new JCheckBox();
    private JCheckBox sleightCB        = new JCheckBox();
    private JCheckBox religionCB       = new JCheckBox();
    private JCheckBox stealthCB        = new JCheckBox();
    private JCheckBox survivalCB       = new JCheckBox();
    // proficiencies map - Gabe likes his maps
    private Map<String, JCheckBox> skillProficiencies = new HashMap<String, JCheckBox>() {{
        put("acrobatics", acrobaticsCB);
        put("animalHandling", animalHandlingCB);
        put("arcana", arcanaCB);
        put("athletics", athleticsCB);
        put("deception", deceptionCB);
        put("history ", historyCB);
        put("insight", insightCB);
        put("intimidation", intimidationCB);
        put("investigation", investigationCB);
        put("medicine", medicineCB);
        put("nature", natureCB);
        put("perception", perceptionCB);
        put("performance", performanceCB);
        put("persuasion", persuasionCB);
        put("sleight", sleightCB);
        put("religion", religionCB);
        put("stealth", stealthCB);
        put("survival", survivalCB);
    }};

    private JTextField strengthThrows     = new JTextField();
    private JTextField dexterityThrows    = new JTextField();
    private JTextField constitutionThrows    = new JTextField();
    private JTextField intelligenceThrows = new JTextField();
    private JTextField wisdomThrows       = new JTextField();
    private JTextField charismaThrows     = new JTextField();
    // saving throws map - Gabe likes his maps
    private Map<String, JTextField> savingThrows = new HashMap<String, JTextField>() {{
        put("strength", strengthThrows);
        put("dexterity", dexterityThrows );
        put("constitution", constitutionThrows);
        put("intelligence", intelligenceThrows);
        put("wisdom", wisdomThrows);
        put("charisma", charismaThrows);
    }};

    private JCheckBox strengthThrowsCB     = new JCheckBox();
    private JCheckBox dexterityThrowsCB    = new JCheckBox();
    private JCheckBox constitutionThrowsCB = new JCheckBox();
    private JCheckBox intelligenceThrowsCB = new JCheckBox();
    private JCheckBox wisdomThrowsCB       = new JCheckBox();
    private JCheckBox charismaThrowsCB     = new JCheckBox();
    // saving throws profeciencies - Gabe likes his maps
    private Map<String, JTextField> savingProficiencies = new HashMap<String, JTextField>() {{
        put("strength", strengthThrows);
        put("dexterity", dexterityThrows );
        put("constitution", constitutionThrows);
        put("intelligence", intelligenceThrows);
        put("wisdom", wisdomThrows);
        put("charisma", charismaThrows);
    }};

    ////////////////////////////////////////////////////////////////////////////////////////////HP STUFF

    private JTextField armor = new JTextField();
    private JTextField initiative = new JTextField();
    private JTextField speed = new JTextField();
    // base stats map - Gabe likes his maps
    private Map<String, JTextField> baseStats = new HashMap<String, JTextField>() {{
        put("armor", armor);
        put("initiative", initiative );
        put("speed", speed);
    }};

    private JTextField maxHP = new JTextField();
    private JTextField currentHP = new JTextField();
    private JTextField temporaryHP = new JTextField();

    private JTextField totalDice = new JTextField();
    private JTextField hitDice = new JTextField();
    // HP stats map - Gabe likes his maps
    private Map<String, JTextField> hpStats = new HashMap<String, JTextField>() {{
        put("maxHP", maxHP);
        put("currentHP", currentHP );
        put("temporaryHP", temporaryHP);
        put("totalHitDice", totalDice);
        put("hitDice", hitDice);
    }};

    
    
    private JRadioButton success1 = new JRadioButton();
    private JRadioButton success2 = new JRadioButton();
    private JRadioButton success3 = new JRadioButton();

    private JRadioButton failure1 = new JRadioButton();
    private JRadioButton failure2 = new JRadioButton();
    private JRadioButton failure3 = new JRadioButton();
    // Death saves map - Gabe likes his maps
    private Map<String, JRadioButton[]> deathSaves = new HashMap<String, JRadioButton[]>() {{
        put("successes", new JRadioButton[] {success1, success2, success3});
        put("failures", new JRadioButton[] {failure1, failure2, failure3});
    }};


    // private JTable attackTable = new JTable();

    private JTextArea featuresText = new JTextArea();

    ////////////////////////////////////////////////////////////////////////////////////////////SPELL PAGE
    private JTextArea personalityTraits = new JTextArea();
    private JTextArea ideals = new JTextArea();
    private JTextArea bonds = new JTextArea();
    private JTextArea flaws = new JTextArea();

    private JTextArea otherProficiencies = new JTextArea();
    private JTextArea additionalFeatures = new JTextArea();


    public CharacterSheetTab(Role role) {
        setLayout(new BorderLayout());

        ////////////////////////////////////////////////////////////////////////////////////////////CHARACTER INFO

        JPanel characterInfoPanel = new JPanel(new GridLayout(3, 4, 5, 0));
        characterInfoPanel.setBorder(BorderFactory.createTitledBorder("Character Info"));

        characterInfoPanel.add(new JLabel("Name:"));
        characterInfoPanel.add(name);
        characterInfoPanel.add(new JLabel("Race:"));
        characterInfoPanel.add(race);

        characterInfoPanel.add(new JLabel("Class:"));
        characterInfoPanel.add(characterClass);
        characterInfoPanel.add(new JLabel("Level:"));
        characterInfoPanel.add(level);

        characterInfoPanel.add(new JLabel("Background:"));
        characterInfoPanel.add(background);
        characterInfoPanel.add(new JLabel("Alignment:"));
        characterInfoPanel.add(alignment);

        ////////////////////////////////////////////////////////////////////////////////////////////SPELL STATS

        JPanel spellStatsPanel = new JPanel(new GridLayout(2, 4));
        spellStatsPanel.setBorder(BorderFactory.createTitledBorder("Spell Stats"));

        spellStatsPanel.add(spellCastingAbility);
        spellCastingAbility.setHorizontalAlignment(JTextField.CENTER);
        spellCastingAbility.setFont(new Font("Arial", Font.PLAIN, 24));

        spellStatsPanel.add(spellSaveDC);
        spellSaveDC.setHorizontalAlignment(JTextField.CENTER);
        spellSaveDC.setFont(new Font("Arial", Font.PLAIN, 24));

        spellStatsPanel.add(spellAttackBonus);
        spellAttackBonus.setHorizontalAlignment(JTextField.CENTER);
        spellAttackBonus.setFont(new Font("Arial", Font.PLAIN, 24));

        spellStatsPanel.add(sorcererPoints);
        sorcererPoints.setHorizontalAlignment(JTextField.CENTER);
        sorcererPoints.setFont(new Font("Arial", Font.PLAIN, 24));

        spellStatsPanel.add(new JLabel("Casting Ability"));
        spellStatsPanel.add(new JLabel("Save DC"));
        spellStatsPanel.add(new JLabel("Attack Bonus"));
        spellStatsPanel.add(new JLabel("Sorcerer Points"));

        ////////////////////////////////////////////////////////////////////////////////////////////TOP PANEL

        JPanel notes = new JPanel(new BorderLayout());
        notes.setBorder(BorderFactory.createTitledBorder("Notes"));
        notes.add(new JTextArea(), BorderLayout.CENTER);

        JList<String> characterSheets = new JList<>();
        characterSheets.addListSelectionListener(e -> {

        });

        JPanel topPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        topPanel.add(characterInfoPanel);
        topPanel.add(spellStatsPanel);
        topPanel.add(notes);
        add(topPanel, BorderLayout.NORTH);

        /////////////////////////////////////////////////////////////////////////////////////////////ABILITY STATS

        JPanel abilitiesPanel = new JPanel(new GridLayout(6, 1, 3,5));

        //<editor-fold desc="ABILITY PANELS////////////////////////////////////////////////////////">
        JPanel strengthPanel = new JPanel(new BorderLayout());
        strengthPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        strengthPanel.add(new JLabel("STRENGTH", SwingConstants.CENTER), BorderLayout.NORTH);
        strengthPanel.add(strength, BorderLayout.CENTER);
        strength.setHorizontalAlignment(JTextField.CENTER);
        strength.setFont(new Font("Arial", Font.PLAIN, 36));
        strengthPanel.add(altStrength, BorderLayout.SOUTH);
        altStrength.setHorizontalAlignment(JTextField.CENTER);
        altStrength.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel dexterityPanel = new JPanel(new BorderLayout());
        dexterityPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        dexterityPanel.add(new JLabel("DEXTERITY", SwingConstants.CENTER), BorderLayout.NORTH);
        dexterityPanel.add(dexterity, BorderLayout.CENTER);
        dexterity.setHorizontalAlignment(JTextField.CENTER);
        dexterity.setFont(new Font("Arial", Font.PLAIN, 36));
        dexterityPanel.add(altDexterity, BorderLayout.SOUTH);
        altDexterity.setHorizontalAlignment(JTextField.CENTER);
        altDexterity.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel constitutionPanel = new JPanel(new BorderLayout());
        constitutionPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        constitutionPanel.add(new JLabel("CONSTITUTION", SwingConstants.CENTER), BorderLayout.NORTH);
        constitutionPanel.add(constitution, BorderLayout.CENTER);
        constitution.setHorizontalAlignment(JTextField.CENTER);
        constitution.setFont(new Font("Arial", Font.PLAIN, 36));
        constitutionPanel.add(altConstitution, BorderLayout.SOUTH);
        altConstitution.setHorizontalAlignment(JTextField.CENTER);
        altConstitution.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel intelligencePanel = new JPanel(new BorderLayout());
        intelligencePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        intelligencePanel.add(new JLabel("INTELLIGENCE", SwingConstants.CENTER), BorderLayout.NORTH);
        intelligencePanel.add(intelligence, BorderLayout.CENTER);
        intelligence.setHorizontalAlignment(JTextField.CENTER);
        intelligence.setFont(new Font("Arial", Font.PLAIN, 36));
        intelligencePanel.add(altIntelligence, BorderLayout.SOUTH);
        altIntelligence.setHorizontalAlignment(JTextField.CENTER);
        altIntelligence.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel wisdomPanel = new JPanel(new BorderLayout());
        wisdomPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        wisdomPanel.add(new JLabel("WISDOM", SwingConstants.CENTER), BorderLayout.NORTH);
        wisdomPanel.add(wisdom, BorderLayout.CENTER);
        wisdom.setHorizontalAlignment(JTextField.CENTER);
        wisdom.setFont(new Font("Arial", Font.PLAIN, 36));
        wisdomPanel.add(altWisdom, BorderLayout.SOUTH);
        altWisdom.setHorizontalAlignment(JTextField.CENTER);
        altWisdom.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel charismaPanel = new JPanel(new BorderLayout());
        charismaPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        charismaPanel.add(new JLabel("CHARISMA", SwingConstants.CENTER), BorderLayout.NORTH);
        charismaPanel.add(charisma, BorderLayout.CENTER);
        charisma.setHorizontalAlignment(JTextField.CENTER);
        charisma.setFont(new Font("Arial", Font.PLAIN, 36));
        charismaPanel.add(altCharisma, BorderLayout.SOUTH);
        altCharisma.setHorizontalAlignment(JTextField.CENTER);
        altCharisma.setFont(new Font("Arial", Font.PLAIN, 18));
        //</editor-fold>

        abilitiesPanel.add(strengthPanel);
        abilitiesPanel.add(dexterityPanel);
        abilitiesPanel.add(constitutionPanel);
        abilitiesPanel.add(intelligencePanel);
        abilitiesPanel.add(wisdomPanel);
        abilitiesPanel.add(charismaPanel);

        JPanel inspirationPanel = new JPanel(new BorderLayout(10,0));
        inspirationPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        inspirationPanel.add(inspiration, BorderLayout.WEST);
        inspiration.setHorizontalAlignment(JTextField.CENTER);
        inspirationPanel.add(new JLabel("INSPIRATION"), BorderLayout.CENTER);

        JPanel proficiencyBonusPanel = new JPanel(new BorderLayout(10,0));
        proficiencyBonusPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        proficiencyBonusPanel.add(proficiencyBonus, BorderLayout.WEST);
        proficiencyBonus.setHorizontalAlignment(JTextField.CENTER);
        proficiencyBonusPanel.add(new JLabel("PROFICIENCY BONUS"), BorderLayout.CENTER);

        JPanel passiveWisdomPanel = new JPanel(new BorderLayout(10,0));
        passiveWisdomPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        passiveWisdomPanel.add(passiveWisdom, BorderLayout.WEST);
        passiveWisdom.setHorizontalAlignment(JTextField.CENTER);
        passiveWisdomPanel.add(new JLabel("PASSIVE WISDOM (PERCEPTION)"), BorderLayout.CENTER);

        JPanel inspirationAndProficiency = new JPanel(new GridLayout(2,1,3,3));
        inspirationAndProficiency.add(inspirationPanel);
        inspirationAndProficiency.add(proficiencyBonusPanel);


        /////////////////////////////////////////////////////////////////////////////////////////////SKILLS PANEL
        JPanel skillsPanel = new JPanel(new BorderLayout());
        JPanel skillsPanel1 = new JPanel(new GridLayout(18, 2));
        JPanel skillsPanel2 = new JPanel(new GridLayout(18, 1));


        JLabel[] skillsLabels =  new JLabel[18];
        String[] skillsNames = {
                "Acrobatics (DEX)", "Animal Handling (WIS)", "Arcana (INT)", "Athletics (STR)",
                "Deception (CHA)", "History (INT)", "Insight (WIS)", "Intimidation (CHA)",
                "Investigation (INT)", "Medicine (WIS)", "Nature (INT)", "Perception (WIS)",
                "Performance (CHA)", "Persuasion (CHA)", "Religion (INT)", "Sleight of Hand (DEX)",
                "Stealth (DEX)", "Survival (WIS)"
        };

        JCheckBox[] skillsBoxes = {
                acrobaticsCB, animalHandlingCB, arcanaCB, athleticsCB, deceptionCB,
                historyCB, insightCB, intimidationCB, investigationCB, medicineCB,
                natureCB, perceptionCB, performanceCB, persuasionCB, religionCB,
                sleightCB, stealthCB, survivalCB
        };

        JTextField[] skillsFields = {
                acrobatics, animalHandling, arcana, athletics, deception,
                history, insight, intimidation, investigation, medicine,
                nature, perception, performance, persuasion, religion,
                sleight, stealth, survival
        };

        for (int i = 0; i < skillsBoxes.length; i++) {
            skillsLabels[i] = new JLabel(skillsNames[i]);
            //skillsLabels[i].setToolTipText(characterSheet.viewStatBreakDown(name));
            skillsPanel1.add(skillsBoxes[i]);
            skillsPanel1.add(skillsFields[i]);
            skillsPanel2.add(skillsLabels[i]);
        }

        skillsPanel.add(skillsPanel1, BorderLayout.WEST);
        skillsPanel.add(skillsPanel2, BorderLayout.CENTER);

        ///////////////////////////////////////////////////////////////////////////////////////SAVING THROWS PANEL
        JPanel throwsPanel = new JPanel(new BorderLayout());
        throwsPanel.setBorder(BorderFactory.createTitledBorder("SAVING THROWS"));
        JPanel throwsPanel1 = new JPanel(new GridLayout(6, 2));
        JPanel throwsPanel2 = new JPanel(new GridLayout(6, 1));

        //<editor-fold desc="THROWS PANEL ADDS //////////////////////////////////////////////">
        JCheckBox[] throwsBoxes = {
                strengthThrowsCB, dexterityThrowsCB, constitutionThrowsCB,
                intelligenceThrowsCB, wisdomThrowsCB, charismaThrowsCB
        };

        JTextField[] throwsFields = {
                strengthThrows, dexterityThrows, constitutionThrows,
                intelligenceThrows, wisdomThrows, charismaThrows
        };

        String[] throwsLabels = {

                "Strength", "Dexterity", "Constitution",
                "Intelligence", "Wisdom", "Charisma"
        };

        for (int i = 0; i < throwsBoxes.length; i++) {
            throwsPanel1.add(throwsBoxes[i]);
            throwsPanel1.add(throwsFields[i]);
            throwsPanel2.add(new JLabel(throwsLabels[i]));
        }
        //</editor-fold>

        throwsPanel.add(throwsPanel1, BorderLayout.WEST);
        throwsPanel.add(throwsPanel2, BorderLayout.CENTER);

        JPanel otherAndThrowsPanel = new JPanel(new BorderLayout(5,3)); // Combines saving throws, insp., and proficiency
        otherAndThrowsPanel.add(inspirationAndProficiency, BorderLayout.NORTH);
        otherAndThrowsPanel.add(throwsPanel, BorderLayout.CENTER);

        JScrollPane skillsScrollPane = new JScrollPane(skillsPanel);
        skillsScrollPane.setBorder(null);

        JPanel skillsTitlePane = new JPanel(new BorderLayout());
        //skillsTitlePane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        skillsTitlePane.setBorder(BorderFactory.createTitledBorder("SKILLS"));
        skillsTitlePane.add(skillsScrollPane, BorderLayout.CENTER);
        //skillsTitlePane.add(new JLabel("SKILLS", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel skillsAndOtherPanel = new JPanel(new BorderLayout(5,3));
        skillsAndOtherPanel.add(otherAndThrowsPanel,  BorderLayout.NORTH);
        skillsAndOtherPanel.add(skillsTitlePane,  BorderLayout.CENTER);
        skillsAndOtherPanel.add(passiveWisdomPanel, BorderLayout.SOUTH);

        JPanel leftPanel = new JPanel(new BorderLayout(5,5));
        leftPanel.add(abilitiesPanel, BorderLayout.WEST);
        leftPanel.add(skillsAndOtherPanel, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////////////////////////////////////MIDDLE PANEL

        JPanel miscPanel = new JPanel(new GridLayout(3, 1, 3,5));

        JPanel armorPanel = new JPanel(new BorderLayout());
        armorPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        armorPanel.add(new JLabel("ARMOR CLASS", SwingConstants.CENTER), BorderLayout.SOUTH);
        armorPanel.add(armor, BorderLayout.CENTER);
        armor.setHorizontalAlignment(JTextField.CENTER);
        armor.setFont(new Font("Arial", Font.PLAIN, 36));

        JPanel initiativePanel = new JPanel(new BorderLayout());
        initiativePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        initiativePanel.add(new JLabel("INITIATIVE", SwingConstants.CENTER), BorderLayout.SOUTH);
        initiativePanel.add(initiative, BorderLayout.CENTER);
        initiative.setHorizontalAlignment(JTextField.CENTER);
        initiative.setFont(new Font("Arial", Font.PLAIN, 36));

        JPanel speedPanel = new JPanel(new BorderLayout());
        speedPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        speedPanel.add(new JLabel("SPEED", SwingConstants.CENTER), BorderLayout.SOUTH);
        speedPanel.add(speed, BorderLayout.CENTER);
        speed.setHorizontalAlignment(JTextField.CENTER);
        speed.setFont(new Font("Arial", Font.PLAIN, 36));

        miscPanel.add(armorPanel);
        miscPanel.add(initiativePanel);
        miscPanel.add(speedPanel);

        /////////////////////////////////////////////////////////////////////////////////////////////DICE PANEL

        JPanel totalDicePanel = new JPanel(new GridLayout(1,2));
        totalDicePanel.add(new JLabel("Total", SwingConstants.CENTER));
        totalDicePanel.add(totalDice);
        totalDice.setHorizontalAlignment(JTextField.CENTER);
        totalDice.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel hitDicePanel = new JPanel(new BorderLayout());
        hitDicePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        hitDicePanel.add(totalDicePanel, BorderLayout.NORTH);
        hitDicePanel.add(hitDice, BorderLayout.CENTER);
        hitDice.setHorizontalAlignment(JTextField.CENTER);
        hitDice.setFont(new Font("Arial", Font.PLAIN, 36));
        hitDicePanel.add(new JLabel("HIT DICE", SwingConstants.CENTER), BorderLayout.SOUTH);

        /////////////////////////////////////////////////////////////////////////////////////////DEATH SAVE PANEL

        JPanel radioSuccessPanel = new JPanel(new GridLayout(1,3));
        radioSuccessPanel.add(success1);
        radioSuccessPanel.add(success2);
        radioSuccessPanel.add(success3);

        JPanel radioFailPanel = new JPanel(new GridLayout(1,3));
        radioFailPanel.add(failure1);
        radioFailPanel.add(failure2);
        radioFailPanel.add(failure3);

        JPanel successFailPanel = new JPanel(new GridLayout(2,2));
        successFailPanel.add(new JLabel("SUCCESSES", SwingConstants.CENTER));
        successFailPanel.add(radioSuccessPanel);
        successFailPanel.add(new JLabel("FAILURES", SwingConstants.CENTER));
        successFailPanel.add(radioFailPanel);

        JPanel deathSavePanel = new JPanel(new BorderLayout());
        deathSavePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        deathSavePanel.add(successFailPanel, BorderLayout.CENTER);
        deathSavePanel.add(new JLabel("DEATH SAVES", SwingConstants.CENTER), BorderLayout.SOUTH);

        /////////////////////////////////////////////////////////////////////////////////////////////////HP PANEL

        JPanel maxHPPanel = new JPanel(new GridLayout(1,2));
        maxHPPanel.add(new JLabel("MAX HP", SwingConstants.CENTER));
        maxHPPanel.add(maxHP);
        maxHP.setHorizontalAlignment(JTextField.CENTER);
        maxHP.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel currentHPPanel = new JPanel(new BorderLayout());
        currentHPPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        currentHPPanel.add(maxHPPanel, BorderLayout.NORTH);
        currentHPPanel.add(currentHP, BorderLayout.CENTER);
        currentHP.setHorizontalAlignment(JTextField.CENTER);
        currentHP.setFont(new Font("Arial", Font.PLAIN, 36));
        currentHPPanel.add(new JLabel("CURRENT HIT POINTS", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel temporaryHPPanel = new JPanel(new BorderLayout());
        temporaryHPPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        temporaryHPPanel.add(temporaryHP, BorderLayout.CENTER);
        temporaryHP.setHorizontalAlignment(JTextField.CENTER);
        temporaryHP.setFont(new Font("Arial", Font.PLAIN, 36));
        temporaryHPPanel.add(new JLabel("TEMPORARY HIT POINTS", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel otherHPPanel = new JPanel(new GridLayout(1,2,5,0));
        otherHPPanel.add(hitDicePanel);
        otherHPPanel.add(deathSavePanel);

        JPanel hpPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        hpPanel.add(currentHPPanel);
        hpPanel.add(temporaryHPPanel);
        hpPanel.add(otherHPPanel);

        //////////////////////////////////////////////////////////////////////////////////////ATTACK PANEL

        JPanel attackPanel = new JPanel(new GridLayout(9, 3));
        attackPanel.add(new JLabel("NAME"));
        attackPanel.add(new JLabel("ATK BONUS"));
        attackPanel.add(new JLabel("DAMAGE/TYPE"));

        JTextField[][] attacks = new JTextField[8][3]; // 8 rows, 3 columns

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                attacks[i][j] = new JTextField();
                attackPanel.add(attacks[i][j]);
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////MIDDLE PANEL BUILDER

        JPanel topMiddlePanel = new JPanel(new BorderLayout(5,0));
        //topMiddlePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        topMiddlePanel.add(miscPanel, BorderLayout.WEST);
        topMiddlePanel.add(hpPanel, BorderLayout.CENTER);

        JPanel bottomMiddlePanel = new JPanel(new BorderLayout(10,5));
        bottomMiddlePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        bottomMiddlePanel.add(attackPanel, BorderLayout.CENTER);
        bottomMiddlePanel.add(new JLabel("ATTACKS & SPELLCASTING", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel middlePanel = new JPanel(new GridLayout(2,1,5,5));
        middlePanel.add(topMiddlePanel);
        middlePanel.add(bottomMiddlePanel);

        ////////////////////////////////////////////////////////////////////////////////////RIGHT PANEL

        JScrollPane featuresPane = new JScrollPane(featuresText);
        featuresPane.setBorder(null);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        rightPanel.add(featuresPane, BorderLayout.CENTER);
        rightPanel.add(new JLabel("FEATURES AND TRAITS", SwingConstants.CENTER), BorderLayout.SOUTH);
        //rightPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        ////////////////////////////////////////////////////////////////////////////////////MAIN PANEL

        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 15, 30));
        mainPanel.add(leftPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(rightPanel);

        add(mainPanel, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////////////////////////////SPELL PAGE
        ////////////////////////////////////////////////////////////////////////////////////SPELL PAGE
        ////////////////////////////////////////////////////////////////////////////////////SPELL PAGE
        ////////////////////////////////////////////////////////////////////////////////////SPELL PAGE

        /////////////////////////////////////////////////////////////////////////////////////////////LEVEL 0

        //<editor-fold desc="//////////////////////////////////////////////////////////SPELL LEVELS">
        JPanel levelZeroPanel = new JPanel(new BorderLayout(5,0));
        JLabel levelZero = new JLabel("level 0");
        //levelZero.setFont(new Font("Arial", Font.PLAIN, 24));
        levelZero.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JLabel cantrips = new JLabel("CANTRIPS", SwingConstants.CENTER);
        //cantrips.setFont(new Font("Arial", Font.PLAIN, 18));
        cantrips.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        //levelZeroPanel.add(levelZero, BorderLayout.WEST);
        levelZeroPanel.add(cantrips, BorderLayout.CENTER);

        JPanel cantripsPanel = new JPanel(new GridLayout(10, 1, 0, 5));
        cantripsPanel.add(levelZeroPanel);
        for (int i = 0; i < 9; i++){
            cantripsPanel.add(new JTextField());
        }

        /////////////////////////////////////////////////////////////////////////////////////////////LEVEL 1

        JPanel l1Panel = new JPanel(new GridLayout(1, 3, 5, 0));
        JLabel l1 = new JLabel("Level 1", SwingConstants.CENTER);
        JTextField l1Total = new JTextField();
        JTextField l1Expended = new JTextField();

        //l1.setFont(new Font("Arial", Font.PLAIN, 24));
        l1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        l1Panel.add(l1);
        l1Panel.add(l1Total);
        l1Panel.add(l1Expended);

        JPanel l1checkBoxes = new JPanel(new GridLayout(9, 1, 0, 5));
        JPanel l1textFields = new JPanel(new GridLayout(9, 1, 0, 5));

        for (int i = 0; i < 9; i++){

            l1checkBoxes.add(new JCheckBox());
            l1textFields.add(new JTextField());

        }

        JPanel l1builderPanel = new JPanel(new BorderLayout());
        l1builderPanel.add(l1Panel, BorderLayout.NORTH);
        l1builderPanel.add(l1checkBoxes, BorderLayout.WEST);
        l1builderPanel.add(l1textFields, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////////////////////////////////////LEVEL 2

        JPanel l2Panel = new JPanel(new GridLayout(1, 3, 5, 0));
        JLabel l2 = new JLabel("Level 2", SwingConstants.CENTER);
        JTextField l2Total = new JTextField();
        JTextField l2Expended = new JTextField(20);

        //l2.setFont(new Font("Arial", Font.PLAIN, 24));
        l2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        l2Panel.add(l2);
        l2Panel.add(l2Total);
        l2Panel.add(l2Expended);

        JPanel l2checkBoxes = new JPanel(new GridLayout(9, 1, 0, 5));
        JPanel l2textFields = new JPanel(new GridLayout(9, 1, 0, 5));

        for (int i = 0; i < 9; i++){

            l2checkBoxes.add(new JCheckBox());
            l2textFields.add(new JTextField());

        }

        JPanel l2builderPanel = new JPanel(new BorderLayout());
        l2builderPanel.add(l2Panel, BorderLayout.NORTH);
        l2builderPanel.add(l2checkBoxes, BorderLayout.WEST);
        l2builderPanel.add(l2textFields, BorderLayout.CENTER);

        //////////////////////////////////////////////////////////////////////////////////////////////LEVEL 3

        JPanel l3Panel = new JPanel(new GridLayout(1, 3, 5, 0));
        JLabel l3 = new JLabel("Level 3", SwingConstants.CENTER);
        JTextField l3Total = new JTextField();
        JTextField l3Expended = new JTextField(20);

        //l3.setFont(new Font("Arial", Font.PLAIN, 24));
        l3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        l3Panel.add(l3);
        l3Panel.add(l3Total);
        l3Panel.add(l3Expended);

        JPanel l3checkBoxes = new JPanel(new GridLayout(10, 1, 0, 5));
        JPanel l3textFields = new JPanel(new GridLayout(10, 1, 0, 5));

        for (int i = 0; i < 10; i++){

            l3checkBoxes.add(new JCheckBox());
            l3textFields.add(new JTextField());

        }

        JPanel l3builderPanel = new JPanel(new BorderLayout());
        l3builderPanel.add(l3Panel, BorderLayout.NORTH);
        l3builderPanel.add(l3checkBoxes, BorderLayout.WEST);
        l3builderPanel.add(l3textFields, BorderLayout.CENTER);

        //////////////////////////////////////////////////////////////////////////////////////////////LEVEL 4

        JPanel l4Panel = new JPanel(new GridLayout(1, 3, 5, 0));
        JLabel l4 = new JLabel("Level 4", SwingConstants.CENTER);
        JTextField l4Total = new JTextField();
        JTextField l4Expended = new JTextField(20);

        //l4.setFont(new Font("Arial", Font.PLAIN, 24));
        l4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        l4Panel.add(l4);
        l4Panel.add(l4Total);
        l4Panel.add(l4Expended);

        JPanel l4checkBoxes = new JPanel(new GridLayout(10, 1, 0, 5));
        JPanel l4textFields = new JPanel(new GridLayout(10, 1, 0, 5));

        for (int i = 0; i < 10; i++){

            l4checkBoxes.add(new JCheckBox());
            l4textFields.add(new JTextField());

        }

        JPanel l4builderPanel = new JPanel(new BorderLayout());
        l4builderPanel.add(l4Panel, BorderLayout.NORTH);
        l4builderPanel.add(l4checkBoxes, BorderLayout.WEST);
        l4builderPanel.add(l4textFields, BorderLayout.CENTER);

        //////////////////////////////////////////////////////////////////////////////////////////////LEVEL 5
        
        JPanel l5Panel = new JPanel(new GridLayout(1, 3, 5, 0));
        JLabel l5 = new JLabel("Level 5", SwingConstants.CENTER);
        JTextField l5Total = new JTextField();
        JTextField l5Expended = new JTextField(20);

        //l5.setFont(new Font("Arial", Font.PLAIN, 24));
        l5.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        l5Panel.add(l5);
        l5Panel.add(l5Total);
        l5Panel.add(l5Expended);

        JPanel l5checkBoxes = new JPanel(new GridLayout(10, 1, 0, 5));
        JPanel l5textFields = new JPanel(new GridLayout(10, 1, 0, 5));

        for (int i = 0; i < 10; i++){

            l5checkBoxes.add(new JCheckBox());
            l5textFields.add(new JTextField());

        }

        JPanel l5builderPanel = new JPanel(new BorderLayout());
        l5builderPanel.add(l5Panel, BorderLayout.NORTH);
        l5builderPanel.add(l5checkBoxes, BorderLayout.WEST);
        l5builderPanel.add(l5textFields, BorderLayout.CENTER);

        //////////////////////////////////////////////////////////////////////////////////////////////LEVEL 6
        
        JPanel l6Panel = new JPanel(new GridLayout(1, 3, 5, 0));
        JLabel l6 = new JLabel("Level 6", SwingConstants.CENTER);
        JTextField l6Total = new JTextField();
        JTextField l6Expended = new JTextField(20);

        //l6.setFont(new Font("Arial", Font.PLAIN, 24));
        l6.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        l6Panel.add(l6);
        l6Panel.add(l6Total);
        l6Panel.add(l6Expended);

        JPanel l6checkBoxes = new JPanel(new GridLayout(7, 1, 0, 5));
        JPanel l6textFields = new JPanel(new GridLayout(7, 1, 0, 5));

        for (int i = 0; i < 7; i++){

            l6checkBoxes.add(new JCheckBox());
            l6textFields.add(new JTextField());

        }

        JPanel l6builderPanel = new JPanel(new BorderLayout());
        l6builderPanel.add(l6Panel, BorderLayout.NORTH);
        l6builderPanel.add(l6checkBoxes, BorderLayout.WEST);
        l6builderPanel.add(l6textFields, BorderLayout.CENTER);

        //////////////////////////////////////////////////////////////////////////////////////////////LEVEL 7
        
        JPanel l7Panel = new JPanel(new GridLayout(1, 3, 5, 0));
        JLabel l7 = new JLabel("Level 7", SwingConstants.CENTER);
        JTextField l7Total = new JTextField();
        JTextField l7Expended = new JTextField(20);

        //l7.setFont(new Font("Arial", Font.PLAIN, 24));
        l7.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        l7Panel.add(l7);
        l7Panel.add(l7Total);
        l7Panel.add(l7Expended);

        JPanel l7checkBoxes = new JPanel(new GridLayout(7, 1, 0, 5));
        JPanel l7textFields = new JPanel(new GridLayout(7, 1, 0, 5));

        for (int i = 0; i < 7; i++){

            l7checkBoxes.add(new JCheckBox());
            l7textFields.add(new JTextField());

        }

        JPanel l7builderPanel = new JPanel(new BorderLayout());
        l7builderPanel.add(l7Panel, BorderLayout.NORTH);
        l7builderPanel.add(l7checkBoxes, BorderLayout.WEST);
        l7builderPanel.add(l7textFields, BorderLayout.CENTER);

        //////////////////////////////////////////////////////////////////////////////////////////////LEVEL 8
        
        JPanel l8Panel = new JPanel(new GridLayout(1, 3, 5, 0));
        JLabel l8 = new JLabel("Level 8", SwingConstants.CENTER);
        JTextField l8Total = new JTextField();
        JTextField l8Expended = new JTextField(20);

        //l8.setFont(new Font("Arial", Font.PLAIN, 24));
        l8.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        l8Panel.add(l8);
        l8Panel.add(l8Total);
        l8Panel.add(l8Expended);

        JPanel l8checkBoxes = new JPanel(new GridLayout(7, 1, 0, 5));
        JPanel l8textFields = new JPanel(new GridLayout(7, 1, 0, 5));

        for (int i = 0; i < 7; i++){

            l8checkBoxes.add(new JCheckBox());
            l8textFields.add(new JTextField());

        }

        JPanel l8builderPanel = new JPanel(new BorderLayout());
        l8builderPanel.add(l8Panel, BorderLayout.NORTH);
        l8builderPanel.add(l8checkBoxes, BorderLayout.WEST);
        l8builderPanel.add(l8textFields, BorderLayout.CENTER);

        //////////////////////////////////////////////////////////////////////////////////////////////LEVEL 9
        
        JPanel l9Panel = new JPanel(new GridLayout(1, 3, 5, 0));
        JLabel l9 = new JLabel("Level 9", SwingConstants.CENTER);
        JTextField l9Total = new JTextField();
        JTextField l9Expended = new JTextField(20);

        //l9.setFont(new Font("Arial", Font.PLAIN, 24));
        l9.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        l9Panel.add(l9);
        l9Panel.add(l9Total);
        l9Panel.add(l9Expended);

        JPanel l9checkBoxes = new JPanel(new GridLayout(7, 1, 0, 5));
        JPanel l9textFields = new JPanel(new GridLayout(7, 1, 0, 5));

        for (int i = 0; i < 7; i++){

            l9checkBoxes.add(new JCheckBox());
            l9textFields.add(new JTextField());

        }

        JPanel l9builderPanel = new JPanel(new BorderLayout());
        l9builderPanel.add(l9Panel, BorderLayout.NORTH);
        l9builderPanel.add(l9checkBoxes, BorderLayout.WEST);
        l9builderPanel.add(l9textFields, BorderLayout.CENTER);
        //</editor-fold>

        //////////////////////////////////////////////////////////////////////////////////////////////LEFT SPELL PANEL

        JPanel leftSpellsPanel = new  JPanel(new GridLayout(3,1,0,5));
        leftSpellsPanel.add(cantripsPanel);
        leftSpellsPanel.add(l1builderPanel);
        leftSpellsPanel.add(l2builderPanel);


        JPanel middleSpellsPanel = new  JPanel(new GridLayout(3,1, 0,5));
        middleSpellsPanel.add(l3builderPanel);
        middleSpellsPanel.add(l4builderPanel);
        middleSpellsPanel.add(l5builderPanel);

        JPanel rightSpellsPanel = new  JPanel(new GridLayout(4,1,0,5));
        rightSpellsPanel.add(l6builderPanel);
        rightSpellsPanel.add(l7builderPanel);
        rightSpellsPanel.add(l8builderPanel);
        rightSpellsPanel.add(l9builderPanel);

        JPanel combinedSpellsPanel = new  JPanel(new GridLayout(1,3,5,5));
        combinedSpellsPanel.add(leftSpellsPanel);
        combinedSpellsPanel.add(middleSpellsPanel);
        combinedSpellsPanel.add(rightSpellsPanel);

        ////////////////////////////////////////////////////////////////////////////////////RIGHT SPELL PANEL

        JPanel personalityTraitsPanel = new JPanel(new BorderLayout());
        JScrollPane sp = new JScrollPane(personalityTraits);
        sp.setBorder(null);
        personalityTraitsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        personalityTraitsPanel.add(sp, BorderLayout.CENTER);
        personalityTraitsPanel.add(new JLabel("PERSONALITY TRAITS", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel idealsPanel = new JPanel(new BorderLayout());
        JScrollPane sp1 = new JScrollPane(ideals);
        sp1.setBorder(null);
        idealsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        idealsPanel.add(sp1, BorderLayout.CENTER);
        idealsPanel.add(new JLabel("IDEALS", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel bondsPanel = new JPanel(new BorderLayout());
        JScrollPane sp2 = new JScrollPane(bonds);
        sp2.setBorder(null);
        bondsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        bondsPanel.add(sp2, BorderLayout.CENTER);
        bondsPanel.add(new JLabel("BONDS", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel flawsPanel = new JPanel(new BorderLayout());
        JScrollPane sp3 = new JScrollPane(flaws);
        sp3.setBorder(null);
        flawsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        flawsPanel.add(sp3, BorderLayout.CENTER);
        flawsPanel.add(new JLabel("FLAWS", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel traitsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        traitsPanel.add(personalityTraitsPanel);
        traitsPanel.add(idealsPanel);
        traitsPanel.add(bondsPanel);
        traitsPanel.add(flawsPanel);

        JPanel otherProficienciesPanel = new JPanel(new BorderLayout());
        JScrollPane sp4 = new JScrollPane(otherProficiencies);
        sp4.setBorder(null);
        otherProficienciesPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        otherProficienciesPanel.add(sp4, BorderLayout.CENTER);
        otherProficienciesPanel.add(new JLabel("OTHER PROFICIENCIES & LANGUAGES", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel topRightSpellsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        topRightSpellsPanel.add(otherProficienciesPanel);
        topRightSpellsPanel.add(traitsPanel);

        JPanel additionalFeaturesPanel = new JPanel(new BorderLayout());
        JScrollPane sp5 = new JScrollPane(additionalFeatures);
        sp5.setBorder(null);
        additionalFeaturesPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        additionalFeaturesPanel.add(sp5, BorderLayout.CENTER);
        additionalFeaturesPanel.add(new JLabel("ADDITIONAL FEATURES & TRAITS", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel rightSpellPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        rightSpellPanel.add(topRightSpellsPanel);
        rightSpellPanel.add(additionalFeaturesPanel);

        /////////////////////////////////////////////////////////////////////////////////////////MAIN SPELL PANEL

        JPanel mainSpellsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        mainSpellsPanel.add(combinedSpellsPanel);
        mainSpellsPanel.add(rightSpellPanel);

        ////////////////////////////////////////////////////////////////////////////////////BOTTOM PANEL

        JButton mainButton = new JButton("MAIN");
        JButton spellButton = new JButton("SPELLS");

        mainButton.addActionListener(e -> {
            sb.makeRounded(mainButton,10, sb.APP_PURPLE, 16, 10, 5);
            sb.makeRounded(spellButton,10, sb.APP_GRAY, 16, 10, 5);
            add(mainPanel, BorderLayout.CENTER);
            topPanel.setVisible(true);
            mainPanel.setVisible(true);
            mainSpellsPanel.setVisible(false);
        });


        spellButton.addActionListener(e -> {
            sb.makeRounded(spellButton,10, sb.APP_PURPLE, 16, 10, 5);
            sb.makeRounded(mainButton,10, sb.APP_GRAY, 16, 10, 5);
            add(mainSpellsPanel, BorderLayout.CENTER);
            topPanel.setVisible(false);
            mainPanel.setVisible(false);
            mainSpellsPanel.setVisible(true);
        });

        JButton saveButton = new JButton("SAVE");
        saveButton.addActionListener(e -> {
            
            // Converting stats to proper map format
            Map[] statsList = new Map[] {
                abilities, abilitiesMod, bonuses, skills, skillProficiencies, 
                spellStats, baseStats, savingThrows, savingProficiencies, hpStats, deathSaves
            };
            String[] statsKeys = {
                "abilities","abilitiesMod","bonuses","skills","skillProficiencies",
                "spellStats","baseStats","savingThrows","savingProficiencies","hpStats","deathSaves"
            };

            Map<String, Object> statsMap = new HashMap<>();
            for(int i = 0; i < statsList.length; i++) {
                Map<String, Object> stat = new HashMap<>();
   
                for(Object obj : statsList[i].entrySet()) {
                    Map.Entry<?, ?> entry = (Map.Entry<?, ?>) obj;
                
                    if(entry.getValue() instanceof JTextField) {
                        stat.put((String)entry.getKey(), ((JTextField)entry.getValue()).getText());
                    } 
                    else if(entry.getValue() instanceof JCheckBox) {
                        stat.put((String)entry.getKey(), ((JCheckBox)entry.getValue()).isSelected());
                    }
                    else if(entry.getValue() instanceof JRadioButton[]){
                        List<Boolean> buttons = new ArrayList<Boolean>();
                        for(JRadioButton button : (JRadioButton[])entry.getValue()){
                            buttons.add(button.isSelected());
                        }
                        stat.put((String)entry.getKey(), buttons);
                    }
                }
                statsMap.put(statsKeys[i], stat);
            }
            
            // converting characterInfo to proper map format
            Map<String, String> infoMap = new HashMap<String, String>() {{
                for(Entry<String, JTextField> charInfo : characterInfo.entrySet()) {
                    put(charInfo.getKey(), charInfo.getValue().getText());
                }
            }};

            //converting description to map format
            JTextArea[] descList = new JTextArea[] {
                featuresText, personalityTraits, ideals, bonds, flaws, additionalFeatures, otherProficiencies
            };
            String[] descKeys = new String[] {"features","personalityTraits","ideals","bonds","flaws","additionalFeatures","otherProficiencies"};
            Map<String,String> descriptionMap = new HashMap<>() {{
                for(int i = 0; i < descList.length; i++){
                    put(descKeys[i], (String) descList[i].getText());
                }
            }};

            // convert the attack table to an array
            int rows = attacks.length;
            int columns = attacks[0].length;
            String[][] attackArray = new String[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    attackArray[row][col] = attacks[row][col].getText();
                }
            }
            System.out.println(attackArray);
            characterSheet.save(statsMap, infoMap, descriptionMap, attackArray);
            

        });


        JPanel bottomPanel = new JPanel(new FlowLayout());
        sb.makeRounded(saveButton,10, sb.APP_RED, 16, 10, 5);
        sb.makeRounded(mainButton,10, sb.APP_PURPLE, 16, 10, 5);
        sb.makeRounded(spellButton,10, sb.APP_GRAY, 16, 10, 5);
        bottomPanel.add(saveButton);
        bottomPanel.add(mainButton);
        bottomPanel.add(spellButton);

        add(bottomPanel, BorderLayout.SOUTH);

    }
}