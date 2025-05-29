/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */
package Match;

import Enums.EClub;
import Enums.EFormation;
import Enums.ETeam;
import Event.EventManager;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.io.IOException;

/**
 * Represents a football match between two clubs. Stores teams, goals,
 * formations, round info and match events. Implements the IMatch interface.
 */
public class Match implements IMatch {

    private IClub homeClub;
    private IClub awayClub;
    private boolean played;
    private ITeam homeTeam;
    private ITeam awayTeam;
    private int homeGoals;
    private int awayGoals;
    private EFormation homeFormation;
    private EFormation awayFormation;
    private int round;
    private IEvent[] event;
    private EventManager eventManager = new EventManager();

    private int eventCount;

    /**
     * Constructs a match with full information including teams, goals, and
     * formations.
     *
     * @param homeClub The home club
     * @param awayClub The away club
     * @param played Whether the match has been played
     * @param homeTeam The home team
     * @param awayTeam The away team
     * @param homeGoals Goals scored by the home team
     * @param awayGoals Goals scored by the away team
     * @param homeFormation Formation used by the home team
     * @param awayFormation Formation used by the away team
     * @param round The round number
     * @param event Array of events in the match
     * @param eventCount Number of events
     */
    public Match(IClub homeClub, IClub awayClub, boolean played, ITeam homeTeam, ITeam awayTeam, int homeGoals, int awayGoals, EFormation homeFormation, EFormation awayFormation, int round, IEvent[] event, int eventCount) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.played = played;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.homeFormation = homeFormation;
        this.awayFormation = awayFormation;
        this.round = round;
        this.event = new IEvent[200];
        this.eventCount = 0;
    }

    /**
     * Constructs a match with only clubs and round, unplayed.
     *
     * @param home The home club
     * @param away The away club
     * @param round The round number
     */
    public Match(IClub home, IClub away, int round) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.round = round;
        this.played = false;
    }

    /**
     * Gets the home team of the match.
     *
     * @return The home club
     * @throws IllegalStateException if the home club is not initialized
     */
    @Override
    public IClub getHomeClub() {
        if (this.homeClub == null) {
            throw new IllegalStateException("Home club is not initialized.");
        }
        return this.homeClub;
    }

    /**
     * Gets the away team of the match.
     *
     * @return The away club
     * @throws IllegalStateException if the away club is not initialized
     */
    @Override
    public IClub getAwayClub() {
        if (this.awayClub == null) {
            throw new IllegalStateException("Away club is not initialized.");
        }
        return this.awayClub;
    }

    /**
     * Checks whether the match has already been played.
     *
     * @return true if the match is marked as played, false otherwise
     */
    @Override
    public boolean isPlayed() {
        return this.played;

    }

    /**
     * Gets the home team of the match.
     *
     * @return The home team
     * @throws IllegalStateException if the home team is not initialized
     */
    @Override
    public ITeam getHomeTeam() {
        if (this.homeTeam == null) {
            throw new IllegalStateException("Home team is not initialized.");
        }
        return this.homeTeam;
    }

    /**
     * Gets the away team of the match.
     *
     * @return The away team
     * @throws IllegalStateException if the away team is not initialized
     */
    @Override
    public ITeam getAwayTeam() {
        if (this.awayTeam == null) {
            throw new IllegalStateException("Away team is not initialized.");
        }
        return this.awayTeam;
    }

    /**
     * Gets the number of goals scored by the home team.
     *
     * @return Goals scored by the home team
     */
    public int getHomeGoals() {
        return this.homeGoals;
    }

    /**
     * Sets the home team for the match.
     *
     * @param homeTeam The team to assign as home team
     */
    public void setHomeTeam(ITeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    /**
     * Sets the away team for the match.
     *
     * @param awayTeam The team to assign as away team
     */
    public void setAwayTeam(ITeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    /**
     * Sets the number of goals for the home team.
     *
     * @param homeGoals Number of goals to set
     */
    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    /**
     * Sets the number of goals for the away team.
     *
     * @param awayGoals Number of goals to set
     */
    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    /**
     * Gets the number of goals scored by the away team.
     *
     * @return Goals scored by the away team
     */
    public int getAwayGoals() {
        return this.awayGoals;
    }

    /**
     * Marks the match as played.
     */
    @Override
    public void setPlayed() {
        this.played = true;
    }

    /**
     * Checks if the match is valid (has distinct clubs and initialized teams).
     *
     * @return true if the match is valid
     */
    @Override
    public boolean isValid() {
        return homeClub != null && awayClub != null
                && !homeClub.equals(awayClub)
                && homeTeam != null && awayTeam != null;
    }

    /**
     * Calculates the total number of events of a given type for the specified
     * club.
     *
     * @param type The class type of the event to count
     * @param iclub The club to filter events by
     * @return The number of events of the specified type related to the club
     */
    @Override
    public int getTotalByEvent(Class type, IClub iclub) {

        return 0;
    }

    /**
     * Returns the winning team, or null if it's a draw.
     *
     * @return The winning team or null
     */
    @Override
    public ITeam getWinner() {
        if (homeGoals > awayGoals) {
            return homeTeam;
        } else if (awayGoals > homeGoals) {
            return awayTeam;
        } else {
            return null;
        }
    }

    /**
     * Gets the round number this match is part of.
     *
     * @return Round number
     */
    @Override
    public int getRound() {
        return this.round;
    }

    /**
     * Gets the formation used by the home team.
     *
     * @return Home formation
     */
    public EFormation getHomeFormation() {
        return this.homeFormation;
    }

    /**
     * Gets the formation used by the away team.
     *
     * @return Away formation
     */
    public EFormation getAwayFormation() {
        return this.awayFormation;
    }

    /**
     * Sets the team for the match using the associated club.
     *
     * @param team The team to set
     * @throws IllegalArgumentException if the team is null
     * @throws IllegalStateException if the match is already played
     * @throws IllegalStateException if the team's club is not part of the match
     */
    @Override
    public void setTeam(ITeam team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null.");
        }

        if (this.played) {
            throw new IllegalStateException("Cannot set team: the match has already been played.");
        }

        if (team.getClub().equals(homeClub)) {
            this.homeTeam = team;
        } else if (team.getClub().equals(awayClub)) {
            this.awayTeam = team;
        } else {
            throw new IllegalStateException("The team's club does not belong to this match.");
        }
    }

    /**
     * Returns a formatted string with the result of the match.
     *
     * @return Match result string
     */
    public String getResult() {

        return homeClub.getName() + " " + homeGoals + " - " + awayGoals + " " + awayClub.getName();
    }

    /**
     * Adds an event to the match.
     *
     * @param ievent The event to add
     */
    @Override
    public void addEvent(IEvent ievent) {
        eventManager.addEvent(ievent);
    }

    /**
     * Returns all events of the match.
     *
     * @return Array of match events
     */
    @Override
    public IEvent[] getEvents() {
        return eventManager.getEvents();
    }

    /**
     * Sets the array of events and their count.
     *
     * @param events Array of events
     * @param count Number of events
     */
    public void setEvents(IEvent[] events, int count) {
        eventManager.setEvents(events);
        eventManager.setEventCount(count);
    }

    /**
     * Returns the number of events in the match.
     *
     * @return Event count
     */
    @Override
    public int getEventCount() {
        return eventManager.getEventCount();
    }

    /**
     * Sets the number of events manually.
     *
     * @param eventCount The new event count
     */
    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    /**
     * Returns a short string summary of the match.
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return "Match between " + homeClub.getName() + " and " + awayClub.getName();
    }

    /**
     * Exports match data to JSON format.
     *
     * @throws IOException if writing fails
     * @throws UnsupportedOperationException if not implemented
     */
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
