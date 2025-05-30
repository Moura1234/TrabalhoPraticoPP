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

import League.Standing;
import com.ppstudios.footballmanager.api.contracts.league.IStanding;

/**
 * Displays the league standings ordered by points and goal difference.
 */
public class StandingsMenu {

    /**
     * Displays the league table with teams ordered by points and goal
     * difference.
     *
     * @param leagueStandings Array of standings to be displayed
     */
    public static void run(IStanding[] leagueStandings) {
        orderStandings(leagueStandings);

        System.out.println("\n================================= League Table =================================\n");
        System.out.printf("    %-30s %5s %5s %5s %5s %5s %5s %5s\n",
                "Team", "Pts", "W", "D", "L", "GS", "GC", "GD");

        for (IStanding s : leagueStandings) {
            Standing st = (Standing) s;
            System.out.printf("    %-30s %5d %5d %5d %5d %5d %5d %5d\n",
                    st.getTeam().getClub().getName(),
                    st.getPoints(),
                    st.getWins(),
                    st.getDraws(),
                    st.getLosses(),
                    st.getGoalScored(),
                    st.getGoalsConceded(),
                    st.getGoalDifference());
        }
    }

    /**
     * Sorts the league standings by points and goal difference.
     *
     * @param leagueStandings Array of standings to be ordered
     */
    public static void orderStandings(IStanding[] leagueStandings) {
        for (int i = 0; i < leagueStandings.length - 1; i++) {
            for (int j = i + 1; j < leagueStandings.length; j++) {
                Standing s1 = (Standing) leagueStandings[i];
                Standing s2 = (Standing) leagueStandings[j];

                boolean swap = false;

                if (s2.getPoints() > s1.getPoints()) {
                    swap = true;
                } else if (s2.getPoints() == s1.getPoints()) {
                    if (s2.getGoalDifference() > s1.getGoalDifference()) {
                        swap = true;
                    }
                }

                if (swap) {
                    IStanding temp = leagueStandings[i];
                    leagueStandings[i] = leagueStandings[j];
                    leagueStandings[j] = temp;
                }
            }
        }
    }
}
