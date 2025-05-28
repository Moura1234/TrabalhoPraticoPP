/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import League.Standing;
import com.ppstudios.footballmanager.api.contracts.league.IStanding;

/**
 *
 * @author joaom
 */
public class StandingsMenu {

    public static void run(IStanding[] leagueStandings) {
        System.out.println("\n===== League Table =====\n");
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
