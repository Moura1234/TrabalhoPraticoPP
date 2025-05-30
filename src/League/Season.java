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

import Enums.EPosition;
import com.ppstudios.footballmanager.api.contracts.league.*;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;
import java.io.IOException;
import Match.Match;
import Team.Formation;
import Team.Team;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.IFormation;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.io.FileWriter;

/**
 * Represents a football season, managing teams, schedule, simulation strategy,
 * and league standings. Provides methods to control the lifecycle of the league
 * from setup to simulation and results.
 */
public class Season implements ISeason {

    private String name;
    private int year;
    private int pointsPerWin;
    private int pointsPerDraw;
    private int pointsPerLoss;
    private int maxTeams;
    private int maxRounds;
    private int currentRound;
    private int currentMatches;
    private IClub[] currentClubs;
    private int clubCount;
    private int numberOfCurrentTeams;
    private ISchedule schedule;
    private IStanding[] leagueStandings;
    private MatchSimulatorStrategy simulatorStrategy;
    private League league;
    private ITeam[] teams;
    private ITeam userTeam;

    /**
     * Constructs a Season object with the specified configuration and initial
     * data.
     *
     * @param name The name of the season
     * @param year The year the season takes place
     * @param pointsPerWin Points awarded for a win
     * @param pointsPerDraw Points awarded for a draw
     * @param pointsPerLoss Points awarded (or deducted) for a loss
     * @param maxTeams Maximum number of teams in the league
     * @param maxRounds Maximum number of rounds to simulate
     * @param currentRound The round the league is currently on
     * @param currentMatches Number of matches simulated so far
     * @param currentClubs Array of current clubs in the league
     * @param clubCount Current number of clubs
     * @param numberOfCurrentTeams Number of active teams
     * @param schedule The schedule of matches
     * @param leagueStandings Array storing each club's standing
     * @param simulatorStrategy Strategy used to simulate matches
     * @param teams Array of teams participating in the league
     * @param userTeam The team controlled by the user
     */
    public Season(String name, int year, int pointsPerWin, int pointsPerDraw, int pointsPerLoss, int maxTeams, int maxRounds, int currentRound, int currentMatches, IClub[] currentClubs, int clubCount, int numberOfCurrentTeams, ISchedule schedule, IStanding[] leagueStandings, MatchSimulatorStrategy simulatorStrategy, ITeam[] teams, ITeam userTeam) {
        this.name = name;
        this.year = year;
        this.pointsPerWin = pointsPerWin;
        this.pointsPerDraw = pointsPerDraw;
        this.pointsPerLoss = pointsPerLoss;
        this.maxTeams = maxTeams;
        this.maxRounds = maxRounds;
        this.currentRound = currentRound;
        this.currentMatches = currentMatches;
        this.currentClubs = currentClubs;
        this.clubCount = clubCount;
        this.numberOfCurrentTeams = numberOfCurrentTeams;
        this.schedule = schedule;
        this.leagueStandings = leagueStandings;
        this.simulatorStrategy = simulatorStrategy;
        this.teams = teams;
        this.userTeam = userTeam;
    }

    /**
     * Gets the year in which the season takes place.
     *
     * @return The season year
     */
    @Override
    public int getYear() {
        return this.year;
    }

    /**
     * Adds a team to the league and updates the schedule.
     *
     * @param iclub The club to add
     * @return true if the club was added successfully, false if the club
     * already exists
     * @throws IllegalArgumentException if the club is null or already in the
     * league
     * @throws IllegalStateException if the league is full
     */
    @Override
    public boolean addClub(IClub iclub) {
        if (iclub == null) {
            throw new IllegalArgumentException("Club cannot be null.");
        }

        if (clubCount >= maxTeams) {
            throw new IllegalStateException("League is full. Cannot add more clubs.");
        }

        for (int i = 0; i < clubCount; i++) {
            if (currentClubs[i].equals(iclub)) {
                throw new IllegalArgumentException("Club is already in the league.");
            }
        }

        currentClubs[clubCount] = iclub;
        leagueStandings[clubCount] = null;
        clubCount++;
        numberOfCurrentTeams++;

        return true;
    }

