package com.flavcreations;


import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Scene extends JFrame
{
	
	private JFrame iFrame;
	
	
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
	
	private JPanel menuPanel;
	
	private JPanel statsPanel;
	
	private JPanel backgroundPanel, backgroundPanel2;
	private JLabel backgroundLabel, backgroundLabel2;
	private ImageIcon backgroundIcon;
	
	private JScrollPane phsp;
	private JTextArea phta;
	private JPanel jspp;
	
	public long turnCycleDelay;
	public long turnCyclePeriod;
	
	private TimerTask gameTask;
	private Timer gameCycleTimer;
	
	private List<Integer> turnList = new ArrayList<Integer>();
	private int turn = 1;
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private Runnable gameCycleEvent;
	
	
	boolean isOpenRoster = false;
	boolean isFightStarted = false;
	boolean isFightPaused = true;
	
	private Player[] Players;
	private Enemy Boss;
	
	private int eventTimer = 0;
	
	
	Scene()
	{
		iFrame = new JFrame("Stream Warriors Info");
		iFrame.setSize(1920,180);
		setTitle("Stream Adventures");
		setLayout(null);
		
		
		
		//getContentPane().setBackground(Color.BLACK);
		
		/*
		// Add background Panel
		backgroundPanel = new JPanel();
		backgroundLabel = new JLabel();
		backgroundIcon = new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\overlay_map_01v1.png");
		//backgroundLabel.setIcon(backgroundIcon);
		//backgroundLabel.setBackground(Color.decode("#c900c9"));
		
		backgroundPanel.add(backgroundLabel);
		//backgroundPanel.setBackground(Color.decode("#c900c9"));
		
		backgroundPanel.setLocation(0,-10);
		backgroundPanel.setSize(1920,1080);
		
		repaint();
		getContentPane().repaint();
		
		*/
		//setupScene();
		//updateScene();
		
		setSize(1920,900);
		setVisible(true);
		
		backgroundPanel = new JPanel();
		backgroundLabel = new JLabel();
		backgroundIcon = new ImageIcon("src/main/java/com/flavcreations/testfiles/overlay_map_01v1.png");
		add(backgroundPanel);
		
		getContentPane().repaint();
		super.repaint();
		backgroundPanel.setLocation(0,-10);
		setVisible(true);
		iFrame.setVisible(true);
		
	}
	
	//start game function
	public void startGame()
	{
		gameCycleEvent = new Runnable()
		{
			@Override
			public void run()
			{
				gameTask();
				
			}
		};
		
		//isFightPaused = false;
		//isFightStarted = true;
		
		// 1000L should be 1 second from Milliseconds?
		long lSecond = 1000L;
		
		turnCycleDelay = lSecond;// / 4;
		turnCyclePeriod = lSecond;// / 4;
		
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
		* Coming soon - check if attacking,
		*
		* cycle through attack animations if attacking
		* instead of idle animations
		*
		* when finished with attack animations resume with idle animations
		 */
		
		if(isFightStarted)// && !isFightPaused)
		{
			if (!isFightPaused) {
				
				//System.out.println("pre turnlist");
				if (turn >= turnList.size()) //turnList.isEmpty())
				{
					turnList.clear();
					
					//System.out.println("enter turn list");
					//double temp_turns = Players.length * 1.6;
					//long temp_turns_long = Math.round(temp_turns);
					
					Random rand = new Random();
					
					int maxBossTurns = (int) Math.rint(Players.length * 0.75);
					int bossTurns = rand.nextInt(maxBossTurns + 1);
					
					int temp_turns = Players.length + bossTurns;
					
					
					for (int temp_turn = 0; temp_turn <= temp_turns; temp_turn++) //temp_turns_long; temp_turn++)
					{
						turnList.add(temp_turn);
						//System.out.println("For loop for adding temp turn: " + temp_turn);
					}
					
					
					//System.out.println("exited for loop and shuffling collection");
					
					Collections.shuffle(turnList);
					
					//System.out.println("after shuffled collection, exiting if");
					//System.out.println(turnList);
					
					turn = 0;
				}
				
				/*if(eventTimer>2)
				{
					eventTimer = 0;
					turn++;
				}*/
				
				//System.out.println("entered gameTask()");
				
				//System.out.println("pre for loop turn: " + turnList.get(turn));
				
				for (int x = 0; x < Players.length; x++) {
					if (x == turnList.get(turn)) {
						battlerLabels.get(x).setIcon(Players[x].idleIcons[eventTimer]);
					}
					
					//check if player is doing regular idle or not? for now it would be attacking or idle
					if (Players[x].attFrame > 2) {
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
				
				//System.out.println("Exited for loop");
				
				if (turnList.get(turn) >= Players.length) {
					bossLabel.setIcon(Boss.idleIcons[eventTimer]);
				}
				
				if (turnList.get(turn) < Players.length) {
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
				
				//System.out.println("end of one animating cycle");
				
				//System.out.println("Event Timer before eventTimer++: " + eventTimer);
				
				eventTimer++;
				
				if (eventTimer > 2) {
					//System.out.println("inside if eventTimer > 3");
					//System.out.println("pre-turn++: " + turn);
					
					turn++;
					
					//System.out.println("post-turn++: " + turn);
					//System.out.println("pre-eventTimer=0: " + eventTimer);
					
					eventTimer = 0;
					
					//System.out.println("post-eventTimer=0: " + eventTimer);
					
				}
				
				//System.out.println("turn: " + turn);
				//System.out.println("Event Timer after eventTimer++: " + eventTimer);
				
			}
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
			0, 0, 0, 0, //1-4
			200, 200, 200, 200, //5-8
			400, 400, 400, 400, //9-12
			600, 600, 600, 600, //13-16
			800, 800, 800, 800, //17-20
			1000, 1000, 1000, 1000, //21-24
			1200, 1200, 1200, 1200, //25-28
	
	};
	
	private int[] pos35y = {
			0, 200, 400, 600, //1-4
			0, 200, 400, 600, //5-8
			0, 200, 400, 600, //9-12
			0, 200, 400, 600, //13-16
			0, 200, 400, 600, //17-20
			0, 200, 400, 600, //21-24
			0, 200, 400, 600  //25-28
	
	};
	
	private void setupScene()
	{
		
		for(int sceneSetupInt = 0; sceneSetupInt < Players.length; sceneSetupInt++) //battlerPanels.length; ss++)
		{
			if(sceneSetupInt < 36)
			{
				battlerPanels.get(sceneSetupInt).setSize(wd,ht);
				battlerLabels.get(sceneSetupInt).setIcon(Players[sceneSetupInt].idleIcons[0]);
				battlerLabels.get(sceneSetupInt).setSize(wd,ht);
				//battlerLabels.get(sceneSetupInt).setText(Players[sceneSetupInt].name);
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
		
		bossLabel.setIcon(Boss.idleIcons[0]);
		bossLabel.setOpaque(false);
		bossPanel.add(bossLabel);
		bossPanel.setOpaque(false);
		bossLabel.setSize(500,500);
		bossPanel.setSize(500,500);
		bossPanel.setLocation(1420,250);
		add(bossPanel);
		
		// Add Boss health Panel and label
		bossHealthPanel = new JPanel();
		bossHealthLabel = new JLabel("Boss Health: ");
		bossHealthLabel.setBackground(Color.BLACK);
		bossHealthLabel.setForeground(Color.GREEN);
		bossHealthPanel.setLocation(1420,0);
		bossHealthPanel.setBackground(Color.BLACK);
		bossHealthPanel.setSize(500,50);
		bossHealthPanel.add(bossHealthLabel);
		add(bossHealthPanel);
		
		
		// Add background Panel
		//backgroundPanel = new JPanel();
		//backgroundLabel = new JLabel();
		//backgroundIcon = new ImageIcon("D:\\GitHub\\FlaviusThePerson\\stream-warriors-engine\\src\\main\\java\\com\\flavcreations\\testfiles\\overlay_map_01v1.png");
		backgroundLabel.setIcon(backgroundIcon);
		//backgroundLabel.setBackground(Color.decode("#c900c9"));
		
		backgroundPanel.add(backgroundLabel);
		//backgroundPanel.setBackground(Color.decode("#c900c9"));
		
		backgroundPanel.setLocation(0,-10);
		backgroundPanel.setSize(1920,1080);
		add(backgroundPanel);
		
		/*
		backgroundPanel2 = new JPanel();
		add(backgroundPanel2);
		*/
		getContentPane().repaint();
		super.repaint();
		
		setVisible(true);
		
		
		
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
		//System.out.println("end of loading player panels/labels");
		
		//initiate scene setup
		setupScene();
		
	}
	
}
