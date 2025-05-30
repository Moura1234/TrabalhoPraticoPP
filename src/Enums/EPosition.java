/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */
package Enums;

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;

/**
 * Enumeration representing player field positions. Implements the
 * IPlayerPosition interface to provide position descriptions.
 */
public enum EPosition implements IPlayerPosition {
    GOALKEEPER, DEFENDER, MIDFIELDER, FORWARD;

    /**
     * Gets a user-friendly description of the player's position.
     *
     * @return A string describing the position
     */
    @Override
    public String getDescription() {
        switch (this) {
            case GOALKEEPER:
                return "Goalkeeper";
            case DEFENDER:
                return "Defender";
            case MIDFIELDER:
                return "Midfielder";
            case FORWARD:
                return "Forward";
            default:
                return "No position was found";
        }
    }

}
