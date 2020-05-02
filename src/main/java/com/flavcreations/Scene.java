package com.flavcreations;


import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.text.SimpleDateFormat;
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
	
	boolean allPlayersDead = false;
	private int playersDead = 0;
	
	long lSecond = 1000L;
	long lMinute = 60000L;
	long lTenMinutes = lMinute * 10;
	int totalPlayerDamageSoFar = 0;
	int totalBossDamageSoFar = 0;
	int totalTurns = 0;
	int totalTurnstwo = 0;
	
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
		
		setSize(1920,1000);
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
		turnCycleDelay = lSecond / 10;
		turnCyclePeriod = lSecond / 10;
	}
	String timeString, timeStringTwo;
	SimpleDateFormat sdf;
	Calendar cal;
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
		
		cal = Calendar.getInstance();
		sdf = new SimpleDateFormat("HH:mm:ss");
		timeString = sdf.format(cal.getTime());
		System.out.println(timeString);
		//isFightPaused = false;
		//isFightStarted = true;
		
		
		
		
		
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
			if (!isFightPaused)
			{
				Random rand = new Random();
				
				//check for player deaths
				playersDead = 0;
				for (int x = 0; x < Players.length; x++)
				{
					if(Players[x].isKO)
					{
						playersDead++;
						//battlerLabels.get(x).setIcon(Players[x].koIcons[0]);
					}
					//if(playersDead>0)System.out.println("Players Dead: " + playersDead);
				}
				
				//System.out.println("pre turnlist");
				if (turn >= turnList.size()) //turnList.isEmpty())
				{
					turnList.clear();
					
					//System.out.println("enter turn list");
					//double temp_turns = Players.length * 1.6;
					//long temp_turns_long = Math.round(temp_turns);
					
					
					
					//int maxBossTurns = (int) Math.rint(Players.length * 0.75);
					//int bossTurns = maxBossTurns + Players.length;//rand.nextInt(maxBossTurns + 1);
					
					//int bossTurns = Players.length * 2;
					
					//int temp_turns = bossTurns;//Players.length + bossTurns;
					//System.out.println("dead players: " + playersDead);
					int bossTurns = Players.length - playersDead;
					totalTurns = Players.length + bossTurns;
					totalTurnstwo = totalTurns;
					//System.out.println("Total Turns: " + totalTurns);
					//System.out.println("Total Turns2: " + totalTurnstwo);
					
					/*for (int temp_turn = 0; temp_turn <= total_turns; temp_turn++) //temp_turns_long; temp_turn++)
					{
						if(Players[temp_turn].isKO)
						{
							temp_turn++;
							System.out.println("temp turn up due to KO player");
						}
						System.out.println("adding turn: " + temp_turn);
						turnList.add(temp_turn);
						
						//System.out.println("For loop for adding temp turn: " + temp_turn);
					}*/
					
					int temp_turn = 0;
					
					
					while(temp_turn <= totalTurns)
					{
						//System.out.println("total turns: " + totalTurns);
						//System.out.println("turn: " + temp_turn);
						if(temp_turn < Players.length)
						{
							if(Players[temp_turn].isKO)
							{
								//System.out.println("Not adding player");
							}
							if(!Players[temp_turn].isKO)
							{
								//System.out.println("adding player");
								turnList.add(temp_turn);
							}
							temp_turn++;
						}
						else
							turnList.add(temp_turn);
							temp_turn++;
						/*if(Players[temp_turn].isKO)
						{
							System.out.println("temp turn up due to ko player");
							temp_turn++;
						}
						else {
							System.out.println("Adding turn: " + temp_turn);
							turnList.add(temp_turn);
							temp_turn++;
						}*/
					}/*
					do {
						if(Players[temp_turn].isKO)
						{
							System.out.println("temp turn up due to ko player");
							temp_turn++;
						}
						System.out.println("Adding turn: " + temp_turn);
						turnList.add(temp_turn);
						temp_turn++;
					}while(temp_turn <= total_turns);
					*/
					
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
				
				
				if(playersDead >= Players.length) allPlayersDead = true;
				
				if(allPlayersDead)
				{
					System.out.println("Players ded, game pausing");
					cal = Calendar.getInstance();
					sdf = new SimpleDateFormat("HH:mm:ss");
					timeStringTwo = sdf.format(cal.getTime());
					System.out.println("start:"+timeString);
					System.out.println("end:"+timeStringTwo);
					System.out.println("boss health:"+Boss.health);
					for(int x = 0; x <= Players.length; x++)
					{
						System.out.println("Player " + x + "'s Health: " + Players[x].health);
					}
					pauseGame();
				}
				if(Boss.ded)
				{
					System.out.println("Boss ded, game pausing");
					cal = Calendar.getInstance();
					sdf = new SimpleDateFormat("HH:mm:ss");
					timeStringTwo = sdf.format(cal.getTime());
					System.out.println("start:"+timeString);
					System.out.println("end:"+timeStringTwo);
					System.out.println("boss health:"+Boss.health);
					for(int x = 0; x < Players.length; x++)
					{
						System.out.println("Player " + Players[x].name + " Health: " + Players[x].health);
					}
					pauseGame();
				}
				
				if(!allPlayersDead) {
					for (int x = 0; x < Players.length; x++) {
						
						if(!Players[x].isKO) {
							if (x == turnList.get(turn)) {
								//System.out.println("player turn: " + eventTimer);
								battlerLabels.get(x).setIcon(Players[x].attackIcons[eventTimer]);
								//System.out.println("Set player att icon");
								if (eventTimer == 2) {
									Boss.updateHealth(3);
									totalPlayerDamageSoFar += 3;
									
									//System.out.println("Total Player Damge So Far: " + totalPlayerDamageSoFar);
									
									if (Boss.ded) bossLabel.setIcon(Boss.koIcons[0]);
								}
							}
							//System.out.println("exiting player turn 1");
							if (x != turnList.get(turn)) {
								//System.out.println("Player but not turn: " + eventTimer + " Idle frame: " + Players[x].idleFrame);
								if (Players[x].idleUp) {
									Players[x].idleFrame += 1;
								}
								
								if (!Players[x].idleUp) {
									Players[x].idleFrame -= 1;
								}
								//System.out.println("setting player idle icon");
								battlerLabels.get(x).setIcon(Players[x].idleIcons[Players[x].idleFrame]);
								
								if (Players[x].idleFrame == 2) {
									Players[x].idleUp = false;
								}
								
								if (Players[x].idleFrame == 0) {
									Players[x].idleUp = true;
								}
							}
						}
					}
					
					//System.out.println("Exited for loop");
				}
				
				if(!Boss.ded) {
					//System.out.println("boss not dead");
					//System.out.println("Total turns: " + totalTurns);
					if (turn < totalTurns) {
						if (turnList.get(turn) > Players.length) {
							
							bossLabel.setIcon(Boss.attackIcons[eventTimer]);
							if (eventTimer == 2) {
								int target = rand.nextInt(Players.length);
								while (Players[target].isKO) {
									target = rand.nextInt(Players.length);
								}
								Players[target].updateHealth(3);
								totalBossDamageSoFar += 3;
								
								//System.out.println("Total Boss Damage So Far: " + totalBossDamageSoFar);
								
								if (Players[target].isKO) battlerLabels.get(target).setIcon(Players[target].koIcons[0]);
								playersDead++;
							}
							
							//System.out.println("animating boss turn");
						} else if (turnList.get(turn) < Players.length) {
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
						//System.out.println("Animating boss idle");
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
					
				
				
				totalTurnstwo = totalTurns - playersDead;
				
				//System.out.println("turn: " + turn);
				//System.out.println("Event Timer after eventTimer++: " + eventTimer);
				
				}
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
	
	private int[] posX = {
			0, 0, 0, 0, 0, 0, 0, 0, //1-8
			100, 100, 100, 100, 100, 100, 100, 100, //9-16
			200, 200, 200, 200, 200, 200, 200, 200, //17-24
			300, 300, 300, 300, 300, 300, 300, 300, //25-32
			400, 400, 400, 400, 400, 400, 400, 400, //33-40
			500, 500, 500, 500, 500, 500, 500, 500, //41-48
			600, 600, 600, 600, 600, 600, 600, 600, //49-56
			700, 700, 700, 700, 700, 700, 700, 700, //57-64
			800, 800, 800, 800, 800, 800, 800, 800, //65-72
			900, 900, 900, 900, 900, 900, 900, 900, //73-80
			1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, //81-88
			1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, //89-96
			1200, 1200, 1200, 1200, 1200, 1200, 1200, 1200, //97-104
			1300, 1300, 1300, 1300, 1300, 1300, 1300, 1300, //105-112
			//1000, 1000, 1000, 1000, 1000, 1000, 1000, 1400,
			
	};
	
	private int[] posY = {
			0, 100, 200, 300, 400, 500, 600, 700, //1-8
			0, 100, 200, 300, 400, 500, 600, 700, //9-16
			0, 100, 200, 300, 400, 500, 600, 700, //17-24
			0, 100, 200, 300, 400, 500, 600, 700, //25-32
			0, 100, 200, 300, 400, 500, 600, 700, //33-40
			0, 100, 200, 300, 400, 500, 600, 700, //41-48
			0, 100, 200, 300, 400, 500, 600, 700, //49-56
			0, 100, 200, 300, 400, 500, 600, 700, //57-64
			0, 100, 200, 300, 400, 500, 600, 700, //65-72
			0, 100, 200, 300, 400, 500, 600, 700, //73-80
			0, 100, 200, 300, 400, 500, 600, 700, //81-88
			0, 100, 200, 300, 400, 500, 600, 700, //89-96
			0, 100, 200, 300, 400, 500, 600, 700, //97-104
			0, 100, 200, 300, 400, 500, 600, 700, //105-112
			
			/*
			0, 100, 200, 300, //5-8
			0, 100, 200, 300, //9-12
			0, 100, 200, 300, //13-16
			0, 100, 200, 300, //17-20
			0, 100, 200, 300, //21-24
			0, 100, 200, 300  //25-28
	*/
	};
	
	private void setupScene()
	{
		System.out.println("setting up scene");
		for(int sceneSetupInt = Players.length - 1; sceneSetupInt >= 0; sceneSetupInt--) //battlerPanels.length; ss++)
		{
			
			{
				battlerPanels.get(sceneSetupInt).setSize(wd,ht);
				battlerLabels.get(sceneSetupInt).setIcon(Players[sceneSetupInt].idleIcons[0]);
				battlerLabels.get(sceneSetupInt).setSize(wd,ht);
				battlerLabels.get(sceneSetupInt).setText(Players[sceneSetupInt].name);
				battlerLabels.get(sceneSetupInt).setForeground(Color.WHITE);
				battlerPanels.get(sceneSetupInt).add(battlerLabels.get(sceneSetupInt));
				battlerPanels.get(sceneSetupInt).updateUI();
				
				add(battlerPanels.get(sceneSetupInt));
				battlerPanels.get(sceneSetupInt).setLocation(posX[sceneSetupInt], posY[sceneSetupInt]);
				battlerPanels.get(sceneSetupInt).setOpaque(false);
				
				effectPanels.get(sceneSetupInt).setSize(wd,ht);
				effectLabels.get(sceneSetupInt).setSize(wd,ht);
				effectPanels.get(sceneSetupInt).add(effectLabels.get(sceneSetupInt));
				
				add(effectPanels.get(sceneSetupInt));
				effectPanels.get(sceneSetupInt).setLocation(posX[sceneSetupInt], posY[sceneSetupInt]);
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
		
		long playerTotalDamage = (((2*3)*60)*10) /2;
		long playerTotalDamageOneMin = (((2)*3)*60) /2;
		
		long totalPlayerHealthPool = playerTotalDamage;
		long healthPerPlayer = totalPlayerHealthPool/(Players.length/2);
		
		/*
		 * 10 minutes = 60 seconds * 10 = 600 seconds = 1 second / 5 = 5 * 600 = 3000
		 * 3000 * 3 = 9000 / 10 = 900
		 *
		 * 1 second * 10  /2 = 0.5 *
		 *
		 * */
		
		//int iBossBaseHealth = 900;
		System.out.println("player total damage in 10 minutes: " + playerTotalDamage);
		System.out.println("player total damage in 01 minutes: " + playerTotalDamageOneMin);
		Boss.setHealth((int)playerTotalDamage);
		for(int x = 0; x < Players.length; x++)
		{
			Players[x].setHealth((int)healthPerPlayer);
			System.out.println("Player " + Players[x].name + "'s Health: " + Players[x].health);
		}
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
