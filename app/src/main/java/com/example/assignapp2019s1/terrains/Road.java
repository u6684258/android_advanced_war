package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.R;

public class Road extends Terrain {

    public Road(){
        this.terrainType = TerrainType.Road;
        this.isOccupied = false;
        this.defenceRating = 1;
        this.movementCost = 1;
        this.pic = R.drawable.road;
    }
}
