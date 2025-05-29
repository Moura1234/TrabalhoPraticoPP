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
/**
 * Class representing a football team. Implements the ITeam interface and
 * manages the club, players, formation, and team logic.
 */
public class Team implements ITeam {

    private int teamStrength;
    private IFormation formation;
    private int positionCount;
    private int playerCount;
    private IClub club;
    private IPlayer[] players;
    private int id;
    private static final int MAX_PLAYERS = 18;

    /**
     * Constructs a team with the specified attributes.
     *
     * @param teamStrength Initial team strength
     * @param formation Initial formation
     * @param positionCount Number of positions
     * @param club Club associated with the team
     * @param players List of players
     * @param id Team identifier
     */
    public Team(int teamStrength, IFormation formation, int positionCount, IClub club, IPlayer[] players, int id) {
        this.teamStrength = teamStrength;
        this.formation = formation;
        this.positionCount = positionCount;
        this.club = club;
        this.players = players;
        this.id = id;

        if (players != null) {
            this.playerCount = players.length;
        } else {
            this.playerCount = 0;
        }
    }

    /**
     * Gets the club of the team.
     *
     * @return Associated club
     */
    @Override
    public IClub getClub() {
        return this.club;
    }

    /**
     * Gets the current formation of the team.
     *
     * @return Team formation
     */
    @Override
    public IFormation getFormation() {
        return this.formation;
    }

    /**
     * Calculates and gets the team's average strength.
     *
     * @return Average team strength
     */
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

    /**
     * Gets a copy of the current players in the team.
     *
     * @return Array of team players
     */
    @Override
    public IPlayer[] getPlayers() {
        IPlayer[] copy = new IPlayer[playerCount];
        for (int i = 0; i < playerCount; i++) {
            copy[i] = players[i];
        }
        return copy;
    }

    /**
     * Adds a player to the team.
     *
     * @param ip Player to add
     */
    @Override
    public void addPlayer(IPlayer ip) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i] == ip) {
                System.out.println("Player is already in the team: " + ip.getName());
                return;
            }
        }
        if (playerCount < MAX_PLAYERS) {
            players[playerCount++] = ip;
            System.out.println("Player added to the team: " + ip.getName());
        } else {
            System.out.println("Team is full. Cannot add: " + ip.getName());
        }
    }

    /**
     * Gets the number of players in the team.
     *
     * @return Player count
     */
    public int getPlayerCount() {
        return this.playerCount;
    }

    /**
     * Counts how many players occupy a specific position.
     *
     * @param ipp Position to count
     * @return Number of players in the position
     */
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

    /**
     * Checks if a given position is valid in the current team formation.
     *
     * @param ipp Position to check
     * @return True if valid, false otherwise
     */
    @Override
    public boolean isValidPositionForFormation(IPlayerPosition ipp) {
        return getPositionCount(ipp) > 0;
    }

    /**
     * Sets the team's formation.
     *
     * @param i Formation to set
     */
    @Override
    public void setFormation(IFormation i) {
        this.formation = i;
    }

    /**
     * Substitutes one player for another by their indexes.
     *
     * @param indexOut Index of player to remove
     * @param indexIn Index of player to insert
     */
    public void substitutePlayer(int indexOut, int indexIn) {
        if (indexOut < 0 || indexOut >= playerCount || indexIn < 0 || indexIn >= playerCount) {
            System.out.println("Invalid substitution indices.");
            return;
        }

        players[indexOut] = players[indexIn];
        players[indexIn] = null;
    }

    /**
     * Gets the players that are on the bench (from index 11 onward).
     *
     * @return Array of bench players
     */
    public IPlayer[] getBenchPlayers() {
        int benchSize = playerCount - 11;
        if (benchSize <= 0) {
            return new IPlayer[0];
        }

        IPlayer[] bench = new IPlayer[benchSize];
        for (int i = 11; i < playerCount; i++) {
            bench[i - 11] = players[i];
        }
        return bench;
    }

    /**
     * Gets the ID of the team.
     *
     * @return Team ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns a string representation of the team.
     *
     * @return String with basic team info
     */
    @Override
    public String toString() {
        return club.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Team other = (Team) obj;
        return this.club.getName().equals(other.club.getName());
        // ou comparar club.getName() por exemplo
    }

    /**
     * Exports the team to a JSON file.
     *
     * @throws IOException If writing fails
     * @throws UnsupportedOperationException If method is not implemented
     */
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
