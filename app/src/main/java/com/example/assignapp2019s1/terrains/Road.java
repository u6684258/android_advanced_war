package com.example.assignapp2019s1.terrains;

public class Road extends Terrain {

    public Road(){
        this.terrainType = TerrainType.Road;
        this.isOccupied = false;
        this.defenceRating = 0.5;
        this.movementCost = 0.5;
    }
}
