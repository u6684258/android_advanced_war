package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.units.Unit;

public class Water extends Terrain {

    public Water() {
        this.terrainType = TerrainType.Water;
        this.isOccupied = false;
        this.defenceRating = 1.2;
        this.movementCost = 2;
    }
}
