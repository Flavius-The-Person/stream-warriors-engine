package com.flavcreations;

import com.flavcreations.characters.Enemy;
import com.flavcreations.characters.Player;
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
	
	List<JPanel> battlerPanels = new ArrayList<JPanel>();
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
	
	Player[] Players;
	private Enemy Boss;
	
	private int eventTimer = 0;
	
	boolean allPlayersDead = false;
	private int playersDead = 0;
	boolean fightWon = false;
	boolean fightOver = false;
	
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
		
		setSize(1920,1000);
		setVisible(true);
		
		backgroundPanel = new JPanel();
		backgroundLabel = new JLabel();
		String baseloc = "src/main/java/com/flavcreations/characters/art/background/";
		backgroundIcon = new ImageIcon(baseloc + "overlay_map_01v1.png");
		add(backgroundPanel);
		
		getContentPane().repaint();
		super.repaint();
		backgroundPanel.setLocation(0,-10);
		setVisible(true);
		iFrame.setVisible(true);
		turnCycleDelay = lSecond / 5;
		turnCyclePeriod = lSecond / 5;
		gameCycleEvent = new Runnable()
		{
			@Override
			public void run()
			{
				gameTask();
				
			}
		};
		scheduler.scheduleAtFixedRate(gameCycleEvent, turnCycleDelay, turnCyclePeriod, MILLISECONDS);
	}
	
	String timeString, timeStringTwo;
	SimpleDateFormat sdf;
	Calendar cal;
	
	//start game function
	public void startGame()
	{
		cal = Calendar.getInstance();
		sdf = new SimpleDateFormat("HH:mm:ss");
		timeString = sdf.format(cal.getTime());
		System.out.println(timeString);
		
		// schedule task gameCycleEvent at turndycledelay, turncycleperiod, for milliseconds.
		//scheduler.scheduleAtFixedRate(gameCycleEvent, turnCycleDelay, turnCyclePeriod, MILLISECONDS);
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
		if(isFightStarted)
		{
			if (!isFightPaused)
			{
				if(!fightOver) 
				{
					Random rand = new Random();
					
					//check for player deaths
					playersDead = 0;
					for (int pd = 0; pd < Players.length; pd++) 
					{
						if (Players[pd].isKO) playersDead++;
					}
					if (playersDead >= Players.length) allPlayersDead = true;
					
					if (allPlayersDead) 
					{
						System.out.println("Players ded, game pausing");
						cal = Calendar.getInstance();
						sdf = new SimpleDateFormat("HH:mm:ss");
						timeStringTwo = sdf.format(cal.getTime());
						System.out.println("start:" + timeString);
						System.out.println("end:" + timeStringTwo);
						System.out.println("boss health:" + Boss.health);
						
						fightOver = true;
						fightWon = false;
					}
					
					if (Boss.isKO) {
						System.out.println("Boss ded, game pausing");
						cal = Calendar.getInstance();
						sdf = new SimpleDateFormat("HH:mm:ss");
						timeStringTwo = sdf.format(cal.getTime());
						System.out.println("start:" + timeString);
						System.out.println("end:" + timeStringTwo);
						
						fightOver = true;
						fightWon = true;
					}
					
					if (!fightOver) 
					{
						int bossTurns, playerTurns = 0;
						if (turn >= turnList.size()) 
						{
							turnList.clear();
							bossTurns = Players.length - playersDead;
							playerTurns = Players.length;
							totalTurns = playerTurns + bossTurns;
							totalTurnstwo = totalTurns;
							
							
							for(int temp_turn = 0; temp_turn <= totalTurns; temp_turn++)
							{
								if (temp_turn < Players.length) {
									if(!Players[temp_turn].isKO)
									{
										turnList.add(temp_turn);
									}
								}
								if(temp_turn >= Players.length)
								{
									turnList.add(temp_turn);
								}
							}
							
							Collections.shuffle(turnList);
							turn = 0;
						}
						
						for (int anim_int = 0; anim_int <= Players.length; anim_int++) 
						{
							if (!fightOver) 
							{
								if (anim_int < Players.length) 
								{
									if (!Players[anim_int].isKO) 
									{
										if (turnList.get(turn) == anim_int) 
										{
											battlerLabels.get(anim_int).setIcon(Players[anim_int].attackIcons[eventTimer]);
											if (eventTimer == 2) 
											{
												Boss.updateHealth(3);
												totalPlayerDamageSoFar += 3;
												if (Boss.isKO) 
												{
													bossLabel.setIcon(Boss.koIcons[0]);
													System.out.println("Boss ded, game pausing");
													cal = Calendar.getInstance();
													sdf = new SimpleDateFormat("HH:mm:ss");
													timeStringTwo = sdf.format(cal.getTime());
													for (int x = 0; x < Players.length; x++) 
													{
														System.out.println("Player " + Players[x].name
																+ " Health: " + Players[x].health);
													}
													System.out.println("start:" + timeString);
													System.out.println("end:" + timeStringTwo);
													fightWon = true;
													fightOver = true;
												}
											}
										}
										if (turnList.get(turn) != anim_int) 
										{
											if (Players[anim_int].idleUp) 
											{
												Players[anim_int].idleFrame += 1;
											}
											
											if (!Players[anim_int].idleUp) 
											{
												Players[anim_int].idleFrame -= 1;
											}
											battlerLabels.get(anim_int).setIcon(Players[anim_int].idleIcons[Players[anim_int].idleFrame]);
											
											if (Players[anim_int].idleFrame == 2) 
											{
												Players[anim_int].idleUp = false;
											}
											
											if (Players[anim_int].idleFrame == 0) 
											{
												Players[anim_int].idleUp = true;
											}
										}
									}
								}
								
								if (anim_int == Players.length) 
								{
									
									if (turnList.get(turn) > playerTurns - playersDead) 
									{
										if(turnList.get(turn) < totalTurnstwo) 
										{
											bossLabel.setIcon(Boss.attackIcons[eventTimer]);
											if (eventTimer == 2) 
											{
												int target = rand.nextInt(Players.length);
												while (Players[target].isKO) 
												{
													target = rand.nextInt(Players.length);
												}
												Players[target].updateHealth(3);
												totalBossDamageSoFar += 3;
												
												if (Players[target].isKO) 
												{
													battlerLabels.get(target).setIcon(Players[target].koIcons[0]);
													playersDead++;
													totalTurnstwo = totalTurns - playersDead;
												}
												if (playersDead >= Players.length) 
												{
													System.out.println("Players ded, game pausing");
													cal = Calendar.getInstance();
													sdf = new SimpleDateFormat("HH:mm:ss");
													timeStringTwo = sdf.format(cal.getTime());
													System.out.println("start:" + timeString);
													System.out.println("end:" + timeStringTwo);
													System.out.println("boss health:" + Boss.health);
													fightOver = true;
												}
											}
										}
									}
									if (turnList.get(turn) < Players.length - playersDead) 
									{
										if (Boss.idleUp) 
										{
											Boss.idleFrame += 1;
										}
										
										if (!Boss.idleUp) 
										{
											Boss.idleFrame -= 1;
										}
										
										bossLabel.setIcon(Boss.idleIcons[Boss.idleFrame]);
										
										if (Boss.idleFrame == 2) 
										{
											Boss.idleUp = false;
										}
										
										if (Boss.idleFrame == 0) 
										{
											Boss.idleUp = true;
										}
									}
								}
							}
						}
						eventTimer++;
						
						if (eventTimer > 2) 
						{
							
							turn++;
							
							eventTimer = 0;
							
							totalTurnstwo = totalTurns - playersDead;
							
						}
						int playerbase_current_health = 0;
						for(int y = 0; y < Players.length; y++)
						{
							playerbase_current_health += Players[y].health;
						}
						System.out.println("Playerbase Health: " + playerbase_current_health);
						System.out.println("Boss Health: " + Boss.health);
					}
				}
				if(fightOver)
				{
					if(fightWon)
					{
						for(int x = 0; x < Players.length; x++) 
						{
							if(!Players[x].isKO) 
							{
								if (Players[x].idleUp) Players[x].idleFrame += 1;
								if (!Players[x].idleUp) Players[x].idleFrame -= 1;
								battlerLabels.get(x).setIcon(Players[x].attackIcons[Players[x].idleFrame]);
								if (Players[x].idleFrame == 2) Players[x].idleUp = false;
								if (Players[x].idleFrame == 0) Players[x].idleUp = true;
							}
						}
					}
					if(!fightWon)
					{
						if (Boss.idleUp) Boss.idleFrame += 1;
						if (!Boss.idleUp) Boss.idleFrame -= 1;
						bossLabel.setIcon(Boss.attackIcons[Boss.idleFrame]);
						if (Boss.idleFrame == 2) Boss.idleUp = false;
						if (Boss.idleFrame == 0) Boss.idleUp = true;
					}
				}
			}
		}
	}
	
	//pause the game
	public void pauseGame()
	{
		isFightPaused = true;
		
	}
	
	private int ht = 200;
	private int wd = 200;
	
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
	};
	
	private void setupScene()
	{
		System.out.println("setting up scene");
		for(int sceneSetupInt = Players.length - 1; sceneSetupInt >= 0; sceneSetupInt--)
		{

			battlerPanels.get(sceneSetupInt).setSize(wd,ht);
			battlerLabels.get(sceneSetupInt).setIcon(Players[sceneSetupInt].idleIcons[0]);
			battlerLabels.get(sceneSetupInt).setSize(wd,ht);
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

		//add boss
		bossPanel = new JPanel();
		bossLabel = new JLabel();

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
		bossHealthPanel.setLocation(1420,0);
		bossHealthPanel.setBackground(Color.BLACK);
		bossHealthPanel.setSize(500,50);
		bossHealthPanel.add(bossHealthLabel);

		// Add background Panel
		backgroundLabel.setIcon(backgroundIcon);
		backgroundPanel.add(backgroundLabel);
		backgroundPanel.setLocation(0,-10);
		backgroundPanel.setSize(1920,1080);
		add(backgroundPanel);

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
		Boss.setData(bossInt);
	}
	
	//add players from roster to the scene
	public <rosterName> void addPlayers(ArrayList<String> roster, ArrayList<Integer> characterChoice, ArrayList<String> rosterName)
	{
		if(isFightStarted)
		{
			System.out.println("Game already started, can't add new players.");
		}
		else if(!isFightStarted) {
			//set players object array size to roster size.
			Players = new Player[roster.size()];
			
			//import random function
			Random randy = new Random();
			
			//use random function to generate a random number between 2 for use when a person doesn't have a set character
			int pgen = randy.nextInt(2);
			
			//for loop through the players object list adding data to each player
			for (int playerLoopInt = 0; playerLoopInt < Players.length; playerLoopInt++) 
			{
				Players[playerLoopInt] = new Player();
				System.out.println("name = " + rosterName.get(playerLoopInt) + " | character choice = " + characterChoice.get(playerLoopInt));
				Players[playerLoopInt].setData(roster.get(playerLoopInt), characterChoice.get(playerLoopInt), rosterName.get(playerLoopInt));
			}
			
			//initiate the load game function
			loadGame();
			
			long damage = 3;
			long fight_length = 1;
			
			long playerTotalDamageOneMin = damage * 60;
			long playerTotalDamage = playerTotalDamageOneMin * fight_length;
			
			long totalPlayerHealthPool = 0;// playerTotalDamage;
			long totalBossHealth = 0;
			long healthPerPlayer = 0;
			if (Players.length > 1) 
			{
				System.out.println("player length > 1 ");
				totalPlayerHealthPool = playerTotalDamage / 2;
				healthPerPlayer = totalPlayerHealthPool / (Players.length / 2);
				totalBossHealth = healthPerPlayer * (Players.length);
				Boss.setHealth((int) totalBossHealth);
			}
			if (Players.length <= 1) 
			{
				totalPlayerHealthPool = playerTotalDamage;
				System.out.println("player length < = 1 ");
				healthPerPlayer = totalPlayerHealthPool;
				Boss.setHealth((int) playerTotalDamage);
			}
			/*
			 * 10 minutes = 60 seconds * 10 = 600 seconds = 1 second / 5 = 5 * 600 = 3000
			 * 3000 * 3 = 9000 / 10 = 900
			 *
			 * 1 second * 10  /2 = 0.5 *
			 *
			 * */
			
			for (int x = 0; x < Players.length; x++) 
			{
				Players[x].setHealth((int) healthPerPlayer);
			}
			System.out.println("Health per player: " + healthPerPlayer);
		}
	}
	
	//load game function to create panels and labels for each character.
	private void loadGame()
	{
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
		
		//initiate scene setup
		setupScene();
	}
	
	public void clearPlayers()
	{
		if(!battlerPanels.isEmpty())
		{
			for (int x = 0; x < battlerPanels.size(); x++) 
			{
				remove(battlerPanels.get(x));
				remove(battlerLabels.get(x));
			}
		}
		
		if(bossPanel!=null) remove(bossPanel);
		battlerPanels.clear();
		battlerLabels.clear();
		bossPanel = null;
		
		revalidate();
		repaint();
		isFightStarted = false;
		fightOver = false;
		fightWon = false;
		allPlayersDead = false;
		if(Boss!=null)Boss.isKO = false;
		playersDead = 0;
		turn = turnList.size()+1;
	}
}
