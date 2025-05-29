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
 *
 * @author joaom
 */
public enum ETacticalAdvantageType {
    OFFENSIVE, DEFENSIVE, BALANCED;

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
