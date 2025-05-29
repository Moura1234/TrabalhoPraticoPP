/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Team;

import com.ppstudios.footballmanager.api.contracts.team.*;
import java.io.IOException;
import Enums.EPosition;

/**
 *
 * @author utilizador
 */
public class Formation implements IFormation {

    private String displayName;
    private int tacticalAdvantage;
    private EPosition[] positions;

    public Formation(String displayName, int tacticalAdvantage, EPosition[] positions) {
        this.displayName = displayName;
        this.tacticalAdvantage = tacticalAdvantage;
        this.positions = positions;

    }
    
public EPosition[] getPositions() {
    return this.positions;
}

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public int getTacticalAdvantage(IFormation i) {
        return this.tacticalAdvantage;
    }
    
public static Formation create442() {
    EPosition[] positions = {
        EPosition.GOALKEEPER,
        EPosition.DEFENDER, EPosition.DEFENDER, EPosition.DEFENDER, EPosition.DEFENDER,
        EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER,
        EPosition.FORWARD, EPosition.FORWARD
    };
    return new Formation("4-4-2", 5, positions);
}

public static Formation create433() {
    EPosition[] positions = {
        EPosition.GOALKEEPER,
        EPosition.DEFENDER, EPosition.DEFENDER, EPosition.DEFENDER, EPosition.DEFENDER,
        EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER,
        EPosition.FORWARD, EPosition.FORWARD, EPosition.FORWARD
    };
    return new Formation("4-3-3", 6, positions);
}

public static Formation create352() {
    EPosition[] positions = {
        EPosition.GOALKEEPER,
        EPosition.DEFENDER, EPosition.DEFENDER, EPosition.DEFENDER,
        EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER, EPosition.MIDFIELDER,
        EPosition.FORWARD, EPosition.FORWARD
    };
    return new Formation("3-5-2", 4, positions);
}

    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
