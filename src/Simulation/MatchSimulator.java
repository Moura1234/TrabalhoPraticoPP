/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simulation;

import Event.*;
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
public class MatchSimulator implements MatchSimulatorStrategy {

    private final Random random = new Random();

    @Override
    public void simulate(IMatch imatch) {
         Match match = (Match) imatch;

    IPlayer[] homePlayers = match.getHomeTeam().getPlayers();
    IPlayer[] awayPlayers = match.getAwayTeam().getPlayers();

    int homeGoals = 0;
    int awayGoals = 0;

    IEvent[] events = new IEvent[90]; // máx 1 evento por minuto (podes ajustar)
    int eventIndex = 0;

    for (int minute = 1; minute <= 90; minute += 5) {
        if (random.nextDouble() < 0.6) { // 60% de chance de evento a cada 5 min
            boolean homeAttacking = random.nextBoolean();
            IPlayer[] attackers = homeAttacking ? homePlayers : awayPlayers;
            IPlayer[] defenders = homeAttacking ? awayPlayers : homePlayers;

            IPlayer attacker = getRandomAttacker(attackers);
            IPlayer defender = getRandomDefender(defenders);

            if (attacker == null) continue;

            int shot = attacker.getShooting();
            int defense = defender != null ? defender.getStamina() : 30;

            if (shot > random.nextInt(shot + defense + 1)) {
                // Golo
                if (homeAttacking) homeGoals++;
                else awayGoals++;

                if (eventIndex < events.length) {
                    events[eventIndex++] = new GoalEvent(attacker, "GOAL", minute);
                }
            } else {
                
                // Remate falhado
                IPlayer goalkeeper = getGoalkeeper(defenders);
                if (eventIndex < events.length) {
                    events[eventIndex++] = new ShotEvent(attacker, goalkeeper, "SHOT", minute);
                }
            }

            // Eventos adicionais
            if (random.nextDouble() < 0.1 && eventIndex < events.length) {
                events[eventIndex++] = new FoulEvent(attacker, "FOUL", minute);
            }

            if (random.nextDouble() < 0.05 && eventIndex < events.length) {
                events[eventIndex++] = new CornerEvent(attacker, "CORNER", minute);
            }

            if (random.nextDouble() < 0.03 && eventIndex < events.length) {
                events[eventIndex++] = new GoalKickEvent(defender, "GOALKICK", minute);
            }
        }
    }

        match.setHomeGoals(homeGoals);
        match.setAwayGoals(awayGoals);
        match.setPlayed();
        match.setEvents (events);

        System.out.println(
                match.getHomeTeam().getClub().getName() + " "
                + homeGoals + " - " + awayGoals + " "
                + match.getAwayTeam().getClub().getName()
        );
    }

    private IPlayer getRandomAttacker(IPlayer[] players) {
        Player[] attackers = new Player[players.length];
        int count = 0;

        for (IPlayer p : players) {
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
    
   private IPlayer getRandomDefender(IPlayer[] players) {
    for (IPlayer p : players) {
        if (p != null && p.getPosition().toString().contains("DEFENDER")) {
            return p;
        }
    }
    return players[random.nextInt(players.length)];
}
 

    private int getTeamDefense(IPlayer[] players) {
        int total = 0;
        int count = 0;

        for (IPlayer p : players) {
            if (p != null && p.getPosition() != null && p instanceof Player) {
                Player realPlayer = (Player) p;
                total += realPlayer.getDefense() + realPlayer.getStamina();
                count++;
            }
        }

        if (count == 0) {
            return 1; // evitar divisão por zero
        }
        return total / count;
    }

    private Player getGoalkeeper(IPlayer[] players) {
        for (IPlayer p : players) {
            if (p instanceof Player player && player.getPosition().toString().equals("GOALKEEPER")) {
                return player;
            }
        }
        return null;
    }
}
