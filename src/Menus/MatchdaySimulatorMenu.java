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

import League.Season;
import Match.Match;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;

/**
 * Simulates all matches in the current round. Updates results and standings
 * automatically.
 */
public class MatchdaySimulatorMenu {

    /**
     * Executes the matchday simulation.
     */
    public static void run(Season season) {
        if (season.isSeasonComplete()) {
            System.out.println("\n The season is already complete!");
            return;
        }

        int round = season.getCurrentRound();
        System.out.println("\n Simulating Round " + (round + 1) + "...");

        season.simulateRound();

        IMatch[] matches = season.getMatches(round);

        System.out.println("\n======= Match Results - Round " + (round + 1) + " =======");
        for (IMatch m : matches) {
            Match match = (Match) m;
            System.out.printf("%-30s %2d - %-2d %-30s\n",
                    match.getHomeTeam().getClub().getName(),
                    match.getHomeGoals(),
                    match.getAwayGoals(),
                    match.getAwayTeam().getClub().getName());
        }
    }
}
