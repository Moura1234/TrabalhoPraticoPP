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

import static Exporter.Exporter.SeasonHtmlGenerator;
import java.io.*;
import Main.Main;
import com.ppstudios.footballmanager.api.contracts.data.htmlgenerators.SeasonHtmlGenerator;

/**
 * Displays the main menu and handles user interaction. Allows access to
 * standings, calendar, squad, simulation, and export options.
 */
public class MainMenu {

    /**
     * Runs the main menu loop until the user chooses to exit.
     */
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
                        StandingsMenu.run(Main.leagueStandings);
                        break;
                    case "2":
                        CalendarMenu.run();
                        break;
                    case "3":
                        SquadMenu.run();
                        break;
                    case "4":
                        MatchdaySimulatorMenu.run(Main.season);
                        break;
                    case "5":
                        MatchSimulatorMenu.run(Main.season);
                    case "6":
                        System.out.println("Saving and exiting...");
                        try {
                            SeasonHtmlGenerator.generate(Main.season, "season_export.html");
                            System.out.println("Season successfully exported to HTML.");
                            Main.season.exportToJson();
                            System.out.println("Season successfully exported to JSON.");
                        } catch (IOException e) {
                            System.out.println("Error exporting season: " + e.getMessage());
                        }

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
