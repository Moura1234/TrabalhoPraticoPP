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

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;

/**
 * Utility class for computing statistics from events. Provides static methods
 * to associate events with teams and players.
 */
public class Statistics {

    /**
     * Checks whether a given event was performed by a player from the specified
     * team.
     *
     * @param event The event to analyze
     * @param team The team to verify against
     * @return true if the player responsible for the event belongs to the team;
     * false otherwise
     */
    public static boolean isFromTeam(IEvent event, ITeam team) {
        IPlayer player = extractPlayer(event);
        return player != null && arrayContains(team.getPlayers(), player);
    }

    /**
     * Extracts the player responsible for the event. Supports multiple event
     * types that involve a player.
     *
     * @param event The event from which to extract the player
     * @return The player involved in the event, or null if not applicable
     */
    private static IPlayer extractPlayer(IEvent event) {
        if (event instanceof IGoalEvent goal) {
            return goal.getPlayer();
        }
        if (event instanceof ShotEvent shot) {
            return shot.getShooter();
        }
        if (event instanceof FoulEvent foul) {
            return foul.getPlayer();
        }
        if (event instanceof CornerEvent corner) {
            return corner.getTaker();
        }
        if (event instanceof GoalKickEvent gk) {
            return gk.getGoalkeeper();
        }

        return null;
    }

    /**
     * Checks if the target player is present in the given array of players.
     *
     * @param players The array of players
     * @param target The player to search for
     * @return true if the target player exists in the array; false otherwise
     */
    public static boolean arrayContains(IPlayer[] players, IPlayer target) {
        for (IPlayer p : players) {
            if (p != null && p.equals(target)) {
                return true;
            }
        }
        return false;
    }
}
