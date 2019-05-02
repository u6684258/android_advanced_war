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

    public Player getOwner() {
        return Owner;
    }

    public void setOwner(Player owner) {
        Owner = owner;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public double getMobility() {
        return Mobility;
    }

    public double getDamageRating() {
        return damageRating;
    }

    public double getDefenseRating() {
        return defenseRating;
    }

    public boolean isHas_ImmediateAttack() {
        return Has_ImmediateAttack;
    }

    public boolean isHas_DirectCounterAttack() {
        return Has_DirectCounterAttack;
    }

    public boolean isCan_fire() {
        return can_fire;
    }

    public void setCan_fire(boolean can_fire) {
        this.can_fire = can_fire;
    }

    public boolean isCan_move() {
        return can_move;
    }

    public void setCan_move(boolean can_move) {
        this.can_move = can_move;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


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

