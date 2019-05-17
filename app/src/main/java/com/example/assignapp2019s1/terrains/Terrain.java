package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.units.Unit;

public abstract class Terrain {
    TerrainType terrainType;
    boolean isOccupied = false;
    double defenceRating;
    double movementCost;


    public City getBuildings() {
        return buildings;
    }

    public void setBuildings(City buildings) {
        this.buildings = buildings;
    }

    City buildings;

//    public Terrain getTerrain() {
//        return terrain;
//    }
//
//    public void setTerrain(Terrain terrain) {
//        this.terrain = terrain;
//    }
//
//    Terrain terrain;
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
        this.isOccupied = true;
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

    public int pic;
}

