/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import java.time.LocalDate;
import Enums.PlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.*;
import Player.Player;


/**
 *
 * @author joaom
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Player p1 = new Player("Nabo", LocalDate.parse("1999-09-19"), 26, "PT", 12, 0,0,0,0, PlayerPosition.CAM, "f", 177, 71, PreferredFoot.Right);
        
        System.out.println(p1);
    }
    
}
