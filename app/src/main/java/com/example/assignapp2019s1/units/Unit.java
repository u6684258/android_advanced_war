package com.example.assignapp2019s1.units;

import com.example.assignapp2019s1.Player;

public abstract class Unit {
    UnitType unitType;
    UnitSubType unitSubType;
    Player Owner;

    //HP of the unit
    int hitpoints;
    int ammo;
    int fuel;
    int attackRange;
    double Mobility;
    // see design document for explain
    double damageRating;
    double defenseRating;

    // see design document for explain
    boolean Has_ImmediateAttack;
    boolean Has_DirectCounterAttack;
    //can the unit move?
    boolean can_fire = false;
    // can the unit fire?
    boolean can_move = false;
    //where is the unit
    String position;

    //the unit moves. change everything need to change.
    public void takeMove(String des) {
        this.position = des;
        this.can_move = false;
        this.fuel--;
        if (!this.Has_ImmediateAttack) {
            this.can_fire = false;
        }
    }
    // the unit fires. change everything need to change.
    public void takeFire(Unit tar) {
        this.can_fire = false;
        this.can_move = false;
        this.ammo--;
    }
}

enum UnitSubType {
    Infantry,
    Land,
    Aerial,
    Sea
}

enum UnitType {
    infantry,
    recon,
    tank,
    artillery
}

