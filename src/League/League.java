package League;

import com.ppstudios.footballmanager.api.contracts.league.ILeague;
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import java.io.IOException;

/**
 *
 * @author joaom
 */
public class League implements ILeague{
    private String name;
    private ISeason []seasons;
    private int seasonCount;
    
    public League(String name, ISeason[] seasons, int seasonCount){
        this.name = name;
        this.seasons = new ISeason[10];
        this.seasonCount = 0;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override 
    public ISeason[] getSeasons(){
        return new ISeason[0];
    }
    
    @Override
    public boolean createSeason(ISeason is){
        return false;
    }
    
    @Override 
    public ISeason removeSeason(int i){
        return null;
    }
    
    @Override
    public ISeason getSeason(int i){
        return null;
    }
    
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
