/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package League;
import com.ppstudios.footballmanager.api.contracts.league.*;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;
import java.io.IOException;

/**
 *
 * @author joaom
 */
public class Season implements ISeason{
    private String name;
    private int year;
    private int pointsPerWin;
    private int pointsPerDraw;
    private int pointsPerLoss;
    private int maxTeams;
    private int maxRounds;
    private int currentRound;
    private int currentMatches;
    private IClub[] currentClubs;
    private int clubCount;
    private int numberOfCurrentTeams;
    private ISchedule schedule;
    private IStanding[] leagueStandings;
    private MatchSimulatorStrategy simulatorStrategy;
    
    public Season(String name, int year, int pointsPerWin, int pointsPerDraw, int pointsPerLoss, int maxTeams, int maxRounds, int currentRound, int currentMatches, IClub[] currentClubs, int clubCount, int numberOfCurrentTeams, ISchedule schedule, IStanding leagueStandings, MatchSimulatorStrategy simulatorStrategy){
        this.name = name;
        this.year = year;
        this.pointsPerWin = pointsPerWin;
        this.pointsPerDraw = pointsPerDraw;
        this.pointsPerLoss = pointsPerLoss;
        this.maxTeams = maxTeams;
        this.maxRounds = maxRounds;
        this.currentRound = 0;
        this.currentMatches = 0;
        this.currentClubs = new IClub [maxTeams];
        this.clubCount = 0;
        this.numberOfCurrentTeams = 0;
        this.schedule = null;
        this.leagueStandings = new IStanding[maxTeams];
        this.simulatorStrategy = null; 
    }

    @Override
    public int getYear() {
        return year;
    }
    
    @Override
     public boolean addClub(IClub iclub){
         return false;
     }
     
     @Override
     public boolean removeClub(IClub iclub){
         return false;
     }
     
     @Override
     public void generateSchedule(){
         
     }
     
     @Override
     public IMatch[] getMatches(){
         return null;
     }
     
     @Override
     public IMatch[] getMatches(int i){
         return null;
     }
     
     @Override
     public void simulateRound(){
         
     }
     
     @Override
     public void simulateSeason(){
         
     }
     
     @Override
     public int getCurrentRound() {
        return currentRound;
    }
     
     @Override
     public boolean isSeasonComplete(){
         return false;
     }

     @Override
     public void resetSeason(){
         
     }
     
     @Override
     public String displayMatchResult(IMatch imatch){
         return "";
     }
     
     @Override
     public void setMatchSimulator(MatchSimulatorStrategy mss){
         
     }
     
     @Override
     public IStanding[] getLeagueStandings() {
        return leagueStandings;
    }
     
     @Override
     public ISchedule getSchedule() {
        return schedule;
    }
     
     @Override
    public int getPointsPerWin() {
        return pointsPerWin;
    }

    @Override
    public int getPointsPerDraw() {
        return pointsPerDraw;
    }

    @Override
    public int getPointsPerLoss() {
        return pointsPerLoss;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxTeams() {
        return maxTeams;
    }

    @Override
    public int getMaxRounds() {
        return maxRounds;
    }

    @Override
    public int getCurrentMatches() {
        return currentMatches;
    }
    
    @Override
    public int getNumberOfCurrentTeams() {
        return numberOfCurrentTeams;
    }

    @Override
    public IClub[] getCurrentClubs() {
        return currentClubs;
    }    
    
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
