/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Team;
import com.ppstudios.footballmanager.api.contracts.team.*;
import com.ppstudios.footballmanager.api.contracts.player.*;
import java.io.IOException;

/**
 *
 * @author joaom
 */
public class Club implements IClub{
    private String name;
    private IPlayer[] players;
    private String code;
    private String country;
    private int foundedYear;
    private String stadiumName;
    private String logo;
    private String playerPosition;
    private String targetPosition;
    private IPlayer best;
    private int playerCount;
    private static final int MAX_PLAYERS =18;
    
    public Club (String name, IPlayer[] players, String code, String country, 
            int foundedYear, String stadiumName, String logo, String playerPosition, String targetPosition, IPlayer best){
        this.name = name;
        this.players = players;
        this.code = code;
        this.country = country;
        this.foundedYear = foundedYear;
        this.stadiumName = stadiumName;
        this.logo = logo;
        this.playerPosition = playerPosition;
        this.targetPosition = targetPosition;
        this.best = best;
        playerCount = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IPlayer[] getPlayers() {
        return this.players;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public int getFoundedYear() {
        return this.foundedYear;
    }

    @Override
    public String getStadiumName() {
        return this.stadiumName;
    }

    @Override
    public String getLogo() {
        return this.logo;
    }

   
    public String getPlayerPosition() {
        return playerPosition;
    }

   
    public String getTargetPosition() {
        return targetPosition;
    }
    
    

    @Override
    public int getPlayerCount() {
        return this.playerCount;
    }
    
    @Override
    public void addPlayer(IPlayer ip){
        
           if (playerCount < MAX_PLAYERS) {
            players[playerCount++] = ip;
        } else {
            System.out.println("Não foi possível adicionar " + ip.getName() + ": plantel cheio.");
    
        } 
    }
    
    @Override
    public boolean isPlayer(IPlayer ip){
        for (int i = 0; i < playerCount; i++) {
            if( players[i] == ip) 
                
                return true;
        }
        return false;
    }
    
    @Override
    public void removePlayer(IPlayer ip){
        
        for (int i = 0; i < playerCount; i++) {
        if (players[i] == ip) {
            for (int j = i; j < playerCount - 1; j++) {
                players[j] = players[j + 1];
            }
            players[--playerCount] = null;
            return;
        }
    }
    }
    
    @Override
    public IPlayer selectPlayer(IPlayerSelector ips, IPlayerPosition ipp){
     
        return null;
    }
    
  
 
    @Override
    public boolean isValid(){
        
        return name != null && !name.isEmpty()
            && code != null && !code.isEmpty()
            && country != null && !country.isEmpty()
            && foundedYear > 1800
            && stadiumName != null && !stadiumName.isEmpty()
            && logo != null && !logo.isEmpty()
            && playerCount >= 11;
        
    }
    
   
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

