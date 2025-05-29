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
import com.ppstudios.footballmanager.api.contracts.team.IFormation;
import java.util.Scanner;
import Enums.Position;

/**
 *
 * @author joaom
 */
public class SquadMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {

        Scanner scanner = new Scanner(System.in);

        IClub club = Main.season.getCurrentClubs()[0];
        IPlayer[] players = club.getPlayers();
        Team team = new Team(5, Formation.create442(), Formation.create442().getPositions().length, club, players, 0);

        int option;
        do {
            System.out.println("\n===== SQUAD MENU =====");
            System.out.println("1. View Starting XI");
            System.out.println("2. Change Formation");
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
                    changeFormation(team);
                    break;
                case 3:
                    substitute(team);
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

    public static int[] printStartingXI(ITeam team) {
        IPlayer[] players = team.getPlayers();
        Position[] formation = ((Formation) team.getFormation()).getPositions();
        boolean[] used = new boolean[players.length];
        int[] indices = new int[formation.length];

        System.out.println("\n--- Starting XI (" + formation.length + ") ---");

        int printed = 0;
        for (Position required : formation) {
            for (int i = 0; i < players.length; i++) {
                if (!used[i] && players[i] != null && players[i].getPosition() == required) {
                    System.out.printf("%d. %s (%s)\n", ++printed, players[i].getName(), players[i].getPosition().getDescription());
                    used[i] = true;
                    indices[printed - 1] = i;
                    break;
                }
            }
        }

        return indices;
    }

    public static void viewStartingXI(ITeam team) {
        printStartingXI(team);
    }

    public static void changeFormation(ITeam team) {
        IFormation currentFormation = team.getFormation();

        System.out.println("\nCurrent formation: " + currentFormation.getDisplayName());
        System.out.println("Choose a new formation:");
        System.out.println("1. 4-4-2");
        System.out.println("2. 4-3-3");
        System.out.println("3. 3-5-2");

        Scanner sc = new Scanner(System.in);
        System.out.print("Option: ");
        int choice = sc.nextInt();

        Formation newFormation;

        switch (choice) {
            case 1:
                newFormation = Formation.create442();
                break;
            case 2:
                newFormation = Formation.create433();
                break;
            case 3:
                newFormation = Formation.create352();
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        ((Team) team).setFormation(newFormation);
        System.out.println("Formation changed successfully!");
    }

    public static void substitute(ITeam team) {
        Scanner scanner = new Scanner(System.in);
        int[] starters = printStartingXI(team); // mostra titulares

        printBenchPlayers(team, starters);      // mostra banco

        System.out.print("\nPlayer OUT index (1–" + starters.length + "): ");
        int outIndex = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.print("Player IN index (11–17): ");
        int inIndex = Integer.parseInt(scanner.nextLine());

        if (outIndex < 0 || outIndex >= starters.length || inIndex < 11 || inIndex >= team.getPlayers().length) {
            System.out.println("Invalid substitution indices.");
            return;
        }

        ((Team) team).substitutePlayer(starters[outIndex], team.getPlayers()[inIndex]);

        System.out.println("Substitution made: "
                + team.getPlayers()[starters[outIndex]].getName() + "  -  "
                + team.getPlayers()[inIndex].getName());
    }

    //        Position[] formationPositions = team.getFormation().getPositions();
    //        IPlayer[] players = team.getPlayers();
    //
    //        System.out.println("\n--- Starting XI ---");
    //        int count = 0;
    //
    //        for (int i = 0; i < formationPositions.length; i++) {
    //            Position pos = formationPositions[i];
    //
    //            for (int j = 0; j < allPlayers.length; j++) {
    //                if (!used[j] && allPlayers[j].getPosition().equals(pos)) {
    //                    System.out.println(i + ". " + allPlayers[j].getName() + " (" + pos + ")");
    //                    used[j] = true;
    //                    count++;
    //                    break;
    //                }
    //            }
    //        }
    //        System.out.println("\n--- Bench Players ---");
    //        for (int i = 11; i < players.length; i++) {
    //            if (players[i] != null) {
    //                System.out.println(i + ". " + players[i].getName() + " (" + players[i].getPosition() + ")");
    //            }
    //        }
    //
    //        System.out.print("\nPlayer OUT(0–10): ");
    //        int out = Integer.parseInt(scanner.nextLine());
    //
    //        System.out.print("Player IN(11–17): ");
    //        int in = Integer.parseInt(scanner.nextLine());
    //
    //        if (out < 0 || out >= 11 || in < 11 || in >= players.length) {
    //            System.out.println("Invalid substitution indices.");
    //            return;
    //        }
    //
    //        ((Team) team).substitutePlayer(out, players[in]);
    //        System.out.println("Substitution made: " + players[out].getName() + " (<-)  |  (->) " + players[in].getName());
    //    }
    public static void printBenchPlayers(ITeam team, int[] startingIndices) {
        IPlayer[] players = team.getPlayers();
        boolean[] isStarter = new boolean[players.length];

        for (int index : startingIndices) {
            isStarter[index] = true;
        }

        System.out.println("\n--- Bench Players ---");
        for (int i = 0; i < players.length; i++) {
            if (!isStarter[i] && players[i] != null) {
                System.out.printf("%d. %s (%s)\n", i, players[i].getName(), players[i].getPosition().getDescription());
            }
        }
    }

    public static void showBench(ITeam team) {
        printBenchPlayers(team);

    }
}
