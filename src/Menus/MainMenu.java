/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;


import java.io.*;
import Main.main;
/**
 *
 * @author joaom
 */
public class MainMenu {
      public static void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String option;

        try {
            do {
                System.out.println("\n===== MAIN MENU =====");
                System.out.println("1 - View standings");
                System.out.println("2 - View calendar");
                System.out.println("3 - Manage squad");
                System.out.println("4 - Simulate matchday");
                System.out.println("5 - Simulate next match");
                System.out.println("6 - Save and exit");
                System.out.print("Option: ");
                option = br.readLine();

                switch (option) {
                    case "1":
                        StandingsMenu.run(main.leagueStandings); 
                        break;
                    case "2":
                        CalendarMenu.run();
                        break;
                    case "3":
                       SquadMenu.run(); 
                        break;
                    case "4":
                       MatchdaySimulatorMenu.run(main.season); 
                        break;
                    case "5":   
                        MatchSimulatorMenu.run (main.season);
                    case "6":
                        System.out.println("Saving and exiting...");
                        // call save logic here if needed
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            } while (!option.equals("6"));

        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
}

