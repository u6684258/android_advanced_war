package com.example.assignapp2019s1.terrains;

public class WorkShop extends City {

    public WorkShop(){
        this.terrainType = TerrainType.Workshop;
        this.isOccupied = false;
        this.defenceRating = 0.7;
        this.movementCost = 1;
        this.incomeGain = 1000;
        this.Owner = null;
        this.capturescore = 20;
        this.maxcapturescore = 20;
    }
}
