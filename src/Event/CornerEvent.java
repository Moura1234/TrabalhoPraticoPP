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
public class CornerEvent extends Event{
    private IPlayer taker;

    public CornerEvent(IPlayer taker, String description, int minute) {
        super(description, minute);
        this.taker = taker;
    }

    public IPlayer getTaker() {
        return this.taker;
    }

    // 25% de probabilidade de resultar em jogada perigosa
    public boolean isDangerous() {
        return Math.random() < 0.25;
    }
    
    @Override
    public String toString() {
        return getMinute() + "' - CORNER by " + taker.getName();
    }
}
