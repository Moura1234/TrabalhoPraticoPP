/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Event;

import com.ppstudios.footballmanager.api.contracts.event.*;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import Player.Player;

/**
 *
 * @author joaom
 */
public class ShotEvent extends Event implements IEvent {

    private IPlayer shooter;
    private IPlayer goalkeeper;

    public ShotEvent(IPlayer shooter, IPlayer goalkeeper, String description, int minute) {
        super(description, minute);
        this.shooter = shooter;
        this.goalkeeper = goalkeeper;
    }

    public IPlayer getShooter() {
        return this.shooter;
    }

    public IPlayer getGoalkeeper() {
        return this.goalkeeper;
    }

    public boolean isGoal() {
        int shotSkill = shooter.getShooting();
        int reflexes = ((Player) goalkeeper).getReflexes();
        double chance = (shotSkill - reflexes + 50) / 100.0;
        return Math.random() < chance;
    }

    public IPlayer getPlayer() {
        return shooter;
    }

    @Override
    public String getDescription() {
        return "SHOT by " + shooter.getName();
    }

}
