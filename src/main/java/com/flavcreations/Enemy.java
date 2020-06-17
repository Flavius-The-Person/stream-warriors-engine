package com.flavcreations;

import javax.swing.*;

public class Enemy
{
    private int baseHealth = 100;
    int health;
    public int idleFrame = 0;
    public int attFrame = 3;
    public boolean idleUp = true;
    String name;
    boolean ded;
    
    public String[] sounds = new String[6];
    /*

       sounds[0] = "";//Atk
       sounds[1] = "";//hit/damaged
       sounds[2] = "";//physical skill
       sounds[3] = "";//magical skill
       sounds[4] = "";//knocked out
       sounds[5] = "";//victory
    */
    public ImageIcon[] idleIcons = new ImageIcon[3];
    /*{
            idleIcons[0] = new ImageIcon("");//idle 1
            idleIcons{1] = new ImageIcon("");//idle 2
            IdleIcons[2] = new ImageIcon("");//idle 3
    */
    public ImageIcon[] guardIcons = new ImageIcon[3];
    /*
            guardIcons[0] = new ImageIcon("");//guard 1
            guardIcons[1] = new ImageIcon("");//guard 2
            guardIcons[2] = new ImageIcon("");//guard 3
    */
    public ImageIcon[] damageIcons = new ImageIcon[3];
    /*
            damageIcons[0] = new ImageIcon("");//damaged 1
            damageIcons[1] = new ImageIcon("");//damaged 2
            damageIcons[2] = new ImageIcon("");//damaged 3
    */
    public ImageIcon[] evadeIcons = new ImageIcon[3];
    /*
            evadeIcons[0] = new ImageIcon("");//evade1
            evadeIcons[1] = new ImageIcon("");//evade2
            evadeIcons[2] = new ImageIcon("");//evade3
    */
    public ImageIcon[] skillIcons = new ImageIcon[3];
    /*
            skillIcons[0] = new ImageIcon("");physical skill 1
            skillIcons[1] = new ImageIcon("");physical skill 2
            skillIcons[2] = new ImageIcon("");physical skill 3
    */
    public ImageIcon[] magicIcons = new ImageIcon[3];
    /*
            magicIcons[0] = new ImageIcon("");//magical skill 1
            magicIcons[1] = new ImageIcon("");//magical skill 2
            magicIcons[2] = new ImageIcon("");//magicak skill 3
    */
    public ImageIcon[] itemIcons = new ImageIcon[3];
    /*
            itemIcons[0] = new ImageIcon("");//use item 1
            itemIcons[1] = new ImageIcon("");//use item 2
            itemIcons[2] = new ImageIcon("");//use item 3
    */
    public ImageIcon[] victoryIcons = new ImageIcon[3];
    /*
            victoryIcons[0] = new ImageIcon("");//victory 1
            victoryIcons[1] = new ImageIcon("");//victory 2
            victoryIcons[2] = new ImageIcon("");//victory 3
     */
    public ImageIcon[] crisisIcons = new ImageIcon[3];
    /*
		   crisisIcons[0] = new ImageIcon("");//crisis/danger 1
		   crisisIcons[1] = new ImageIcon("");//crisis/danger 2
		   crisisIcons[2] = new ImageIcon("");//crisis/danger 3
	*/
    public ImageIcon[] abnormalIcons = new ImageIcon[3];
    /*
		   abnormalIcons[0] = new ImageIcon("");//abnormal state 1
		   abnormalIcons[1] = new ImageIcon("");//abnormal state 2
		   abnormalIcons[2] = new ImageIcon("");//abnormal state 3
	*/
    public ImageIcon[] sleepIcons = new ImageIcon[3];
    /*
		   sleepIcons[0] = new ImageIcon("");//sleeping 1
		   sleepIcons[1] = new ImageIcon("");//sleeping 2
		   sleepIcons[2] = new ImageIcon("");//sleeping 3
	*/
    public ImageIcon[] koIcons = new ImageIcon[3];
    /*
		   koIcons[0] = new ImageIcon("");//knocked out 1
		   koIcons[2] = new ImageIcon("");//knocked out 2
		   koIcons[1] = new ImageIcon("");//knocked out 3
	*/
    public ImageIcon[] attackIcons = new ImageIcon[3];
    /*
		   attackIcons[0] = new ImageIcon("");//attack 1
		   attackIcons[2] = new ImageIcon("");//attack 2
		   attackIcons[1] = new ImageIcon("");//attack 3
	*/
    
    
    public void setData(String bname) {
        //health = baseHealth;
        name = bname;
        ded = false;
        System.out.println("name" + name);
    
    
        if (name == "celestialguard-horus") {
    
            System.out.println("CelestialGuard-horus was name of boss");
    
            String baseloc = "src/main/java/com/flavcreations/testfiles/500/";
    
            idleIcons[0] = new ImageIcon( //idle 1
                    baseloc + "Fungoliath/Fungoliath-Idle-01.png");
            idleIcons[1] = new ImageIcon( //idle 2
                    baseloc + "Fungoliath/Fungoliath-Idle-02.png");
            idleIcons[2] = new ImageIcon( //idle 3
                    baseloc + "Fungoliath/Fungoliath-Idle-03.png");
    
            koIcons[0] = new ImageIcon(//knocked out 1
                    baseloc + "Fungoliath/Fungoliath-Dead-01.png");
            koIcons[1] = new ImageIcon(//knocked out 2
                    baseloc + "Fungoliath/Fungoliath-Dead-02.png");
            koIcons[2] = new ImageIcon(//knocked out 3
                    baseloc + "Fungoliath/Fungoliath-Dead-03.png");
    
            attackIcons[0] = new ImageIcon(//attack 1
                    baseloc + "Fungoliath/Fungoliath-Stabbing-01.png");
            attackIcons[1] = new ImageIcon(//attack 2
                    baseloc + "Fungoliath/Fungoliath-Stabbing-02.png");
            attackIcons[2] = new ImageIcon(//attack 3
                    baseloc + "Fungoliath/Fungoliath-Stabbing-03.png");
            
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
        
            victoryIcons[0] = new ImageIcon("");//victory 1
            victoryIcons[1] = new ImageIcon("");//victory 2
            victoryIcons[2] = new ImageIcon("");//victory 3
        
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
        health = health - damage;
        if(health <= 0)
        {
            ded=true;
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
