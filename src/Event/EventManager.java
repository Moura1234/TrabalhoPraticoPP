/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Event;
import com.ppstudios.footballmanager.api.contracts.event.*;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import java.io.IOException;


/**
 *
 * @author joaom
 */
public class EventManager implements IEventManager{
    private IEvent[] events;
    private int eventCount;
    
    public EventManager(IEvent[] events, int eventCount){
        this.events = new IEvent[100];
        this.eventCount = 0;
    }
    
    @Override
    public void addEvent(IEvent ievent){
        
    }
    
    @Override
    public IEvent[] getEvents(){
        return new IEvent[0];
    }
    
    @Override
    public int getEventCount(){
        return this.eventCount;
    }
    
}
