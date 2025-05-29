/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package League;

import Enums.Position;
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

/**
 *
 * @author joaom
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

        // Verifica se já há calendário
        if (this.schedule != null) {
            throw new IllegalStateException("Schedule has already been generated.");
        }

        int totalRounds = clubCount - 1;
        int matchesPerRound = clubCount / 2;
        IMatch[][] matches = new IMatch[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int homeIndex = (round + match) % (clubCount - 1);
                int awayIndex = (clubCount - 1 - match + round) % (clubCount - 1);

                if (match == 0) {
                    awayIndex = clubCount - 1;
                }

                IClub homeClub = currentClubs[homeIndex];
                IClub awayClub = currentClubs[awayIndex];

                IPlayer[] homePlayers = homeClub.getPlayers();
                IPlayer[] awayPlayers = awayClub.getPlayers();

                Formation formation = Formation.create433();
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

    // @Override
//    public void generateSchedule() {
//
//        if (clubCount < 2) {
//            throw new IllegalStateException("Número insuficiente de clubes para gerar calendário.");
//        }
//
//        int totalRounds = clubCount - 1;
//        int matchesPerRound = clubCount / 2;
//        IMatch[][] matches = new IMatch[totalRounds][matchesPerRound];
//
//        for (int round = 0; round < totalRounds; round++) {
//            for (int match = 0; match < matchesPerRound; match++) {
//                int homeIndex = (round + match) % (clubCount - 1);
//                int awayIndex = (clubCount - 1 - match + round) % (clubCount - 1);
//
//                if (match == 0) {
//                    awayIndex = clubCount - 1;
//                }
//
//                IClub homeClub = currentClubs[homeIndex];
//                IClub awayClub = currentClubs[awayIndex];
//
//
//                // Criação do jogo sem resultado nem equipas montadas ainda
//                IMatch game = new Match(
//                        homeClub,
//                        awayClub,
//                        round // apenas jornada marcada
//                );
//
//                matches[round][match] = game;
//            }
//        }
//
//        this.schedule = new Schedule(matches); // guarda o calendário completo
//    }
    @Override
    public IMatch[] getMatches() {
        if (schedule != null) {
            return schedule.getAllMatches();
        } else {
            return new IMatch[0];
        }
    }

    @Override
    public IMatch[] getMatches(int i) {
        if (schedule != null) {
            return schedule.getMatchesForRound(i);
        } else {
            return new IMatch[0];
        }
    }

    @Override
    public void simulateRound() {
        if (currentRound < maxRounds) {
            IMatch[] roundMatches = schedule.getMatchesForRound(currentRound);

            if (simulatorStrategy != null) {
                for (IMatch match : roundMatches) {
                    if (match == null) {
                        System.out.println(" Match nulo!");
                        continue;
                    }

                    if (match.getHomeClub() == null || match.getAwayClub() == null) {
                        System.out.println(" Match com clube nulo na jornada " + currentRound);
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

    private void prepareTeamsFor(Match match) {
        IClub home = match.getHomeClub();
        IClub away = match.getAwayClub();

        IPlayer[] homePlayers = home.getPlayers();
        IPlayer[] awayPlayers = away.getPlayers();

        Formation formation = Formation.create433(); // ou outra, fixa ou aleatória

        int homeId = findClubIndex(home);
        int awayId = findClubIndex(away);

        ITeam homeTeam = new Team(100, formation, formation.getPositions().length, home, homePlayers, homeId);
        ITeam awayTeam = new Team(100, formation, formation.getPositions().length, away, awayPlayers, awayId);

        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
    }

    private int findClubIndex(IClub club) {
        for (int i = 0; i < currentClubs.length; i++) {
            if (currentClubs[i] == club) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void simulateSeason() {
        while (!isSeasonComplete()) {
            simulateRound();
        }
    }

    @Override
    public int getCurrentRound() {
        return this.currentRound;
    }

    @Override
    public boolean isSeasonComplete() {
        return currentRound >= maxRounds;
    }

    @Override
    public void resetSeason() {
        currentRound = 0;
        currentMatches = 0;
        schedule = null;
        for (int i = 0; i < leagueStandings.length; i++) {
            leagueStandings[i] = null;
        }
    }

    @Override
    public String displayMatchResult(IMatch imatch) {
        if (imatch instanceof Match m) {
            return m.getHomeClub().getName() + " " + m.getHomeGoals() + " - "
                    + m.getAwayGoals() + " " + m.getAwayClub().getName();
        }
        return "Resultado indisponível";
    }

    @Override
    public void setMatchSimulator(MatchSimulatorStrategy mss) {
        this.simulatorStrategy = mss;
    }

    @Override
    public IStanding[] getLeagueStandings() {
        return this.leagueStandings;
    }

    @Override
    public ISchedule getSchedule() {
        return this.schedule;
    }

    @Override
    public int getPointsPerWin() {
        return this.pointsPerWin;
    }

    @Override
    public int getPointsPerDraw() {
        return this.pointsPerDraw;
    }

    @Override
    public int getPointsPerLoss() {
        return this.pointsPerLoss;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getMaxTeams() {
        return this.maxTeams;
    }

    @Override
    public int getMaxRounds() {
        return this.maxRounds;
    }

    @Override
    public int getCurrentMatches() {
        return this.currentMatches;
    }

    @Override
    public int getNumberOfCurrentTeams() {
        return this.numberOfCurrentTeams;
    }

    @Override
    public IClub[] getCurrentClubs() {
        return this.currentClubs;
    }

    public void setCurrentClubs(IClub[] clubs) {
        this.currentClubs = clubs;
    }

    public void setClubCount(int count) {
        this.clubCount = count;
    }

    public League getLeague() {
        return this.league;
    }

    public ITeam[] getTeams() {
        return this.teams;
    }

    public ITeam getUserTeam() {
        return this.userTeam;
    }

    public void setUserTeam(ITeam team) {
        this.userTeam = team;
    }

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
        return null; // nenhum jogo por simular
    }

    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
