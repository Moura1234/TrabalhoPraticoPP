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
 * Represents a goal kick event in a football match. This class extends the base
 * Event class and tracks the goalkeeper who performed the goal kick.
 */
public class GoalKickEvent extends Event {

    private IPlayer goalkeeper;

    /**
     * Constructs a GoalKickEvent with the specified goalkeeper, description,
     * and minute.
     *
     * @param goalkeeper The player who performed the goal kick
     * @param description A textual description of the event
     * @param minute The minute in which the event occurred
     */
    public GoalKickEvent(IPlayer goalkeeper, String description, int minute) {
        super(description, minute);
        this.goalkeeper = goalkeeper;
    }

    /**
     * Gets the goalkeeper involved in this event.
     *
     * @return The goalkeeper who performed the goal kick
     */
    public IPlayer getGoalkeeper() {
        return this.goalkeeper;
    }

    /**
     * Determines if the goal kick was accurate based on the goalkeeper's
     * passing skill.
     *
     * @return true if the kick is considered accurate, false otherwise
     */
    public boolean isAccurate() {
        int passing = goalkeeper.getPassing();
        return Math.random() < (passing / 100.0);
    }

    /**
     * Returns a string representation of the goal kick event.
     *
     * @return A formatted string describing the event
     */
    @Override
    public String toString() {
        return getMinute() + "' - GOALKICK by " + goalkeeper.getName();
    }
}
