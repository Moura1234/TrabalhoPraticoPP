/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import League.Season;
import Match.Match;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;

/**
 *
 * @author joaom
 */
public class MatchdaySimulatorMenu {
    
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

