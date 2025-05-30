/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

import Event.*;
import static Event.Statistics.isFromTeam;
import League.League;
import League.Season;
import League.Standing;
import Match.Match;
import Simulation.MatchSimulator;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.util.Scanner;

/**
 *
 * @author utilizador
 */
public class MatchSimulatorMenu {

    public static void run(Season season) {

        League league = season.getLeague();

        ITeam userTeam = season.getUserTeam();
        Match nextMatch = season.getNextMatchForTeam(userTeam);

        if (nextMatch == null) {
            System.out.println("There´s no more games to simulate.");
            return;
        }

        int currentRound = nextMatch.getRound();
        IMatch[] roundMatches = season.getSchedule().getMatchesForRound(currentRound);

        MatchSimulator simulator = new MatchSimulator();
        System.out.println("\n--- Simulating Round " + (currentRound + 1) + " ---");

        for (IMatch im : roundMatches) {
            Match match = (Match) im;

            if (!match.isPlayed()) {
                simulator.simulate(match);
                updateStandings(match, season.getLeagueStandings());
            }
        }

        season.setCurrentRound(currentRound + 1);

        System.out.println("\n\nYour Match:");
        System.out.println(
                nextMatch.getHomeTeam().getClub().getName() + " "
                + nextMatch.getHomeGoals() + " - " + nextMatch.getAwayGoals() + " "
                + nextMatch.getAwayTeam().getClub().getName()
        );

        System.out.println("\n--- MATCH EVENTS ---");
        for (int i = 0; i < nextMatch.getEventCount(); i++) {
            IEvent e = nextMatch.getEvents()[i];

            if (e instanceof IGoalEvent) {
                IGoalEvent goal = (IGoalEvent) e;
                System.out.printf("%d' - GOAL by %s\n", e.getMinute(), goal.getPlayer().getName());
            } else if (e instanceof ShotEvent) {
                ShotEvent shot = (ShotEvent) e;
                System.out.printf("%d' - SHOT by %s\n", e.getMinute(), shot.getShooter().getName());
            } else if (e instanceof FoulEvent) {
                FoulEvent foul = (FoulEvent) e;
                System.out.printf("%d' - FOUL by %s\n", e.getMinute(), foul.getPlayer().getName());
            } else if (e instanceof CornerEvent) {
                CornerEvent corner = (CornerEvent) e;
                System.out.printf("%d' - CORNER by %s\n", e.getMinute(), corner.getTaker().getName());
            } else if (e instanceof GoalKickEvent) {
                GoalKickEvent gk = (GoalKickEvent) e;
                System.out.printf("%d' - GOAL KICK by %s\n", e.getMinute(), gk.getGoalkeeper().getName());
            } else {
                // fallback para eventos genéricos
                System.out.printf("%d' - %s\n", e.getMinute(), e.getDescription());
            }
        }

        int homeShots = 0;
        int awayShots = 0;
        int totalFouls = 0;

        for (int i = 0; i < nextMatch.getEventCount(); i++) {
            IEvent e = nextMatch.getEvents()[i];

            if (e instanceof ShotEvent || e instanceof IGoalEvent) {
                if (isFromTeam(e, nextMatch.getHomeTeam())) {
                    homeShots++;
                } else if (isFromTeam(e, nextMatch.getAwayTeam())) {
                    awayShots++;
                }
            }

            if (e.getDescription().startsWith("FOUL")) {
                totalFouls++;
            }
        }

        int totalShots = homeShots + awayShots;
        int homePossession = 50;
        int awayPossession = 50;
        if (totalShots > 0) {
            homePossession = (homeShots * 100) / totalShots;
            awayPossession = 100 - homePossession;
        }

        System.out.println("\n[---MATCH STATS---]");
        System.out.println("Shots: " + homeShots + " - " + awayShots);
        System.out.println("Ball Possesion: " + homePossession + "% - " + awayPossession + "%");
        System.out.println("Total Fouls: " + totalFouls);

    }

    private static void updateStandings(Match match, IStanding[] standings) {
        if (!match.isPlayed()) {
            return;
        }

        ITeam home = match.getHomeTeam();
        ITeam away = match.getAwayTeam();
        int homeGoals = match.getHomeGoals();
        int awayGoals = match.getAwayGoals();

        Standing homeStanding = null;
        Standing awayStanding = null;

        for (IStanding s : standings) {
            Standing standing = (Standing) s;
            if (standing.getTeam().getClub().equals(home.getClub())) {
                homeStanding = standing;
            } else if (standing.getTeam().getClub().equals(away.getClub())) {
                awayStanding = standing;
            }
        }

        if (homeStanding != null && awayStanding != null) {
            homeStanding.updateStats(homeGoals, awayGoals, 3, 1, 0);
            awayStanding.updateStats(awayGoals, homeGoals, 3, 1, 0);
        }
    }
}
