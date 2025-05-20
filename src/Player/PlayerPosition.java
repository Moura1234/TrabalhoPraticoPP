/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Player;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;


/**
 *
 * @author joaom
 */
public class PlayerPosition implements IPlayerPosition{
    private String description;
    
    public PlayerPosition(String description){
        this.description = description;
    }
    
    @Override
    public String getDescription(){
        return this.description;
    }
}
