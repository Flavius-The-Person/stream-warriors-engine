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

import static java.util.concurrent.TimeUnit.MILLISECONDS;

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
	
	private TimerTask gameTask;
	private Timer gameCycleTimer;
	
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private Runnable gameCycleEvent;
	
	
	boolean isOpenRoster = false;
	boolean isFightStarted = false;
	boolean isFightPaused = false;
	
	private Player[] Players;
	private Enemy Boss;
	
	
	Scene()
	{
		
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
				//setupScene();
		
		//updateScene();
		
		setSize(1920,1080);
		setVisible(true);
		
	}
	
	//start game function
	public void startGame()
	{
		gameCycleEvent = new Runnable() {
			@Override
			public void run() {
				gameTask();
				
			}
		};
		isFightPaused = false;
		isFightStarted = true;
		
		// 1000L should be 1 second from Milliseconds?
		long lSecond = 1000L;
		
		turnCycleDelay = lSecond / 4;
		turnCyclePeriod = lSecond / 4;
		
		// schedule task gameCycleEvent at turndycledelay, turncycleperiod, for milliseconds.
		scheduler.scheduleAtFixedRate(gameCycleEvent, turnCycleDelay, turnCyclePeriod, MILLISECONDS);
		
	}
	
	//game task on the timer
	public void gameTask()
	{
		/*
		* While the game is started cycle through idle animations
		* with a for loop, then cycle boss through idle animations
		*
		*
		* Coming soon - check if attacking,
		*
		* cycle through attack animations if attacking
		* instead of idle animations
		*
		* when finished with attack animations resume with idle animations
		 */
		if(isFightStarted)// && !isFightPaused)
		{
			System.out.println("entered gameTask()");
			for(int x = 0; x < Players.length; x++)
			{
				//check if player is doing regular idle or not? for now it would be attacking or idle
				if(Players[x].attFrame < 3)
				{
					battlerLabels.get(x).setIcon(Players[x].attackIcons[Players[x].attFrame]);
					Players[x].attFrame++;
				}
				if(Players[x].attFrame > 2) {
					if (Players[x].idleUp) {
						Players[x].idleFrame += 1;
						
					}
					if (!Players[x].idleUp) {
						Players[x].idleFrame -= 1;
					}
					battlerLabels.get(x).setIcon(Players[x].idleIcons[Players[x].idleFrame]);
					if (Players[x].idleFrame == 2) {
						Players[x].idleUp = false;
					}
					if (Players[x].idleFrame == 0) {
						Players[x].idleUp = true;
					}
				}
			}
			System.out.println("Exited for loop");
			
			if(Boss.attFrame < 3)
			{
				bossLabel.setIcon(Boss.attackIcons[Boss.attFrame]);
				Boss.attFrame++;
			}
			if(Boss.attFrame > 2) {
				
				if (Boss.idleUp) {
					Boss.idleFrame += 1;
				}
				if (!Boss.idleUp) {
					Boss.idleFrame -= 1;
				}
				bossLabel.setIcon(Boss.idleIcons[Boss.idleFrame]);
				if (Boss.idleFrame == 2) {
					Boss.idleUp = false;
				}
				if (Boss.idleFrame == 0) {
					Boss.idleUp = true;
				}
			}
			
			System.out.println("end of one animating cycle");
		}
	}
	
	
	public void updateScene()
	{
	
	}
	
	
	//pause the game
	public void pauseGame()
	{
		isFightPaused = true;
		
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
		
		//System.out.println(Players[0].name);
		
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
				effectPanels.get(sceneSetupInt).add(effectLabels.get(sceneSetupInt));
				
				add(effectPanels.get(sceneSetupInt));
				effectPanels.get(sceneSetupInt).setLocation(pos35x[sceneSetupInt],pos35y[sceneSetupInt]);
				effectPanels.get(sceneSetupInt).setOpaque(false);
				
			}
		}
		
		//add boss
		bossPanel = new JPanel();
		bossLabel = new JLabel();
		ImageIcon bossIcon = new ImageIcon( //idle 1
				"D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\" +
						"src\\main\\java\\com\\flavcreations\\testfiles\\500\\" +
						"celestialguard-horus-idle1.png");
		//ImageIcon bossIcon = new ImageIcon(
				//"D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\500\\celestialguard-horus-idle1.png");
		bossLabel.setIcon(Boss.idleIcons[0]);
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
		
		startGame();
		
		
	}
	
	//pull integer from Menu based on combo box selection
	public void setBoss(int bossInt)
	{
		//create new boss enemy
		Boss = new Enemy();
		
		//if bossint = 0, set boss to celestial guard - horus
		if(bossInt == 0)
		{
			Boss.setData("celestialguard-horus");
		}
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