    /**
     * Removes a team from the league and updates the schedule.
     *
     * @param iclub The club to remove
     * @return true if the club was removed successfully
     * @throws IllegalArgumentException if the club is null
     * @throws IllegalStateException if the club is not in the league
     */
    @Override
    public boolean removeClub(IClub iclub) {
        if (iclub == null) {
            throw new IllegalArgumentException("Club cannot be null.");
        }

        for (int i = 0; i < clubCount; i++) {
            if (currentClubs[i].equals(iclub)) {
                for (int j = i; j < clubCount - 1; j++) {
                    currentClubs[j] = currentClubs[j + 1];
                    leagueStandings[j] = leagueStandings[j + 1];
                }

                currentClubs[--clubCount] = null;
                leagueStandings[clubCount] = null;
                numberOfCurrentTeams--;
                return true;
            }
        }

        throw new IllegalStateException("Club is not in the league.");
    }

    /**
     * Generates a schedule for the league. A schedule is a set of matches that
     * determine the order in which teams will play against each other.
     *
     * @throws IllegalStateException if the league is empty or if a schedule
     * already exists
     */
    @Override
    public void generateSchedule() {
        if (clubCount < 2) {
            throw new IllegalStateException("Insufficient number of clubs to generate a schedule.");
        }
        if (this.schedule != null) {
            throw new IllegalStateException("Schedule has already been generated.");
        }
        maxRounds = (clubCount - 1) * 2;
        int matchesPerRound = clubCount / 2;
        IMatch[][] matches = new IMatch[maxRounds][matchesPerRound];

        for (int round = 0; round < maxRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int homeIndex = (round + match) % (clubCount - 1);
                int awayIndex = (clubCount - 1 - match + round) % (clubCount - 1);
                if (match == 0) {
                    awayIndex = clubCount - 1;
                }
                IClub homeClub, awayClub;
                int homeIndexEffective, awayIndexEffective;

                if (round < maxRounds / 2) {
                    homeClub = currentClubs[homeIndex];
                    awayClub = currentClubs[awayIndex];
                    homeIndexEffective = homeIndex;
                    awayIndexEffective = awayIndex;
                } else {

                    homeClub = currentClubs[awayIndex];
                    awayClub = currentClubs[homeIndex];
                    homeIndexEffective = awayIndex;
                    awayIndexEffective = homeIndex;
                }

                Formation formation = Formation.create433();
                IPlayer[] homePlayers = getStartingPlayers(homeClub, formation);
                IPlayer[] awayPlayers = getStartingPlayers(awayClub, formation);

                ITeam homeTeam = new Team(100, formation, formation.getPositions().length, homeClub, homePlayers, homeIndex);
                ITeam awayTeam = new Team(100, formation, formation.getPositions().length, awayClub, awayPlayers, awayIndex);

                Match game = new Match(
                        homeClub,
                        awayClub,
                        false,
                        homeTeam,
                        awayTeam,
                        0,
                        0,
                        Enums.EFormation.HomeFormation,
                        Enums.EFormation.AwayFormation,
                        round,
                        null,
                        0
                );
                matches[round][match] = game;
            }
        }
        this.schedule = new Schedule(matches);
    }

    private IPlayer[] getStartingPlayers(IClub club, Formation formation) {
        EPosition[] positions = formation.getPositions();
        IPlayer[] allPlayers = club.getPlayers();
        IPlayer[] starting11 = new IPlayer[positions.length];
        boolean[] used = new boolean[allPlayers.length];

        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < allPlayers.length; j++) {
                if (!used[j] && allPlayers[j] != null && allPlayers[j].getPosition() == positions[i]) {
                    starting11[i] = allPlayers[j];
                    used[j] = true;
                    break;
                }
            }
        }
        return starting11;
    }

    /**
     * Retrieves all matches from the season schedule.
     *
     * @return Array of all matches, or empty array if schedule is not available
     */
    @Override
    public IMatch[] getMatches() {
        if (schedule != null) {
            return schedule.getAllMatches();
        } else {
            return new IMatch[0];
        }
    }

    /**
     * Retrieves all matches for the specified round.
     *
     * @param i The round index
     * @return Array of matches in that round, or empty array if schedule is not
     * available
     */
    @Override
    public IMatch[] getMatches(int i) {
        if (schedule != null) {
            return schedule.getMatchesForRound(i);
        } else {
            return new IMatch[0];
        }
    }

    /**
     * Simulates a round of matches.
     *
     * @throws IllegalStateException if the league is empty or not scheduled
     */
    @Override
    public void simulateRound() {
        if (clubCount == 0) {
            throw new IllegalStateException("Cannot simulate round: league is empty.");
        }

        if (this.schedule == null) {
            throw new IllegalStateException("Cannot simulate round: league has no schedule.");
        }

        if (currentRound < maxRounds) {
            IMatch[] roundMatches = schedule.getMatchesForRound(currentRound);

            if (simulatorStrategy != null) {
                for (IMatch match : roundMatches) {
                    if (match == null) {
                        System.out.println("Match null!");
                        continue;
                    }

                    if (match.getHomeClub() == null || match.getAwayClub() == null) {
                        System.out.println("Match with null club on matchday " + currentRound);
                        System.out.println(" Home: " + match.getHomeClub());
                        System.out.println(" Away: " + match.getAwayClub());
                        continue;
                    }

                    prepareTeamsFor((Match) match);
                    simulatorStrategy.simulate(match);

                    Match realMatch = (Match) match;
                    if (realMatch.isPlayed()) {
                        Team home = (Team) realMatch.getHomeTeam();
                        Team away = (Team) realMatch.getAwayTeam();

                        int homeGoals = realMatch.getHomeGoals();
                        int awayGoals = realMatch.getAwayGoals();

                        Standing homeStanding = (Standing) leagueStandings[home.getId()];
                        Standing awayStanding = (Standing) leagueStandings[away.getId()];

                        homeStanding.updateStats(homeGoals, awayGoals, 3, 1, 0);
                        awayStanding.updateStats(awayGoals, homeGoals, 3, 1, 0);
                    }
                }
            }

            currentRound++;
            currentMatches++;
        }
    }

    /**
     * Prepares both home and away teams for the given match by assigning team
     * objects with players, formations, and club information.
     *
     * @param match The match for which teams will be prepared
     */
    private void prepareTeamsFor(Match match) {
        IClub home = match.getHomeClub();
        IClub away = match.getAwayClub();

        IPlayer[] homePlayers = home.getPlayers();
        IPlayer[] awayPlayers = away.getPlayers();

        Formation formation = Formation.create433();

        int homeId = findClubIndex(home);
        int awayId = findClubIndex(away);

        ITeam homeTeam = new Team(100, formation, formation.getPositions().length, home, homePlayers, homeId);
        ITeam awayTeam = new Team(100, formation, formation.getPositions().length, away, awayPlayers, awayId);

        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
    }

    /**
     * Finds the index of the given club in the currentClubs array.
     *
     * @param club The club to search for
     * @return The index of the club in the array, or -1 if not found
     */
    private int findClubIndex(IClub club) {
        for (int i = 0; i < currentClubs.length; i++) {
            if (currentClubs[i] == club) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Simulates a full season of matches by executing each round sequentially.
     *
     * @throws IllegalStateException if the league is empty or not scheduled
     */
    @Override
    public void simulateSeason() {
        if (clubCount == 0) {
            throw new IllegalStateException("Cannot simulate season: league is empty.");
        }

        if (this.schedule == null) {
            throw new IllegalStateException("Cannot simulate season: league has no schedule.");
        }

        while (!isSeasonComplete()) {
            simulateRound();
        }
    }

    /**
     * Gets the index of the current round being simulated.
     *
     * @return The current round number
     */
    @Override
    public int getCurrentRound() {
        return this.currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    /**
     * Checks whether the season has completed all scheduled rounds.
     *
     * @return true if the season is complete, false otherwise
     */
    @Override
    public boolean isSeasonComplete() {
        return currentRound >= maxRounds;
    }

    /**
     * Resets the season to its initial state, clearing standings and schedule.
     */
    @Override
    public void resetSeason() {
        currentRound = 0;
        currentMatches = 0;
        schedule = null;
        for (int i = 0; i < leagueStandings.length; i++) {
            leagueStandings[i] = null;
        }
    }

    /**
     * Returns a string representation of the match result.
     *
     * @param imatch The match to display
     * @return A formatted string with match result, or a fallback message if
     * unavailable
     */
    @Override
    public String displayMatchResult(IMatch imatch) {
        if (imatch instanceof Match m) {
            return m.getHomeClub().getName() + " " + m.getHomeGoals() + " - "
                    + m.getAwayGoals() + " " + m.getAwayClub().getName();
        }
        return "Resultado indisponível";
    }

    /**
     * Sets the strategy used to simulate matches in the league.
     *
     * @param mss The match simulation strategy to apply
     */
    @Override
    public void setMatchSimulator(MatchSimulatorStrategy mss) {
        this.simulatorStrategy = mss;
    }

    /**
     * Gets the current league standings for all clubs in the season.
     *
     * @return An array of standings, one for each club
     */
    @Override
    public IStanding[] getLeagueStandings() {
        return this.leagueStandings;
    }

    /**
     * Gets the schedule of matches for the season.
     *
     * @return The season's match schedule
     */
    @Override
    public ISchedule getSchedule() {
        return this.schedule;
    }

    /**
     * Gets the number of points awarded for a win.
     *
     * @return The number of points awarded for a win
     * @throws IllegalStateException if the match simulator is not initialized
     */
    @Override
    public int getPointsPerWin() {
        if (this.simulatorStrategy == null) {
            throw new IllegalStateException("Match simulator is not initialized.");
        }
        return this.pointsPerWin;
    }

    /**
     * Gets the number of points awarded for a draw.
     *
     * @return The number of points awarded for a draw
     */
    @Override
    public int getPointsPerDraw() {
        return this.pointsPerDraw;
    }

    /**
     * Gets the number of points for a loss.
     *
     * @return The number of points awarded or deducted for a loss
     * @throws IllegalStateException if the match simulator is not initialized
     */
    @Override
    public int getPointsPerLoss() {
        if (this.simulatorStrategy == null) {
            throw new IllegalStateException("Match simulator is not initialized.");
        }
        return this.pointsPerLoss;
    }

    /**
     * Gets the name of the season.
     *
     * @return The season name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the maximum number of teams allowed in the league.
     *
     * @return Maximum number of teams
     */
    @Override
    public int getMaxTeams() {
        return this.maxTeams;
    }

    /**
     * Gets the maximum number of rounds to be played in the season.
     *
     * @return Maximum number of rounds
     */
    @Override
    public int getMaxRounds() {
        return this.maxRounds;
    }

    /**
     * Gets the current number of matches that have been simulated.
     *
     * @return The number of matches played so far
     */
    @Override
    public int getCurrentMatches() {
        return this.currentMatches;
    }

    /**
     * Gets the current number of teams in the league.
     *
     * @return The number of active teams
     */
    @Override
    public int getNumberOfCurrentTeams() {
        return this.numberOfCurrentTeams;
    }

    /**
     * Gets the array of current clubs participating in the league.
     *
     * @return Array of clubs currently in the league
     */
    @Override
    public IClub[] getCurrentClubs() {
        return this.currentClubs;
    }

    /**
     * Sets the array of current clubs in the league.
     *
     * @param clubs Array of clubs to assign
     */
    public void setCurrentClubs(IClub[] clubs) {
        this.currentClubs = clubs;
    }

    /**
     * Sets the number of clubs currently in the league.
     *
     * @param count Number of clubs
     */
    public void setClubCount(int count) {
        this.clubCount = count;
    }

    /**
     * Gets the league instance associated with this season.
     *
     * @return The League object
     */
    public League getLeague() {
        return this.league;
    }

    /**
     * Gets the array of all teams in the league.
     *
     * @return Array of teams
     */
    public ITeam[] getTeams() {
        return this.teams;
    }

    /**
     * Gets the team controlled by the user.
     *
     * @return The user's team
     */
    public ITeam getUserTeam() {
        return this.userTeam;
    }

    /**
     * Sets the team to be controlled by the user.
     *
     * @param team The user's selected team
     */
    public void setUserTeam(ITeam team) {
        this.userTeam = team;
    }

    /**
     * Retrieves the next unplayed match for the specified team.
     *
     * @param team The team to find the next match for
     * @return The next unplayed Match, or null if none remain
     */
    public Match getNextMatchForTeam(ITeam team) {
        if (schedule == null) {
            return null;
        }

        for (int round = 0; round < schedule.getNumberOfRounds(); round++) {
            IMatch[] matches = schedule.getMatchesForRound(round);
            for (IMatch match : matches) {
                if (match == null) {
                    continue;
                }
                Match realMatch = (Match) match;

                if (!realMatch.isPlayed()
                        && (realMatch.getHomeTeam().equals(team) || realMatch.getAwayTeam().equals(team))) {
                    return realMatch;
                }
            }
        }
        return null;
    }

    /**
     * Exports the season data to a JSON file.
     *
     * @throws IOException If file writing fails
     * @throws UnsupportedOperationException If method is not implemented
     */
    @Override
    public void exportToJson() throws IOException {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"competition\": \"" + name + "\",\n");
        json.append("  \"year\": " + year + ",\n");

        json.append("  \"teams\": [\n");
        for (int i = 0; i < teams.length; i++) {
            ITeam t = teams[i];
            if (t != null) {
                json.append("    {\n");
                json.append("      \"name\": \"" + t.getClub().getName() + "\"\n");
                json.append("    }");
                if (i < teams.length - 1) {
                    json.append(",");
                }
                json.append("\n");
            }
        }
        json.append("  ],\n");

        json.append("  \"rounds\": [\n");
        for (int r = 0; r < schedule.getNumberOfRounds(); r++) {
            IMatch[] matches = schedule.getMatchesForRound(r);
            json.append("    {\n");
            json.append("      \"round\": " + (r + 1) + ",\n");
            json.append("      \"matches\": [\n");

            for (int m = 0; m < matches.length; m++) {
                Match match = (Match) matches[m];
                json.append("        {\n");
                json.append("          \"home\": \"" + match.getHomeTeam().getClub().getName() + "\",\n");
                json.append("          \"away\": \"" + match.getAwayTeam().getClub().getName() + "\",\n");
                json.append("          \"score\": \"" + match.getHomeGoals() + " - " + match.getAwayGoals() + "\"\n");
                json.append("        }");
                if (m < matches.length - 1) {
                    json.append(",");
                }
                json.append("\n");
            }

            json.append("      ]\n");
            json.append("    }");
            if (r < schedule.getNumberOfRounds() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("  ]\n");

        json.append("}");

        FileWriter writer = new FileWriter("season_export.json");
        writer.write(json.toString());
        writer.close();

        System.out.println("Season export completed: season_export.json");
    }

}
