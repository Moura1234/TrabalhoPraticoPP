/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */
package Main;

import com.ppstudios.footballmanager.api.contracts.player.*;
import com.ppstudios.footballmanager.api.contracts.league.*;
import com.ppstudios.footballmanager.api.contracts.match.*;
import com.ppstudios.footballmanager.api.contracts.team.*;
import JSONLoader.*;
import League.*;
import Match.*;
import Simulation.*;
import Team.*;
import Menus.MainMenu;
import Menus.TeamSelectionMenu;

public class Main {

    public static IStanding[] leagueStandings;
    public static Season season;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            IClub[] clubs = JsonManualLoader.loadClubsFromJson("JSONfiles/files/clubs.json");

            for (IClub club : clubs) {
                String fileName = null;

                switch (club.getName()) {
                    case "Sport Lisboa e Benfica":
                        fileName = "Benfica";
                        break;
                    case "Futebol Clube do Porto":
                        fileName = "Porto";
                        break;
                    case "Sporting Clube de Portugal":
                        fileName = "Sporting";
                        break;
                    case "Sporting Clube de Braga":
                        fileName = "Braga";
                        break;
                    case "Vitoria Sport Clube":
                        fileName = "Vitoria";
                        break;
                    case "Boavista Futebol Clube":
                        fileName = "Boavista";
                        break;
                    case "Casa Pia Atletico Clube":
                        fileName = "CasaPia";
                        break;
                    case "Grupo Desportivo Estoril Praia":
                        fileName = "Estoril";
                        break;
                    case "C. Futebol Estrela da Amadora":
                        fileName = "EstrelaAmadora";
                        break;
                    case "Futebol Clube de Famalicao":
                        fileName = "Famalicao";
                        break;
                    case "Sport Clube Farense":
                        fileName = "Farense";
                        break;
                    case "Gil Vicente Futebol Clube":
                        fileName = "GilVicente";
                        break;
                    case "Moreirense Futebol Clube":
                        fileName = "Moreirense";
                        break;
                    case "Clube Desportivo Nacional":
                        fileName = "Nacional";
                        break;
                    case "Rio Ave Futebol Clube":
                        fileName = "RioAve";
                        break;
                    case "Clube Desportivo Santa Clara":
                        fileName = "SantaClara";
                        break;
                    case "AVS Futebol SAD":
                        fileName = "AFS";
                        break;
                    case "Futebol Clube de Arouca":
                        fileName = "Arouca";
                        break;
                    default:
                        fileName = null;
                        break;
                }

                if (fileName != null) {
                    String path = "JSONfiles/files/players/" + fileName + ".json";
                    IPlayer[] players = JsonManualLoader.loadPlayersFromJson(path);
                    ((Club) club).setPlayers(players);
                }
            }

            Team[] teams = new Team[clubs.length];

            for (int i = 0; i < clubs.length; i++) {
                Club club = (Club) clubs[i];
                IPlayer[] players = club.getPlayers();
                teams[i] = new Team(i, Formation.create442(), 5, club, players, i);

            }

            Main.leagueStandings = new Standing[clubs.length];
            for (int i = 0; i < clubs.length; i++) {
                Main.leagueStandings[i] = new Standing(teams[i]);
            }

            int totalRounds = clubs.length - 1;

            IMatch[][] rounds = new IMatch[totalRounds][];
            for (int i = 0; i < totalRounds; i++) {
                rounds[i] = new IMatch[clubs.length / 2];
            }
            Schedule schedule = new Schedule(rounds);

            for (int i = 0; i < schedule.getNumberOfRounds(); i++) {
                IMatch[] roundMatches = schedule.getMatchesForRound(i);

                for (IMatch match : roundMatches) {
                    if (match == null || !(match instanceof Match)) {
                        continue;
                    }
                    Match realMatch = (Match) match;

                    if (realMatch.isPlayed()) {
                        Team home = (Team) realMatch.getHomeTeam();
                        Team away = (Team) realMatch.getAwayTeam();
                        int homeGoals = realMatch.getHomeGoals();
                        int awayGoals = realMatch.getAwayGoals();

                        Standing homeStanding = null;
                        Standing awayStanding = null;

                        for (int j = 0; j < leagueStandings.length; j++) {
                            if (leagueStandings[j].getTeam().getClub().equals(home.getClub())) {
                                homeStanding = (Standing) leagueStandings[j];
                            }
                            if (leagueStandings[j].getTeam().getClub().equals(away.getClub())) {
                                awayStanding = (Standing) leagueStandings[j];
                            }
                        }

                        if (homeStanding != null && awayStanding != null) {
                            homeStanding.updateStats(homeGoals, awayGoals, 3, 1, 0);
                            awayStanding.updateStats(awayGoals, homeGoals, 3, 1, 0);
                        }
                    }
                }
            }
            League league = new League("Liga Portuguesa", null, 0);

            season = new Season("Liga Portuguesa", 2025, 3, 1, 0,
                    clubs.length, clubs.length - 1, 0, 0,
                    clubs, clubs.length, clubs.length, null,
                    leagueStandings, new MatchSimulator(), teams, null);

            league.createSeason(season);

            season.setCurrentClubs(clubs);
            season.setClubCount(clubs.length);

            season.generateSchedule();

            for (int i = 0; i < schedule.getNumberOfRounds(); i++) {
                IMatch[] roundMatches = schedule.getMatchesForRound(i);

                for (IMatch match : roundMatches) {
                    if (match == null) {
                        continue;
                    }
                    Match realMatch = (Match) match;

                    if (realMatch.isPlayed()) {
                        Team home = (Team) realMatch.getHomeTeam();
                        Team away = (Team) realMatch.getAwayTeam();
                        int homeGoals = realMatch.getHomeGoals();
                        int awayGoals = realMatch.getAwayGoals();

                        Standing homeStanding = null;
                        Standing awayStanding = null;

                        for (int j = 0; j < leagueStandings.length; j++) {
                            if (leagueStandings[j].getTeam().getClub().equals(home.getClub())) {
                                homeStanding = (Standing) leagueStandings[j];
                            }
                            if (leagueStandings[j].getTeam().getClub().equals(away.getClub())) {
                                awayStanding = (Standing) leagueStandings[j];
                            }
                        }

                        if (homeStanding != null && awayStanding != null) {
                            homeStanding.updateStats(homeGoals, awayGoals, 3, 1, 0);
                            awayStanding.updateStats(awayGoals, homeGoals, 3, 1, 0);
                        }
                    }
                }
            }

            ITeam userTeam = TeamSelectionMenu.run(season);
            season.setUserTeam(userTeam);

            MainMenu.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

