/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Team;

import com.ppstudios.footballmanager.api.contracts.team.*;
import java.io.IOException;

/**
 *
 * @author utilizador
 */
public class Formation implements IFormation {
    
 private String DisplayName;
 private int TacticalAdvantage;

    public Formation(String DisplayName, int TacticalAdvantage) {
        this.DisplayName = DisplayName;
        this.TacticalAdvantage = TacticalAdvantage;
        
        
    }

    @Override
    
    public String getDisplayName() {
        return DisplayName;
    }

  
     @Override
    public int getTacticalAdvantage(IFormation i) {
        return this.TacticalAdvantage;
    }
    
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    } 
    }

    

 
