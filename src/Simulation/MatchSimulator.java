/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simulation;

import Enums.EPosition;
import Event.*;
import Match.Match;
import Player.Player;
import Team.Formation;
import Team.Team;
import com.ppstudios.footballmanager.api.contracts.event.*;
import com.ppstudios.footballmanager.api.contracts.match.*;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
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

        IPlayer[] homePlayers = getStartingPlayers(match.getHomeTeam());
        IPlayer[] awayPlayers = getStartingPlayers(match.getAwayTeam());

        int homeGoals = 0;
        int awayGoals = 0;

        IEvent[] events = new IEvent[90];
        int eventIndex = 0;

        for (int minute = 1; minute <= 90; minute += 5) {
            if (random.nextDouble() < 0.6) {
                boolean homeAttacking = random.nextBoolean();
                IPlayer[] attackers = homeAttacking ? homePlayers : awayPlayers;
                IPlayer[] defenders = homeAttacking ? awayPlayers : homePlayers;

                IPlayer attacker = getRandomAttacker(attackers);
                IPlayer defender = getRandomDefender(defenders);

                if (attacker == null) {
                    continue;
                }

                int shot = attacker.getShooting();
                int defense = defender != null ? defender.getStamina() + 20 : 50;

                if (shot > random.nextInt(shot + defense + 1)) {

                    if (homeAttacking) {
                        homeGoals++;
                    } else {
                        awayGoals++;
                    }

                    if (eventIndex < events.length) {
                        events[eventIndex++] = new GoalEvent(attacker, "GOAL", minute);
                    }
                } else {

                    IPlayer goalkeeper = getGoalkeeper(defenders);
                    if (eventIndex < events.length) {
                        events[eventIndex++] = new ShotEvent(attacker, goalkeeper, "SHOT", minute);
                    }
                }

                if (random.nextDouble() < 0.4 && eventIndex < events.length) {
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
        IEvent[] finalEvents = new IEvent[eventIndex];

        for (int i = 0; i < eventIndex; i++) {
            finalEvents[i] = events[i];
        }

        match.setEvents(finalEvents, eventIndex);

        match.setPlayed();

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
            return 1;
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

    private IPlayer[] getStartingPlayers(ITeam team) {
        IPlayer[] allPlayers = team.getPlayers();
        EPosition[] positions = ((Formation) team.getFormation()).getPositions();
        IPlayer[] starters = new IPlayer[positions.length];

        boolean[] used = new boolean[allPlayers.length];
        int count = 0;

        for (EPosition required : positions) {
            for (int i = 0; i < allPlayers.length; i++) {
                IPlayer p = allPlayers[i];
                if (!used[i] && p != null && p.getPosition() == required) {
                    starters[count++] = p;
                    used[i] = true;
                    break;
                }
            }
        }

        return starters;
    }

}
