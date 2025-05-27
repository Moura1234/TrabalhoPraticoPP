/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package League;

import com.ppstudios.footballmanager.api.contracts.league.*;
import com.ppstudios.footballmanager.api.contracts.team.*;



/**
 *
 * @author utilizador
 */
public class Standing implements IStanding {
    
    private ITeam team;
    private int points;
    private int win;
    private int draw;
    private int loss;
    private int goalsScored;
    private int goalsConceded;
    private int totalMatches;

    public Standing(ITeam team) {
        this.team = team;
        this.points = 0;
        this.win = 0;
        this.draw = 0;
        this.loss = 0;
        this.goalsScored = 0;
        this.goalsConceded = 0;
        this.totalMatches = 0;
    }

    @Override
    public ITeam getTeam() {
        return this.team;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public int getWins() {
        return this.win;
    }

    @Override
    public int getDraws() {
        return this.draw;
    }

    @Override
    public int getLosses() {
        return this.loss;
    }

    @Override
    public int getGoalScored() {
        return this.goalsScored;
    }

    @Override
    public int getGoalsConceded() {
        return this.goalsConceded;
    }

    @Override
    public int getTotalMatches() {
        return this.totalMatches;
    }
    
    @Override
    public int getGoalDifference() {
        return this.goalsScored - this.goalsConceded;
    }
    
    @Override
    public void addPoints(int points) {
       this.points +=points;
    }
    
    @Override
    public void addWin(int pointsPerWin){
        win++;
    totalMatches++;
    points += pointsPerWin;
        
    }
    
    @Override
    public void addDraw(int pointsPerDraw){
        draw++;
    totalMatches++;
    points += pointsPerDraw;
    }
    
    @Override
    public void addLoss(int pointsPerLoss){
      loss++;
    totalMatches++;
    points += pointsPerLoss;   
    }
    
}
