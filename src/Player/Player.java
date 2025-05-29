/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Nome: João Miguel Oliveira Moura
 * Número: 8230310 
 * Turma: LSIRC 1T2
 *
 * Nome: Rodrigo António Amorim Gonçalo Soares
 * Número: 8230329
 * Turma: LSIRC 1T2
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
 * Class representing a football player with personal and technical attributes.
 * Implements the IPlayer interface
 */
public class Player implements IPlayer {

    private String name;
    private LocalDate birthDate;
    private String nationality;
    private int number;
    private int shooting;
    private int passing;
    private int stamina;
    private int defense;
    private int speed;
    private int reflexes;
    private IPlayerPosition position;
    private String photo;
    private float height;
    private float weight;
    private PreferredFoot preferredFoot;

    /**
     * Constructs a Player with all attributes defined.
     *
     * @param name Player's name
     * @param birthDate Player's birth date
     * @param nationality Player's nationality
     * @param number Player's number
     * @param shooting Shooting skill
     * @param passing Passing skill
     * @param stamina Stamina level
     * @param defense Defensive ability
     * @param speed Speed attribute
     * @param reflexes Reflexes attribute
     * @param position Player's field position
     * @param photo Path or URL to the player's photo
     * @param height Player's Height
     * @param weight Player's Weight
     * @param preferredFoot Player's preferred foot (left or right)
     */
    public Player(String name, LocalDate birthDate, String nationality, int number,
            int shooting, int passing, int stamina, int defense,
            int speed, int reflexes, IPlayerPosition position,
            String photo, float height, float weight, PreferredFoot preferredFoot) {
        this.name = name;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.number = number;
        this.shooting = shooting;
        this.passing = passing;
        this.stamina = stamina;
        this.defense = defense;
        this.speed = speed;
        this.reflexes = reflexes;
        this.position = position;
        this.photo = photo;
        this.height = height;
        this.weight = weight;
        this.preferredFoot = preferredFoot;
    }

    /**
     * Gets the name of the player.
     *
     * @return Player's name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the birth date of the player.
     *
     * @return Player's birth date
     */
    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Gets the age of the player.
     *
     * @return Player's age (currently hardcoded)
     */
    @Override
    public int getAge() {
        return 3; // TODO: replace with actual age calculation
    }

    /**
     * Gets the nationality of the player.
     *
     * @return Player's nationality
     */
    @Override
    public String getNationality() {
        return this.nationality;
    }

    /**
     * Sets the player's position.
     *
     * @param ipp New player position
     * @throws IllegalArgumentException if the position is null
     */
    @Override
    public void setPosition(IPlayerPosition ipp) {
        if (ipp == null) {
            throw new IllegalArgumentException("Player position cannot be null.");
        }
        this.position = ipp;
    }

    /**
     * Gets the player's number.
     *
     * @return Jersey number
     */
    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * Gets the shooting skill of the player.
     *
     * @return Shooting value
     */
    @Override
    public int getShooting() {
        return this.shooting;
    }

    /**
     * Gets the passing skill of the player.
     *
     * @return Passing value
     */
    @Override
    public int getPassing() {
        return this.passing;
    }

    /**
     * Gets the stamina of the player.
     *
     * @return Stamina value
     */
    @Override
    public int getStamina() {
        return this.stamina;
    }

    /**
     * Gets the defensive ability of the player.
     *
     * @return Defense value
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Gets the speed of the player.
     *
     * @return Speed value
     */
    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Gets the reflexes of the player.
     *
     * @return Reflexes value
     */
    public int getReflexes() {
        return this.reflexes;
    }

    /**
     * Gets the current position of the player.
     *
     * @return Player's position
     */
    @Override
    public IPlayerPosition getPosition() {
        return this.position;
    }

    /**
     * Gets the photo path or URL of the player.
     *
     * @return Photo path or URL
     */
    @Override
    public String getPhoto() {
        return this.photo;
    }

    /**
     * Gets the height of the player.
     *
     * @return Height in meters
     */
    @Override
    public float getHeight() {
        return this.height;
    }

    /**
     * Gets the weight of the player.
     *
     * @return Weight in kilograms
     */
    @Override
    public float getWeight() {
        return this.weight;
    }

    /**
     * Gets the preferred foot of the player.
     *
     * @return Preferred foot
     */
    @Override
    public PreferredFoot getPreferredFoot() {
        return this.preferredFoot;
    }

    /**
     * Exports the player data to a JSON file.
     *
     * @throws IOException if writing fails
     * @throws UnsupportedOperationException if method is not yet implemented
     */
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
