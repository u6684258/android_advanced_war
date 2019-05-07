package com.example.assignapp2019s1.units;

import com.example.assignapp2019s1.Player;

public class Tank extends Unit {

    public Tank(Player owner, String position){
        this.unitType = UnitType.tank;
        this.unitSubType = UnitSubType.Land;
        this.owner = owner;
        this.position = position;

        this.hitpoints = 10;
        this.maxhitpoints = 10;
        this.ammo = 25;
        this.fuel = 70;
        this.mobility = 10;
        this.attackRange = 1;
        this.damageRating = 12.5;
        this.totaldamageRating = hitpoints * damageRating;
        this.defenseRating = 9.5;
        this.totaldefenserating = hitpoints * defenseRating;

        this.Has_ImmediateAttack = true;
        this.Has_DirectCounterAttack = true;
        this.can_fire = false;
        this.can_move = false;

    }

}
