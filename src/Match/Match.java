package Match;

import Enums.EClub;
import Enums.EFormation;
import Enums.ETeam;
import Event.EventManager;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.io.IOException;

/**
 *
 * @author joaom
 */
public class Match implements IMatch {
    
    private IClub homeClub;
    private IClub awayClub;
    private boolean played;
    private ITeam homeTeam;
    private ITeam awayTeam;
    private int homeGoals;
    private int awayGoals;
    private EFormation homeFormation;
    private EFormation awayFormation;
    private int round;
    private IEvent [] event;
    private EventManager eventManager = new EventManager();
    
    private int eventCount;

public Match (IClub homeClub, IClub awayClub, boolean played, ITeam homeTeam, ITeam awayTeam, int homeGoals, int awayGoals, EFormation homeFormation, EFormation awayFormation, int round,IEvent[] event, int eventCount) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.played = played;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.homeFormation = homeFormation;
        this.awayFormation = awayFormation;
        this.round = round;
        this.event = new IEvent[200];
        this.eventCount = 0;
}


    @Override
    public IClub getHomeClub() {
        return this.homeClub;
    }

    @Override
    public IClub getAwayClub() {
        return this.awayClub;
    }

    @Override
    public boolean isPlayed(){
    return this.played;
        
    }
    
    @Override
   public ITeam getHomeTeam() {
   return this.homeTeam;
}

    @Override
    public ITeam getAwayTeam(){
        return this.awayTeam;
    }

    public int getHomeGoals() {
        return this.homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public int getAwayGoals() {
        return this.awayGoals;
    }
    
    @Override
     public void setPlayed() {
        this.played = true; 
     }
     
     @Override
     public boolean isValid(){
    return homeClub != null && awayClub != null &&
           !homeClub.equals(awayClub) &&
           homeTeam != null && awayTeam != null;
     }
    
    @Override
    public int getTotalByEvent(Class type, IClub iclub){
    
        return 0;
    }
    
    @Override
     public ITeam getWinner(){
         if (homeGoals > awayGoals) {
        return homeTeam;
    } 
         else if (awayGoals > homeGoals) {
        return awayTeam;
    } 
       else{
        return null; 
    }    
    }
     
   @Override
    public int getRound(){
    
    return this.round;    
    }

    public EFormation getHomeFormation() {
        return this.homeFormation;
    }

    public EFormation getAwayFormation() {
        return this.awayFormation;
    }
    
    @Override
     public void setTeam(ITeam iteam) {
         
if (iteam.getClub().equals(homeClub)) {
        this.homeTeam = iteam;
    } else if (iteam.getClub().equals(awayClub)) {
        this.awayTeam = iteam;
    } else {
        System.out.println("A equipa não pertence a este jogo.");
    }

     }
    
    public String getResult() {
        
        return homeClub.getName() + " " + homeGoals + " - " + awayGoals + " " + awayClub.getName();
}
    @Override
    public void addEvent (IEvent ievent){
       eventManager.addEvent(ievent); 
     }
    
    @Override
    public IEvent[] getEvents(){
    return eventManager.getEvents();    
     }
    
    public void setEvents(IEvent[] events) {
        this.event = events;
    }
    
    @Override
     public int getEventCount(){
         return eventManager.getEventCount();  
     }
     
     public void setEventCount(int eventCount) {
    this.eventCount = eventCount;
    }
     
    
     @Override
    public String toString() {
        return "Match between " + homeClub.getName() + " and " + awayClub.getName();
    }
    
     @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }


