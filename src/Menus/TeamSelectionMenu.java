/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import League.League;
import League.Season;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.util.Scanner;

/**
 *
 * @author utilizador
 */
public class TeamSelectionMenu {

 
    private static final Scanner scanner = new Scanner(System.in);

    public static ITeam run(Season season) {
        
    System.out.println("==============================================");
    System.out.println("==============================================");
    System.out.println("             Football Manager 26              ");
    System.out.println("==============================================");
    System.out.println("==============================================\n");
   

        League league = season.getLeague();
        ITeam[] teams = season.getTeams();

        System.out.println("===== TEAM SELECTION =====");
        for (int i = 0; i < teams.length; i++) {
            if (teams[i] != null) {
            System.out.println(i + ". " + teams[i]);
  
            }
        }

        System.out.print("Enter the number of the team you want to control: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            choice = -1;
        }

        if (choice < 0 || choice >= teams.length || teams[choice] == null) {
            System.out.println("Invalid choice. Defaulting to the first available team.");
            return teams[0];
        } else {
            System.out.println("You are now controlling: " + teams[choice]);
            return teams[choice];
        }
    }

}
 

