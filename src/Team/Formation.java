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

    private String displayName;
    private int tacticalAdvantage;

    public Formation(String displayName, int tacticalAdvantage) {
        this.displayName = displayName;
        this.tacticalAdvantage = tacticalAdvantage;

    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public int getTacticalAdvantage(IFormation i) {
        return this.tacticalAdvantage;
    }

    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
