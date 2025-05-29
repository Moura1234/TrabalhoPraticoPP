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
public class FoulEvent extends Event{
     private final IPlayer player;

    public FoulEvent(IPlayer player, String description, int minute) {
        super(description, minute);
        this.player = player;
    }

    public IPlayer getPlayer() {
        return this.player;
    }

    
    public boolean isSuccessful() {
        return Math.random() < 0.7;
    }
    
    @Override
    public String toString() {
        return getMinute() + "' - FAUL by " + player.getName();
    }
}
