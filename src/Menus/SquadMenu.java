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
import Enums.EPosition;

/**
 *
 * @author joaom
 */
public class SquadMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {

        Scanner scanner = new Scanner(System.in);

        ITeam team = Main.season.getUserTeam();

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

    public static void viewStartingXI(ITeam team) {
        IPlayer[] players = team.getPlayers();
        EPosition[] formationPositions = ((Formation) team.getFormation()).getPositions();
        boolean[] used = new boolean[players.length];

        System.out.println("\n--- Starting XI (" + formationPositions.length + ") ---");

        int printed = 0;
        for (EPosition requiredPosition : formationPositions) {
            for (int i = 0; i < players.length; i++) {
                if (!used[i] && players[i] != null && players[i].getPosition() == requiredPosition) {
                    IPlayer p = players[i];
                    used[i] = true;
                    System.out.printf("%d. %s (%s)\n", ++printed, p.getName(), p.getPosition().getDescription());
                    break;
                }
            }
        }
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
        IPlayer[] players = team.getPlayers();
        EPosition[] formationPositions = ((Formation) team.getFormation()).getPositions();
        boolean[] used = new boolean[players.length];
        int[] startingIndices = new int[formationPositions.length];
        int[] benchRealIndices = new int[players.length]; // array fixo para armazenar os índices reais dos suplentes
        int benchCount = 0;

        System.out.println("\n--- Starting XI (" + formationPositions.length + ") ---");

        int printed = 0;
        for (EPosition requiredPosition : formationPositions) {
            for (int i = 0; i < players.length; i++) {
                if (!used[i] && players[i] != null && players[i].getPosition() == requiredPosition) {
                    IPlayer p = players[i];
                    used[i] = true;
                    startingIndices[printed] = i; // índice real
                    System.out.printf("%d. %s (%s)\n", printed, p.getName(), p.getPosition().getDescription());
                    printed++;
                    break;
                }
            }
        }

        // Mostrar banco com mapeamento visual -> real
        System.out.println("\n--- Bench Players ---");
        int displayIndex = 11;
        for (int i = 0; i < players.length; i++) {
            if (!used[i] && players[i] != null) {
                System.out.printf("%d. %s (%s)\n", displayIndex, players[i].getName(), players[i].getPosition().getDescription());
                benchRealIndices[benchCount] = i; // guardar índice real
                benchCount++;
                displayIndex++;
            }
        }

        System.out.print("\nPlayer OUT index (0-10): ");
        int out = Integer.parseInt(scanner.nextLine());

        System.out.print("Player IN index (11-" + (10 + benchCount) + "): ");
        int in = Integer.parseInt(scanner.nextLine());

        if (out < 0 || out >= startingIndices.length || (in - 11) < 0 || (in - 11) >= benchCount) {
            System.out.println("Invalid substitution indices.");
            return;
        }

        int realOutIndex = startingIndices[out];
        int realInIndex = benchRealIndices[in - 11];

        ((Team) team).substitutePlayer(realOutIndex, realInIndex);

        System.out.println("Substitution made: " + players[realOutIndex].getName()
                + "  (<-)   |   (->)  " + players[realInIndex].getName());

    }

    public static void showBench(ITeam team) {
        IPlayer[] allPlayers = team.getPlayers();
        int starters = ((Formation) team.getFormation()).getPositions().length;

        System.out.println("\n--- Bench Players ---");
        for (int i = starters; i < allPlayers.length; i++) {
            if (allPlayers[i] != null) {
                System.out.println(i + ". " + allPlayers[i].getName() + " (" + allPlayers[i].getPosition() + ")");
            }
        }
    }

}
