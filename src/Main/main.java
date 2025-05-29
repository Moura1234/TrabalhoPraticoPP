/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import java.time.LocalDate;
import Enums.*;
import com.ppstudios.footballmanager.api.contracts.player.*;
import com.ppstudios.footballmanager.api.contracts.event.*;
import com.ppstudios.footballmanager.api.contracts.league.*;
import com.ppstudios.footballmanager.api.contracts.match.*;
import com.ppstudios.footballmanager.api.contracts.simulation.*;
import com.ppstudios.footballmanager.api.contracts.team.*;
import Event.*;
import JSONLoader.*;
import League.*;
import Match.*;
import Player.*;
import Simulation.*;
import Team.*;
import Enums.*;
import Menus.MainMenu;
import Menus.TeamSelectionMenu;

/**
 * 3
 *
 *
 * @author joaomjdbshdd
 */
public class Main {

    public static IStanding[] leagueStandings;
    public static Season season;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            // 1. Carregar clubes do ficheiro JSON
            IClub[] clubs = JsonManualLoader.loadClubsFromJson("JSONfiles/files/clubs.json");

            System.out.println("Total de clubes carregados: " + clubs.length);
            for (int i = 0; i < clubs.length; i++) {
                if (clubs[i] == null) {
                    System.out.println(" Clube nulo no índice: " + i);
                } else {
                    System.out.println("" + clubs[i].getName());
                }
            }
            // 2. Atribuir jogadores a cada clube
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
                    ((Club) club).setPlayers(players); // Cast se IClub não tiver setPlayers
                }
            }

            //Criar array de Team a partir do Club
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

            // 4. Criar uma época (Season)
            season = new Season("Liga Portuguesa", 2025, 3, 1, 0,
                    clubs.length, clubs.length - 1, 0, 0,
                    clubs, clubs.length, clubs.length, null,
                    leagueStandings, new MatchSimulator(), teams, null
            );

            season.setCurrentClubs(clubs);
            season.setClubCount(clubs.length);

            for (IClub club : clubs) {
                try {
                    season.addClub(club);
                } catch (IllegalStateException | IllegalArgumentException e) {
                    System.out.println("Não foi possível adicionar o clube: " + club.getName() + " → " + e.getMessage());
                }
            }

            season.generateSchedule();

            // 5. Gerar o calendário
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
//
