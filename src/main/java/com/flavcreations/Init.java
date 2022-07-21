package com.flavcreations;

import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class Init
{
    public static void main(String[] args)
    {
        Scanner inp = new Scanner(System.in);
        Menu menu = new Menu();
        
        String sInp;
        System.out.println("What is the player to be put in?");
        sInp = inp.nextLine();

        while(!(sInp.equals("done")))
        {
            if(!menu.llg.isOpenRoster)
            {
                System.out.println("Roster is not open");
            }
            else
            if(menu.rosterArrayList.size() == 28)
            {
                System.out.println("Roster at max");
            }
            else
            if(menu.llg.isOpenRoster)
            {
                if(menu.rosterArrayList.contains(sInp))
                {
                    System.out.println("Player already in roster");
                }
                if(!menu.rosterArrayList.contains(sInp))
                {
                    System.out.println("input was: " + sInp);
                    Random randy = new Random();
                    String playerID = "0";
                    int characterChoice = randy.nextInt(6);
                    String displayName = "test";
                    menu.addPlayer(playerID, characterChoice, displayName);
                }
            }
            System.out.println("What is the player to be put in?");
            sInp = inp.nextLine();
        }
    }
}
