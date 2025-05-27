/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Event;

import com.ppstudios.footballmanager.api.contracts.event.*;

/**
 *
 * @author joaom
 */
public class EventManager implements IEventManager {

    private IEvent[] events;
    private int eventCount;
    
    public EventManager() {
        this.events = new IEvent[200];
        this.eventCount = 0;
    }

    public EventManager(IEvent[] events, int eventCount) {
        this.events = events;
        this.eventCount = eventCount;
    }

    @Override
    public void addEvent(IEvent ievent) {
        if (eventCount < events.length) {
            events[eventCount++] = ievent;
        } else {
            System.out.println("Limite de eventos atingido.");
        }
    }

    @Override
    public IEvent[] getEvents() {
        IEvent[] copy = new IEvent[eventCount];
        for (int i = 0; i < eventCount; i++) {
            copy[i] = events[i];
        }
        return copy;
    }

    @Override
    public int getEventCount() {
        return this.eventCount;
    }

}
