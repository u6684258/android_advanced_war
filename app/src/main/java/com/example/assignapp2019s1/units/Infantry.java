package com.example.assignapp2019s1.units;

import com.example.assignapp2019s1.Player;

public class Infantry extends Unit {


    public Infantry(Player owner, String position) {
        this.unitType = UnitType.infantry;
        this.unitSubType = UnitSubType.Infantry;
        this.owner = owner;
        this.position = position;
        this.unitCost = 1000;

        this.hitpoints = 10;
        this.maxhitpoints = 10;
        this.ammo = 99;
        this.fuel = 999;
        this.mobility = 6;
        this.visionscore = 2;

        this.attackRange = 1;
        this.damageRating = 10;
        this.defenseRating = 5;
        this.totaldamageRating = hitpoints * damageRating;
        this.totaldefenserating = hitpoints * defenseRating;

        this.Has_ImmediateAttack = true;
        this.Has_DirectCounterAttack = true;
        this.can_fire = false;
        this.can_move = false;
        this.Can_capture = true;
    }
}
