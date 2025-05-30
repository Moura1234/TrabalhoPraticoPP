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
import Player.Player;

/**
 * Represents a shot event in a football match. Stores information about the
 * shooter and the goalkeeper.
 */
public class ShotEvent extends Event implements IEvent {

    private IPlayer shooter;
    private IPlayer goalkeeper;

    /**
     * Constructs a ShotEvent with the specified shooter, goalkeeper,
     * description, and minute.
     *
     * @param shooter The player who performed the shot
     * @param goalkeeper The goalkeeper trying to save the shot
     * @param description A brief description of the event
     * @param minute The minute in which the event occurred
     */
    public ShotEvent(IPlayer shooter, IPlayer goalkeeper, String description, int minute) {
        super(description, minute);
        this.shooter = shooter;
        this.goalkeeper = goalkeeper;
    }

    /**
     * Returns the player who took the shot.
     *
     * @return The shooter
     */
    public IPlayer getShooter() {
        return this.shooter;
    }

    /**
     * Returns the goalkeeper defending against the shot.
     *
     * @return The goalkeeper
     */
    public IPlayer getGoalkeeper() {
        return this.goalkeeper;
    }

    /**
     * Determines whether the shot resulted in a goal based on player
     * attributes.
     *
     * @return true if the shot is successful (goal), false otherwise
     */
    public boolean isGoal() {
        int shotSkill = shooter.getShooting();
        int reflexes = ((Player) goalkeeper).getReflexes();
        double chance = (shotSkill - reflexes + 50) / 100.0;
        return Math.random() < chance;
    }

    /**
     * Returns the player associated with this event (shooter).
     *
     * @return The shooter
     */
    public IPlayer getPlayer() {
        return shooter;
    }

    /**
     * Returns a short textual description of the event.
     *
     * @return A string representing the shot event
     */
    @Override
    public String getDescription() {
        return "SHOT by " + shooter.getName();
    }
}
