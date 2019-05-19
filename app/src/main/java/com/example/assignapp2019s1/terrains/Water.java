package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.R;

public class Water extends Terrain {

    public Water() {
        this.terrainType = TerrainType.Water;
        this.isOccupied = false;
        this.defenceRating = 1;
        this.movementCost = 99;
        this.pic = R.drawable.water;
    }
}
