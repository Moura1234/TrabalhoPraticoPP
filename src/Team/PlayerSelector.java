/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.team.IPlayerSelector;
import com.ppstudios.footballmanager.api.contracts.team.IClub;

/**
 *
 * @author joaom
 */
/**
 * Class that implements a player selection strategy. Implements the
 * IPlayerSelector interface by selecting the best player for a given position.
 */
public class PlayerSelector implements IPlayerSelector {

    /**
     * Selects the best player for the given position in the specified club. The
     * player is chosen based on the highest average of shooting, passing,
     * stamina, and speed.
     *
     * @param iclub The club to search players in
     * @param ipp The position to match
     * @return The best matching player
     * @throws IllegalArgumentException if the club or position is null
     * @throws IllegalStateException if the club has no players or no player is
     * found for the position
     */
    @Override
    public IPlayer selectPlayer(IClub iclub, IPlayerPosition ipp) {
        if (iclub == null || ipp == null) {
            throw new IllegalArgumentException("Club and position must not be null.");
        }

        IPlayer[] players = iclub.getPlayers();
        if (players == null || players.length == 0) {
            throw new IllegalStateException("Club has no players.");
        }

        IPlayer best = null;
        int bestScore = -1;

        for (IPlayer p : players) {
            
            if (p == null || p.getPosition() == null || p.getPosition().getDescription() == null) {
                continue;
            }

           
            if (!p.getPosition().getDescription().equalsIgnoreCase(ipp.getDescription())) {
                continue;
            }

            
            int score = (p.getShooting() + p.getPassing() + p.getStamina() + p.getSpeed()) / 4;

            if (score > bestScore) {
                best = p;
                bestScore = score;
            }
        }

        if (best == null) {
            throw new IllegalStateException("No player found for the specified position.");
        }

        return best;
    }
}
