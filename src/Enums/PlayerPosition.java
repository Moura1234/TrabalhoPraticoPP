/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enums;
import com.ppstudios.footballmanager.api.contracts.player.*;


/**
 *
 * @author joaom
 */

public enum PlayerPosition implements IPlayerPosition{
    GK, LB, CB, RB, CDM, CM, CAM, LW, ST, RW;

    @Override
    public String getDescription() {
        switch (this) {
            case GK:
                return "Goalkeeper";
            case LB:
                return "Left Back";
            case CB:
                return "Center Back";
            case RB:
                return "Right Back";   
            case CDM:
                return "Defensive Midfielder";
            case CM:
                return "Midfielder";  
            case CAM:
                return "Offensive Midfielder";
            case LW:
                return "Left Winger";    
            case ST:
                return "Striker";    
            default:
                return "Right Winger";    
        }
    }
}
