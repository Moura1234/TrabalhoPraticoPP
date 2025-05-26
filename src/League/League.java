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
    private static final int MAX_SEASONS = 10;
    
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
          ISeason[] result = new ISeason[seasonCount];
    for (int i = 0; i < seasonCount; i++) {
        result[i] = seasons[i];
    }
    return result;
     
    }
    
     @Override
    public boolean createSeason(ISeason is) {
        if (seasonCount < MAX_SEASONS) {
         seasons[seasonCount++] = is;

        return true; 
    }
        return false;
    }
    
    
    @Override 
    public ISeason removeSeason(int i){

         if (i >= 0 && i < seasonCount) {
            ISeason removed = seasons[i];
            for (int j = i; j < seasonCount - 1; j++) {
                seasons[j] = seasons[j + 1];
            }
            seasons[--seasonCount] = null;
            return removed;
        }
        return null;
    }
    
    @Override
    public ISeason getSeason(int i){
      
        if (i >= 0 && i < seasonCount) {
            return seasons[i];
    }
        return null;
    }
    
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     @Override
    public String toString() {
        return "League{name='" + name + "', seasonCount=" + seasonCount + "}";
    }
}
