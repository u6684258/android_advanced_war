package com.example.assignapp2019s1.units;

import com.example.assignapp2019s1.Player;

public class Infantry extends Unit {
    UnitType unitType = UnitType.infantry;
    UnitSubType unitSubType = UnitSubType.Infantry;
    Player owner;

    int hitpoints = 10;
    int ammo = 10;
    int fuel = 999;
    double Mobility = 6;
    int attackRange = 1;
    double damageRating = 10;
    double defenseRating = 5;

    boolean Has_ImmediateAttack = true;
    boolean Has_DirectCounterAttack = true;
    boolean can_fire = false;
    boolean can_move = false;

    String position;

    public Infantry(Player owner, String position) {
        this.owner = owner;
        this.position = position;
    }
}
