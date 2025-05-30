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

import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import Main.Main;
import Match.Match;

/**
 * Displays the season calendar with matches per round. Shows results if matches
 * have been played.
 */
public class CalendarMenu {

    /**
     * Prints all matches of the season round by round. Shows scores if played,
     * or "vs." if not.
     */
    public static void run() {
        System.out.println("\n===== CALENDAR =====");

        int totalRounds = Main.season.getMaxRounds();
        for (int round = 0; round < totalRounds; round++) {
            System.out.println("\n======================");
            System.out.println("Round " + (round + 1));
            System.out.println("======================");

            IMatch[] matches = Main.season.getMatches(round);

            for (IMatch match : matches) {
                if (match == null) {
                    continue;
                }
                Match realMatch = (Match) match;
                String home = match.getHomeClub().getName();
                String away = match.getAwayClub().getName();

                if (realMatch.isPlayed()) {
                    int homeGoals = realMatch.getHomeGoals();
                    int awayGoals = realMatch.getAwayGoals();
                    System.out.printf("%-30s %2d - %2d %-30s\n", home, homeGoals, awayGoals, away);
                } else {
                    System.out.printf("%-30s   vs.   %-30s\n", home, away);
                }
            }
        }
    }
}
