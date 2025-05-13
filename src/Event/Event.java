package Event;

import com.ppstudios.footballmanager.api.contracts.event.*;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import java.io.IOException;

/**
 *
 * @author joaom
 */
public class Event implements IEvent, IEventManager {

    private String descrition;
    private int minute;
    private IEvent[] events;
    private int eventCount;

    public Event(String description, int minute, IEvent[] events, int eventCount) {
        this.descrition = description;
        this.minute = minute;
        this.events = new IEvent[100];
        this.eventCount = 0;
    }

    @Override
    public String getDescription() {
        return this.descrition;
    }

    @Override
    public int getMinute() {
        return this.minute;
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
    
    @Override
    public IPlayer getPlayer(){
        return null;
    }

    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
