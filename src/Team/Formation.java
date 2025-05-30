/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */
package Team;

import com.ppstudios.footballmanager.api.contracts.team.*;
import java.io.IOException;
import Enums.EPosition;

/**
 * Represents a tactical team formation with a name, tactical advantage value,
 * and predefined player positions. Implements the IFormation interface.
 */
public class Formation implements IFormation {

    private final String displayName;
    private final int tacticalAdvantage;
    private final EPosition[] positions;

    /**
     * Constructs a Formation with the specified name, tactical advantage, and
     * player positions.
     *
     * @param displayName The name of the formation (e.g., "4-4-2")
     * @param tacticalAdvantage The tactical advantage score of this formation
     * @param positions The array of player positions for this formation
     */
    public Formation(String displayName, int tacticalAdvantage, EPosition[] positions) {
        this.displayName = displayName;
        this.tacticalAdvantage = tacticalAdvantage;
        this.positions = positions;

    }

    /**
     * Gets the array of player positions defined for this formation.
     *
     * @return Array of player positions
     */
    public EPosition[] getPositions() {
        return this.positions;
    }

    /**
     * Gets the display name of the formation.
     *
     * @return The display name of the formation
     */
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Gets the tactical advantage of the home team compared to the given
     * formation.
     *
     * @param formation The opponent's formation to compare against
     * @return The tactical advantage value
     * @throws IllegalStateException if the formations are not set
     */
    @Override
    public int getTacticalAdvantage(IFormation formation) {
        if (formation == null) {
            throw new IllegalStateException("Opponent formation must not be null.");
        }

        return this.tacticalAdvantage;
    }

    /**
     * Creates a 4-4-2 formation.
     *
     * @return A new Formation object representing the 4-4-2 layout
     */
    public static Formation create442() {
        EPosition[] positions = {
            EPosition.GOALKEEPER,
            EPosition.DEFENDER, EPosition.DEFENDER, EPosition.DEFENDER, EPosition.DEFENDER,
            EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER,
            EPosition.FORWARD, EPosition.FORWARD
        };
        return new Formation("4-4-2", 5, positions);
    }

    /**
     * Creates a 4-3-3 formation.
     *
     * @return A new Formation object representing the 4-3-3 layout
     */
    public static Formation create433() {
        EPosition[] positions = {
            EPosition.GOALKEEPER,
            EPosition.DEFENDER, EPosition.DEFENDER, EPosition.DEFENDER, EPosition.DEFENDER,
            EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER,
            EPosition.FORWARD, EPosition.FORWARD, EPosition.FORWARD
        };
        return new Formation("4-3-3", 6, positions);
    }

    /**
     * Creates a 3-5-2 formation.
     *
     * @return A new Formation object representing the 3-5-2 layout
     */
    public static Formation create352() {
        EPosition[] positions = {
            EPosition.GOALKEEPER,
            EPosition.DEFENDER, EPosition.DEFENDER, EPosition.DEFENDER,
            EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER,
            EPosition.FORWARD, EPosition.FORWARD
        };
        return new Formation("3-5-2", 4, positions);
    }

    /**
     * Exports the formation to a JSON format.
     *
     * @throws IOException if the export fails
     * @throws UnsupportedOperationException if the method is not implemented
     */
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
