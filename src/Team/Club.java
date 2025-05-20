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
    private int playerCount;
    
    public Club (String name, IPlayer[] players, String code, String country, 
            int foundedYear, String stadiumName, String logo){
        this.name = name;
        this.players = players;
        this.code = code;
        this.country = country;
        this.foundedYear = foundedYear;
        this.stadiumName = stadiumName;
        this.logo = logo;
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

    @Override
    public int getPlayerCount() {
        return this.playerCount;
    }
    
    @Override
    public void addPlayer(IPlayer ip){
        
           if (playerCount < MAX_PLAYERS) {
            players[playerCount++] = player;
            return true;
        } else {
            System.out.println("Não foi possível adicionar " + player.getName() + ": plantel cheio.");
            return false;
        } }
    }
    
    @Override
    public boolean isPlayer(IPlayer ip){
        return false;
    }
    
    @Override
    public void removePlayer(IPlayer ip){
        
    }
    
    @Override
    public IPlayer selectPlayer(IPlayerSelector ips, IPlayerPosition ipp){
        return null;
    }
    
    @Override
    public boolean isValid(){
        return false;
    }
    
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
