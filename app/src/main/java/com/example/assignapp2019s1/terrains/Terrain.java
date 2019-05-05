package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.units.Unit;

public abstract class Terrain {
    TerrainType terrainType;
    boolean isOccupied = false;
    double defenceRating;
    double movementCost;
    Unit unitHere;

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public double getDefenceRating() {
        return defenceRating;
    }

    public void setDefenceRating(double defenceRating) {
        this.defenceRating = defenceRating;
    }

    public double getMovementCost() {
        return movementCost;
    }

    public void setMovementCost(double movementCost) {
        this.movementCost = movementCost;
    }

    public Unit getUnitHere() {
        return unitHere;
    }

    public void setUnitHere(Unit unitHere) {
        this.unitHere = unitHere;
    }

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
}
