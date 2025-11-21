package app.tabs;

import javax.swing.*;
import java.awt.*;
import app.Role;

public class CharacterSheetTab extends JPanel {

    ////////////////////////////////////////////////////////////////////////////////////////////CHARACTER INFO
    public JTextField name = new JTextField();
    public JTextField race = new JTextField();
    public JTextField characterClass = new JTextField();
    public JTextField level = new JTextField();
    public JTextField background= new JTextField();
    public JTextField alignment = new JTextField();
    ////////////////////////////////////////////////////////////////////////////////////////////SPELL INFO
    public JTextField spellCastingAbility = new JTextField();
    public JTextField spellSaveDC = new JTextField();
    public JTextField spellAttackBonus = new JTextField();
    public JTextField sorcererPoints = new JTextField();
    ////////////////////////////////////////////////////////////////////////////////////////////ABILITIES
    public JTextField strength = new JTextField();
    public JTextField altStrength = new JTextField(5);
    public JTextField dexterity = new JTextField();
    public JTextField altDexterity = new JTextField();
    public JTextField constitution = new JTextField();
    public JTextField altConstitution = new JTextField();
    public JTextField intelligence = new JTextField();
    public JTextField altIntelligence = new JTextField();
    public JTextField wisdom = new JTextField();
    public JTextField altWisdom = new JTextField();
    public JTextField charisma = new JTextField();
    public JTextField altCharisma = new JTextField();
    ////////////////////////////////////////////////////////////////////////////////////////////SKILLS
    public JTextField inspiration = new JTextField(5);
    public JTextField proficiencyBonus = new JTextField(5);
    public JTextField passiveWisdom = new JTextField(5);

    public JTextField acrobatics     = new JTextField();
    public JTextField animalHandling = new JTextField();
    public JTextField arcana         = new JTextField();
    public JTextField athletics      = new JTextField();
    public JTextField deception      = new JTextField();
    public JTextField history        = new JTextField();
    public JTextField insight        = new JTextField();
    public JTextField intimidation   = new JTextField();
    public JTextField investigation  = new JTextField();
    public JTextField medicine       = new JTextField();
    public JTextField nature         = new JTextField();
    public JTextField perception     = new JTextField();
    public JTextField performance    = new JTextField();
    public JTextField persuasion     = new JTextField();
    public JTextField sleight        = new JTextField();
    public JTextField religion       = new JTextField();
    public JTextField stealth        = new JTextField();
    public JTextField survival       = new JTextField();

    public JCheckBox acrobaticsCB     = new JCheckBox();
    public JCheckBox animalHandlingCB = new JCheckBox();
    public JCheckBox arcanaCB         = new JCheckBox();
    public JCheckBox athleticsCB      = new JCheckBox();
    public JCheckBox deceptionCB      = new JCheckBox();
    public JCheckBox historyCB        = new JCheckBox();
    public JCheckBox insightCB        = new JCheckBox();
    public JCheckBox intimidationCB   = new JCheckBox();
    public JCheckBox investigationCB  = new JCheckBox();
    public JCheckBox medicineCB       = new JCheckBox();
    public JCheckBox natureCB         = new JCheckBox();
    public JCheckBox perceptionCB     = new JCheckBox();
    public JCheckBox performanceCB    = new JCheckBox();
    public JCheckBox persuasionCB     = new JCheckBox();
    public JCheckBox sleightCB        = new JCheckBox();
    public JCheckBox religionCB       = new JCheckBox();
    public JCheckBox stealthCB        = new JCheckBox();
    public JCheckBox survivalCB       = new JCheckBox();

    public JTextField strengthThrows     = new JTextField();
    public JTextField dexterityThrows    = new JTextField();
    public JTextField constitutionThrows    = new JTextField();
    public JTextField intelligenceThrows = new JTextField();
    public JTextField wisdomThrows       = new JTextField();
    public JTextField charismaThrows     = new JTextField();

    public JCheckBox strengthThrowsCB     = new JCheckBox();
    public JCheckBox dexterityThrowsCB    = new JCheckBox();
    public JCheckBox constitutionThrowsCB = new JCheckBox();
    public JCheckBox intelligenceThrowsCB = new JCheckBox();
    public JCheckBox wisdomThrowsCB       = new JCheckBox();
    public JCheckBox charismaThrowsCB     = new JCheckBox();

    ////////////////////////////////////////////////////////////////////////////////////////////HP STUFF

    public JTextField armor = new JTextField();
    public JTextField initiative = new JTextField();
    public JTextField speed = new JTextField();

    public JTextField maxHP = new JTextField();
    public JTextField currentHP = new JTextField();
    public JTextField temporaryHP = new JTextField();

