package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.units.Unit;

public abstract class Terrain {
    TerrainType terrainType;
    boolean isOccupied = false;
    double defenceRating;
    double movementCost;
    Unit unitHere;

    public void takePosition(Unit unit) {
        if (!isOccupied) {
            this.unitHere = unit;
            isOccupied = !isOccupied;
            //
        }
    }

    public void leavePosition() {
        if (isOccupied) {
            this.unitHere = null;
            isOccupied = !isOccupied;
        }
    }
}

enum TerrainType {
    Grass,//draft: movement cost1
    Mountain,//mc:2
    Road,//0.5
    Forest,//1.5
    Water,//infantry 2, tank infinity
    City, //1
    Workshop//1
    //
}
