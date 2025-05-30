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
 * Enumeration representing the tactical role of a team in a match. Indicates
 * whether a team is playing as the home or away formation.
 */
public enum EFormation {
    HomeFormation, AwayFormation;

    /**
     * Converts the enum constant to a user-friendly string.
     *
     * @return A string representation of the formation type
     */
    public String EFormationToString() {
        switch (this) {
            case HomeFormation:
                return "Home Formation";
            case AwayFormation:
                return "Away Formation";
            default:
                return "Error!";
        }
    }
}
