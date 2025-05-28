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
public class MatchSimulator implements MatchSimulatorStrategy {

    private final Random random = new Random();

    @Override
    public void simulate(IMatch imatch) {
        Match match = (Match) imatch;

        IPlayer[] homePlayers = match.getHomeTeam().getPlayers();
        IPlayer[] awayPlayers = match.getAwayTeam().getPlayers();

        int homeGoals = 0;
        int awayGoals = 0;

        Random random = new Random();
        int numAttacks = 6 + random.nextInt(5); // entre 6 e 10 jogadas

        for (int i = 0; i < numAttacks; i++) {
            boolean homeAttacking = random.nextBoolean();

            IPlayer[] attackers;
            IPlayer[] defenders;

            if (homeAttacking) {
                attackers = homePlayers;
                defenders = awayPlayers;
            } else {
                attackers = awayPlayers;
                defenders = homePlayers;
            }

            IPlayer attacker = getRandomAttacker(attackers);
            int attackPower = attacker.getShooting() + attacker.getStamina();

            int defensePower = getTeamDefense(defenders);

            int chance = random.nextInt(attackPower + defensePower + 1);
            if (chance < attackPower) {
                if (homeAttacking) {
                    homeGoals++;
                } else {
                    awayGoals++;
                }
            }
        }

        match.setHomeGoals(homeGoals);
        match.setAwayGoals(awayGoals);
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

    private Player getGoalkeeper(Team team) {
        for (IPlayer p : team.getPlayers()) {
            if (p instanceof Player player && player.getPosition().toString().equals("GOALKEEPER")) {
                return player;
            }
        }
        return null;
    }
}
