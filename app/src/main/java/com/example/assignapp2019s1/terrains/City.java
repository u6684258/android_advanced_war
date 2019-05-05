package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.Player;

public class City extends Terrain {
    int captureScore;
    Player Owner;

    public City(){
        this.terrainType = TerrainType.City;
        this.isOccupied = false;
        this.defenceRating = 1;
        this.movementCost = 1;
        this.Owner = null;
        this.captureScore = 20;
    }
}
