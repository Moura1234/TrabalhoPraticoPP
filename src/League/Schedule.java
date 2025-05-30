/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */
package League;

import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.league.ISchedule;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.io.IOException;

/**
 * Represents a match schedule for a league season. Organizes matches by round.
 * Implements the ISchedule interface.
 */
public class Schedule implements ISchedule {

    private final IMatch[][] rounds;

    /**
     * Constructs a schedule with the given rounds of matches.
     *
     * @param rounds An array of matches organized by rounds
     */
    public Schedule(IMatch[][] rounds) {
        this.rounds = rounds;
    }

    /**
     * Gets all matches for a specific round.
     *
     * @param round The round number
     * @return Array of matches for the specified round
     * @throws IllegalArgumentException if the round number is invalid
     * @throws IllegalStateException if the schedule or the round is not
     * properly initialized
     */
    @Override
    public IMatch[] getMatchesForRound(int round) {
        if (rounds == null) {
            throw new IllegalStateException("Schedule is not initialized.");
        }

        if (round < 0 || round >= rounds.length) {
            throw new IllegalArgumentException("Invalid round number: " + round);
        }

        if (rounds[round] == null) {
            throw new IllegalStateException("Matches for round " + round + " are not set.");
        }

        return rounds[round];
    }

    /**
     * Gets all matches for a specific team.
     *
     * @param team The team to filter matches by
     * @return Array of matches involving the specified team
     * @throws IllegalArgumentException if the team is null
     * @throws IllegalStateException if the schedule or matches are not
     * initialized
     */
    @Override
    public IMatch[] getMatchesForTeam(ITeam team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null.");
        }

        if (rounds == null) {
            throw new IllegalStateException("Schedule is not initialized.");
        }

        int total = 0;

        for (IMatch[] round : rounds) {
            if (round == null) {
                throw new IllegalStateException("Match data for a round is not set.");
            }
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

    /**
     * Gets the total number of rounds in the schedule.
     *
     * @return Number of rounds
     */
    @Override
    public int getNumberOfRounds() {
        return rounds.length;
    }

    /**
     * Gets all matches in the schedule.
     *
     * @return A flat array (copy) of all matches from all rounds
     * @throws IllegalStateException if the schedule is not initialized
     */
    @Override
    public IMatch[] getAllMatches() {
        if (rounds == null) {
            throw new IllegalStateException("Schedule is not initialized.");
        }

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

    /**
     * Sets the given team for all matches in the specified round.
     *
     * @param team The team to assign to matches
     * @param roundIndex The index of the round
     * @throws IllegalArgumentException if the team is null or the round is
     * invalid
     * @throws IllegalStateException if the schedule or round data is not
     * initialized, or if the clubs in the team are not part of the league
     */
    @Override
    public void setTeam(ITeam team, int roundIndex) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null.");
        }

        if (rounds == null) {
            throw new IllegalStateException("Schedule is not initialized.");
        }

        if (roundIndex < 0 || roundIndex >= rounds.length) {
            throw new IllegalArgumentException("Invalid round index.");
        }

        if (rounds[roundIndex] == null) {
            throw new IllegalStateException("Matches for round " + roundIndex + " are not set.");
        }

        IClub club = team.getClub();
        if (club == null || club.getPlayers() == null) {
            throw new IllegalStateException("Club or players in the team are not initialized.");
        }

        for (IMatch match : rounds[roundIndex]) {
            match.setTeam(team);
        }
    }

    /**
     * Exports the schedule to a JSON file.
     *
     * @throws IOException if writing to the file fails
     * @throws UnsupportedOperationException if the method is not implemented
     */
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
