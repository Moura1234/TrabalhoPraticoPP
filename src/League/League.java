/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */

package League;

import com.ppstudios.footballmanager.api.contracts.league.ILeague;
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import java.io.IOException;

/**
 * Represents a football league containing seasons. Manages the creation and
 * removal of seasons within the league.
 */
public class League implements ILeague {

    private final String name;
    private final ISeason[] seasons;
    private int seasonCount;
    private static final int MAX_SEASONS = 10;

    /**
     * Constructs a League instance with the specified name and initializes its
     * season structure.
     *
     * @param name The name of the league
     * @param seasons Ignored (array initialized internally with a fixed size)
     * @param seasonCount Initial number of seasons (typically 0)
     */
    public League(String name, ISeason[] seasons, int seasonCount) {
        this.name = name;
        this.seasons = new ISeason[MAX_SEASONS];
        this.seasonCount = 0;
    }

    /**
     * Gets the name of the league.
     *
     * @return The league name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the array of existing seasons in the league.
     *
     * @return An array containing all created seasons
     */
    @Override
    public ISeason[] getSeasons() {
        ISeason[] result = new ISeason[seasonCount];
        for (int i = 0; i < seasonCount; i++) {
            result[i] = seasons[i];
        }
        return result;

    }

    /**
     * Creates a season in the league.
     *
     * @param is The season to create
     * @return true if the season was created successfully, false otherwise
     * @throws IllegalArgumentException if the season is null or already exists
     */
    @Override
    public boolean createSeason(ISeason is) {
        if (is == null) {
            throw new IllegalArgumentException("Season cannot be null.");
        }

        for (int i = 0; i < seasonCount; i++) {
            if (seasons[i] == is) {
                throw new IllegalArgumentException("Season already exists.");
            }
        }

        if (seasonCount < MAX_SEASONS) {
            seasons[seasonCount++] = is;
            return true;
        }

        return false;
    }

    /**
     * Removes a season from the league based on its year.
     *
     * @param year The year of the season to remove
     * @return The removed season
     * @throws IllegalArgumentException if the season is not found
     */
    @Override
    public ISeason removeSeason(int year) {
        for (int i = 0; i < seasonCount; i++) {
            if (seasons[i] != null && seasons[i].getYear() == year) {
                ISeason removed = seasons[i];
                for (int j = i; j < seasonCount - 1; j++) {
                    seasons[j] = seasons[j + 1];
                }
                seasons[--seasonCount] = null;
                return removed;
            }
        }
        throw new IllegalArgumentException("Season with year " + year + " not found.");
    }

    /**
     * Gets a season from the league based on its year.
     *
     * @param year The year of the season to retrieve
     * @return The season with the given year
     * @throws IllegalArgumentException if the season is not found
     */
    @Override
    public ISeason getSeason(int year) {
        for (int i = 0; i < seasonCount; i++) {
            if (seasons[i] != null && seasons[i].getYear() == year) {
                return seasons[i];
            }
        }
        throw new IllegalArgumentException("Season with year " + year + " not found.");
    }

    /**
     * Returns a string representation of the league.
     *
     * @return A string describing the league name and number of seasons
     */
    @Override
    public String toString() {
        return "League{name='" + name + "', seasonCount=" + seasonCount + "}";
    }

    /**
     * Exports the league data to a JSON file.
     *
     * @throws IOException if writing to the file fails
     * @throws UnsupportedOperationException if the method is not yet
     * implemented
     */
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
