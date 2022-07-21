package com.flavcreations;

import com.flavcreations.characters.Player;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class Menu extends JFrame
{

    Scene llg;
    private JLabel menuImageLabel;
    private ImageIcon menuImageIcon;
    private JButton openRosterButton;
    private String backgroundImage = "";
    private JLabel backgroundLabel;
    private JPanel backgroundPanel;
    
    private DefaultListModel<String> dlm;
    
    public ArrayList<String> rosterArrayList = new ArrayList<>();
    public ArrayList<String> rosterNameArrayList = new ArrayList<>();
    public ArrayList<Integer> rosterCharArrayList = new ArrayList<>();
    
    private JScrollPane rosterScrollPane;
    
    private JButton startButton, restartButton;
    private JList<String> rosterList;
    private JButton websiteButton, patreonButton, githubButton, creditsButton;
    
    private JLabel titleLabel;
    private JComboBox<String> bossComboBox;
    
    private String[] bossChoices = {"Fungoliath", "Fungoliath"};
    private int bossChoice;
    private ImageIcon[] bossChoiceImages = {
            new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\500\\Fungoliath\\Fungoliath-Idle-01.png"),
            new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\500\\Fungoliath\\Fungoliath-Idle-01.png")
    };

    public Menu()
    {
        super("Stream Warriors Menu");
        
        llg = new Scene();
        
        setSize(600,930);
        menuImageIcon = new ImageIcon(backgroundImage);
        menuImageLabel = new JLabel();
        menuImageLabel.setIcon(menuImageIcon);
        menuImageLabel.setSize(600,930);
        
        getContentPane().setBackground(Color.BLACK);
        
        titleLabel = new JLabel("Game Name");
        titleLabel.setForeground(Color.RED);
        
        bossComboBox = new JComboBox<>(bossChoices);
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
                if(!llg.isOpenRoster)
                {
                    if(llg.isFightStarted)
                    {
                        if(llg.isFightPaused)
                        {
                            llg.isFightPaused = false;
                            startButton.setText("Pause");
                            
                        }
                        else if(!llg.isFightPaused)
                        {
                            llg.isFightPaused = true;
                            startButton.setText("Start/Resume");
                        }
                    }else if(!llg.isFightStarted)
                    {
                        llg.isFightStarted = true;
                        llg.isFightPaused = false;
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
                System.out.println("restart selected");
                llg.isFightStarted = false;
                llg.isOpenRoster = false;
                clearRoster();
                llg.clearPlayers();
                startButton.setText("Start");
                openRosterButton.setText("Open Roster");
            }
        });
        
        openRosterButton = new JButton("Open Roster");
        openRosterButton.setBackground(Color.DARK_GRAY);
        openRosterButton.setForeground(Color.RED);
        openRosterButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(llg.isOpenRoster)
                {
                    if(rosterNameArrayList.isEmpty())
                    {
                        System.out.println("Roster is empty!!!");
                    }
                    else
                        if(llg.battlerPanels.isEmpty()){


                        llg.isOpenRoster = false;
                        openRosterButton.setText("Open Roster");

                        System.out.println("boss combo box index=" + bossComboBox.getSelectedIndex());
                        llg.setBoss(bossComboBox.getSelectedIndex());
                        llg.addPlayers(rosterArrayList, rosterCharArrayList, rosterNameArrayList);
                    }
                    else if(!llg.battlerPanels.isEmpty())
                    {
                        llg.isOpenRoster = false;
                        openRosterButton.setText("Open Roster");
                    }

                } 
		else if(!llg.isOpenRoster)
                {
                    if(!llg.isFightStarted)
                    {

                        llg.isOpenRoster = true;
                        openRosterButton.setText("Close Roster");

                        if(rosterArrayList.size()<1) 
			{
                            for (int playerCount = 0; playerCount < 104; playerCount++) 
			    {
                                Random randy = new Random();
                                int pchoice = randy.nextInt(6);
                                String playerId = "playerID:" + playerCount + 1;
                                String playerName = "playerName:" + playerCount + 1;
                                addPlayer(playerId, pchoice, playerName);
                            }
			    
                            int pids = rosterArrayList.size();
                            int pcs = rosterCharArrayList.size();
                            int pns = rosterNameArrayList.size();
                            System.out.println("rosterArrayList size = " + pids + " || rosterCharArrayList size = " + pcs + " || rosterNameArrayList size = " + pns);
                        }
                    }
                }
            }
        });
        
        dlm = new DefaultListModel<>();
        
        websiteButton = new JButton("Website");
        websiteButton.setBackground(Color.DARK_GRAY);
        websiteButton.setForeground(Color.RED);
        websiteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                openWebPage("https://flavcreations.com/");
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
                openWebPage("https://flavcreations.com/");
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
        bossComboBox.setSize(200,50);
        bossComboBox.setLocation(200,450);
        
        add(openRosterButton);
        openRosterButton.setSize(200,50);
        openRosterButton.setLocation(200,500);
        
        rosterScrollPane = new JScrollPane();
        add(rosterScrollPane);
        rosterScrollPane.setSize(200,100);
        rosterScrollPane.setLocation(200,550);
        
        
        add(startButton);
        startButton.setSize(200,50);
        startButton.setLocation(200,650);
        add(restartButton);
        restartButton.setSize(200,50);
        restartButton.setLocation(200,700);
        
        add(websiteButton);
        websiteButton.setSize(200,50);
        websiteButton.setLocation(200,750);
        
        add(githubButton);
        githubButton.setSize(200,50);
        githubButton.setLocation(0,800);
        add(creditsButton);
        creditsButton.setSize(200,50);
        creditsButton.setLocation(200, 800);
        add(patreonButton);
        patreonButton.setSize(200,50);
        patreonButton.setLocation(400, 800);
        
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
    public void addPlayer(String player, int iChoice, String playerName)
    {
        rosterArrayList.add(player);
        rosterCharArrayList.add(iChoice);
        rosterNameArrayList.add(playerName);

        dlm.addElement(playerName);
        System.out.println("dlm added: " + playerName);

        rosterList = new JList<>(dlm);

        rosterScrollPane.add(rosterList);
        rosterScrollPane.updateUI();
        rosterScrollPane.repaint();
        rosterScrollPane.revalidate();
        rosterScrollPane.updateUI();

        revalidate();
        repaint();
    }
    
    //method for clearing the roster/scrollpane
    public void clearRoster()
    {
        if(!llg.isOpenRoster)
        {
            return;
        } else
            rosterArrayList.clear();
        rosterCharArrayList.clear();
        rosterNameArrayList.clear();
        dlm.clear();
        rosterList = null;
        rosterList = new JList<>();
        rosterScrollPane = new JScrollPane(rosterList);
        rosterScrollPane.updateUI();
    }
}
