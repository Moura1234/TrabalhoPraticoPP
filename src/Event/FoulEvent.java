/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */
package Event;

import com.ppstudios.footballmanager.api.contracts.event.*;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

/**
 * Represents a foul event in a football match. This class stores the player
 * responsible for the foul, the description and minute of the event.
 */
public class FoulEvent extends Event {

    private final IPlayer player;

    /**
     * Constructs a new FoulEvent.
     *
     * @param player The player who committed the foul
     * @param description The description of the foul
     * @param minute The minute when the foul occurred
     * @throws IllegalArgumentException if player is null or minute is negative
     */
    public FoulEvent(IPlayer player, String description, int minute) {
        super(description, minute);
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (minute < 0) {
            throw new IllegalArgumentException("Minute cannot be negative.");
        }
        this.player = player;
    }

    /**
     * Gets the player who committed the foul.
     *
     * @return The player who committed the foul
     */
    public IPlayer getPlayer() {
        return this.player;
    }

    /**
     * Determines whether the foul was successful (e.g., not resulting in a
     * card). Simulated with 70% success probability.
     *
     * @return true if the foul was considered successful; false otherwise
     */
    public boolean isSuccessful() {
        return Math.random() < 0.7;
    }

    /**
     * Returns a string representation of the foul event.
     *
     * @return A string in the format: "minute' - FAUL by player name"
     */
    @Override
    public String toString() {
        return getMinute() + "' - FAUL by " + player.getName();
    }
}
