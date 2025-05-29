/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import Main.Main;
import Match.Match;
/**
 *
 * @author joaom
 */
public class CalendarMenu {
    public static void run() {
        System.out.println("\n===== CALENDAR =====");

        int totalRounds = Main.season.getMaxRounds(); // assumes you have this method
        for (int round = 0; round < totalRounds; round++) {
            System.out.println("\n======================");
            System.out.println("Round " + (round + 1));
            System.out.println("======================");

            IMatch[] matches = Main.season.getMatches(round);

            for (IMatch match : matches) {
                Match realMatch = (Match) match;
                String home = match.getHomeClub().getName();
                String away = match.getAwayClub().getName();
              if (realMatch.isPlayed()) {
             int homeGoals = realMatch.getHomeGoals();
             int awayGoals = realMatch.getAwayGoals();
             System.out.printf("%-30s %2d - %2d %-30s\n", home, homeGoals, awayGoals, away);
             } else {
            System.out.printf("%-30s   -   %-30s\n", home, away); // com traço
    }

            }
        }
    }
}
