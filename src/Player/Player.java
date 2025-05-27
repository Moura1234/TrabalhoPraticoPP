/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Player;


import com.ppstudios.footballmanager.api.contracts.player.*;
import java.time.LocalDate;
import Enums.Position;
import java.io.IOException;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;

import java.time.LocalDate;
import Enums.Position;

/**
 *
 * @author joaom
 */

public class Player implements IPlayer{

    private String name;
    private LocalDate birthDate;
    private String nationality;
    private int number;
    private int shooting;
    private int passing;
    private int stamina;
    private int defense;
    private int speed;
    private Position position;
    private String photo;
    private float height;
    private float weight;
    private PreferredFoot preferredFoot;
    
    public Player(String name, LocalDate birthDate, String nationality, int number, int shooting, int passing, 
            int stamina,int defense, int speed, Position position, String photo, float height, float weight, PreferredFoot preferredFoot){
        this.name = name;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.number = number;
        this.shooting = shooting;
        this.passing = passing;
        this.stamina = stamina;
        this.defense = defense;
        this.speed = speed;
        this.position = position;
        this.photo = photo;
        this.height = height;
        this.weight = weight;
        this.preferredFoot = preferredFoot;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    
    @Override
    public int getAge() {
        return 3;
    }

    @Override
    public String getNationality() {
        return this.nationality;
    }
    
    @Override
    public void setPosition(IPlayerPosition ipp){
        this.position = ipp;
    }
           
    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public int getShooting() {
        return this.shooting;
    }

    @Override
    public int getPassing() {
        return this.passing;
    }

    @Override
    public int getStamina() {
        return this.stamina;
    }

    public LocalDate getBirthdate() {
        return birthDate;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public IPlayerPosition getPosition() {
        return this.position;
    }

    @Override
    public String getPhoto() {
        return this.photo;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public PreferredFoot getPreferredFoot() {
        return this.preferredFoot;
    }
    
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
