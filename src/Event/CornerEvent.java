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

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

/**
 * Represents a corner kick event in a football match. Contains information
 * about the player who takes the corner and determines if the play results in a
 * dangerous situation.
 */
public class CornerEvent extends Event {

    private final IPlayer taker;

    /**
     * Constructs a CornerEvent with the specified taker, description, and
     * minute.
     *
     * @param taker The player who takes the corner
     * @param description A brief description of the event
     * @param minute The minute the event occurred
     */
    public CornerEvent(IPlayer taker, String description, int minute) {
        super(description, minute);
        this.taker = taker;
    }

    /**
     * Gets the player who took the corner.
     *
     * @return The corner taker
     */
    public IPlayer getTaker() {
        return this.taker;
    }

    /**
     * Determines whether the corner kick leads to a dangerous play. There is a
     * 25% chance of being considered dangerous.
     *
     * @return true if the play is dangerous, false otherwise
     */
    public boolean isDangerous() {
        return Math.random() < 0.25;
    }

    @Override
    public String getDescription(){
        return "CORNER by" + taker.getName();
    }
    
    /**
     * Returns a string representation of the corner event.
     *
     * @return A formatted string indicating the minute and the taker
     */
    @Override
    public String toString() {
        return getMinute() + " - " + getDescription();
    }
}
