package com.flavcreations.characters;

import javax.swing.*;

public class Player
{
    private int maxHealth = 100;
    public int health;
    public int idleFrame = 0;
    public int attFrame = 3;
    public boolean idleUp = true;
    public String name;
    public boolean isKO = false;
    public boolean isAnimating = false;
    
    /*might use and might not use contemplating controllingg turns here
    public boolean turn = false;
    public int attack_phase = 0;
    */
    
    public String[] sounds = new String[6];
    public String[] petSounds = new String[6];
    public String[] summonSounds = new String[6];
    /*
       sounds[0] = "";//Atk
       sounds[1] = "";//hit/damaged
       sounds[2] = "";//physical skill
       sounds[3] = "";//magical skill
       sounds[4] = "";//knocked out
       sounds[5] = "";//victory
    */
    public ImageIcon[] idleIcons = new ImageIcon[3];
    public ImageIcon[] petIdleIcons = new ImageIcon[3];
    public ImageIcon[] summonIdleIcons = new ImageIcon[3];
    /*{
            idleIcons[0] = new ImageIcon("");//idle 1
            idleIcons{1] = new ImageIcon("");//idle 2
            IdleIcons[2] = new ImageIcon("");//idle 3
    */
    public ImageIcon[] guardIcons = new ImageIcon[3];
    public ImageIcon[] petGuardIcons = new ImageIcon[3];
    public ImageIcon[] summonGuardIcons = new ImageIcon[3];
    /*
            guardIcons[0] = new ImageIcon("");//guard 1
            guardIcons[1] = new ImageIcon("");//guard 2
            guardIcons[2] = new ImageIcon("");//guard 3
    */
    public ImageIcon[] damageIcons = new ImageIcon[3];
    public ImageIcon[] petDamageIcons = new ImageIcon[3];
    public ImageIcon[] summonDamageIcons = new ImageIcon[3];
    /*
            damageIcons[0] = new ImageIcon("");//damaged 1
            damageIcons[1] = new ImageIcon("");//damaged 2
            damageIcons[2] = new ImageIcon("");//damaged 3
    */
    public ImageIcon[] evadeIcons = new ImageIcon[3];
    public ImageIcon[] petEvadeIcons = new ImageIcon[3];
    public ImageIcon[] summonEvadeIcons = new ImageIcon[3];
    /*
            evadeIcons[0] = new ImageIcon("");//evade1
            evadeIcons[1] = new ImageIcon("");//evade2
            evadeIcons[2] = new ImageIcon("");//evade3
    */
    public ImageIcon[] skillIcons = new ImageIcon[3];
    public ImageIcon[] petSkillIcons = new ImageIcon[3];
    public ImageIcon[] summonSkillIcons = new ImageIcon[3];
    /*
            skillIcons[0] = new ImageIcon("");physical skill 1
            skillIcons[1] = new ImageIcon("");physical skill 2
            skillIcons[2] = new ImageIcon("");physical skill 3
    */
    public ImageIcon[] magicIcons = new ImageIcon[3];
    public ImageIcon[] petMagicIcons = new ImageIcon[3];
    public ImageIcon[] summonMagicIcons = new ImageIcon[3];
    /*
            magicIcons[0] = new ImageIcon("");//magical skill 1
            magicIcons[1] = new ImageIcon("");//magical skill 2
            magicIcons[2] = new ImageIcon("");//magicak skill 3
    */
    public ImageIcon[] itemIcons = new ImageIcon[3];
    public ImageIcon[] petItemIcons = new ImageIcon[3];
    public ImageIcon[] summonItemIcons = new ImageIcon[3];
    /*
            itemIcons[0] = new ImageIcon("");//use item 1
            itemIcons[1] = new ImageIcon("");//use item 2
            itemIcons[2] = new ImageIcon("");//use item 3
    */
    public ImageIcon[] victoryIcons = new ImageIcon[3];
    public ImageIcon[] petVictoryIcons = new ImageIcon[3];
    public ImageIcon[] summonVictoryIcons = new ImageIcon[3];
    /*
            victoryIcons[0] = new ImageIcon("");//victory 1
            victoryIcons[1] = new ImageIcon("");//victory 2
            victoryIcons[2] = new ImageIcon("");//victory 3
     */
    public ImageIcon[] crisisIcons = new ImageIcon[3];
    public ImageIcon[] petCrisisIcons = new ImageIcon[3];
    public ImageIcon[] summonCrisisIcons = new ImageIcon[3];
    /*
		   crisisIcons[0] = new ImageIcon("");//crisis/danger 1
		   crisisIcons[1] = new ImageIcon("");//crisis/danger 2
		   crisisIcons[2] = new ImageIcon("");//crisis/danger 3
	*/
    public ImageIcon[] abnormalIcons = new ImageIcon[3];
    public ImageIcon[] petAbnormalIcons = new ImageIcon[3];
    public ImageIcon[] summonAbnormalIcons = new ImageIcon[3];
    /*
		   abnormalIcons[0] = new ImageIcon("");//abnormal state 1
		   abnormalIcons[1] = new ImageIcon("");//abnormal state 2
		   abnormalIcons[2] = new ImageIcon("");//abnormal state 3
	*/
    public ImageIcon[] sleepIcons = new ImageIcon[3];
    public ImageIcon[] petSleepIcons = new ImageIcon[3];
    public ImageIcon[] summonSleepIcons = new ImageIcon[3];
    /*
		   sleepIcons[0] = new ImageIcon("");//sleeping 1
		   sleepIcons[1] = new ImageIcon("");//sleeping 2
		   sleepIcons[2] = new ImageIcon("");//sleeping 3
	*/
    public ImageIcon[] koIcons = new ImageIcon[3];
    public ImageIcon[] petKoIcons = new ImageIcon[3];
    public ImageIcon[] summonKoIcons = new ImageIcon[3];
    /*
		   koIcons[0] = new ImageIcon("");//knocked out 1
		   koIcons[2] = new ImageIcon("");//knocked out 2
		   koIcons[1] = new ImageIcon("");//knocked out 3
	*/
    public ImageIcon[] attackIcons = new ImageIcon[3];
    public ImageIcon[] petAttackIcons = new ImageIcon[3];
    public ImageIcon[] summonAttackIcons = new ImageIcon[3];
    /*
		   attackIcons[0] = new ImageIcon("");//attack 1
		   attackIcons[2] = new ImageIcon("");//attack 2
		   attackIcons[1] = new ImageIcon("");//attack 3
	*/

