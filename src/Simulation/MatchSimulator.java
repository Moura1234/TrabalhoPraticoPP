/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simulation;
import Event.GoalEvent;
import Match.Match;
import Player.Player;
import Team.Team;
import com.ppstudios.footballmanager.api.contracts.event.*;
import com.ppstudios.footballmanager.api.contracts.match.*;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;
import java.util.Random;

/**
 *
 * @author utilizador
 */
public class MatchSimulator implements MatchSimulatorStrategy{


    private final Random random = new Random();

    @Override
    public void simulate(IMatch imatch) {
        if (imatch == null) throw new IllegalArgumentException("Match is null.");
        if (!(imatch instanceof Match)) throw new IllegalStateException("Invalid match implementation.");
        Match match = (Match) imatch;

        if (match.isPlayed()) throw new IllegalStateException("Match already played.");
        if (match.getHomeTeam() == null || match.getAwayTeam() == null)
            throw new IllegalStateException("Teams not initialized.");

        Team home = (Team) match.getHomeTeam();
        Team away = (Team) match.getAwayTeam();

        IEvent[] events = new IEvent[90];
        int eventCount = 0;

        for (int minute = 0; minute < 90; minute++) {
            if (random.nextDouble() < 0.2) {
                boolean isHomeAttack = random.nextBoolean();
                Team attacking = isHomeAttack ? home : away;
                Team defending = isHomeAttack ? away : home;

                Player attacker = getRandomAttacker(attacking);
                Player goalkeeper = getGoalkeeper(defending);

                if (attacker != null && goalkeeper != null) {
                    boolean goal = attacker.getShooting() > goalkeeper.getDefense() &&
                                   random.nextDouble() < 0.5;

                    if (goal) {
                        GoalEvent goalEvent = new GoalEvent(attacker.getName(), attacking.getClub().getName(), minute);
                        events[eventCount++] = goalEvent;

                        if (isHomeAttack) {
                            match.setHomeGoals(match.getHomeGoals() + 1);
                        } else {
                            match.setAwayGoals(match.getAwayGoals() + 1);
                        }
                    }
                }
            }
        }

        match.setEvents(events);
        match.setEventCount(eventCount);
        match.setPlayed();  // marca o jogo como já jogado
}
    
    private Player getRandomAttacker(Team team) {
    Player[] attackers = new Player[team.getPlayerCount()];
    int count = 0;

    for (IPlayer p : team.getPlayers()) {
        if (p instanceof Player player) {
            if (player.getPosition().toString().contains("FORWARD")) {
                attackers[count] = player;
                count++;
            }
        }
    }

    if (count > 0) {
        int index = random.nextInt(count);
        return attackers[index];
    } else {
        return null;
    }
}
    
    private Player getGoalkeeper(Team team) {
        for (IPlayer p : team.getPlayers()) {
            if (p instanceof Player player && player.getPosition().toString().equals("GOALKEEPER")) {
                return player;
            }
        }
        return null;
    }
}