    public JTextField totalDice = new JTextField();
    public JTextField hitDice = new JTextField();

    public JRadioButton success1 = new JRadioButton();
    public JRadioButton success2 = new JRadioButton();
    public JRadioButton success3 = new JRadioButton();

    public JRadioButton failure1 = new JRadioButton();
    public JRadioButton failure2 = new JRadioButton();
    public JRadioButton failure3 = new JRadioButton();

    public JTextArea featuresText = new JTextArea();

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

        JPanel spellStats = new JPanel(new GridLayout(2, 4));
        spellStats.setBorder(BorderFactory.createTitledBorder("Spell Stats"));

        spellStats.add(spellCastingAbility);
        spellCastingAbility.setHorizontalAlignment(JTextField.CENTER);
        spellCastingAbility.setFont(new Font("Arial", Font.PLAIN, 24));

        spellStats.add(spellSaveDC);
        spellSaveDC.setHorizontalAlignment(JTextField.CENTER);
        spellSaveDC.setFont(new Font("Arial", Font.PLAIN, 24));

        spellStats.add(spellAttackBonus);
        spellAttackBonus.setHorizontalAlignment(JTextField.CENTER);
        spellAttackBonus.setFont(new Font("Arial", Font.PLAIN, 24));

        spellStats.add(sorcererPoints);
        sorcererPoints.setHorizontalAlignment(JTextField.CENTER);
        sorcererPoints.setFont(new Font("Arial", Font.PLAIN, 24));

        spellStats.add(new JLabel("Casting Ability"));
        spellStats.add(new JLabel("Save DC"));
        spellStats.add(new JLabel("Attack Bonus"));
        spellStats.add(new JLabel("Sorcerer Points"));

        ////////////////////////////////////////////////////////////////////////////////////////////TOP PANEL

