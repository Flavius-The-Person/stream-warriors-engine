package com.flavcreations;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* A menu without stream warriors layout (patreon/github sponsors/website buttons and whatnot)
 */
public class menu2 extends JFrame
{
    /*
    * Class variables used within this class.
    * Most can be set to private as they don't need to be used outside this class.
     */
    private JLabel menuImgLbl, ttlLbl;
    private ImageIcon menuImgIcn;
    private JButton startButton, opnRstrBtn;
    private String bgImg = "";
    private boolean opnRstr = false;
    boolean fightStarted = false;
    boolean fightPaused = false;
    private DefaultListModel dlm;
    private ArrayList<String> roster = new ArrayList<String>();
    private JScrollPane rjsp;
    private JList rosterList;




    public menu2()
    {
        //setting the title of the frame.
        super("Stream Warriors Menu");

        //setting the size of the frame
        setSize(615,840);

        /*
        *this was a test for setting the background off the panel,
        * I haven't made the image yet so I haven't yet tested it but it should work.
        * Might have to add it in underneath all the other elements though.
        *
        menuImgIcn = new ImageIcon(bgImg);
        menuImgLbl = new JLabel();
        menuImgLbl.setIcon(menuImgIcn);
        menuImgLbl.setSize(600,900);
        */
        getContentPane().setBackground(Color.BLACK);

        ttlLbl = new JLabel("Game Name");
        ttlLbl.setForeground(Color.RED);



        /*
        * creating a open roster button to enable/disable
        * allowing new additions to the roster.
         */
        opnRstrBtn = new JButton("Open Roster");
        opnRstrBtn.setBackground(Color.DARK_GRAY);
        opnRstrBtn.setForeground(Color.RED);
        opnRstrBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(opnRstr)
                {
                    opnRstr = false;
                    opnRstrBtn.setText("Open Roster");
                } else if(!opnRstr)
                {
                    if(!fightStarted)
                    {
                        opnRstr = true;
                        opnRstrBtn.setText("Close Roster");
                    }
                }
            }
        });

        /*
        * adding a member to the roster to make sure it works
        * you can take this out if you want
         */
        roster.add("Flavius");

        /*
        * adding roster to a default list model
         */
        dlm = new DefaultListModel();
        for (String rAdd : roster) {
            dlm.addElement(rAdd);
        }
        /*
        * adding the dlm to the rosterList JList
         */
        rosterList = new JList(dlm);

        /*
        * adding the rosterList JList to the JScrollPane
        *
        * still working on getting the scroll panes to
        * change foreground/background colors
         */
        rjsp = new JScrollPane(rosterList);

        /*
         * creating a start button for starting/pausing/unpausing the game.
         */
        startButton = new JButton("Start");
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.RED);
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!opnRstr)
                {
                    if(fightStarted)
                    {
                        if(fightPaused)
                        {
                            fightPaused = false;
                            startButton.setText("Pause");
                        } else if(!fightPaused)
                        {
                            fightPaused = true;
                            startButton.setText("Start/Resume");
                        }
                    }else if(!fightStarted)
                    {
                        fightStarted = true;
                        startButton.setText("Pause");
                    }
                }
            }
        });

        /*
        * adding the title label, buttons and roster scroll pane to the frame
        * while also adding custom size/locations instead of using a layout manager
         */
        add(ttlLbl);
        ttlLbl.setSize(600,200);
        ttlLbl.setLocation(0,0);
        add(startButton);
        startButton.setSize(300,100);
        startButton.setLocation(150,600);
        add(opnRstrBtn);
        opnRstrBtn.setSize(300,100);
        opnRstrBtn.setLocation(150,300);
        add(rjsp);
        rjsp.setSize(300,200);
        rjsp.setLocation(150,400);

        /*
        * using a last jlabel to fix content pane trying
        * to take a button as full size until I can find a work around?
        */
        JLabel endLbl = new JLabel("");
        add(endLbl);

        //Set frame visible
        setVisible(true);

    }

    //method for adding a player to the roster
    public void addPlayer(String player) {

        roster.add(player);
        dlm.addElement(player);
        rosterList = new JList(dlm);
        rjsp = new JScrollPane(rosterList);
        rjsp.updateUI();
    }

    //method for clearing all players from the roster
    public void clearPlayers() {
        if(!opnRstr)
        {
            return;
        }else {

            roster.clear();
            dlm.clear();
            rosterList = new JList(dlm);
            rjsp = new JScrollPane(rosterList);
            rjsp.updateUI();
            addPlayer("Flavius");
            addPlayer("Weylyn");
        }
    }
}