    public void setData(String pname, int pgen)
    {
        health = maxHealth;
        name = pname;
        isKO = false;
        boolean inDB = false;
        
        String baseloc = "src/main/java/com/flavcreations/testfiles/200/";
        
        String[] sCharIcons = {
                                "Anima/Bunny/Bunny","Anima/Cat/Cat","Anima/Fox/Fox",
                                "Anima/Paintcat/Paintcat","Anima/Puffy/Puffy","Anima/Witchcat/Witchcat"};
        String[] sKoIcons = {
                                "Anima/Bunny/Bunny","Anima/Cat/Cat","Anima/Fox/Fox",
                                "Anima/Paintcat/Paintcat","Anima/Puffy/Puffy","Anima/Witchcat/Witchcat"};
        String[] sAttackIcons = {//"Knight/Knight_Swinging_"
                                "Anima/Bunny/Bunny_Swinging_","Anima/Cat/Cat_Swinging_","Anima/Fox/Fox_Swinging_",
                                "Anima/Paintcat/Paintcat_Swinging_","Anima/Puffy/Puffy_Swinging_","Anima/Witchcat/Witchcat_Swinging_" };
        String[] sVictoryIcons = {//"Knight/Knigh_Victory_"
                                "Anima/Bunny/Bunny","Anima/Cat/Cat","Anima/Fox/Fox",
                                "Anima/Paintcat/Paintcat","Anima/Puffy/Puffy","Anima/Witchcat/Witchcat" };
    
        String[] sDamagedIcons = {""};
        String[] sGuardIcons = {""};
        String[] sEvadeIcons = {""};
        String[] sPSkillIcons = {""};
        String[] sMSkillIcons = {""};
        String[] sIUseIcons = {""};
        String[] sCrisisIcons = {""};
        String[] sAbnormalIcons = {""};
        String[] sSleepIcons = {""};
        
        //check if in db

        /*
         *
         * put notes here for top of head thinking whatever?
         *
         * If I randomly select a set of attack anims like set 1 = 0-2, set 2 = 3-5, set 3 = 6-8
         * I can effectively randomize the animations a bit and it will only make me do a slight bit more file saving?
         * More of an issue for anything that doesn't have more than one set of attacks though which should be ok if
         * I just duplicate 1-3 and add the numbers so that it just thinks its more but its really renamed original 1-3?
         *
         */
        
        
        if (inDB)
        {
            //System.out.println("Player found in db");
            
            idleIcons[0] = new ImageIcon( //idle 1
                    baseloc + sCharIcons[0] + "01.png");
            idleIcons[1] = new ImageIcon( //idle 2
                    baseloc + sCharIcons[0] + "02.png");
            idleIcons[2] = new ImageIcon( //idle 3
                    baseloc + sCharIcons[0] + "03.png");
            
            int iKoIcons = 0;
            
            if(iKoIcons == 0) {
                koIcons[0] = new ImageIcon( //knocked out 1
                        baseloc + sAttackIcons[0] + "01.png");
                koIcons[1] = new ImageIcon( //knocked out 1
                        baseloc + sAttackIcons[0] + "02.png");
                koIcons[2] = new ImageIcon( //knocked out 1
                        baseloc + sAttackIcons[0] + "03.png");
            }
            if(iKoIcons >= 1)
            {
                koIcons[0] = new ImageIcon( //knocked out 1
                        baseloc + sKoIcons[iKoIcons-1] + "01.png");
                koIcons[1] = new ImageIcon( //knocked out 1
                        baseloc + sKoIcons[iKoIcons-1] + "02.png");
                koIcons[2] = new ImageIcon( //knocked out 1
                        baseloc + sKoIcons[iKoIcons-1] + "03.png");
            }
        
            attackIcons[0] = new ImageIcon(//attack 1
                    baseloc + sAttackIcons[0] +  "01.png");//attack 1
            attackIcons[1] = new ImageIcon(//attack 2
                    baseloc + sAttackIcons[0] + "02.png");//attack 2
            attackIcons[2] = new ImageIcon(//attack 3
                    baseloc + sAttackIcons[0] + "03.png");//attack 3
    
            victoryIcons[0] = new ImageIcon(//victory 1
                    baseloc + sCharIcons[0] + "_Swinging_" + "01.png");//attack 3//victory 1
            victoryIcons[1] = new ImageIcon(//victory 2
                    baseloc + sCharIcons[0] + "_Swinging_" + "02.png");//attack 3//victory 2
            victoryIcons[2] = new ImageIcon(//victory 3
                    baseloc + sCharIcons[0] + "_Swinging_" + "03.png");//attack 3//victory 3

            guardIcons[0] = new ImageIcon("");//guard 1
            guardIcons[1] = new ImageIcon("");//guard 2
            guardIcons[2] = new ImageIcon("");//guard 3

            damageIcons[0] = new ImageIcon("");//damaged 1
            damageIcons[1] = new ImageIcon("");//damaged 2
            damageIcons[2] = new ImageIcon("");//damaged 3

            evadeIcons[0] = new ImageIcon("");//evade1
            evadeIcons[1] = new ImageIcon("");//evade2
            evadeIcons[2] = new ImageIcon("");//evade3

            skillIcons[0] = new ImageIcon("");//physical skill 1
            skillIcons[1] = new ImageIcon("");//physical skill 2
            skillIcons[2] = new ImageIcon("");//physical skill 3

            magicIcons[0] = new ImageIcon("");//magical skill 1
            magicIcons[1] = new ImageIcon("");//magical skill 2
            magicIcons[2] = new ImageIcon("");//magicak skill 3

            itemIcons[0] = new ImageIcon("");//use item 1
            itemIcons[1] = new ImageIcon("");//use item 2
            itemIcons[2] = new ImageIcon("");//use item 3

            crisisIcons[0] = new ImageIcon("");//crisis/danger 1
            crisisIcons[1] = new ImageIcon("");//crisis/danger 2
            crisisIcons[2] = new ImageIcon("");//crisis/danger 3

            abnormalIcons[0] = new ImageIcon("");//abnormal state 1
            abnormalIcons[1] = new ImageIcon("");//abnormal state 2
            abnormalIcons[2] = new ImageIcon("");//abnormal state 3

            sleepIcons[0] = new ImageIcon("");//sleeping 1
            sleepIcons[1] = new ImageIcon("");//sleeping 2
            sleepIcons[2] = new ImageIcon("");//sleeping 3



            //since I can't store and reproduce the file itself from a database,
            //I can store the file in the program and the name of the file in the database.
            //which will be sounds[x] = "sound location" + sound name + ".fileformat";
            //I believe this should work and will just have to update both
            //the character creation app and the game itself when I add new voice stuff

            //ultimately if I use the same file in the creator it will already have the same name
            //so I just need to take the name of the file being used
            //which makes it sounds[x] = "sound location" + filename.format --- all at once

            sounds[0] = "";//Atk
            sounds[1] = "";//hit/damaged
            sounds[2] = "";//physical skill
            sounds[3] = "";//magical skill
            sounds[4] = "";//knocked out
            sounds[5] = "";//victory
        }
        else if(!inDB)
        {
            //System.out.println("Player not found in db");
            idleIcons[0] = new ImageIcon( //idle 1
                    baseloc + sCharIcons[pgen] + "_Idle_" + "01.png");
            idleIcons[1] = new ImageIcon( //idle 2
                    baseloc + sCharIcons[pgen] + "_Idle_" + "02.png");
            idleIcons[2] = new ImageIcon( //idle 3
                    baseloc + sCharIcons[pgen] + "_Idle_" + "03.png");
    
            int iKoIcons = 0;
    
            if(iKoIcons == 0) {
                koIcons[0] = new ImageIcon( //knocked out 1
                        baseloc + sCharIcons[pgen]  + "_Dead_" + "01.png");
                koIcons[1] = new ImageIcon( //knocked out 1
                        baseloc + sCharIcons[pgen]  + "_Dead_" + "02.png");
                koIcons[2] = new ImageIcon( //knocked out 1
                        baseloc + sCharIcons[pgen]  + "_Dead_" + "03.png");
            }
            if(iKoIcons >= 1)
            {
                koIcons[0] = new ImageIcon( //knocked out 1
                        baseloc + sKoIcons[iKoIcons-1] + "01.png");
                koIcons[1] = new ImageIcon( //knocked out 1
                        baseloc + sKoIcons[iKoIcons-1] + "02.png");
                koIcons[2] = new ImageIcon( //knocked out 1
                        baseloc + sKoIcons[iKoIcons-1] + "03.png");
            }
    
            attackIcons[0] = new ImageIcon(//attack 1
                    baseloc + sAttackIcons[pgen] + "_Swinging_" +  "01.png");//attack 1
            attackIcons[1] = new ImageIcon(//attack 2
                    baseloc + sAttackIcons[pgen] + "_Swinging_" + "02.png");//attack 2
            attackIcons[2] = new ImageIcon(//attack 3
                    baseloc + sAttackIcons[pgen] + "_Swinging_" + "03.png");//attack 3
    
            victoryIcons[0] = new ImageIcon(//victory 1
                    baseloc + sVictoryIcons[pgen] + "_Victory_" + "01.png");//attack 3//victory 1
            victoryIcons[1] = new ImageIcon(//victory 2
                    baseloc + sVictoryIcons[pgen] + "_Victory_" + "02.png");//attack 3//victory 2
            victoryIcons[2] = new ImageIcon(//victory 3
                    baseloc + sVictoryIcons[pgen] + "_Victory_" + "03.png");//attack 3//victory 3
            
            guardIcons[0] = new ImageIcon("");//guard 1
            guardIcons[1] = new ImageIcon("");//guard 2
            guardIcons[2] = new ImageIcon("");//guard 3
    
            damageIcons[0] = new ImageIcon("");//damaged 1
            damageIcons[1] = new ImageIcon("");//damaged 2
            damageIcons[2] = new ImageIcon("");//damaged 3
    
            evadeIcons[0] = new ImageIcon("");//evade1
            evadeIcons[1] = new ImageIcon("");//evade2
            evadeIcons[2] = new ImageIcon("");//evade3
    
            skillIcons[0] = new ImageIcon("");//physical skill 1
            skillIcons[1] = new ImageIcon("");//physical skill 2
            skillIcons[2] = new ImageIcon("");//physical skill 3
    
            magicIcons[0] = new ImageIcon("");//magical skill 1
            magicIcons[1] = new ImageIcon("");//magical skill 2
            magicIcons[2] = new ImageIcon("");//magicak skill 3
    
            itemIcons[0] = new ImageIcon("");//use item 1
            itemIcons[1] = new ImageIcon("");//use item 2
            itemIcons[2] = new ImageIcon("");//use item 3
    
            crisisIcons[0] = new ImageIcon("");//crisis/danger 1
            crisisIcons[1] = new ImageIcon("");//crisis/danger 2
            crisisIcons[2] = new ImageIcon("");//crisis/danger 3
    
            abnormalIcons[0] = new ImageIcon("");//abnormal state 1
            abnormalIcons[1] = new ImageIcon("");//abnormal state 2
            abnormalIcons[2] = new ImageIcon("");//abnormal state 3
    
            sleepIcons[0] = new ImageIcon("");//sleeping 1
            sleepIcons[1] = new ImageIcon("");//sleeping 2
            sleepIcons[2] = new ImageIcon("");//sleeping 3
            
    
            sounds[0] = "";//Atk
            sounds[1] = "";//hit/damaged
            sounds[2] = "";//physical skill
            sounds[3] = "";//magical skill
            sounds[4] = "";//knocked out
            sounds[5] = "";//victory
        }
    }

    public void updateHealth(int damage)
    {
        /*
        * update the health based on damage taken,
        * and if the character dies set health to 0 and ded bool = true;
         */
        health = health - damage;
        if(health <= 0)
        {
            isKO = true;
            health = 0;
            System.out.println(this.name + " is dead");
        }
    }
    
    public void animate()
    {
    
    }
    public void setHealth(int pHealth)
    {
        health = pHealth;
    }
    

}
