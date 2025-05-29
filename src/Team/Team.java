/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Team;

import com.ppstudios.footballmanager.api.contracts.player.*;
import com.ppstudios.footballmanager.api.contracts.team.*;
import java.io.IOException;

/**
 *
 * @author utilizador
 */
public class Team implements ITeam {
    private int teamStrength;
    private IFormation formation;
    private int positionCount;
    private int playerCount;
    private IClub club;
    private IPlayer[] players;
    private static final int MAX_PLAYERS = 18;

    public Team(int teamStrength, IFormation formation, int positionCount, IClub club, IPlayer[] players) {
        this.teamStrength = teamStrength;
        this.formation = formation;
        this.positionCount = positionCount;
        this.club = club;
        this.players = players;

        if (players != null) {
            this.playerCount = players.length;
        } else {
            this.playerCount = 0;

        }
    }

    @Override
    public IClub getClub() {
        return this.club;
    }

    @Override
    public IFormation getFormation() {
        return this.formation;
    }

    @Override
    public int getTeamStrength() {
        IPlayer[] players = club.getPlayers();
        int total = 0;
        for (IPlayer p : players) {
            total += (p.getShooting() + p.getPassing() + p.getSpeed() + p.getStamina()) / 4;
        }

        if (players.length > 0) {
            return total / players.length;
        } else {
            return 0;
        }

    }

    @Override
    public IPlayer[] getPlayers() {
        IPlayer[] copy = new IPlayer[playerCount];

        for (int i = 0; i < playerCount; i++) {
            copy[i] = players[i];
        }
        return copy;

    }

    @Override
    public void addPlayer(IPlayer ip) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i] == ip) {
                System.out.println("O jogador já se encontra na equipa: " + ip.getName());
                return;
            }
        }
        if (playerCount < MAX_PLAYERS) {
            players[playerCount++] = ip;
            System.out.println("Jogador adicionado à equipa: " + ip.getName());
        } else {
            System.out.println("Plantel cheio. Não foi possível adicionar: " + ip.getName());
        }
    }

    public int getPlayerCount() {
           return this.playerCount;
}
    
    @Override
    public int getPositionCount(IPlayerPosition ipp) {
        int count = 0;
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getPosition().equals(ipp)) {
                count++;
            }
        }
        return count;
    }


    @Override
    public boolean isValidPositionForFormation(IPlayerPosition ipp) {
        return getPositionCount(ipp) > 0;

    }

    @Override
    public void setFormation(IFormation i) {
        this.formation = i;

    }
    
    public void substitutePlayer(int index, IPlayer newPlayer) {
    if (index < 0 || index >= playerCount) {
        System.out.println("Invalid player index.");
        return;
    }

    System.out.println("Substituting: " + players[index].getName() + " ➡ " + newPlayer.getName());
    players[index] = newPlayer;
}

 
    public IPlayer[] getBenchPlayers() {
    int benchSize = playerCount - 11;
    if (benchSize <= 0) return new IPlayer[0];

    IPlayer[] bench = new IPlayer[benchSize];
    for (int i = 11; i < playerCount; i++) {
        bench[i - 11] = players[i];
    }
    return bench;
}
    
    
    @Override
    public String toString() {
        return "Team{"
                + "club=" + club.getName()
                + ", formation=" + formation
                + ", playerCount=" + playerCount
                + ", teamStrength=" + teamStrength
                + '}';
    }

    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}


