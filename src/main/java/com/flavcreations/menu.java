package com.flavcreations;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class menu extends JFrame
{
    //class variables and object imports (such as scene and enemy)
    scene sc;
    enemy en;

    private JLabel menuImgLbl;
    private ImageIcon menuImgIcn;
    private JButton opnRstrBtn;
    private String bgImg = "";

    private DefaultListModel dlm, dlm2, dlm3;
    boolean opnRstr = false;
    boolean fightStarted = false;
    boolean fightPaused = false;
    public ArrayList<String> roster = new ArrayList<String>();
    private JScrollPane rjsp, pjsp, vjsp;
    private ArrayList<String> patrons = new ArrayList<String>();
    private ArrayList<String> voiceArtists = new ArrayList<String>();

    private JButton startButton;
    private JList rosterList;
    private JButton websiteButton, patreonButton, githubButton, creditsButton;
    private JList patronList, vAList;
    private JLabel titleLbl, strtLbl, rstrOpnLbl, rstrLbl, ptrnLbl, vALbl;





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

        titleLbl = new JLabel("Game Name");
        titleLbl.setForeground(Color.RED);

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
                    //sc.loadGame();

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
        dlm = new DefaultListModel();
        for (String rAdd : roster) {
            dlm.addElement(rAdd);
        }
        rosterList = new JList(dlm);
        rjsp = new JScrollPane(rosterList);

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


        add(titleLbl);
        titleLbl.setSize(600,200);
        titleLbl.setLocation(0,0);
        add(startButton);
        startButton.setSize(300,100);
        startButton.setLocation(150,600);
        add(opnRstrBtn);
        opnRstrBtn.setSize(300,100);
        opnRstrBtn.setLocation(150,300);
        add(rjsp);
        rjsp.setSize(300,200);
        rjsp.setLocation(150,400);
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

    //open a website link when function is called with a url string
    public void openWebPage(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method for adding a player via string input.
    public void addPlayer(String player) {
        roster.add(player);
        dlm.addElement(player);
        rosterList = new JList(dlm);
        rjsp = new JScrollPane(rosterList);
        rjsp.updateUI();
    }

    //method for clearing the roster/scrollpane
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
