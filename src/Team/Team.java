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
public class Team implements IClub, ITeam, IPlayer, IFormation {

    private int TeamStrength;
    private IFormation Formation;
    private int PositionCount;
    private IClub Club;
    private IPlayer[] Players;

    public Team(int TeamStrength, IFormation Formation, int PositionCount, IClub Club, IPlayer[] Players) {
        this.TeamStrength = TeamStrength;
        this.Formation = Formation;
        this.PositionCount = PositionCount;
        this.Club = Club;
        this.Players = Players;
    }

    
    @Override
    
    public int getTeamStrength() {
        return this.TeamStrength;
    }

    @Override
    
    public IClub getClub() {
        return this.Club;
    }

    @Override
    
    public IPlayer[] getPlayers() {
        return this.Players;
    }

    
    
    
    
    
    
    
    
    
    
    
    
}
