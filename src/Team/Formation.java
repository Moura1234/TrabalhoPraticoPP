/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Team;

import com.ppstudios.footballmanager.api.contracts.team.*;
import java.io.IOException;
import Enums.Position;

/**
 *
 * @author utilizador
 */
public class Formation implements IFormation {

    private String displayName;
    private int tacticalAdvantage;
    private Position[] positions;

    public Formation(String displayName, int tacticalAdvantage, Position[] positions) {
        this.displayName = displayName;
        this.tacticalAdvantage = tacticalAdvantage;
        this.positions = positions;

    }
    
public Position[] getPositions() {
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
    Position[] positions = {
        Position.GOALKEEPER,
        Position.DEFENDER, Position.DEFENDER, Position.DEFENDER, Position.DEFENDER,
        Position.MIDFIELDER, Position.MIDFIELDER, Position.MIDFIELDER, Position.MIDFIELDER,
        Position.FORWARD, Position.FORWARD
    };
    return new Formation("4-4-2", 5, positions);
}

public static Formation create433() {
    Position[] positions = {
        Position.GOALKEEPER,
        Position.DEFENDER, Position.DEFENDER, Position.DEFENDER, Position.DEFENDER,
        Position.MIDFIELDER, Position.MIDFIELDER, Position.MIDFIELDER,
        Position.FORWARD, Position.FORWARD, Position.FORWARD
    };
    return new Formation("4-3-3", 6, positions);
}

public static Formation create352() {
    Position[] positions = {
        Position.GOALKEEPER,
        Position.DEFENDER, Position.DEFENDER, Position.DEFENDER,
        Position.MIDFIELDER, Position.MIDFIELDER, Position.MIDFIELDER, Position.MIDFIELDER, Position.MIDFIELDER,
        Position.FORWARD, Position.FORWARD
    };
    return new Formation("3-5-2", 4, positions);
}

    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
