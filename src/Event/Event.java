package Event;

import com.ppstudios.footballmanager.api.contracts.event.*;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import java.io.IOException;

/**
 *
 * @author joaom
 */
public class Event implements IEvent {

    private String descrition;
    private int minute;

    public Event(String description, int minute) {
        this.descrition = description;
        this.minute = minute;
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
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
