package com.example.assignapp2019s1.terrains;

import com.example.assignapp2019s1.Player;
import com.example.assignapp2019s1.R;

public class City extends Terrain {

    Player Owner;
    int capturescore;

    public int getIncomeGain() {
        return incomeGain;
    }

    public void setIncomeGain(int incomeGain) {
        this.incomeGain = incomeGain;
    }

    int incomeGain;

    public int getMaxcapturescore() {
        return maxcapturescore;
    }

    public void setMaxcapturescore(int maxcapturescore) {
        this.maxcapturescore = maxcapturescore;
    }

    int maxcapturescore;

    public void leavePosition() {
        if (isOccupied) {
            this.unitHere = null;
            isOccupied = !isOccupied;
            this.setCapturescore(maxcapturescore);
        }
    }


    public Player getOwner() {
        return Owner;
    }

    public void setOwner(Player owner) {
        Owner = owner;
    }
    public int getCapturescore() {
        return capturescore;
    }

    public void setCapturescore(int capturescore) {
        this.capturescore = capturescore;
    }

    public City(){
        this.terrainType = TerrainType.City;
        this.isOccupied = false;
        this.defenceRating = 0.7;
        this.movementCost = 1;
        this.Owner = null;
        this.capturescore = 20;
        this.maxcapturescore = 20;
        this.incomeGain = 1000;
        this.pic = R.drawable.city_neutral;
        this.buildings = this;
    }
}
