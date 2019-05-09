package com.example.assignapp2019s1.units;

import com.example.assignapp2019s1.Player;

public class MediumTank extends Unit {
    public MediumTank(Player owner, String position){
        this.unitType = UnitType.MdTank;
        this.unitSubType = UnitSubType.Land;
        this.owner = owner;
        this.position = position;
        this.unitCost = 12000;

        this.hitpoints = 10;
        this.maxhitpoints = 10;
        this.ammo = 8;
        this.fuel = 50;
        this.mobility = 10;
        this.attackRange = 1;
        this.visionscore = 1;
        this.damageRating = 17.5;
        this.totaldamageRating = hitpoints * damageRating;
        this.defenseRating = 13.5;
        this.totaldefenserating = hitpoints * defenseRating;


        this.Has_ImmediateAttack = true;
        this.Has_DirectCounterAttack = true;
        this.can_fire = false;
        this.can_move = false;

    }

}
