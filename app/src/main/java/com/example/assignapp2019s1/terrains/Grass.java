package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.units.Unit;

public class Grass extends Terrain {


    public Grass(){
        this.terrainType = TerrainType.Grass;
        this.isOccupied = false;
        this.defenceRating = 1;
        this.movementCost = 1;
    }



}
