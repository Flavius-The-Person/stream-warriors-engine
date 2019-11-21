package com.flavcreations;

import java.util.Scanner;

public class init
{

    public static void main(String[] args)
    {
        Scanner inp = new Scanner(System.in);
        menu mn = new menu();

        String sInp;
        System.out.println("What is the player to be put in?");
        sInp = inp.nextLine();

        while(!(sInp.equals("done")))
        {
            if(!mn.opnRstr)
            {
                System.out.println("Roster is not open");
            }
            if(mn.opnRstr)
            {
                if(mn.roster.contains(sInp))
                {
                    System.out.println("Player already in roster");
                }
                if(!mn.roster.contains(sInp)) {

                    System.out.println("input was: " + sInp);
                    mn.addPlayer(sInp);
                }
            }
            System.out.println("What is the player to be put in?");
            sInp = inp.nextLine();



        }






    }
}
