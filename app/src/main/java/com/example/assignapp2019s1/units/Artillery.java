package com.example.assignapp2019s1.units;

import com.example.assignapp2019s1.Player;

public class Artillery extends Unit {
    public Artillery(Player owner, String position){
        this.unitType = UnitType.artillery;
        this.unitSubType = UnitSubType.Land;
        this.owner = owner;
        this.position = position;
        this.unitCost = 6000;

        this.hitpoints = 10;
        this.maxhitpoints = 10;
        this.ammo = 9;
        this.fuel = 60;
        this.mobility = 10;
        this.rangeDeadZone = 1;
        this.attackRange = 3;
        this.visionscore = 1;
        this.damageRating = 15;
        this.totaldamageRating = hitpoints * damageRating;
        this.defenseRating = 7.5;
        this.totaldefenserating = hitpoints * defenseRating;


        this.Has_ImmediateAttack = true;
        this.Has_DirectCounterAttack = true;
        this.can_fire = false;
        this.can_move = false;

    }
}
