package com.flavcreations;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class scene extends JFrame {
    private JFrame healthFrame, healthframe2;
    private JPanel[] battlerPanels;
    private JPanel healthPanel;
    private JPanel bossHealthPanel;
    private JPanel[] effectPanels;
    private JLabel[] battlerLabels;
    private JLabel[] effectLabels;

    private JPanel pboss;
    private JLabel lboss;
    private JPanel eboss;
    public player[] players;

    scene()
    {
        super("Stream Warriors Fight");

        getContentPane().setBackground(Color.MAGENTA);

        setSize(1920,1080);
        setVisible(true);

        TimerTask updatePanels = new TimerTask()
        {
            public void run()
            {

                //System.out.println("update Panels task");
            }
        };

        ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();

        long updatePanDelay = 1000L;
        long updatePanPeriod = 1000L;

        ex.scheduleAtFixedRate(updatePanels, updatePanDelay, updatePanPeriod, TimeUnit.MILLISECONDS);

    }


    private void setupScene()
    {


        //full size = 1600 , 900 or full size = 1920 , 1080

        /*

        1920-200 = 1720
                                                     |---
        could put players around the boss like this: |[x]
                                                     |---

        Or just completely cut out that section around the boss?

         */

        // lets swap to 1920/1080
        getContentPane().setSize(1920, 1080);


        // lets assume we need 80 from the 1080 for stats
        // 1080 - 80 = 1000 = 1000/100 = 10




        //250 250
        //1700 / 250 = 6.8 rounded down to 6
        //1000 / 250 = 4
        //6 * 4 = 24
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
        pboss.setSize(900,900);
        pboss.setLocation(1000,25);

        //player health panel
        healthPanel = new JPanel();
        healthPanel.setBackground(Color.RED);
        healthPanel.setSize(900,75);
        healthPanel.setLocation(1000,925);
        add(healthPanel);

        JPanel finalPan = new JPanel();
        add(finalPan);
        finalPan.setBackground(Color.MAGENTA);
/*
        healthFrame = new JFrame("Health Frame");
        healthFrame.setSize(1920,80);

        healthFrame2 = new JFrame("Health Frame 2");
        healthFrame2.setSize(80,1920);

        healthFrame.setVisible(true);
        healthFrame2.setVisible(true);
*/


        getContentPane().setBackground(Color.magenta);

        // 1700 / 150 = 11.3333333_ rounded down to 11 across
        // 1000 / 150 = 6.66666667_ rounded down to 6 down
        // 11 * 6 = 66

        // 1700 / 200 = 8.5 rounded down to 8 across
        // 1000 / 200 = 5 down
        // 8 * 5 = 40

        // 1700 / 250 = 6.8 rounded down to 6 across
        // 1000 / 250 = 4 down
        // 6 * 4 = 24

        // 1700 / 300 = 5.6 rounded down to 5 across
        // 1000 / 300 = 3.333333_ rounded down to 3 down
        // 5 x 3 = 15

        // 1700 / 500 = 3.4 rounded down to 3 across
        // 1000 / 500 = 2 down
        // 3 x 2 = 6

        //probably not going to go this low due to the lack of likelyhood there are this many people playing? but maybe?
        // 1700 / 50  = 34 across
        // 1000 / 50 = 20 down
        // 35 x 50 = 1750





            /*
            btlr1 = new JPanel();
            btlr2 = new JPanel();
            btlr3 = new JPanel();
            btlr4 = new JPanel();
            btlr5 = new JPanel();
            boss = new JPanel();
            finalPan = new JPanel();

            lbtlr1 = new JLabel(btlrshlths[0]);
            lbtlr2 = new JLabel(btlrshlths[1]);
            lbtlr3 = new JLabel(btlrshlths[2]);
            lbtlr4 = new JLabel(btlrshlths[3]);
            lbtlr5 = new JLabel(btlrshlths[4]);
            lboss = new JLabel(bossshlths);

            finalPan.setBackground(Color.MAGENTA);

            btlr1.setSize(150,150);
            btlr2.setSize(150,150);
            btlr3.setSize(150,150);
            btlr4.setSize(150,150);
            btlr5.setSize(150,150);
            boss.setSize(750,750);
boss.setLocation(825,0);
            btlr1.add(lbtlr1);
            btlr2.add(lbtlr2);
            btlr3.add(lbtlr3);
            btlr4.add(lbtlr4);
            btlr5.add(lbtlr5);
            boss.add(lboss);

            getContentPane().setBackground(Color.MAGENTA);
            setSize(1600, 900);
            add(btlr1);
            btlr1.setLocation(0,0);
            add(btlr2);
            btlr2.setLocation(0,150);
            add(btlr3);
            btlr3.setLocation(0, 300);
            add(btlr4);
            btlr4.setLocation(0,450);
            add(btlr5);
            btlr5.setLocation(0,600);

            add(boss);
            boss.setLocation(825,0);

            add(finalPan);*/

    }
    public void loadRoster(ArrayList<String> roster, String charType)
    {
        /*int ri = 0;
        for(String rAdd : roster)
        {

            players[ri][0] = rAdd;
            players[ri][1] = charType;
            ri++;

                    /* for (String pAdd : patrons) {
            dlm2.addElement(pAdd);
        }

        playerCount = ri;*/
    }

    public void loadGame()//, int pgen)
    {
        int playerCount = players.length;
        /*players = new player[roster.size()];
        int playerCount = roster.size();
        Random randy = new Random();

        int pgen = randy.nextInt(2);
        for(int gri = 0; gri < playerCount; gri++)
        {
            players[gri].setData(roster.get(gri), pgen);
        }*/

        battlerPanels = new JPanel[playerCount];
        battlerLabels = new JLabel[playerCount];
        effectPanels = new JPanel[playerCount+1];
        effectLabels = new JLabel[playerCount+1];
        System.out.println("end of setting player count, and panel/label arrays");

        for(int rri = 0; rri < playerCount; rri++)
        {
            battlerPanels[rri] = new JPanel();
            battlerLabels[rri] = new JLabel();
            battlerLabels[rri].setText(players[rri].name + " testing");//; = new JLabel(player[rri].name); // + " : "+ player[rri].health);

            System.out.println("player " + rri + " should be " + players[rri].name);
            effectPanels[rri] = new JPanel();
            effectLabels[rri] = new JLabel("Eff Pan");
            effectLabels[rri].setText("Eff Pan " + rri);//  = new JLabel("Eff Pan " + rri);

            battlerPanels[rri].add(battlerLabels[rri]);
            System.out.println(players[rri].name);
        }
        System.out.println("end of loading player panels/labels");
        //effectPanels[playerCount] = new JPanel();
        //effectLabels[playerCount] = new JLabel();

        setupScene();

    }
    public void addPlayers(ArrayList<String> roster)
    {
        players = new player[roster.size()];
        Random randy = new Random();
        int pgen = randy.nextInt(2);
        for(int pint = 0; pint < players.length; pint++)
        {
            players[pint] = new player();
            players[pint].setData(roster.get(pint), pgen);
        }
        loadGame();
    }

}
