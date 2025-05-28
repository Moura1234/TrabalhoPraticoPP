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

/**
 *
 * @author joaom
 */
public class main {

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

            // 3. Criar classificações
        

            
            //Criar array de Team a partir do Club
        Team[] teams = new Team[clubs.length];

           for (int i = 0; i < clubs.length; i++) {
             Club club = (Club) clubs[i];
             IPlayer[] players = club.getPlayers();
            teams[i] = new Team(i, Formation.create442(), 5, club, players);
            
            
        }
           
                IStanding[] leagueStandings = new Standing[clubs.length];
             for (int i = 0; i < clubs.length; i++) {
             leagueStandings[i] = new Standing(teams[i]); 
          }



          
            // 4. Criar uma época (Season)
            Season season = new Season("Liga Portuguesa", 2025, 3, 1, 0,
                    clubs.length, clubs.length - 1, 0, 0,
                    clubs, clubs.length, clubs.length, null,
                    leagueStandings, new MatchSimulator()
            );

            // 5. Gerar o calendário
            season.generateSchedule();
            ISchedule schedule = season.getSchedule();

      
            
            
           System.out.println("Simulating Round " + (season.getCurrentRound() + 1));
           while (!season.isSeasonComplete()) {
           season.simulateRound();
}

           for (int i = 0; i < schedule.getNumberOfRounds(); i++) {
    IMatch[] roundMatches = schedule.getMatchesForRound(i);

    for (IMatch match : roundMatches) {
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

          

int totalRounds = schedule.getNumberOfRounds();

for (int i = 0; i < totalRounds; i++) {
    IMatch[] roundMatches = schedule.getMatchesForRound(i);

    System.out.println("======================");
    System.out.println("Round " + (i + 1));
    System.out.println("======================");

    for (IMatch match : roundMatches) {
        Match realMatch = (Match) match; // cast necessário

        if (realMatch.isPlayed()) {
            System.out.printf("%-30s %2d - %-2d %-30s\n",
                realMatch.getHomeTeam().getClub().getName(),
                realMatch.getHomeGoals(),
                realMatch.getAwayGoals(),
                realMatch.getAwayTeam().getClub().getName());
        }
    }

    System.out.println(); // linha em branco entre jornadas
}


for (int i = 0; i < leagueStandings.length - 1; i++) {
    for (int j = i + 1; j < leagueStandings.length; j++) {
        Standing s1 = (Standing) leagueStandings[i];
        Standing s2 = (Standing) leagueStandings[j];

        boolean swap = false;

        if (s2.getPoints() > s1.getPoints()) {
            swap = true;
        } else if (s2.getPoints() == s1.getPoints()) {
            if (s2.getGoalDifference() > s1.getGoalDifference()) {
                swap = true;
            }
        }

        if (swap) {
            IStanding temp = leagueStandings[i];
            leagueStandings[i] = leagueStandings[j];
            leagueStandings[j] = temp;
        }
    }
}

  System.out.println("\n===== League Table =====\n");
System.out.printf("    %-30s %5s %5s %5s %5s %5s %5s %5s\n",
    "Team", "Pts", "W", "D", "L", "GS", "GC", "GD");

for (IStanding s : leagueStandings) {
    Standing st = (Standing) s;
    System.out.printf("    %-30s %5d %5d %5d %5d %5d %5d %5d\n",
        st.getTeam().getClub().getName(),
        st.getPoints(),
        st.getWins(),
        st.getDraws(),
        st.getLosses(),
        st.getGoalScored(),
        st.getGoalsConceded(),
        st.getGoalDifference());
}

                
          }
         
 catch (Exception e) {
            e.printStackTrace();
        }
    }
    }


