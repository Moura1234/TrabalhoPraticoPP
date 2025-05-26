/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package League;

import com.ppstudios.footballmanager.api.contracts.league.ISchedule;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.io.IOException;

/**
 *
 * @author joaom
 */
public class Schedule implements ISchedule {

    private IMatch[][] rounds; 

    public Schedule(IMatch[][] rounds) {
        this.rounds = rounds;
    }

    @Override
    public IMatch[] getMatchesForRound(int i) {
        if (i >= 0 && i < rounds.length) {
            return rounds[i];
        }
        return new IMatch[0];
    }

    @Override
    public IMatch[] getMatchesForTeam(ITeam team) {
        int total = 0;

        // método que conta quantos jogos existem
        for (IMatch[] round : rounds) {
            for (IMatch match : round) {
                if (match.getHomeTeam().equals(team) || match.getAwayTeam().equals(team)) {
                    total++;
                }
            }
        }

        IMatch[] matches = new IMatch[total];
        int index = 0;

        for (IMatch[] round : rounds) {
            for (IMatch match : round) {
                if (match.getHomeTeam().equals(team) || match.getAwayTeam().equals(team)) {
                    matches[index++] = match;
                }
            }
        }

        return matches;
    }

    @Override
    public int getNumberOfRounds() {
        return rounds.length;
    }

    @Override
    public IMatch[] getAllMatches() {
        int total = 0;
        for (IMatch[] round : rounds) {
            total += round.length;
        }

        IMatch[] all = new IMatch[total];
        int index = 0;
        for (IMatch[] round : rounds) {
            for (IMatch match : round) {
                all[index++] = match;
            }
        }
        return all;
    }

    @Override
    public void setTeam(ITeam team, int roundIndex) {
        if (roundIndex < 0 || roundIndex >= rounds.length) {
            return;
        }

        for (IMatch match : rounds[roundIndex]) {
            match.setTeam(team);
        }
    }
    
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
