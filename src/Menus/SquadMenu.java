/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import Team.Team;
import Player.Player;
import Main.Main;
import Team.Formation;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.util.Scanner;

/**
 *
 * @author joaom
 */
public class SquadMenu {
    
   public static void run() {
       
       Scanner scanner = new Scanner(System.in);
       
        IClub club = Main.season.getCurrentClubs()[0]; 
        IPlayer[] players = club.getPlayers();
       Team team = new Team(5,Formation.create442(),Formation.create442().getPositions().length,club,players); 

     int option;
    do {
        System.out.println("\n===== SQUAD MENU =====");
        System.out.println("1. View Starting XI");
        System.out.println("2. Change Formation to 4-3-3");
        System.out.println("3. Make substitution");
        System.out.println("4. Show Bench");
        System.out.println("5. Back");

        System.out.print("Option: ");
        option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1:
                viewStartingXI(team);
                break;
            case 2:
                changeFormation(team, "433");
                break;
            case 3:
                System.out.print("Player OUT index (0-10): ");
                int out = Integer.parseInt(scanner.nextLine());
                System.out.print("Player IN index: ");
                int in = Integer.parseInt(scanner.nextLine());
                substitute(team, out, in);
                break;
            case 4:
                showBench(team);
                break;
            case 5:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid option.");
        }
    } while (option != 5);
}
 

    public static void viewStartingXI(ITeam team) {
        System.out.println("\n--- Starting XI ---");
        IPlayer[] players = team.getPlayers();
        for (int i = 0; i < players.length; i++) {
            IPlayer p = players[i];
            if (p != null) {
                System.out.println((i + 1) + ". " + p.getName() + " (" + p.getPosition() + ")");
            }
        }
    }

    public static void changeFormation(ITeam team, String option) {
        Formation newFormation;
        switch (option) {
            case "442": newFormation = Formation.create442(); break;
            case "433": newFormation = Formation.create433(); break;
            case "352": newFormation = Formation.create352(); break;
            default:
                System.out.println("Invalid formation.");
                return;
        }

        ((Team) team).setFormation(newFormation);
        System.out.println(" Formation changed to " + option);
    }

  public static void substitute(ITeam team, int outIndex, int inIndex) {
    IPlayer[] players = team.getPlayers();

    if (outIndex < 0 || outIndex >= 11 || inIndex < 0 || inIndex >= players.length) {
        System.out.println("Invalid substitution indices.");
        return;
    }

    ((Team) team).substitutePlayer(outIndex, players[inIndex]);
    System.out.println(" Substitution made: starter #" + (outIndex + 1) + " replaced with player #" + inIndex);
}

    public static void showBench(ITeam team) {
        IPlayer[] bench = ((Team) team).getBenchPlayers();
        System.out.println("\n--- Bench Players ---");
        for (int i = 0; i < bench.length; i++) {
            if (bench[i] != null) {
                System.out.println(i + ". " + bench[i].getName() + " (" + bench[i].getPosition() + ")");
            }
        }
    }
} 

