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
 * Enumeration representing the side of a team in a match. Used to identify
 * whether a team is playing as the home or away team.
 */
public enum ETeam {
    HomeTeam, AwayTeam;

    /**
     * Converts the enum constant to a human-readable string.
     *
     * @return "Home Team", "Away Team", or "Error!" if undefined
     */
    public String ETeamToString() {
        switch (this) {
            case HomeTeam:
                return "Home Team";
            case AwayTeam:
                return "Away Team";
            default:
                return "Error!";
        }
    }
}
