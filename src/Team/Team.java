/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Team;

import com.ppstudios.footballmanager.api.contracts.player.*;
import com.ppstudios.footballmanager.api.contracts.team.*;

/**
 *
 * @author utilizador
 */
public class Team implements ITeam{

    private int teamStrength;
    private IFormation formation;
    private int positionCount;
    private int playerCount;
    private IClub club;
    private IPlayer[] players;

    public Team(int teamStrength, IFormation formation, int positionCount, IClub club, IPlayer[] players) {
        this.teamStrength = teamStrength;
        this.formation = formation;
        this.positionCount = positionCount;
        this.club = club;
        this.players = players;
        this.playerCount = 0;
    }

      @Override
    
    public IClub getClub() {
        return this.club;
    }
    
        @Override
    
    public IFormation getFormation() {
        
        return this.formation;
    }
    
    @Override
    
    public int getTeamStrength() {
        
        return this.teamStrength;
    }

    @Override
    
    public IPlayer[] getPlayers() {
         IPlayer[] copy = new IPlayer[playerCount];
        for (int i = 0; i < playerCount; i++) {
            copy[i] = players[i];
        }
         return copy;sdgh
 
    }

    
    
 }

