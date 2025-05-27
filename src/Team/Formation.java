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
        Position.GK,
        Position.LB, Position.CB, Position.CB, Position.RB,
        Position.LW, Position.CM, Position.CM, Position.RW,
        Position.ST, Position.ST
    };
    return new Formation("4-4-2", 5, positions);
}

public static Formation create433() {
    Position[] positions = {
        Position.GK,
        Position.LB, Position.CB, Position.CB, Position.RB,
        Position.CDM, Position.CM, Position.CAM,
        Position.LW, Position.ST, Position.RW
    };
    return new Formation("4-3-3", 6, positions);
}

public static Formation create352() {
    Position[] positions = {
        Position.GK,
        Position.CB, Position.CB, Position.CB,
        Position.LW, Position.CDM, Position.CM, Position.CAM, Position.RW,
        Position.ST, Position.ST
    };
    return new Formation("3-5-2", 4, positions);
}

    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
