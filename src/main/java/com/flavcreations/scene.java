package com.flavcreations;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class scene extends JFrame
{
    
    private JPanel[] battlerPanels;
    private JLabel[] battlerLabels;
    private JPanel healthPanel;
    private JPanel bossHealthPanel;
    private JPanel[] effectPanels;
    private JLabel[] effectLabels;

    private JPanel pboss;
    private JLabel lboss;
    private JPanel eboss;
    private JLabel elboss;
    
    public player[] players;
    
    public long turnCycleDelay;
    public long turnCyclePeriod;
    private List<Integer> actions = new ArrayList<Integer>();
    
    scene()
    {
        super("Stream Warriors Fight");

        getContentPane().setBackground(Color.MAGENTA);

        setSize(1920,1080);
        setVisible(true);

        
        /*
        * set timer task to updateScene();
        * which will animate and update
        * the scene while processing battle actions
         */
        /*
        TimerTask updatePanels = new TimerTask()
        {
            public void run()
            {
                updateScene();
                //System.out.println("update Panels task");
            }
        };
        
        
        ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
        
        //plan to put in the menu a setting to manipulate how fast events take place
        turnCycleDelay = 60000L;
        turnCyclePeriod = 60000L; //60000 milliseconds in a minute
    
        long secondMilliSeconds = 1000L;
        long smsDelay = secondMilliSeconds;
        long smsPeriod = secondMilliSeconds;
        
        
        
        
        ex.scheduleAtFixedRate(updatePanels, smsDelay, smsPeriod, TimeUnit.MILLISECONDS);
        */

    }
    
    private Integer stance = 0;
    private boolean stanceUp = true;
    
    private void updateScene()
    {
        generateTurns();
        
        final int y = 0;
        
        TimerTask actionTask = new TimerTask()
        {
            public void run()
            {
                //doTurn(actions.get(y));
            }
        };
        
        //System.out.println("players.length-" + players.length);
        
        for(int s = 0; s <= players.length; s++)
        {
            if(s < players.length)
            {
                battlerLabels[s].setText(players[s].name + "-" + players[s].health + " Frame:" + stance);
                System.out.println("update player" + s);
                battlerLabels[s].updateUI();
            }
            if(s == players.length)
            {
                lboss.setText("Boss-health Frame:" + stance);
                System.out.println("update boss");
                lboss.updateUI();
            }
            
        }
    
        if(stanceUp)
        {
            stance++;
            if(stance>=2)
            {
                stanceUp = false;
            }
        }
        else if(!stanceUp)
        {
            stance--;
            if(stance<=0)
            {
                stanceUp = true;
            
            }
        }
        
        /*
        for(int y = 0; y < players.length + 1; y++)
        {
            long actionDelay = turnCycleDelay/players.length;
            long actionPeriod = turnCyclePeriod/players.length;
            
        
        }*/
        
        
    }
    
    private void doTurn(Integer p)
    {
        for(int s = 0; s < players.length; s++)
        {
            /*if(s == p)
            {
                battlerLabels[s].setIcon(players[s].attackIcons[0]);
            }
            if(s!=p)
            {
                battlerLabels[s].setIcon(players[s].idleIcons[stance]);
            }*/
            
            
            if(stanceUp)
            {
                stance++;
                if(stance>=2)
                {
                    stanceUp = false;
                }
            }
            else if(!stanceUp)
            {
                stance--;
                if(stance<=0) {
                    stanceUp = true;
                }
            }
            
        }
    }

    private void generateTurns()
    {
        //make a random list of integers
        Random randy = new Random();
        actions.clear();
    
        //for loop for while integer list is less than number of players + 1 for boss
        for(int x = 0; x < players.length + 1; x++)
        {
            //generate a random integer within player range + 1 for boss
            int actionInt = randy.nextInt(players.length + 1);
        
            //while the generated integer is already in the list, generate a new one.
            while(actions.contains(actionInt))
            {
                actionInt = randy.nextInt(players.length + 1);
            }
            //if the new integer is not in the list, add it to the list.
            actions.add(actionInt);
        }
        
    }
    private void setupScene()
    {


        /*
        full size = 1600 , 900 or full size = 1920 , 1080
        1920-200 = 1720
                                                     |---
        could put players around the boss like this: |[x]
                                                     |---
        Or just completely cut out that section around the boss?
         */
        // lets swap to 1920/1080
        // lets assume we need 80 from the 1080 for stats
        // 1080 - 80 = 1000 = 1000/100 = 10
        // 250 250
        // 1700 / 250 = 6.8 rounded down to 6
        // 1000 / 250 = 4
        // 6 * 4 = 24

        int[] pos24x = {
                0, 0, 0, 0,             //1-4
                250, 250, 250, 250,     //2-8
                500, 500, 500, 500,     //3-12
                750, 750, 750, 750,     //4-16
                1000, 1000, 1000, 1000, //5-20
                1250, 1250, 1500, 1500, //6-24
        };

        int[] pos24y = {
                0, 250, 500, 750, //1-4
                0, 250, 500, 750, //2-8
                0, 250, 500, 750, //3-12
                0, 250, 500, 750, //4-16
                0, 250, 500, 750, //5-20
                0, 750, 0,   750   //6-24
        };

        //set background color and add panels to frame
        for(int tpi = 0; tpi < battlerPanels.length; tpi ++ )
        {
            battlerPanels[tpi].setBackground(Color.GREEN);
            add(battlerPanels[tpi]);

            if(battlerPanels.length < 17)
            {
                battlerPanels[tpi].setSize(250,250);
                battlerPanels[tpi].setLocation(pos24x[tpi], pos24y[tpi]);
            }
        }

        //boss health panel
        bossHealthPanel = new JPanel();
        bossHealthPanel.setBackground(Color.RED);
        bossHealthPanel.setSize(900,25);
        bossHealthPanel.setLocation(1000,0);
        add(bossHealthPanel);

        //boss panel
        lboss = new JLabel("Boss");
        pboss = new JPanel();
        pboss.setBackground(Color.DARK_GRAY);
        pboss.add(lboss);
        add(pboss);
        pboss.setSize(500,500);
        pboss.setLocation(1000,25);
        eboss = new JPanel();
        elboss = new JLabel();
        add(elboss);
        elboss.setSize(500,500);
        elboss.setLocation(1000,25);


        /*
        * for some reason the player health panel
        * is not popping up again in the frame
        * until the game is activated (start is pushed)
        */
        //player health panel
        healthPanel = new JPanel();
        healthPanel.setBackground(Color.RED);
        healthPanel.setSize(900,75);
        healthPanel.setLocation(1000,925);
        add(healthPanel);


        /*
        * I haven't quite figured this one out,
        * I'm guessing the last panel added to the frame
        * becomes the primary content pane which gets changed.
        * So if the last thing added was the above healthPanel
        * it would make that health panel go full screen and
        * change it to magenta down below.
        * If you happen to know a fix to this please let me know.
        */
        JPanel finalPan = new JPanel();
        add(finalPan);
        finalPan.setBackground(Color.MAGENTA);
        updateScene();
    
        TimerTask updatePanels = new TimerTask()
        {
            public void run()
            {
                updateScene();
                //System.out.println("update Panels task");
            }
        };
    
    
        ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
    
        //plan to put in the menu a setting to manipulate how fast events take place
        turnCycleDelay = 60000L;
        turnCyclePeriod = 60000L; //60000 milliseconds in a minute
    
        long secondMilliSeconds = 1000L;
        long smsDelay = secondMilliSeconds;
        long smsPeriod = secondMilliSeconds;
    
    
    
    
        ex.scheduleAtFixedRate(updatePanels, smsDelay, smsPeriod, TimeUnit.MILLISECONDS);

        //getContentPane().setBackground(Color.magenta);
    }

    //add players from roster to the scene
    public void addPlayers(ArrayList<String> roster)
    {
        //set players object array size to roster size.
        players = new player[roster.size()];
        
        //import random function
        Random randy = new Random();
        
        //use random function to generate a random number between 2 for use when a person doesn't have a set character
        int pgen = randy.nextInt(2);
        
        //for loop through the players object array adding data to each player
        for(int pint = 0; pint < players.length; pint++)
        {
            players[pint] = new player();
            players[pint].setData(roster.get(pint), pgen);
        }
        
        //initiate the load game function
        loadGame();
    }
    
    //load game function to create panels and labels for each character.
    public void loadGame()
    {
        //initiate pannels and labels arrays for battler panels/labels and effect panels/labels
        battlerPanels = new JPanel[players.length];
        battlerLabels = new JLabel[players.length];
        effectPanels = new JPanel[players.length+1];
        effectLabels = new JLabel[players.length+1];
        
        //console print stating that the panels and labels were created for testing purposes (will be taken out later)
        System.out.println("end of setting player count, and panel/label arrays");
        
        
        for(int rri = 0; rri < players.length; rri++)
        {
            //create a new panel and label for player characters
            battlerPanels[rri] = new JPanel();
            battlerLabels[rri] = new JLabel();
            battlerLabels[rri].setText(players[rri].name + "-" + players[rri].health);
            System.out.println("player " + rri + " should be " + players[rri].name);
            
            //create a new pannel and label for battle effects
            effectPanels[rri] = new JPanel();
            effectLabels[rri] = new JLabel("Eff Pan");
            effectLabels[rri].setText("Eff Pan " + rri);
            
            battlerPanels[rri].add(battlerLabels[rri]);
            System.out.println(players[rri].name);
        }
        
        //console print stating that player data has been loaded into panels/labels
        System.out.println("end of loading player panels/labels");
        effectPanels[players.length] = new JPanel();
        effectLabels[players.length] = new JLabel();
        setupScene();
        
    }
    
}
