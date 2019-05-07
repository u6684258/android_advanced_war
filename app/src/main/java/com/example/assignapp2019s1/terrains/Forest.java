package com.example.assignapp2019s1.terrains;

public class Forest extends Terrain {

    public Forest(){
        this.terrainType = TerrainType.Forest;
        this.isOccupied = false;
        this.defenceRating = 0.8;
        this.movementCost = 2;

    }
}
