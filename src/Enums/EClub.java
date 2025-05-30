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
 * Enumeration representing the club's side in a match context. HomeClub:
 * Represents the club playing at home. AwayClub: Represents the club playing
 * away
 */
public enum EClub {
    HomeClub, AwayClub;

    /**
     * Converts the club side into a user-friendly string.
     *
     * @return "Home Club", "Away Club", or "Error!" if undefined
     */
    public String EClubToString() {
        switch (this) {
            case HomeClub:
                return "Home Club";
            case AwayClub:
                return "Away Club";
            default:
                return "Error!";

        }
    }
}
