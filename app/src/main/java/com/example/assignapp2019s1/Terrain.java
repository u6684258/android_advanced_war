package com.example.assignapp2019s1;

public abstract class Terrain {
    TerrainType terrainType;
    boolean isOccupied;
    double defenceRating;
    double movementCost;
    int captureScore;
}

enum TerrainType {
    Grass,
    Mountain,
    Road,
    Forest,
    Pipe,
    City,
    Workshop
}
