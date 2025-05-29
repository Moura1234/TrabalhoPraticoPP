/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enums;

/**
 *
 * @author utilizador
 */
public enum EClub {
    HomeClub, AwayClub;

    public String EClubToString() {
        switch (this) {
            case HomeClub:
                return "Home Club";
            case AwayClub:
                return "Away Club";
            default:
                return "Error!";
                           
        }
    }
}
