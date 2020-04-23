package com.flavcreations;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class Menu extends JFrame
{
    //class variables and object imports (such as scene and enemy)
    Scene scene ;
    

    private JLabel menuImageLabel;
    private ImageIcon menuImageIcon;
    private JButton openRosterButton;
    private String backgroundImage = "";
    private JLabel backgroundLabel;
    private JPanel backgroundPanel;

    private DefaultListModel dlm;
    
    /*
    boolean isOpenRoster = false;
    boolean isFightStarted = false;
    boolean isFightPaused = false;
    */
    
    public ArrayList<String> rosterArrayList = new ArrayList<String>();
    private JScrollPane rosterScrollPane;
    
    private JButton startButton, restartButton;
    private JList rosterList;
    private JButton websiteButton, patreonButton, githubButton, creditsButton;
    
    
    
    private JLabel titleLabel;
    private JComboBox bossComboBox;
    
    private String[] bossChoices = {"boss1", "not available", "not available"};
    private int bossChoice;
    private ImageIcon[] bossChoiceImages = {
            new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\500\\celestialguard-horus-idle1.png"),
            new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\500\\celestialguard-horus-idle1.png")
    };
    
    
    
    /*** Variables no longer using? Or might not be using?
     *
     * ArrayList<String> patrons = new ArrayList<String>();
     * ArrayList<String> voiceArtists = new ArrayList<String>();
     * JScrollPane pjsp, vjsp
     * JList patronList, vAList;
     * JLabel strtLbl, rstrOpnLbl, rstrLbl, ptrnLbl, vALbl;
     * DefaultListModel dlm2, dlm3
     *
     */
    
    
    
    
    
    public Menu()
    {
        super("Stream Warriors Menu");
        
        scene = new Scene();
        
        setSize(600,930);
        menuImageIcon = new ImageIcon(backgroundImage);
        menuImageLabel = new JLabel();
        menuImageLabel.setIcon(menuImageIcon);
        menuImageLabel.setSize(600,930);

        getContentPane().setBackground(Color.BLACK);

        titleLabel = new JLabel("Game Name");
        titleLabel.setForeground(Color.RED);

        bossComboBox = new JComboBox(bossChoiceImages);
        bossComboBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                bossChoice = actionEvent.getID();
                System.out.println("bossChoice-" + bossChoice);
                
            }
        });
        
        startButton = new JButton("Start");
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.RED);
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!scene.isOpenRoster)
                {
                    if(scene.isFightStarted)
                    {
                        if(scene.isFightPaused)
                        {
                            scene.isFightPaused = false;
                            startButton.setText("Pause");
                            
                        }
                        else if(!scene.isFightPaused)
                        {
                            scene.isFightPaused = true;
                            startButton.setText("Start/Resume");
                        }
                    }else if(!scene.isFightStarted)
                    {
                        scene.isFightStarted = true;
                        scene.isFightPaused = false;
                        startButton.setText("Pause");
                    }
                }
            }
        });
        
        restartButton = new JButton("Restart");
        restartButton.setBackground(Color.DARK_GRAY);
        restartButton.setForeground(Color.RED);
        restartButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(scene.isFightStarted)
                {
                    scene.isFightStarted = false;
                    scene.isOpenRoster = true;
                    clearPlayers();
                }
            }
        });
        
        openRosterButton = new JButton("Open Roster");
        openRosterButton.setBackground(Color.DARK_GRAY);
        openRosterButton.setForeground(Color.RED);
        openRosterButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(scene.isOpenRoster)
                {
                    scene.isOpenRoster = false;
                    openRosterButton.setText("Open Roster");

                    System.out.println(rosterArrayList);
                    System.out.println("boss combo box index=" + bossComboBox.getSelectedIndex());
                    scene.setBoss(bossComboBox.getSelectedIndex());
                    scene.addPlayers(rosterArrayList);
                    

                } else if(!scene.isOpenRoster)
                {
                    if(!scene.isFightStarted)
                    {
                        scene.isOpenRoster = true;
                        openRosterButton.setText("Close Roster");
                    }
                }
            }
        });

        rosterArrayList.add("Flavius");
        dlm = new DefaultListModel();
        for (String rAdd : rosterArrayList)
        {
            dlm.addElement(rAdd);
        }
        rosterList = new JList(dlm);
        rosterScrollPane = new JScrollPane(rosterList);

        websiteButton = new JButton("Website");
        websiteButton.setBackground(Color.DARK_GRAY);
        websiteButton.setForeground(Color.RED);
        websiteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                openWebPage("https://www.flavcreations.com");
            }
        });

        githubButton = new JButton("Github Sponsors");
        githubButton.setBackground(Color.DARK_GRAY);
        githubButton.setForeground(Color.RED);
        githubButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                openWebPage("https://github.com/sponsors/Flavius-The-Person");
            }
        });

        creditsButton = new JButton("Credits");
        creditsButton.setBackground(Color.DARK_GRAY);
        creditsButton.setForeground(Color.RED);
        creditsButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                openWebPage("https://www.flavcreations.com");
            }
        });

        patreonButton = new JButton("Patreon");
        patreonButton.setBackground(Color.DARK_GRAY);
        patreonButton.setForeground(Color.RED);
        patreonButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                openWebPage("https://www.patreon.com/FlavCreations");
            }
        });


        add(titleLabel);
        titleLabel.setSize(600,200);
        titleLabel.setLocation(0,0);
        
        add(bossComboBox);
        bossComboBox.setSize(200,200);
        bossComboBox.setLocation(200,300);
        
        add(openRosterButton);
        openRosterButton.setSize(200,60);
        openRosterButton.setLocation(200,500);
        add(rosterScrollPane);
        rosterScrollPane.setSize(200,100);
        rosterScrollPane.setLocation(200,560);
    
    
        add(startButton);
        startButton.setSize(200,60);
        startButton.setLocation(200,660);
        add(restartButton);
        restartButton.setSize(200,60);
        restartButton.setLocation(200,710);
        
        add(websiteButton);
        websiteButton.setSize(200,50);
        websiteButton.setLocation(200,760);
        
        add(githubButton);
        githubButton.setSize(200,50);
        githubButton.setLocation(0,810);
        add(creditsButton);
        creditsButton.setSize(200,50);
        creditsButton.setLocation(200, 810);
        add(patreonButton);
        patreonButton.setSize(200,50);
        patreonButton.setLocation(400, 810);

        JLabel endLbl = new JLabel("");
        add(endLbl);

        setVisible(true);

    }

    //open a website link when function is called with a url string
    public void openWebPage(String url)
    {
        try
        {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //method for adding a player via string input.
    public void addPlayer(String player)
    {
        rosterArrayList.add(player);
        dlm.addElement(player);
        rosterList = new JList(dlm);
        rosterScrollPane = new JScrollPane(rosterList);
        rosterScrollPane.updateUI();
    }

    //method for clearing the roster/scrollpane
    public void clearPlayers()
    {
        if(!scene.isOpenRoster)
        {
            return;
        } else
            rosterArrayList.clear();
            dlm.clear();
            rosterList = new JList(dlm);
            rosterScrollPane = new JScrollPane(rosterList);
            rosterScrollPane.updateUI();
            addPlayer("Flavius");
            addPlayer("Weylyn");
    }
}
