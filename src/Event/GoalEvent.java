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
 * Represents a goal event in a football match. Extends the base Event class and
 * implements the IGoalEvent interface, associating a goal with a specific
 * player.
 */
public class GoalEvent extends Event implements IGoalEvent {

    private IPlayer player;

    /**
     * Constructs a new GoalEvent.
     *
     * @param player The player who scored the goal
     * @param description A description of the goal event
     * @param minute The minute the goal occurred
     */
    public GoalEvent(IPlayer player, String description, int minute) {
        super(description, minute);
        this.player = player;
    }

    /**
     * Returns the player who scored the goal.
     *
     * @return The player responsible for the goal
     */
    @Override
    public IPlayer getPlayer() {
        return player;
    }

    /**
     * Returns a string representation of the goal event.
     *
     * @return A string describing the goal event
     */
    @Override
    public String toString() {
        return getMinute() + "' - GOAL by " + player.getName();
    }
}
