/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */
package Menus;

import League.League;
import League.Season;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.util.Scanner;

/**
 * Allows the user to select a team from the available options.
 */
public class TeamSelectionMenu {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a list of available teams and allows the user to choose one.
     *
     * @param season The season containing the teams
     * @return The team selected by the user
     */
    public static ITeam run(Season season) {

        System.out.println("==============================================");
        System.out.println("==============================================");
        System.out.println("             Football Manager 25              ");
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
