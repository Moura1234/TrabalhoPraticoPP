/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enums;

/**
 *
 * @author utilizador
 */
public enum EFormation {
    HomeFormation, AwayFormation;
    
    public String EFormationToString(){
        switch(this){
            case HomeFormation:
                return "Home Formation";
            case AwayFormation:
                return "Away Formation";
            default:
                return "Error!";
        }
    }
}
