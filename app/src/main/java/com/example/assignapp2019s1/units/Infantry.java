package com.example.assignapp2019s1.units;

import com.example.assignapp2019s1.Player;

public class Infantry extends Unit {


    public Infantry(Player owner, String position) {
        this.unitType = UnitType.infantry;
        this.unitSubType = UnitSubType.Infantry;
        this.owner = owner;
        this.position = position;

        this.hitpoints = 10;
        this.ammo = 10;
        this.fuel = 999;
        this.mobility = 6;
        this.attackRange = 1;
        this.damageRating = 10;
        this.defenseRating = 5;

        this.Has_ImmediateAttack = true;
        this.Has_DirectCounterAttack = true;
        this.can_fire = false;
        this.can_move = false;
    }
}
