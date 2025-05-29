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

    public Season(String name, int year, int pointsPerWin, int pointsPerDraw, int pointsPerLoss, int maxTeams, int maxRounds, int currentRound, int currentMatches, IClub[] currentClubs, int clubCount, int numberOfCurrentTeams, ISchedule schedule, IStanding[] leagueStandings, MatchSimulatorStrategy simulatorStrategy) {
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
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public boolean addClub(IClub iclub) {
        if (clubCount >= maxTeams) {
            return false;
        }

        for (int i = 0; i < clubCount; i++) {
            if (currentClubs[i].equals(iclub)) {
                return false; // clube existe
            }
        }

        currentClubs[clubCount] = iclub;
        leagueStandings[clubCount] = null;
        clubCount++;
        numberOfCurrentTeams++;

        return true;

    }

    @Override
    public boolean removeClub(IClub iclub) {

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
        return false;
    }

//    @Override
//    public void generateSchedule() {
//        if (clubCount < 2) {
//            throw new IllegalStateException("Número insuficiente de clubes para gerar calendário.");
//        }
//
//        int totalRounds = clubCount - 1; // 17 jornadas 
//        int matchesPerRound = clubCount / 2; // 9 jogos por jornada
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
//                IPlayer[] homePlayers = homeClub.getPlayers();
//                IPlayer[] awayPlayers = awayClub.getPlayers();
//
//                Formation formation = Formation.create433(); // usa qualquer formação base
//                ITeam homeTeam = new Team(100, formation, formation.getPositions().length, homeClub, homePlayers);
//                ITeam awayTeam = new Team(100, formation, formation.getPositions().length, awayClub, awayPlayers);
//
//                Match game = new Match(
//                        homeClub,
//                        awayClub,
//                        false,
//                        homeTeam,
//                        awayTeam,
//                        0,
//                        0,
//                        Enums.EFormation.HomeFormation,
//                        Enums.EFormation.AwayFormation,
//                        round,
//                        null, // Sem eventos por agora
//                        0 // eventCount
//                );
//
//                matches[round][match] = game;
//            }
//        }
//
//        this.schedule = new Schedule(matches);
//    }
    @Override
    public void generateSchedule() {
        System.out.println("=== DEBUG currentClubs dentro de generateSchedule ===");
        for (int i = 0; i < clubCount; i++) {
            if (currentClubs[i] == null) {
                System.out.println("❌ NULO no índice " + i);
            } else {
                System.out.println("✅ " + currentClubs[i].getName());
            }
        }

        if (clubCount < 2) {
            throw new IllegalStateException("Número insuficiente de clubes para gerar calendário.");
        }

        int totalRounds = clubCount - 1;
        int matchesPerRound = clubCount / 2;
        IMatch[][] matches = new IMatch[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int homeIndex = (round + match) % (clubCount - 1);
                int awayIndex = (clubCount - 1 - match + round) % (clubCount - 1);
                if (homeIndex < 0 || homeIndex >= clubCount || awayIndex < 0 || awayIndex >= clubCount || homeIndex == awayIndex) {
                    System.out.println("❌ Índice inválido ou jogo contra si mesmo. Ignorar.");
                    continue;
                }

                if (match == 0) {
                    awayIndex = clubCount - 1;
                }

                IClub homeClub = currentClubs[homeIndex];
                IClub awayClub = currentClubs[awayIndex];

                if (homeClub == null || awayClub == null) {
                    System.out.println("ERRO - homeClub ou awayClub null na ronda " + round);
                    System.out.println("homeIndex = " + homeIndex + ", awayIndex = " + awayIndex);
                }

                // Criação do jogo sem resultado nem equipas montadas ainda
                IMatch game = new Match(
                        homeClub,
                        awayClub,
                        round // apenas jornada marcada
                );

                matches[round][match] = game;
            }
        }

        this.schedule = new Schedule(matches); // guarda o calendário completo
    }

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
                        System.out.println("❌ Match nulo!");
                        continue;
                    }
                    if (match.getHomeClub() == null || match.getAwayClub() == null) {
                        System.out.println("❌ Match com clube nulo na jornada " + currentRound);
                        System.out.println("→ Home: " + match.getHomeClub());
                        System.out.println("→ Away: " + match.getAwayClub());
                    }

                    prepareTeamsFor((Match) match);
                    simulatorStrategy.simulate(match);
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

        ITeam homeTeam = new Team(100, formation, formation.getPositions().length, home, homePlayers);
        ITeam awayTeam = new Team(100, formation, formation.getPositions().length, away, awayPlayers);

        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
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

    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
