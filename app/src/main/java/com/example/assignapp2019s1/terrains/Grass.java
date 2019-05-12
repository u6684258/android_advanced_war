package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.R;
import com.example.assignapp2019s1.units.Unit;

public class Grass extends Terrain {

    public Grass(){
        this.terrainType = TerrainType.Grass;
        this.isOccupied = false;
        this.defenceRating = 0.9;
        this.movementCost = 1;
        this.pic = R.drawable.grass;
    }



}
