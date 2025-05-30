
/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */
package Player;

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;

/**
 * Represents a player's position on the field using a custom textual
 * description.
 */
public class PlayerPosition implements IPlayerPosition {

    private String description;

    /**
     * Constructs a PlayerPosition with the specified description.
     *
     * @param description The description
     */
    public PlayerPosition(String description) {
        this.description = description;
    }

    /**
     * Gets the textual description of the player's position.
     *
     * @return The description of the position 
     */
    @Override
    public String getDescription() {
        return this.description;
    }

}
