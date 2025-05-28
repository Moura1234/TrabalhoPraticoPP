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
public class GoalKickEvent extends Event{
    private IPlayer goalkeeper;

    public GoalKickEvent(IPlayer goalkeeper, String description, int minute) {
        super(description, minute);
        this.goalkeeper = goalkeeper;
    }

    public IPlayer getGoalkeeper() {
        return this.goalkeeper;
    }

    public boolean isAccurate() {
        int passing = goalkeeper.getPassing();
        return Math.random() < (passing / 100.0);
    }
    
    @Override
    public String toString() {
        return getMinute() + "' - GOALKICK by " + goalkeeper.getName();
    }
}


