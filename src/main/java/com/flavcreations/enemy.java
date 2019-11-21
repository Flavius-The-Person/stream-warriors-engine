package com.flavcreations;

import javax.swing.*;

public class enemy
{
    private int maxHealth = 1000000;
    int health;
    String name;
    boolean ded;
    String[] sounds = new String[6];
    /*
        0Atk     - 1 sound? - attacing the enemy shouty hyuah?
        1Hit     - 1 sound? - being hit, an oof, a squee, a something?
        2Skill   - 1 sound? - using a physical skill so maybe a hear me roar kinda funny thing or something more serious if you want?
        3Magic   - 1 sound? - using a magical skill so maybe harry potter spell words or something?
        4Ko      - 1 sound? - getting knocked out so "Ugh, they got me!" or "Ugh! I should have bought better shoulder pads" or something serious or silly if you want.
        5Victory - 1 sound? - a victorious battle, so like a fanfare or shout of success or "Ugh! thank goodness its over." or whatever?
    */
    ImageIcon[] bIcons = new ImageIcon[21];
    /*{
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""), //stance
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""), //atk set 1
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""), //hit set 1
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""), //skill set 1
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""), //magic set 1
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""),  //KO set
            new ImageIcon(""), new ImageIcon(""), new ImageIcon("")  //victory set
    };*/
    public void setData(String bname)
    {
        health = maxHealth;
        name = bname;
        ded = false;
        bIcons[0]  = new ImageIcon("");//stance 1
        bIcons[1]  = new ImageIcon("");//stance 2
        bIcons[2]  = new ImageIcon("");//stance 3
        bIcons[3]  = new ImageIcon("");//atk 1
        bIcons[4]  = new ImageIcon("");//atk 2
        bIcons[5]  = new ImageIcon("");//atk 3
        bIcons[6]  = new ImageIcon("");//hit 1
        bIcons[7]  = new ImageIcon("");//hit 2
        bIcons[8]  = new ImageIcon("");//hit 3
        bIcons[9]  = new ImageIcon("");//skill 1-1
        bIcons[10] = new ImageIcon("");//skill 1-2
        bIcons[11] = new ImageIcon("");//skill 1-3
        bIcons[12] = new ImageIcon("");//magic 1-1
        bIcons[13] = new ImageIcon("");//magic 1-2
        bIcons[14] = new ImageIcon("");//magic 1-3
        bIcons[15] = new ImageIcon("");//ko 1
        bIcons[16] = new ImageIcon("");//ko 2
        bIcons[17] = new ImageIcon("");//ko 3
        bIcons[18] = new ImageIcon("");//victory 1
        bIcons[19] = new ImageIcon("");//victory 2
        bIcons[20] = new ImageIcon("");//victory 3

        sounds[0] = "";
        sounds[1] = "";
        sounds[2] = "";
        sounds[3] = "";
        sounds[4] = "";
        sounds[5] = "";
    }
    public void updateHealth(int damage)
    {
        health = health - damage;
        if(health < 0) health = 0;
        ded = true;
    }
}
