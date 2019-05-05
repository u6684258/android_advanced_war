package com.example.assignapp2019s1.terrains;

public class WorkShop extends Terrain {

    public WorkShop(){
        this.terrainType = TerrainType.Workshop;
        this.isOccupied = false;
        this.defenceRating = 1;
        this.movementCost = 1;
    }
}
