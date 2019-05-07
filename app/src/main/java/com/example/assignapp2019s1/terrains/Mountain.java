package com.example.assignapp2019s1.terrains;

public class Mountain extends Terrain {

    public Mountain() {
        this.terrainType = TerrainType.Mountain;
        this.isOccupied = false;
        this.defenceRating = 0.6;
        this.movementCost = 2;
    }
}
