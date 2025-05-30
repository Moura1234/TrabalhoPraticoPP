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
import java.io.IOException;

/**
 * Represents a football match event with a description and the minute it
 * occurred. Implements the IEvent interface.
 */
public class Event implements IEvent {

    private String description;
    private int minute;

    /**
     * Constructs an Event with the specified description and minute.
     *
     * @param description A brief description of the event
     * @param minute The minute of the match when the event occurred
     */
    public Event(String description, int minute) {
        this.description = description;
        this.minute = minute;
    }

    /**
     * Gets the description of the event.
     *
     * @return The event's description
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the minute in which the event occurred.
     *
     * @return The minute of the event
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Exports the event data to a JSON file.
     *
     * @throws IOException if writing the file fails
     * @throws UnsupportedOperationException if not implemented
     */
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
