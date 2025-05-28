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
                        fileName = "Benfica"; break;
                    case "Futebol Clube do Porto":
                        fileName = "Porto"; break;
                    case "Sporting Clube de Portugal":
                        fileName = "Sporting"; break;
                    case "Sporting Clube de Braga":
                        fileName = "Braga"; break;
                    case "Vitoria Sport Clube":
                        fileName = "Vitoria"; break;
                    case "Boavista Futebol Clube":
                        fileName = "Boavista"; break;
                    case "Casa Pia Atletico Clube":
                        fileName = "CasaPia"; break;
                    case "Grupo Desportivo Estoril Praia":
                        fileName = "Estoril"; break;
                    case "Clube de Futebol Estrela da Amadora":
                        fileName = "EstrelaAmadora"; break;
                    case "Futebol Clube de Famalicao":
                        fileName = "Famalicao"; break;
                    case "Sport Clube Farense":
                        fileName = "Farense"; break;
                    case "Gil Vicente Futebol Clube":
                        fileName = "GilVicente"; break;
                    case "Moreirense Futebol Clube":
                        fileName = "Moreirense"; break;
                    case "Clube Desportivo Nacional":
                        fileName = "Nacional"; break;
                    case "Rio Ave Futebol Clube":
                        fileName = "RioAve"; break;
                    case "Clube Desportivo Santa Clara":
                        fileName = "SantaClara"; break;
                    case "AVS Futebol SAD":
                        fileName = "AFS"; break;
                    case "Futebol Clube de Arouca":
                        fileName = "Arouca"; break;
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
            IStanding[] leagueStandings = new Standing[clubs.length];

            // 4. Criar uma época (Season)
            Season season = new Season("Liga Portuguesa", 2025, 3, 1, 0,
                clubs.length, clubs.length - 1, 0, 0,
                clubs, clubs.length, clubs.length, null,
                leagueStandings, new MatchSimulator()
            );

            // 5. Gerar o calendário
            season.generateSchedule();

            // 6. Simular a 1ª jornada
            season.simulateRound();

            // 7. Mostrar resultados
            IMatch[] matches = season.getMatches(season.getCurrentRound() - 1);
            for (IMatch match : matches) {
                Match m = (Match) match;
                System.out.println(
                    m.getHomeTeam().getClub().getName() + " " +
                    m.getHomeGoals() + " - " +
                    m.getAwayGoals() + " " +
                    m.getAwayTeam().getClub().getName()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



    
    

