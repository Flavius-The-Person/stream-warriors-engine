package com.flavcreations;

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
            if(!menu.scene.isOpenRoster)
            {
                System.out.println("Roster is not open");
            }
            else
            if(menu.rosterArrayList.size() == 28)
            {
                System.out.println("Roster at max");
                //return;
            }
            else
            if(menu.scene.isOpenRoster)
            {
                if(menu.rosterArrayList.contains(sInp))
                {
                    System.out.println("Player already in roster");
                }
                if(!menu.rosterArrayList.contains(sInp))
                {
                    System.out.println("input was: " + sInp);
                    menu.addPlayer(sInp);
                }
            }
            System.out.println("What is the player to be put in?");
            sInp = inp.nextLine();
        }
    }
}
