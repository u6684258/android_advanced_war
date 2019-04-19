package com.example.assignapp2019s1;

public abstract class Unit {
    UnitType unitType;
    UnitSubType unitSubType;
    Player Owner;
    int hitpoints;
    int ammo;
    int fuel;
    int actionPoints;
    double Mobility;
    double damageRating;
    double defenseRating;


}

enum UnitSubType {
    Infantry,
    Land,
    Aerial,
    Sea
}

enum UnitType {
    infantry,
    recon,
    tank,
    artillery
}

