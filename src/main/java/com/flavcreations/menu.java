package com.flavcreations;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class menu extends JFrame
{
    //public player[] players;
    scene sc;
    enemy en;

    private JPanel menuPan;
    private JLabel pauseLbl, rosterOpnLbl, rosterLbl, menuImgLbl;
    private ImageIcon menuImgIcn;
    private JButton opnRstrBtn;
    private String bgImg = "";

    private DefaultListModel dlm, dlm2, dlm3;
    boolean opnRstr = false;
    boolean fightStarted = false;
    boolean fightPaused = false;
    ArrayList<String> roster = new ArrayList<String>();
    JScrollPane rjsp, pjsp, vjsp;
    ArrayList<String> patrons = new ArrayList<String>();
    ArrayList<String> voiceArtists = new ArrayList<String>();

    /*
    */
    private JButton startButton;
    private JList rosterList;
    private JButton websiteButton, patreonButton, githubButton, creditsButton;
    private JList patronList, vAList;
    private JLabel ttlLbl, ttlLbl2, strtLbl, rstrOpnLbl, rstrLbl, ptrnLbl, vALbl;


    /*
     */




    public menu()
    {
        super("Stream Warriors Menu");

        sc = new scene();
        en = new enemy();
        setSize(615,840);
        menuImgIcn = new ImageIcon(bgImg);
        menuImgLbl = new JLabel();
        menuImgLbl.setIcon(menuImgIcn);
        menuImgLbl.setSize(600,900);

        getContentPane().setBackground(Color.BLACK);

        ttlLbl = new JLabel("Game Name");
        ttlLbl.setForeground(Color.RED);

        strtLbl = new JLabel("Press to Start/Pause/Unpause fight:");
        strtLbl.setForeground(Color.RED);

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

        rstrOpnLbl = new JLabel("Press to Open/Close Roster");
        rstrOpnLbl.setForeground(Color.RED);
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

                    System.out.println(roster);
                    sc.addPlayers(roster);
                    sc.loadGame();

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

        roster.add("Flavius");
        rstrLbl = new JLabel("Roster:");
        rstrLbl.setForeground(Color.RED);
        dlm = new DefaultListModel();
        for (String rAdd : roster) {
            dlm.addElement(rAdd);
        }
        rosterList = new JList(dlm);//JList<Object>(dlm);
        rjsp = new JScrollPane(rosterList);

        ptrnLbl = new JLabel("PATRONS:");
        ptrnLbl.setForeground(Color.RED);
        patrons.add("FlavCreations");
        dlm2 = new DefaultListModel();
        for (String pAdd : patrons) {
            dlm2.addElement(pAdd);
        }
        patronList = new JList(dlm2);
        pjsp = new JScrollPane(patronList);

        vALbl = new JLabel("Voice Artists:");
        vALbl.setForeground(Color.RED);
        dlm3 = new DefaultListModel();
        for(String vAdd : voiceArtists)
        {
            dlm3.addElement(vAdd);
        }
        vAList = new JList(dlm3);
        vjsp = new JScrollPane(vAList);


        websiteButton = new JButton("Website");
        websiteButton.setBackground(Color.DARK_GRAY);
        websiteButton.setForeground(Color.RED);
        websiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWebPage("https://www.flavcreations.com");
            }
        });
        githubButton = new JButton("Github Sponsors");
        githubButton.setBackground(Color.DARK_GRAY);
        githubButton.setForeground(Color.RED);
        githubButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWebPage("https://github.com/sponsors/Flavius-The-Person");
            }
        });
        creditsButton = new JButton("Credits");
        creditsButton.setBackground(Color.DARK_GRAY);
        creditsButton.setForeground(Color.RED);
        creditsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWebPage("https://www.flavcreations.com");
            }
        });
        patreonButton = new JButton("Patreon");
        patreonButton.setBackground(Color.DARK_GRAY);
        patreonButton.setForeground(Color.RED);
        patreonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWebPage("https://www.patreon.com/FlavCreations");
            }
        });


        add(ttlLbl);
        ttlLbl.setSize(600,200);
        ttlLbl.setLocation(0,0);

        /*
        add(strtLbl);
        strtLbl.setSize(300,100);
        strtLbl.setLocation(0,200);
        */
        add(startButton);
        startButton.setSize(300,100);
        startButton.setLocation(150,600);

        /*
        add(rstrOpnLbl);
        rstrOpnLbl.setSize(300,100);
        rstrOpnLbl.setLocation(0,300);
        */
        add(opnRstrBtn);
        opnRstrBtn.setSize(300,100);
        opnRstrBtn.setLocation(150,300);
        /*
        add(rstrLbl);
        rstrLbl.setSize(300, 100);
        rstrLbl.setLocation(0,400);
        */
        add(rjsp);
        rjsp.setSize(300,200);
        rjsp.setLocation(150,400);
        /*
        add(vALbl);
        vALbl.setSize(300,100);
        vALbl.setLocation(0,500);
        add(vjsp);
        vjsp.setSize(300,100);
        vjsp.setLocation(300,500);
        add(ptrnLbl);
        ptrnLbl.setSize(300,100);
        ptrnLbl.setLocation(0,600);
        add(pjsp);
        pjsp.setSize(300,100);
        pjsp.setLocation(300,600);
        */

        add(websiteButton);
        websiteButton.setSize(300,50);
        websiteButton.setLocation(150,700);

        add(githubButton);
        githubButton.setSize(200,50);
        githubButton.setLocation(0,750);
        add(creditsButton);
        creditsButton.setSize(200,50);
        creditsButton.setLocation(200, 750);
        add(patreonButton);
        patreonButton.setSize(200,50);
        patreonButton.setLocation(400, 750);

        JLabel endLbl = new JLabel("");
        add(endLbl);

        setVisible(true);

    }

    public void openWebPage(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPlayer(String player) {
        roster.add(player);
        System.out.println(roster.toString());
        System.out.println(roster.size());
        dlm.addElement(player);
        rosterList = new JList(dlm);
        rjsp = new JScrollPane(rosterList);
        rjsp.updateUI();
    }

    public void clearPlayers() {
        if(!opnRstr)
        {
            return;
        }else

            roster.clear();
            dlm.clear();
            rosterList = new JList(dlm);
            rjsp = new JScrollPane(rosterList);
            rjsp.updateUI();
            addPlayer("Flavius");
            addPlayer("Weylyn");
    }
}
