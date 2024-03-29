package com.flavcreations.characters;

import javax.swing.*;

public class Enemy
{
    private int baseHealth = 100;
    public int health;
    public int idleFrame = 0;
    public int attFrame = 3;
    public boolean idleUp = true;
    public String name;
    public boolean isKO;
    
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
    
    public void setData(int bId) 
    {
        isKO = false;
        System.out.println("name: " + name);
        String contentRootLoc = "src/main/java/com/flavcreations/characters/art/boss/";
        //String sourceRootLoc = "com/flavcreations/characters/art/boss/";
        String baseloc = contentRootLoc;

        String[] sIdleIcons = {"Fungoliath/Fungoliath-Idle-"};
        String[] sAttackIcons = {"Fungoliath/Fungoliath-Stabbing-"};
        String[] sKoIcons = {"Fungoliath/Fungoliath-Dead-"};
        String[] sDamagedIcons = {"PhantomKnight"};
        String[] sVictoryIcons = {"Fungoliath/Fungoliath-Stabbing-"};
        
        String[] sGuardIcons = {""};
        String[] sEvadeIcons = {""};
        String[] sPSkillIcons = {""};
        String[] sMSkillIcons = {""};
        String[] sIUseIcons = {""};
        String[] sCrisisIcons = {""};
        String[] sAbnormalIcons = {""};
        String[] sSleepIcons = {""};
        
        System.out.println("Fungoliath was name of boss");
        
        idleIcons[0] = new ImageIcon( //idle 1
                baseloc + sIdleIcons[bId] + "01.png");
        idleIcons[1] = new ImageIcon( //idle 2
                baseloc + sIdleIcons[bId] + "02.png");
        idleIcons[2] = new ImageIcon( //idle 3
                baseloc + sIdleIcons[bId] + "03.png");
        
        koIcons[0] = new ImageIcon( //knocked out 1
                baseloc + sKoIcons[bId] + "01.png");
        koIcons[1] = new ImageIcon( //knocked out 1
                baseloc + sKoIcons[bId] + "02.png");
        koIcons[2] = new ImageIcon( //knocked out 1
                baseloc + sKoIcons[bId] + "03.png");
        
        attackIcons[0] = new ImageIcon(//attack 1
                baseloc + sAttackIcons[bId] + "01.png");//attack 1
        attackIcons[1] = new ImageIcon(//attack 2
                baseloc + sAttackIcons[bId] + "02.png");//attack 2
        attackIcons[2] = new ImageIcon(//attack 3
                baseloc + sAttackIcons[bId] + "03.png");//attack 3
        
        victoryIcons[0] = new ImageIcon(//victory 1
                baseloc + sVictoryIcons[bId] + "01.png");//attack 3//victory 1
        victoryIcons[1] = new ImageIcon(//victory 2
                baseloc + sVictoryIcons[bId] + "02.png");//attack 3//victory 2
        victoryIcons[2] = new ImageIcon(//victory 3
                baseloc + sVictoryIcons[bId] + "03.png");//attack 3//victory 3
        
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
	
    public void updateHealth(int damage)
    {
        health = health - damage;
        if(health <= 0)
        {
            isKO =true;
            health = 0;
        }
    }
    
    public void animate()
    {
    
    }
    
    public void setHealth(int playerTotalDamage)
    {
        health = playerTotalDamage;
        System.out.println("Boss Health: " + health);
        
    }
}
