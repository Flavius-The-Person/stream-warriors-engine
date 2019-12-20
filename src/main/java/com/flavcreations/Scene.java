package com.flavcreations;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scene extends JFrame
{
	private JFrame swFrame;
	
	private List<JPanel> battlerPanels2;
	private List<JLabel> battlerLabels2;
	
	private List<JPanel> effectPanels2;
	private List<JLabel> effectLabels2;
	
	
	private JPanel[] battlerPanels;
	private JLabel[] battlerLabels;
	
	private JPanel[] effectPanels;
	private JLabel[] effectLabels;
	
	private JPanel pBoss;
	private JLabel lBoss;
	private JPanel pBossH;
	private JLabel lBossH;
	
	private JPanel epBoss;
	private JLabel elBoss;
	
	private JPanel bgPanel;
	private JLabel bgLabel;
	private ImageIcon bgIcon;
	
	private JScrollPane phsp;
	private JTextArea phta;
	private JPanel jspp;
	
	public long turnCycleDelay;
	public long turnCyclePeriod;
	
	
	private Player[] Players;
	
	Scene()
	{
		
		setLayout(null);
		//setupScene();
		
		updateScene();
		
		setSize(1920,1080);
		setVisible(true);
	}
	private void updateScene()
	{
	
	}
	private int ht = 200;
	private int wd = 200;
	
	/*
	1920 - 520 = 1400 / 200 = 7
	x							x==== 35
	1080 - 80 =  1000 / 200 = 5
	
	1920 - 520 = 1400 / 250 = 5.75ish? -- 5.75ish - .75ish = 5
	x 														x========== 20
	1080 / 250 = 4 ----------------------------------------- 4
		
	 */
	
	private int[] pos35x = {
			0, 0, 0, 0, 0, //1-5
			200, 200, 200, 200, 200, //6-10
			400, 400, 400, 400, 400, //11-15
			600, 600, 600, 600, 600, //16-20
			800, 800, 800, 800, 800, //21-25
			1000, 1000, 1000, 1000, 1000, //26-30
			1200, 1200, 1200, 1200, 1200 // 31 -35
	
	};
	
	private int[] pos35y = {
			0, 200, 400, 600, 800, //1-5
			0, 200, 400, 600, 800, //6-10
			0, 200, 400, 600, 800, //11-15
			0, 200, 400, 600, 800, //16-20
			0, 200, 400, 600, 800, //21-25
			0, 200, 400, 600, 800, //26-30
			0, 200, 400, 600, 800  //31-35
	
	};
	
	private void setupScene()
	{
		System.out.println(Players[0].name);
		for(int ss = 0; ss < battlerPanels.length; ss++)
		{
			if(ss < 36)
			{
				battlerPanels[ss].setSize(wd,ht);
				battlerLabels[ss].setIcon(Players[ss].idleIcons[0]);
				System.out.println(Players[ss].idleIcons[0]);
				battlerLabels[ss].setSize(wd,ht);
				battlerPanels[ss].add(battlerLabels[ss]);
				add(battlerPanels[ss]);
				battlerPanels[ss].setLocation(pos35x[ss],pos35y[ss]);
				battlerPanels[ss].setOpaque(false);
			}
				/*
				battlerPanels[2].setLocation(0,ht*2);
				 */
		/*	while(ss < 36)
			{
			
			}
			*/
		}
		/*
		battlerPanels = new JPanel[5]; battlerLabels = new JLabel[5];
		battlerPanels[0] = new JPanel(); battlerLabels[0] = new JLabel();
		battlerPanels[1] = new JPanel(); battlerLabels[1] = new JLabel();
		battlerPanels[2] = new JPanel(); battlerLabels[2] = new JLabel();
		battlerPanels[3] = new JPanel(); battlerLabels[3] = new JLabel();
		battlerPanels[4] = new JPanel(); battlerLabels[4] = new JLabel();
		
		ImageIcon icontest = new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\200\\PhantomKnightIdle1.png");
		
		battlerLabels[0].setIcon(players[0].idleIcons[0]);
		battlerLabels[0].setSize(wd,ht);
		battlerLabels[0].setOpaque(false);
		
		battlerPanels[0].add(battlerLabels[0]);
		battlerPanels[0].setSize(wd,ht);
		battlerPanels[0].setLocation(0,0);
		battlerPanels[0].setOpaque(false);
		
		add(battlerPanels[0]);
		
		battlerLabels[1].setIcon(icontest);
		battlerLabels[1].setSize(wd,ht);
		battlerLabels[1].setOpaque(false);
		
		battlerPanels[1].add(battlerLabels[1]);
		battlerPanels[1].setSize(wd,ht);
		battlerPanels[1].setLocation(0,ht);
		battlerPanels[1].setOpaque(false);
		
		
		add(battlerPanels[1]);
		
		battlerLabels[2].setIcon(icontest);
		battlerLabels[2].setSize(wd,ht);
		battlerLabels[2].setOpaque(false);
		
		battlerPanels[2].add(battlerLabels[2]);
		battlerPanels[2].setSize(wd,ht);
		battlerPanels[2].setLocation(0,ht*2);
		battlerPanels[2].setOpaque(false);
		
		add(battlerPanels[2]);
		
		battlerLabels[3].setIcon(icontest);
		battlerLabels[3].setSize(wd,ht);
		battlerLabels[3].setOpaque(false);
		
		battlerPanels[3].add(battlerLabels[3]);
		battlerPanels[3].setSize(wd,ht);
		battlerPanels[3].setLocation(0,ht*3);
		battlerPanels[3].setOpaque(false);
		add(battlerPanels[3]);
		
		
		battlerLabels[4].setIcon(icontest);
		battlerLabels[4].setSize(wd,ht);
		battlerLabels[4].setOpaque(false);
		
		battlerPanels[4].add(battlerLabels[4]);
		battlerPanels[4].setSize(wd,ht);
		battlerPanels[4].setLocation(0,ht*4);
		battlerPanels[4].setOpaque(false);
		add(battlerPanels[4]);
		
		
		ImageIcon icontest3 = new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\200\\PhantomKnightIdle3rev.png");
		battlerLabels[0].setIcon(players[0].idleIcons[2]);
		/*
		dlm = new DefaultListModel();
		for(int pn = 0; pn < players.length; pn++)
		{
			dlm.addElement(players[pn].name + "-" + players[pn].health);
		}
		
		playerData = new JList(dlm);
		phsp = new JScrollPane(playerData);
		jspp = new JPanel();
		jspp.add(phsp);
		jspp.setSize(200,130);
		jspp.setBackground(Color.BLACK);
		add(jspp);
		jspp.setLocation(0,950);
		*/
		
		pBoss = new JPanel();
		lBoss = new JLabel();
		ImageIcon bossIcon = new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\500\\celestialguard-horus-idle1.png");
		lBoss.setIcon(bossIcon);
		lBoss.setOpaque(false);
		pBoss.add(lBoss);
		pBoss.setOpaque(false);
		lBoss.setSize(500,500);
		pBoss.setSize(500,500);
		pBoss.setLocation(1400,250);
		add(pBoss);
		
		
		pBossH = new JPanel();
		lBossH = new JLabel("Boss Health: ");
		lBossH.setBackground(Color.BLACK);
		lBossH.setForeground(Color.GREEN);
		pBossH.setLocation(1400,0);
		pBossH.setBackground(Color.BLACK);
		pBossH.setSize(500,50);
		pBossH.add(lBossH);
		add(pBossH);
		
		
		
		
		
		
		
		
		
		
		
		bgPanel = new JPanel();
		bgLabel = new JLabel();
		bgIcon = new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\overlay_map_01v1.png");
		bgLabel.setIcon(bgIcon);
		bgPanel.add(bgLabel);
		bgPanel.setLocation(0,-10);
		bgPanel.setSize(1920,1080);
		add(bgPanel);
		
		
		
		
		
		
		
		/*JPanel finPan = new JPanel();
		add(finPan);*/
		
	}
	//add players from roster to the scene
	public void addPlayers(ArrayList<String> roster)
	{
		//set players object array size to roster size.
		Players = new Player[roster.size()];
		
		//import random function
		Random randy = new Random();
		
		//use random function to generate a random number between 2 for use when a person doesn't have a set character
		int pgen = randy.nextInt(2);
		
		//for loop through the players object array adding data to each player
		for(int pint = 0; pint < Players.length; pint++)
		{
			Players[pint] = new Player();
			Players[pint].setData(roster.get(pint), pgen);
		}
		
		//initiate the load game function
		loadGame();
	}
	
	//load game function to create panels and labels for each character.
	private void loadGame()
	{
		//initiate pannels and labels arrays for battler panels/labels and effect panels/labels
		battlerPanels = new JPanel[Players.length];
		battlerLabels = new JLabel[Players.length];
		effectPanels = new JPanel[Players.length];
		effectLabels = new JLabel[Players.length];
		battlerPanels2.add(new JPanel());
		
		
		//console print stating that the panels and labels were created for testing purposes (will be taken out later)
		System.out.println("end of setting player count, and panel/label arrays");
		
		
		for(int rri = 0; rri < Players.length; rri++)
		{
			//create a new panel and label for player characters
			battlerPanels[rri] = new JPanel();
			battlerPanels[rri].setLayout(null);
			battlerLabels[rri] = new JLabel();
			battlerLabels[rri].setText(Players[rri].name);
			System.out.println("player " + rri + " should be " + Players[rri].name);
			
			//create a new pannel and label for battle effects
			effectPanels[rri] = new JPanel();
			effectPanels[rri].setLayout(null);
			effectLabels[rri] = new JLabel("Eff Pan");
			effectLabels[rri].setText("Eff Pan " + rri);
			
			battlerPanels[rri].add(battlerLabels[rri]);
			System.out.println(Players[rri].name);
		}
		
		//console print stating that player data has been loaded into panels/labels
		System.out.println("end of loading player panels/labels");
		setupScene();
		
	}
	
}