        JPanel notes = new JPanel(new BorderLayout());
        notes.setBorder(BorderFactory.createTitledBorder("Notes"));
        notes.add(new JTextArea(), BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        topPanel.add(characterInfoPanel);
        topPanel.add(spellStats);
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
        skillsPanel.setBorder(BorderFactory.createTitledBorder("SKILLS"));

        JPanel skillsPanel1 = new JPanel(new GridLayout(18, 2));
        JPanel skillsPanel2 = new JPanel(new GridLayout(18, 1));

        //<editor-fold desc="SKILLS PANEL ADDS ///////////////////////////////////////////////////">
        skillsPanel1.add(acrobaticsCB);
        skillsPanel1.add(acrobatics);
        skillsPanel2.add(new JLabel("Acrobatics (DEX)"));

        skillsPanel1.add(animalHandlingCB);
        skillsPanel1.add(animalHandling);
        skillsPanel2.add(new JLabel("Animal Handling (WIS)"));

        skillsPanel1.add(arcanaCB);
        skillsPanel1.add(arcana);
        skillsPanel2.add(new JLabel("Arcana (INT)"));

        skillsPanel1.add(athleticsCB);
        skillsPanel1.add(athletics);
        skillsPanel2.add(new JLabel("Athletics (STR)"));

        skillsPanel1.add(deceptionCB);
        skillsPanel1.add(deception);
        skillsPanel2.add(new JLabel("Deception (CHA)"));

        skillsPanel1.add(historyCB);
        skillsPanel1.add(history);
        skillsPanel2.add(new JLabel("History (INT)"));

        skillsPanel1.add(insightCB);
        skillsPanel1.add(insight);
        skillsPanel2.add(new JLabel("Insight (WIS)"));

        skillsPanel1.add(intimidationCB);
        skillsPanel1.add(intimidation);
        skillsPanel2.add(new JLabel("Intimidation (CHA)"));

        skillsPanel1.add(investigationCB);
        skillsPanel1.add(investigation);
        skillsPanel2.add(new JLabel("Investigation (INT)"));

        skillsPanel1.add(medicineCB);
        skillsPanel1.add(medicine);
        skillsPanel2.add(new JLabel("Medicine (WIS)"));

        skillsPanel1.add(natureCB);
        skillsPanel1.add(nature);
        skillsPanel2.add(new JLabel("Nature (INT)"));

        skillsPanel1.add(perceptionCB);
        skillsPanel1.add(perception);
        skillsPanel2.add(new JLabel("Perception (WIS)"));

        skillsPanel1.add(performanceCB);
        skillsPanel1.add(performance);
        skillsPanel2.add(new JLabel("Performance (CHA)"));

        skillsPanel1.add(persuasionCB);
        skillsPanel1.add(persuasion);
        skillsPanel2.add(new JLabel("Persuasion (CHA)"));

        skillsPanel1.add(religionCB);
        skillsPanel1.add(religion);
        skillsPanel2.add(new JLabel("Religion (INT)"));

        skillsPanel1.add(sleightCB);
        skillsPanel1.add(sleight);
        skillsPanel2.add(new JLabel("Sleight of Hand (DEX)"));

        skillsPanel1.add(stealthCB);
        skillsPanel1.add(stealth);
        skillsPanel2.add(new JLabel("Stealth (DEX)"));

        skillsPanel1.add(survivalCB);
        skillsPanel1.add(survival);
        skillsPanel2.add(new JLabel("Survival (WIS)"));
        //</editor-fold>

        skillsPanel.add(skillsPanel1, BorderLayout.WEST);
        skillsPanel.add(skillsPanel2, BorderLayout.CENTER);

        ///////////////////////////////////////////////////////////////////////////////////////SAVING THROWS PANEL
        JPanel throwsPanel = new JPanel(new BorderLayout());
        throwsPanel.setBorder(BorderFactory.createTitledBorder("SAVING THROWS"));
        JPanel throwsPanel1 = new JPanel(new GridLayout(6, 2));
        JPanel throwsPanel2 = new JPanel(new GridLayout(6, 1));

        //<editor-fold desc="THROWS PANEL ADDS //////////////////////////////////////////////">
        throwsPanel1.add(strengthThrowsCB);
        throwsPanel1.add(strengthThrows);
        throwsPanel2.add(new JLabel("Strength"));

        throwsPanel1.add(dexterityThrowsCB);
        throwsPanel1.add(dexterityThrows);
        throwsPanel2.add(new JLabel("Dexterity"));

        throwsPanel1.add(constitutionThrowsCB);
        throwsPanel1.add(constitutionThrows);
        throwsPanel2.add(new JLabel("Constitution"));

        throwsPanel1.add(intelligenceThrowsCB);
        throwsPanel1.add(intelligenceThrows);
        throwsPanel2.add(new JLabel("Intelligence"));

        throwsPanel1.add(wisdomThrowsCB);
        throwsPanel1.add(wisdomThrows);
        throwsPanel2.add(new JLabel("Wisdom"));

        throwsPanel1.add(charismaThrowsCB);
        throwsPanel1.add(charismaThrows);
        throwsPanel2.add(new JLabel("Charisma"));
        //</editor-fold>

        throwsPanel.add(throwsPanel1, BorderLayout.WEST);
        throwsPanel.add(throwsPanel2, BorderLayout.CENTER);

        JPanel otherAndThrowsPanel = new JPanel(new BorderLayout()); // Combines saving throws, insp., and proficiency
        otherAndThrowsPanel.add(inspirationAndProficiency,  BorderLayout.NORTH);
        otherAndThrowsPanel.add(throwsPanel,  BorderLayout.CENTER);

        JPanel skillsAndOtherPanel = new JPanel(new BorderLayout(5,3)); // Combines all others with skills
        skillsAndOtherPanel.add(otherAndThrowsPanel,  BorderLayout.NORTH);
        skillsAndOtherPanel.add(new JScrollPane(skillsPanel),  BorderLayout.CENTER);
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

        for (int i = 0; i < (8); i++){
           attackPanel.add(new JTextField());
           attackPanel.add(new JTextField());
           attackPanel.add(new JTextField());
        }

        /////////////////////////////////////////////////////////////////////////////////////MIDDLE PANEL BUILDER

        JPanel topMiddlePanel = new JPanel(new BorderLayout(5,0));
        //topMiddlePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        topMiddlePanel.add(miscPanel, BorderLayout.WEST);
        topMiddlePanel.add(hpPanel, BorderLayout.CENTER);

        JPanel bottomMiddlePanel = new JPanel(new BorderLayout());
        bottomMiddlePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        bottomMiddlePanel.add(attackPanel, BorderLayout.CENTER);
        bottomMiddlePanel.add(new JLabel("ATTACKS & SPELLCASTING", SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel middlePanel = new JPanel(new GridLayout(2,1));
        middlePanel.add(topMiddlePanel);
        middlePanel.add(new JScrollPane(bottomMiddlePanel));

        ////////////////////////////////////////////////////////////////////////////////////RIGHT PANEL

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JScrollPane(featuresText), BorderLayout.CENTER);
        rightPanel.add(new JLabel("FEATURES AND TRAITS", SwingConstants.CENTER), BorderLayout.SOUTH);
        //rightPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        ////////////////////////////////////////////////////////////////////////////////////MAIN PANEL

        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 15, 30));
        centerPanel.add(leftPanel);
        centerPanel.add(middlePanel);
        centerPanel.add(rightPanel);

        add(centerPanel, BorderLayout.CENTER);
    }
}
