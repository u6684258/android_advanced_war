package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.units.Unit;

public abstract class Terrain {
    TerrainType terrainType;
    boolean isOccupied;
    double defenceRating;
    double movementCost;
    int captureScore;
    Unit unitHere;

    public void takePosition(Unit unit) {
        this.unitHere = unit;
        isOccupied = !isOccupied;
    }

    public void leavePosition() {
        this.unitHere = null;
        isOccupied = !isOccupied;
    }
}

enum TerrainType {
    Grass,
    Mountain,
    Road,
    Forest,
    Pipe,
    Water,
    City,
    Workshop
}
