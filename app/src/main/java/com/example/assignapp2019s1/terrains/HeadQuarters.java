package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.R;

public class HeadQuarters extends City {
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    String position;

    public HeadQuarters(){
        this.terrainType = TerrainType.HeadQuarters;
        this.isOccupied = false;
        this.defenceRating = 0.6;
        this.movementCost = 1;
        this.Owner = null;
        this.capturescore = 20;
        this.maxcapturescore = 20;
        this.incomeGain = 2000;
        this.pic = R.drawable.hq_red;
        this.buildings = this;


    }
}
