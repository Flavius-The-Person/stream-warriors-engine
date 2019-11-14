package com.flavcreations;

import javax.swing.*;

public class player
{
    private int maxHealth = 1000;
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
    ImageIcon[] pIcons = new ImageIcon[21];
    /*{
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""), //stance
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""), //atk set 1
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""), //hit set 1
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""), //skill set 1
            new ImageIcon(""), new ImageIcon(""), new ImageIcon(""), //magic set 1
            new ImageIcon(""), new ImageIcon(""), new ImageIcon("")  //KO set
            new ImageIcon(""), new ImageIcon(""), new ImageIcon("")  //victory set
    };*/



    public void setData(String pname, int pgen)
    {
        health = maxHealth;
        name = pname;
        ded = false;
        boolean inDB = false;
        //check if in db

        if (inDB)
        {
            System.out.println("Player found in db");
            System.out.println("Player found in db");
            pIcons[0] = new ImageIcon("");//stance 1
            pIcons[1] = new ImageIcon("");//stance 2
            pIcons[2] = new ImageIcon("");//stance 3
            pIcons[3] = new ImageIcon("");//atk 1
            pIcons[4] = new ImageIcon("");//atk 2
            pIcons[5] = new ImageIcon("");//atk 3
            pIcons[6] = new ImageIcon("");//hit 1
            pIcons[7] = new ImageIcon("");//hit 2
            pIcons[8] = new ImageIcon("");//hit 3
            pIcons[9] = new ImageIcon("");//skill 1-1
            pIcons[10] = new ImageIcon("");//skill 1-2
            pIcons[11] = new ImageIcon("");//skill 1-3
            pIcons[12] = new ImageIcon("");//magic 1-1
            pIcons[13] = new ImageIcon("");//magic 1-2
            pIcons[14] = new ImageIcon("");//magic 1-3
            pIcons[15] = new ImageIcon("");//ko 1
            pIcons[16] = new ImageIcon("");//ko 2
            pIcons[17] = new ImageIcon("");//ko 3
            pIcons[18] = new ImageIcon("");//victory 1
            pIcons[19] = new ImageIcon("");//victory 2
            pIcons[20] = new ImageIcon("");//victory 3


            //since I can't store and reproduce the file itself from a database,
            //I can store the file in the program and the name of the file in the database.
            //which will be sounds[x] = "sound location" + sound name + ".fileformat";
            //I believe this should work and will just have to update both
            //the character creation app and the game itself when I add new voice stuff

            //ultimately if I use the same file in the creator it will already have the same name
            //so I just need to take the name of the file being used
            //which makes it sounds[x] = "sound location" + filename.format --- all at once

            sounds[0] = "";
            sounds[1] = "";
            sounds[2] = "";
            sounds[3] = "";
            sounds[4] = "";
            sounds[5] = "";
        }
    }

    public void updateHealth(int damage)
    {
        health = health - damage;
        if(health < 0) health = 0;
        ded = true;
    }

}
