package com.flavcreations;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class scene extends JFrame
{
	private JFrame swFrame;
	private JPanel[] battlerPanels;
	private JLabel[] battlerLabels;
	
	private JPanel[] effectPanels;
	private JLabel[] effectLabels;
	
	private JPanel pBoss;
	private JLabel lBoss;
	
	private JPanel epBoss;
	private JLabel elBoss;
	
	private JPanel bgPanel;
	private JLabel bgLabel;
	public ImageIcon bgIcon;
	
	public long turnCycleDelay;
	public long turnCyclePeriod;
	
	
	public player[] players;
	
	scene()
	{
		//swFrame = new JFrame("Stream Warriors Battle");
		
		setLayout(null);
		//setupScene();
		
		updateScene();
		
		//swFrame.setSize(1920, 1080);
		//swFrame.setVisible(true);
		
		
		
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
	
	};
	private int[] pos35y = {
	
	};
	private void setupScene()
	{
		for(int ss = 0; ss < battlerPanels.length; ss++)
		{
				/*
				battlerPanels[2].setLocation(0,ht*2);
				 */
			while(ss < 35)
			{
				
			}
		}
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
		players = new player[roster.size()];
		
		//import random function
		Random randy = new Random();
		
		//use random function to generate a random number between 2 for use when a person doesn't have a set character
		int pgen = randy.nextInt(2);
		
		//for loop through the players object array adding data to each player
		for(int pint = 0; pint < players.length; pint++)
		{
			players[pint] = new player();
			players[pint].setData(roster.get(pint), pgen);
		}
		
		//initiate the load game function
		loadGame();
	}
	
	//load game function to create panels and labels for each character.
	public void loadGame()
	{
		//initiate pannels and labels arrays for battler panels/labels and effect panels/labels
		battlerPanels = new JPanel[players.length];
		battlerLabels = new JLabel[players.length];
		effectPanels = new JPanel[players.length+1];
		effectLabels = new JLabel[players.length+1];
		
		//console print stating that the panels and labels were created for testing purposes (will be taken out later)
		System.out.println("end of setting player count, and panel/label arrays");
		
		
		for(int rri = 0; rri < players.length; rri++)
		{
			//create a new panel and label for player characters
			battlerPanels[rri] = new JPanel();
			battlerPanels[rri].setLayout(null);
			battlerLabels[rri] = new JLabel();
			battlerLabels[rri].setText(players[rri].name + "-" + players[rri].health);
			System.out.println("player " + rri + " should be " + players[rri].name);
			
			//create a new pannel and label for battle effects
			effectPanels[rri] = new JPanel();
			effectPanels[rri].setLayout(null);
			effectLabels[rri] = new JLabel("Eff Pan");
			effectLabels[rri].setText("Eff Pan " + rri);
			
			battlerPanels[rri].add(battlerLabels[rri]);
			System.out.println(players[rri].name);
		}
		
		//console print stating that player data has been loaded into panels/labels
		System.out.println("end of loading player panels/labels");
		effectPanels[players.length] = new JPanel();
		effectLabels[players.length] = new JLabel();
		setupScene();
		
	}
	
}
