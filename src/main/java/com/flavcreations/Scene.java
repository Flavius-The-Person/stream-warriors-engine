package com.flavcreations;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scene extends JFrame
{
	private JFrame swFrame;
	
	private List<JPanel> battlerPanels = new ArrayList<JPanel>();
	private List<JLabel> battlerLabels = new ArrayList<JLabel>();
	
	private List<JPanel> effectPanels = new ArrayList<JPanel>();
	private List<JLabel> effectLabels = new ArrayList<JLabel>();
	
	
	
	private JPanel bossPanel;
	private JLabel bossLabel;
	private JPanel bossHealthPanel;
	private JLabel bossHealthLabel;
	
	private JPanel bossEffectPanel;
	private JLabel bossEffectLabel;
	
	private JPanel backgroundPanel;
	private JLabel backgroundLabel;
	private ImageIcon backgroundIcon;
	
	private JScrollPane phsp;
	private JTextArea phta;
	private JPanel jspp;
	
	public long turnCycleDelay;
	public long turnCyclePeriod;
	
	
	private Player[] Players;
	
	Scene()
	{
		
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
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
		for(int sceneSetupInt = 0; sceneSetupInt < Players.length; sceneSetupInt++) //battlerPanels.length; ss++)
		{
			if(sceneSetupInt < 36)
			{
				battlerPanels.get(sceneSetupInt).setSize(wd,ht);
				battlerLabels.get(sceneSetupInt).setIcon(Players[sceneSetupInt].idleIcons[0]);
				battlerLabels.get(sceneSetupInt).setSize(wd,ht);
				battlerPanels.get(sceneSetupInt).add(battlerLabels.get(sceneSetupInt));
				add(battlerPanels.get(sceneSetupInt));
				battlerPanels.get(sceneSetupInt).setLocation(pos35x[sceneSetupInt],pos35y[sceneSetupInt]);
				battlerPanels.get(sceneSetupInt).setOpaque(false);
				
				effectPanels.get(sceneSetupInt).setSize(wd,ht);
				effectLabels.get(sceneSetupInt).setSize(wd,ht);
				add(effectPanels.get(sceneSetupInt));
				effectPanels.get(sceneSetupInt).setLocation(pos35x[sceneSetupInt],pos35y[sceneSetupInt]);
				effectPanels.get(sceneSetupInt).setOpaque(false);
				
			}
		/*	while(ss < 36)
			{
			
			}
			*/
		}
		
		//add boss
		bossPanel = new JPanel();
		bossLabel = new JLabel();
		ImageIcon bossIcon = new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\500\\celestialguard-horus-idle1.png");
		bossLabel.setIcon(bossIcon);
		bossLabel.setOpaque(false);
		bossPanel.add(bossLabel);
		bossPanel.setOpaque(false);
		bossLabel.setSize(500,500);
		bossPanel.setSize(500,500);
		bossPanel.setLocation(1400,250);
		add(bossPanel);
		
		// Add Boss health Panel and label
		bossHealthPanel = new JPanel();
		bossHealthLabel = new JLabel("Boss Health: ");
		bossHealthLabel.setBackground(Color.BLACK);
		bossHealthLabel.setForeground(Color.GREEN);
		bossHealthPanel.setLocation(1400,0);
		bossHealthPanel.setBackground(Color.BLACK);
		bossHealthPanel.setSize(500,50);
		bossHealthPanel.add(bossHealthLabel);
		add(bossHealthPanel);
		
		
		// Add background Panel
		backgroundPanel = new JPanel();
		backgroundLabel = new JLabel();
		backgroundIcon = new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\overlay_map_01v1.png");
		backgroundLabel.setIcon(backgroundIcon);
		backgroundPanel.add(backgroundLabel);
		backgroundPanel.setLocation(0,-10);
		backgroundPanel.setSize(1920,1080);
		add(backgroundPanel);
		
		
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
		
		//for loop through the players object list adding data to each player
		for(int playerLoopInt = 0; playerLoopInt < Players.length; playerLoopInt++)
		{
			Players[playerLoopInt] = new Player();
			Players[playerLoopInt].setData(roster.get(playerLoopInt), pgen);
		}
		
		//initiate the load game function
		loadGame();
	}
	
	//load game function to create panels and labels for each character.
	private void loadGame()
	{
		//initiate panels and labels arrays for battler panels/labels and effect panels/labels
		/*battlerPanels = new JPanel[Players.length];
		battlerLabels = new JLabel[Players.length];
		effectPanels = new JPanel[Players.length];
		effectLabels = new JLabel[Players.length];
		battlerPanels2.add(new JPanel());
		*/
		
		//console print stating that the panels and labels were created for testing purposes (will be taken out later)
		// System.out.println("end of setting player count, and panel/label arrays");
		battlerPanels.clear();
		battlerLabels.clear();
		
		//loop through players and add panels and labels for each player
		for(int addPlayerInt = 0; addPlayerInt < Players.length; addPlayerInt++)
		{
			//create a new panel and label for player characters
			
			battlerPanels.add(new JPanel());
			battlerLabels.add(new JLabel());
			
			effectPanels.add(new JPanel());
			effectLabels.add(new JLabel());
			
		}
		
		//console print stating that player data has been loaded into panels/labels
		System.out.println("end of loading player panels/labels");
		
		//initiate scene setup
		setupScene();
		
	}
	
}
