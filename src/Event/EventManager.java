/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
 */
package Event;

import com.ppstudios.footballmanager.api.contracts.event.*;

/**
 * Class that manages a collection of events. Implements the IEventManager
 * interface and handles adding and retrieving events.
 */
public class EventManager implements IEventManager {

    private IEvent[] events;
    private int eventCount;

    /**
     * Constructs a new EventManager with capacity for 200 events.
     */
    public EventManager() {
        this.events = new IEvent[200];
        this.eventCount = 0;
    }

    /**
     * Constructs an EventManager with a predefined array and count of events.
     *
     * @param events The array of events to use
     * @param eventCount The initial number of events
     */
    public EventManager(IEvent[] events, int eventCount) {
        this.events = events;
        this.eventCount = eventCount;
    }

    /**
     * Adds an event to the list.
     *
     * @param ievent The event to add
     * @throws IllegalArgumentException if the event is null
     * @throws IllegalStateException if the event list is full
     */
    @Override
    public void addEvent(IEvent ievent) {
        if (ievent == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }

        if (eventCount >= events.length) {
            throw new IllegalStateException("Maximum number of events reached.");
        }

        events[eventCount++] = ievent;
    }

    /**
     * Returns a copy of the list of all recorded events.
     *
     * @return Array of events
     */
    @Override
    public IEvent[] getEvents() {
        IEvent[] copy = new IEvent[eventCount];
        for (int i = 0; i < eventCount; i++) {
            copy[i] = events[i];
        }
        return copy;
    }

    /**
     * Gets the current number of recorded events.
     *
     * @return The number of events
     */
    @Override
    public int getEventCount() {
        return this.eventCount;
    }

    /**
     * Sets the array of events directly.
     *
     * @param events The new array of events
     */
    public void setEvents(IEvent[] events) {
        this.events = events;
    }

    /**
     * Sets the count of events manually.
     *
     * @param count The new event count
     */
    public void setEventCount(int count) {
        this.eventCount = count;
    }
}
