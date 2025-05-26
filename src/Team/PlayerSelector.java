/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class PlayerSelector implements IPlayerSelector {
    
     
     @Override
    public IPlayer selectPlayer(IClub iclub, IPlayerPosition ipp) {
      IPlayer best = null;
        int bestScore = -1;

        for (IPlayer p : iclub.getPlayers()) {
            // Verifica nulidade e posição
            if (p == null || p.getPosition() == null || p.getPosition().getDescription() == null) continue;

            // Confirma se a posição bate certo
            if (!p.getPosition().getDescription().equalsIgnoreCase(ipp.getDescription())) continue;

            // Calcula média dos atributos relevantes
            int score = (p.getShooting() + p.getPassing() + p.getStamina() + p.getSpeed()) / 4;

            // Atualiza se for o melhor até agora
            if (score > bestScore) {
                best = p;
                bestScore = score;
            }
        }

        return best; // pode ser null se ninguém for elegível
    }
    
    }

