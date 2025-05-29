/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import League.League;
import League.Season;
import Match.Match;
import Simulation.MatchSimulator;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.util.Scanner;

/**
 *
 * @author utilizador
 */
public class MatchSimulatorMenu {

    public static void run(Season season) {

        League league = season.getLeague(); //  extrair a League a partir da Season

        ITeam userTeam = season.getUserTeam(); //  a equipa escolhida pelo utilizador
        Match nextMatch = season.getNextMatchForTeam(userTeam); // obter o próximo jogo dessa equipa

        if (nextMatch == null) {
            System.out.println("There´s no more games to simulate.");
            return;
        }

        System.out.println("\nSimulating: "
                + nextMatch.getHomeTeam().getClub().getName()
                + " vs "
                + nextMatch.getAwayTeam().getClub().getName()
        );

        MatchSimulator simulator = new MatchSimulator(); // ou passar season se necessário
        simulator.simulate(nextMatch);

        System.out.println("\n--- MATCH EVENTS ---");
        for (int i = 0; i < nextMatch.getEventCount(); i++) {
            IEvent e = nextMatch.getEvents()[i];
            System.out.printf("%d' - %s\n", e.getMinute(), e.getDescription());
        }
    }
}
