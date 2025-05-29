/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Team;

import com.ppstudios.footballmanager.api.contracts.team.*;
import com.ppstudios.footballmanager.api.contracts.player.*;
import java.io.IOException;

/**
 *
 * @author joaom
 */
/**
 * Class representing a football club. Implements the IClub interface and
 * manages players and team logic.
 */
public class Club implements IClub {

    private String name;
    private IPlayer[] players;
    private String code;
    private String country;
    private int foundedYear;
    private String stadiumName;
    private String logo;
    private String playerPosition;
    private String targetPosition;
    private IPlayer selected;
    private int playerCount;
    private static final int MAX_PLAYERS = 18;

    /**
     * Constructs a club with the given information and initial player list.
     *
     * @param name Club name
     * @param players Initial players array
     * @param code Club code
     * @param country Country of the club
     * @param foundedYear Year the club was founded
     * @param stadiumName Name of the club's stadium
     * @param logo Path or URL to the club logo
     * @param playerPosition Default player position label
     * @param targetPosition Target position label
     * @param selected Initially selected player
     */
    public Club(String name, IPlayer[] players, String code, String country,
            int foundedYear, String stadiumName, String logo,
            String playerPosition, String targetPosition, IPlayer selected) {
        this.name = name;
        this.players = players;
        this.code = code;
        this.country = country;
        this.foundedYear = foundedYear;
        this.stadiumName = stadiumName;
        this.logo = logo;
        this.playerPosition = playerPosition;
        this.targetPosition = targetPosition;
        this.selected = selected;
        playerCount = 0;
    }

    /**
     * Gets the name of the club.
     *
     * @return Club name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the array of players in the club.
     *
     * @return Array of players
     */
    @Override
    public IPlayer[] getPlayers() {
        return this.players;
    }

    /**
     * Sets the array of players and updates player count.
     *
     * @param players New array of players
     */
    public void setPlayers(IPlayer[] players) {
        this.players = players;
        this.playerCount = players.length;
    }

    /**
     * Gets the club code.
     *
     * @return Club code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the country of the club.
     *
     * @return Club country
     */
    @Override
    public String getCountry() {
        return this.country;
    }

    /**
     * Gets the year the club was founded.
     *
     * @return Founded year
     */
    @Override
    public int getFoundedYear() {
        return this.foundedYear;
    }

    /**
     * Gets the name of the stadium.
     *
     * @return Stadium name
     */
    @Override
    public String getStadiumName() {
        return this.stadiumName;
    }

    /**
     * Gets the club logo.
     *
     * @return Logo path or URL
     */
    @Override
    public String getLogo() {
        return this.logo;
    }

    /**
     * Gets the player position label.
     *
     * @return Player position label
     */
    public String getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Gets the target position label.
     *
     * @return Target position label
     */
    public String getTargetPosition() {
        return targetPosition;
    }

    /**
     * Gets the number of players in the club.
     *
     * @return Player count
     */
    @Override
    public int getPlayerCount() {
        return this.playerCount;
    }

    /**
     * Selects a player using the given selector and position.
     *
     * @param ips Player selector strategy
     * @param ipp Desired player position
     * @return Selected player
     * @throws IllegalArgumentException if the position is null
     * @throws IllegalStateException if the club is empty or no player is found
     * for the position
     */
    @Override
    public IPlayer selectPlayer(IPlayerSelector ips, IPlayerPosition ipp) {
        if (ipp == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }

        if (playerCount == 0) {
            throw new IllegalStateException("Club has no players.");
        }

        IPlayer selected = ips.selectPlayer(this, ipp);

        if (selected == null) {
            throw new IllegalStateException("No player found for position: " + ipp.getDescription());
        }

        return selected;
    }

    /**
     * Adds a player to the club if not already present and there is room.
     *
     * @param ip Player to add
     * @throws IllegalArgumentException if the player is null or already in the
     * club
     * @throws IllegalStateException if the club is full
     */
    @Override
    public void addPlayer(IPlayer ip) {
        if (ip == null) {
            throw new IllegalArgumentException("Cannot add null player.");
        }

        if (isPlayer(ip)) {
            throw new IllegalArgumentException("Player is already in the squad: " + ip.getName());
        }

        if (playerCount >= MAX_PLAYERS) {
            throw new IllegalStateException("Squad is full. Cannot add: " + ip.getName());
        }

        players[playerCount++] = ip;
    }

    /**
     * Checks if a player is already part of the club.
     *
     * @param ip Player to check
     * @return True if player is in the club, false otherwise
     * @throws IllegalArgumentException if the player is null or not valid
     */
    @Override
    public boolean isPlayer(IPlayer ip) {
        if (ip == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }

        if (ip.getName() == null || ip.getPosition() == null) {
            throw new IllegalArgumentException("Invalid player: missing name or position.");
        }

        for (int i = 0; i < playerCount; i++) {
            if (players[i] == ip) {
                return true;
            }
        }

        return false;
    }

    /**
     * Removes a player from the club if present.
     *
     * @param ip Player to remove
     * @throws IllegalArgumentException if the player is null or not in the club
     */
    @Override
    public void removePlayer(IPlayer ip) {
        if (ip == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }

        boolean found = false;

        for (int i = 0; i < playerCount; i++) {
            if (players[i] == ip) {
                // Shift players to remove the one at index i
                for (int j = i; j < playerCount - 1; j++) {
                    players[j] = players[j + 1];
                }
                players[--playerCount] = null;
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Player is not in the club.");
        }
    }

    /**
     * Validates the club according to required structure and player
     * composition.
     *
     * @return true if the club is valid
     * @throws IllegalStateException if the club has missing information or
     * invalid player conditions
     */
    @Override
    public boolean isValid() {
        if (name == null || name.isEmpty()
                || code == null || code.isEmpty()
                || country == null || country.isEmpty()
                || foundedYear <= 1800
                || stadiumName == null || stadiumName.isEmpty()
                || logo == null || logo.isEmpty()) {
            throw new IllegalStateException("Club information is incomplete.");
        }

        if (players == null || playerCount == 0) {
            throw new IllegalStateException("Club has no players.");
        }

        if (playerCount < 16) {
            throw new IllegalStateException("Club must have at least 16 players.");
        }

        int goalkeepers = 0;
        boolean hasPositions = false;

        for (int i = 0; i < playerCount; i++) {
            IPlayer p = players[i];
            if (p != null && p.getPosition() != null) {
                hasPositions = true;

                String posDesc = p.getPosition().getDescription().toLowerCase();
                if (posDesc.contains("goalkeeper") || posDesc.contains("gk")) {
                    goalkeepers++;
                }
            }
        }

        if (!hasPositions) {
            throw new IllegalStateException("Club has no players with valid positions.");
        }

        if (goalkeepers == 0) {
            throw new IllegalStateException("Club must have at least 1 goalkeeper.");
        }

        return true;
    }

    /**
     * Returns a string representation of the club.
     *
     * @return String with club summary
     */
    @Override
    public String toString() {
        return "Club{"
                + "name='" + name + '\''
                + ", code='" + code + '\''
                + ", country='" + country + '\''
                + ", stadium='" + stadiumName + '\''
                + ", players=" + playerCount
                + '}';
    }

    /**
     * Exports the club data to a JSON file.
     *
     * @throws IOException If file writing fails
     * @throws UnsupportedOperationException If method is not implemented
     */
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
