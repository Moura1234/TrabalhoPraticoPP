/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Event;

import com.ppstudios.footballmanager.api.contracts.event.*;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

/**
 *
 * @author joaom
 */
public class GoalEvent extends Event implements IGoalEvent {
    public String player;
    
    public GoalEvent(String player, String description, int minute){
        super(description, minute);
        this.player = player;
    }

    @Override
    public IPlayer getPlayer(){
        return null;
    }
}
    
    

