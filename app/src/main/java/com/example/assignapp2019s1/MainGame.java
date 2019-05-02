package com.example.assignapp2019s1;


import com.example.assignapp2019s1.terrains.Terrain;
import com.example.assignapp2019s1.units.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class MainGame {
    Board current;
    Player player1;
    Player player2;
    HashMap moves;
    int turnCount;
    int whoseTurn;
    boolean gameStart;

    //initialise
    public MainGame() {

    }

    /*
    given a player, and an arrayList of all units, find out all units of this player.
    Some tips: there is an arrayList storing all units for current game in Board. therfore, after
    writing this function, it is easy to get units for player of the current turn.
    And units have a variable recording which player it belongs.
     */
    public static ArrayList<Unit> allUnitsOfCurrentPlayer(Player player, ArrayList<Unit> allUnits){
        return null;
    }
    /*
    given a unit and the map with terrain, calculate all reachable place of this unit.
    tips: unit has a variable recording its current position. Terrain record the unit on it as well.
    the unit can only move if it still have action points.
     */
    public static ArrayList<String> getMovementRange(Unit unit, HashMap<String, Terrain> map) {

        return null;
    }
    /*
    given a unit and a destination and the map with terrain, check if the move is legal.
    illegal: des already been taken by some unit;
             the unit cannot stay on the terrain of des(e.g, tank on water);
             des is not in move range;
             unit cannot move anymore(already moved, use can_move of the unit)
    tips: unit has a variable recording its current position. Terrain record the unit on it as well.
          change hashmap to board, if you need to access arrayList of all units as well.
     */
    public static boolean isLegalMove(Unit unit, String des, HashMap<String, Terrain> map) {
        return false;
    }

    /*
    move a unit to destination.
    need to: check if the move is legal
             change the unit's variables(use takeMove() of the unit).
             remove unit from current board position(use leavePosition() in Terrain)
             add unit to new position(use takePosition() in Terrain)
    tips: change hashmap to board, if you need to access arrayList of all units as well.
     */
    public static void move(Unit unit, String des, HashMap<String, Terrain> map){}

    /*
    given a unit and the map with terrain, calculate all reachable enemies the unit can attack.
    tips: unit has a variable recording its current position. Terrains record the unit on it as well.
          change arrayList to board, if you need to access arrayList of all units as well.
     */
    public static ArrayList<String> getAttackRange(Unit unit, HashMap<String, Terrain> map, ArrayList<Unit> allUnits) {
        return null;
    }

    /*
    calculate damage, formula in design document
     */
    public static int getDamage(Unit attacker, Unit beAttacked, HashMap<String, Terrain> map) {
        return 1;
    }

    /*
    attack some unit.
    need to: check is target in range?
             calculate damage and decrease HP
             if the target can counter attack(Has_DirectCounterAttack in unit), do another attack,
           but change attacker and target. Remember to decrese hp first, as counter attack happens
           after been attacked.
             set whatever configures changed for units beside hp, use takeFire() in Unit.
             if unit destroyed, remove it from arraylist of all units
     */
    public static void attack(Unit attacker, Unit beAttacked, Board board){}

    /*
    if the player set the unit to "wait", use this. set the unit so that it cannot take any actions.
     */
    public static void _wait(Unit unit) {
        unit.setCan_move(false);
        unit.setCan_fire(false);
    }

    /*
    capture the city with the unit. decrease captureScore of the city, formula in design document
    Make sure the unit cannot take any other actions after this.
     */
    public static void capture(Unit unit, Terrain city){}

    /*
    check game over
     */
    public static void checkGameOver() {}

    /*
    call this when switch turn
    need to:    switch current player
                add money
                make units of this player actionable
                turn count++
     */
    public static void switchTurn(){}

    // not sure how to do these yet:
    //cancelMove();
    //calculateScore();
    //these will be in gui:
    //showPossibleActions();

}
