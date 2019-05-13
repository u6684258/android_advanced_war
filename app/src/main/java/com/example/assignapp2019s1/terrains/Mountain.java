package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.R;

public class Mountain extends Terrain {

    public Mountain() {
        this.terrainType = TerrainType.Mountain;
        this.isOccupied = false;
        this.defenceRating = 0.6;
        this.movementCost = 2;
        this.pic = R.drawable.mountain;
    }
}
