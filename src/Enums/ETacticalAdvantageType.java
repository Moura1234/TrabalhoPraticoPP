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

/**
 * Represents the tactical advantage type a team may have during a match. It
 * defines whether the team is playing offensively, defensively, or in a
 * balanced manner
 */
public enum ETacticalAdvantageType {
    OFFENSIVE, DEFENSIVE, BALANCED;

    /**
     * Converts the tactical advantage type to a descriptive human-readable string.
     *
     * @return A string describing the advantage type
     */
    public String ETacticalAdvantageTypeToString() {
        switch (this) {
            case OFFENSIVE:
                return "The advantage is offensive";
            case DEFENSIVE:
                return "The advantage is defensive";
            case BALANCED:
                return "The advantage is balanced";
            default:
                return "No advantages";
        }
    }

}
