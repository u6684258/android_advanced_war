package com.example.assignapp2019s1.units;

import com.example.assignapp2019s1.Player;

public abstract class Unit {
    public UnitType getUnitType() {
        return unitType;
    }

    UnitType unitType;
    UnitSubType unitSubType;
    Player owner;

    //HP of the unit
    double hitpoints;



    double maxhitpoints;
    int ammo;
    int fuel;
    int attackRange;

    public int getRangeDeadZone() {
        return rangeDeadZone;
    }

    public void setRangeDeadZone(int rangeDeadZone) {
        this.rangeDeadZone = rangeDeadZone;
    }

    int rangeDeadZone;
    double mobility;
    double movePoint;
    // see design document for explain
    double damageRating;
    double defenseRating;
    double totaldamageRating;
    double totaldefenserating;

    public double getTotaldamageRating() {
        return totaldamageRating;
    }

    public void setTotaldamageRating(double totaldamageRating) {
        this.totaldamageRating = totaldamageRating;
    }

    public double getTotaldefenserating() {
        return totaldefenserating;
    }

    public void setTotaldefenserating(double totaldefenserating) {
        this.totaldefenserating = totaldefenserating;
    }

    public double getMaxhitpoints() {
        return maxhitpoints;
    }

    public void setMaxhitpoints(double maxhitpoints) {
        this.maxhitpoints = maxhitpoints;
    }


    // see design document for explain
    boolean Has_ImmediateAttack;
    boolean Has_DirectCounterAttack;

    public boolean isCan_capture() {
        return Can_capture;
    }

    public void setCan_capture(boolean can_capture) {
        Can_capture = can_capture;
    }

    boolean Can_capture;
    //can the unit move?
    boolean can_fire = false;
    // can the unit fire?
    boolean can_move = false;
    //where is the unit
    String position;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public double getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(double hitpoints) {
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
        return mobility;
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

    public double getMovePoint() {
        return movePoint;
    }

    public void setMovePoint(double movePoint) {
        this.movePoint = movePoint;
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

