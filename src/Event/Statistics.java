/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Event;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;

/**
 *
 * @author utilizador
 */
public class Statistics {
    
  public static boolean isFromTeam(IEvent event, ITeam team) {
        IPlayer player = extractPlayer(event);
        return player != null && arrayContains(team.getPlayers(), player);
    }

    private static IPlayer extractPlayer(IEvent event) {
        if (event instanceof IGoalEvent goal) return goal.getPlayer();
        if (event instanceof ShotEvent shot) return shot.getShooter();
        if (event instanceof FoulEvent foul) return foul.getPlayer();
        if (event instanceof CornerEvent corner) return corner.getTaker();
        if (event instanceof GoalKickEvent gk) return gk.getGoalkeeper();
        
        return null;
    }

    public static boolean arrayContains(IPlayer[] players, IPlayer target) {
        for (IPlayer p : players) {
            if (p != null && p.equals(target)) {
                return true;
            }
        }
        return false;
    }
}  

