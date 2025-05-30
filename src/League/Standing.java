
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

import com.ppstudios.footballmanager.api.contracts.league.*;
import com.ppstudios.footballmanager.api.contracts.team.*;

/**
 * Constructs a new Standing object for a given team. Initializes all
 * statistics (points, results, goals and matches) to zero.
 * 
 */
public class Standing implements IStanding {

    private ITeam team;
    private int points;
    private int win;
    private int draw;
    private int loss;
    private int goalsScored;
    private int goalsConceded;
    private int totalMatches;

    /**
     * Constructs a new Standing object for a given team. Initializes all
     * statistics (points, results, goals and matches) to zero.
     *
     * @param team The team associated with this standing
     */
    public Standing(ITeam team) {
        this.team = team;
        this.points = 0;
        this.win = 0;
        this.draw = 0;
        this.loss = 0;
        this.goalsScored = 0;
        this.goalsConceded = 0;
        this.totalMatches = 0;
    }

    /**
     * Gets the team associated with this standing.
     *
     * @return The team
     * @throws IllegalStateException if the team is not initialized
     */
    @Override
    public ITeam getTeam() {
        if (this.team == null) {
            throw new IllegalStateException("Team is not initialized.");
        }
        return this.team;
    }

    /**
     * Gets the total points of the team.
     *
     * @return The total points
     * @throws IllegalStateException if the points are not initialized
     */
    @Override
    public int getPoints() {
        if (this.points < 0) {
            throw new IllegalStateException("Points are not initialized.");
        }
        return this.points;
    }

    /**
     * Gets the total number of wins.
     *
     * @return The total number of wins
     * @throws IllegalStateException if the wins are not initialized
     */
    @Override
    public int getWins() {
        if (this.win < 0) {
            throw new IllegalStateException("Wins are not initialized.");
        }
        return this.win;
    }

    /**
     * Gets the total number of draws.
     *
     * @return The total number of draws
     * @throws IllegalStateException if the draws are not initialized
     */
    @Override
    public int getDraws() {
        if (this.draw < 0) {
            throw new IllegalStateException("Draws are not initialized.");
        }
        return this.draw;
    }

    /**
     * Gets the total number of losses.
     *
     * @return The total number of losses
     * @throws IllegalStateException if the losses are not initialized
     */
    @Override
    public int getLosses() {
        if (this.loss < 0) {
            throw new IllegalStateException("Losses are not initialized.");
        }
        return this.loss;
    }

    /**
     * Gets the total number of goals scored by the team.
     *
     * @return The total number of goals scored
     * @throws IllegalStateException if the goals scored are not initialized
     */
    @Override
    public int getGoalScored() {
        if (this.goalsScored < 0) {
            throw new IllegalStateException("Goals scored are not initialized.");
        }
        return this.goalsScored;
    }

    /**
     * Gets the total number of goals conceded by the team.
     *
     * @return The total number of goals conceded
     * @throws IllegalStateException if the goals conceded are not initialized
     */
    @Override
    public int getGoalsConceded() {
        if (this.goalsConceded < 0) {
            throw new IllegalStateException("Goals conceded are not initialized.");
        }
        return this.goalsConceded;
    }

    /**
     * Gets the total number of matches played.
     *
     * @return The total number of matches
     * @throws IllegalStateException if the matches are not initialized
     */
    @Override
    public int getTotalMatches() {
        if (this.totalMatches < 0) {
            throw new IllegalStateException("Total matches are not initialized.");
        }
        return this.totalMatches;
    }

    /**
     * Gets the goal difference of the team.
     *
     * @return The goal difference
     * @throws IllegalStateException if the goal difference cannot be computed
     * due to uninitialized values
     */
    @Override
    public int getGoalDifference() {
        if (this.goalsScored < 0 || this.goalsConceded < 0) {
            throw new IllegalStateException("Goal difference cannot be calculated: uninitialized values.");
        }
        return this.goalsScored - this.goalsConceded;
    }

    /**
     * Adds points to the team's total.
     *
     * @param points The points to add
     * @throws IllegalArgumentException if points is negative
     */
    @Override
    public void addPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points to add cannot be negative.");
        }
        this.points += points;
    }

    /**
     * Adds a win to the team's record.
     *
     * @param pointsPerWin The points awarded for a win
     * @throws IllegalArgumentException if pointsPerWin is negative
     */
    @Override
    public void addWin(int pointsPerWin) {
        if (pointsPerWin < 0) {
            throw new IllegalArgumentException("Points per win cannot be negative.");
        }

        win++;
        totalMatches++;
        points += pointsPerWin;
    }

    /**
     * Adds a draw to the team's record.
     *
     * @param pointsPerDraw The points awarded for a draw
     * @throws IllegalArgumentException if pointsPerDraw is negative
     */
    @Override
    public void addDraw(int pointsPerDraw) {
        if (pointsPerDraw < 0) {
            throw new IllegalArgumentException("Points per draw cannot be negative.");
        }

        draw++;
        totalMatches++;
        points += pointsPerDraw;
    }

    /**
     * Adds a loss to the team's record.
     *
     * @param pointsPerLoss The points awarded for a loss
     * @throws IllegalArgumentException if pointsPerLoss is negative
     */
    @Override
    public void addLoss(int pointsPerLoss) {
        if (pointsPerLoss < 0) {
            throw new IllegalArgumentException("Points per loss cannot be negative.");
        }

        loss++;
        totalMatches++;
        points += pointsPerLoss;
    }

    public void updateStats(int goalsScored, int goalsConceded, int pointsPerWin, int pointsPerDraw, int pointsPerLoss) {
        this.goalsScored += goalsScored;
        this.goalsConceded += goalsConceded;

        if (goalsScored > goalsConceded) {
            addWin(pointsPerWin);
        } else if (goalsScored == goalsConceded) {
            addDraw(pointsPerDraw);
        } else {
            addLoss(pointsPerLoss);
        }
    }

}
