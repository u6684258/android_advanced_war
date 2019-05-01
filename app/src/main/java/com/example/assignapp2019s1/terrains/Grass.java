package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.units.Unit;

public class Grass extends Terrain {
    TerrainType terrainType = TerrainType.Grass;
    boolean isOccupied = false;
    double defenceRating = 1;
    double movementCost = 1.5;
    int captureScore = 0;

    public Grass(){}



}
