package com.example.assignapp2019s1;


import com.example.assignapp2019s1.terrains.City;
import com.example.assignapp2019s1.terrains.Terrain;
import com.example.assignapp2019s1.terrains.TerrainType;
import com.example.assignapp2019s1.terrains.WorkShop;
import com.example.assignapp2019s1.units.Infantry;
import com.example.assignapp2019s1.units.MediumTank;
import com.example.assignapp2019s1.units.Tank;
import com.example.assignapp2019s1.units.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class MainGame {
    Board current;
    Player player1;
    Player player2;
    HashMap moves;
    int turnCount = 0;
    int whoseTurn = 1;
    boolean gameStart = false;

    //initialise
    public MainGame(String map) {
        this.player1 = new Player();
        this.player2 = new Player();
        this.moves = new HashMap();
        this.gameStart = true;
        this.current = new Board(map);
    }

    public MainGame(HashMap<String, String> map) {
        this.player1 = new Player();
        this.player2 = new Player();
        this.moves = new HashMap();
        this.gameStart = true;
        this.current = new Board(map);
    }

    /*
    given a player, and an arrayList of all units, find out all units of this player.
    Some tips: there is an arrayList storing all units for current game in Board. therfore, after
    writing this function, it is easy to get units for player of the current turn.
    And units have a variable recording which player it belongs.
     */
    public static ArrayList<Unit> allUnitsOfCurrentPlayer(Player player, ArrayList<Unit> allUnits){
        ArrayList<Unit> outcome = new ArrayList<>();
        for (Unit u : allUnits) {
            if (u.getOwner() == player) {
                outcome.add(u);
            }
        }
        return outcome;
    }

    /*
    given a unit and the map with terrain, calculate all reachable place of this unit.
    tips: unit has a variable recording its current position. Terrain record the unit on it as well.
    the unit can only move if it still have action points.
     */
    public static ArrayList<String> getMovementRange(Unit unit, Board board) {
        ArrayList<String> outcome = new ArrayList<>();
        outcome = allMoveableNeighbors(unit.getPosition(), unit.getPosition(), unit.getMovePoint(), board, outcome);
        outcome.remove(unit.getPosition());
        return outcome;
    }

    public static ArrayList<String> allMoveableNeighbors(String startPos, String des, double remainedMovePoints, Board board, ArrayList<String> list){
        ArrayList<String> outcome = new ArrayList<>();
        for (String to : board.neighbors(des)) {
            if (!list.contains(to) && canMoveThere(startPos, remainedMovePoints, to, board) && !outcome.contains(to)) {
                outcome.add(to);
            }
        }
        for (String to : outcome) {
            if (!list.contains(to)){
            list.add(to);
            list = allMoveableNeighbors(startPos, to, remainedMovePoints, board, list);
            }
        }
        return list;
    }

    public static boolean canMoveThere(String startPos, double remainedMovePoints, String des, Board currentBoard) {
        int initDis = Board.distance(startPos, des);
        double lowestCost = 999;
        String towards = "";

        while (remainedMovePoints > 0){
            if (currentBoard.neighbors(startPos).contains(des)) {
                if (currentBoard.map.get(des).getMovementCost() <= remainedMovePoints){
                    return true;
                }
                else return false;
            }
            else {
            for (String to : currentBoard.neighbors(startPos)) {
                double a = currentBoard.map.get(to).getMovementCost();
                if (Board.distance(to, des) < initDis &&
                        a < lowestCost && a < remainedMovePoints &&
                        !currentBoard.map.get(to).isOccupied()) {
                    lowestCost = currentBoard.map.get(to).getMovementCost();
                    towards = to;
                }
            }}
            remainedMovePoints-=lowestCost;
            lowestCost = 999;
            if (!towards.isEmpty())
                initDis = Board.distance(towards, des);
            startPos = towards;
            towards = "";

        }
        return false;
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
    public static boolean isLegalMove(Unit unit, String des, Board currentBoard) {
        boolean outcome = true;
        if (currentBoard.map.get(des).isOccupied())
            outcome = false;
        if (currentBoard.map.get(des).getTerrainType() == TerrainType.Water)
            outcome = false;
        if (!getMovementRange(unit, currentBoard).contains(des))
            outcome = false;
        if (!unit.isCan_move()) {
            outcome = false;
        }
        return outcome;
    }

    /*
    move a unit to destination.
    need to: check if the move is legal
             change the unit's variables(use takeMove() of the unit).
             remove unit from current board position(use leavePosition() in Terrain)
             add unit to new position(use takePosition() in Terrain)
    tips: change hashmap to board, if you need to access arrayList of all units as well.
     */
    public static void move(Unit unit, String des, Board currentBoard){
        if (!isLegalMove(unit, des, currentBoard))
            return;
        String pos = unit.getPosition();
        unit.takeMove(des);
        currentBoard.map.get(pos).leavePosition();
        currentBoard.map.get(des).takePosition(unit);
    }

    /*
    given a unit and the map with terrain, calculate all reachable enemies' position the unit can attack.
    tips: unit has a variable recording its current position. Terrains record the unit on it as well.
          change arrayList to board, if you need to access arrayList of all units as well.
     */
    public static ArrayList<String> getAttackRange(Unit unit, Board board) {
        double movePointTemp = unit.getMovePoint();
        Player owner = unit.getOwner();
        int attackRange = unit.getAttackRange();
        ArrayList<String> range = MainGame.getMovementRange(unit, board);
        ArrayList<String> outcome = new ArrayList<>();
        outcome.addAll(range);
        ArrayList<String> toadd = new ArrayList<>();
        for (String pos : range) {
            ArrayList<String> shootable = board.neighborsInRange(pos, attackRange);
            for (String x : shootable) {
                if (!outcome.contains(x) && !toadd.contains(x)) {
                    toadd.add(x);
                }
            }
        }
        outcome.addAll(toadd);
        outcome.remove(unit.getPosition());
        ArrayList<String> toremove = new ArrayList<>();
        for (String pos : outcome) {
           if (!board.map.get(pos).isOccupied())
               toremove.add(pos);
           else if (board.map.get(pos).getUnitHere().getOwner() == owner)
               toremove.add(pos);
        }
        outcome.removeAll(toremove);
        return outcome;
    }

    /*
    calculate damage, formula in design document
     */
    public static double getDamage(Unit attacker, Unit defender, HashMap<String, Terrain> map) {
        double damageDealt = 0;
        double damagePercentage = 0;
        damageDealt = ((attacker.getTotaldamageRating() - defender.getTotaldefenserating()) / 10) * map.get(defender.getPosition()).getDefenceRating();
        damagePercentage = Math.floor(damageDealt/attacker.getMaxhitpoints() * 100);
        return damagePercentage;
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
    public static void attack(Unit attacker, Unit defender, Board board) {
        double damageDealt = 0;

        if (attacker.getDamageRating() * attacker.getHitpoints() < defender.getDefenseRating() * defender.getHitpoints()) {
            damageDealt = 0;
        } else {
            damageDealt = ((attacker.getDamageRating() * attacker.getHitpoints() - defender.getDefenseRating() * defender.getHitpoints()) / 10)
                    * board.map.get(defender.getPosition()).getDefenceRating();
            defender.setHitpoints(defender.getHitpoints() - damageDealt);

        }
        if (defender.isHas_DirectCounterAttack()) {
            if (defender.getDamageRating() * defender.getHitpoints() < attacker.getDefenseRating() * attacker.getHitpoints()) {
                damageDealt = 0;
            } else {
                damageDealt = ((defender.getDamageRating() * defender.getHitpoints() - attacker.getDefenseRating() * attacker.getHitpoints()) / 10) * board.map.get(attacker.getPosition()).getDefenceRating();
                attacker.setHitpoints(defender.getHitpoints() - damageDealt);
                if(attacker.getHitpoints() <= 0){
                    board.units.remove(attacker);
                }
            }
        }
        if(defender.getHitpoints() <= 0){
            //remove unit function
            board.units.remove(defender);
        }

        _wait(attacker);






    }

    /*
    if the player set the unit to "wait", use this. set the unit so that it cannot take any actions.
     */
    public static void _wait(Unit unit) {
        unit.setCan_move(false);
        unit.setCan_fire(false);
    }

    public static void _activate(Unit unit){
        unit.setCan_fire(true);
        unit.setCan_move(true);
    }

    /*
    capture the city with the unit. decrease captureScore of the city, formula in design document
    Make sure the unit cannot take any other actions after this.
     */
    public static void capture(Unit unit, City city){

        Double unitHP = Math.ceil(unit.getHitpoints());
        if(unit.isCan_capture()){
            city.setCapturescore(city.getCapturescore() - unitHP.intValue() );
            if (city.getCapturescore() <= 0){
                city.setOwner(unit.getOwner());
            }
        }
        _wait(unit);

        if(!city.getUnitHere().isCan_capture() || city.getUnitHere() == null){
            city.setCapturescore(city.getMaxcapturescore());

        }

    }

    /*If a unit is on friendly territory they will resupply for max ammo and fuel in addition to
    healing 2 hitpoints. The player will then spend 10% of the unit cost for every unit that was
    resupplied. Later implementation of aircraft means that ordinary cities cannot refuel aircraft,
    only airports can.
     */
    public static void resupply(City city){
        Double moneyspent = Math.floor(city.getUnitHere().getUnitCost()*0.1);

        if(city.getOwner() == city.getUnitHere().getOwner()){
            city.getUnitHere().setAmmo(city.getUnitHere().getMaxammo());
            city.getUnitHere().setFuel(city.getUnitHere().getMaxfuel());

            if(city.getUnitHere().getHitpoints() < city.getUnitHere().getMaxhitpoints()){
                city.getUnitHere().setHitpoints(city.getUnitHere().getHitpoints() + 2);

                if(city.getUnitHere().getHitpoints()>= city.getUnitHere().getMaxhitpoints()){
                    city.getUnitHere().setHitpoints(city.getUnitHere().getMaxhitpoints());
                }

            }
            city.getOwner().spendMoney(moneyspent.intValue());


        }
    }

//Default method for workshops to deploy units - unit selected will be deployed on top of workshop and money subtracted.
    public static void deployUnit(Unit unit, Board board, Player player, WorkShop workShop){
        Double unitcost = unit.getUnitCost();
        board.units.add(unit);
        unit.setOwner(player);
        workShop.setUnitHere(unit);
        player.spendMoney(unitcost.intValue());
    }


//Method used to create a unit of any player anywhere on the map. Most likely will be used for debugging/testing.
    public static void summonUnit(Unit unit, Board board, Player player, String position){
        board.units.add(unit);
        board.map.get(position).setUnitHere(unit);
        unit.setOwner(player);
    }

/*
Two units of the same type are able to reinforce each other as long as the unit being reinforced(selected unit)
has less than max hp. The reinforcing unit will be removed from the board after this.
If the reinforcement would go over the max hp, the player is refunded the unit cost
proportional to the amount of hp that was wasted.
 */

    public static void reinforce(Unit selectedUnit, Unit reinforcements, Board board){
        Double HPDiff = (Math.abs(selectedUnit.getHitpoints() - reinforcements.getHitpoints()));
        Double moneyRefund = Math.floor(selectedUnit.getUnitCost() * HPDiff/10);

        if(selectedUnit.getHitpoints() < selectedUnit.getMaxhitpoints() && selectedUnit.getUnitType() == reinforcements.getUnitType()){
            selectedUnit.setHitpoints(selectedUnit.getHitpoints() + reinforcements.getHitpoints());

            if(selectedUnit.getHitpoints() > selectedUnit.getMaxhitpoints()){
                selectedUnit.setHitpoints(selectedUnit.getMaxhitpoints());
                selectedUnit.getOwner().setMoney(selectedUnit.getOwner().getMoney() + moneyRefund.intValue());
            }

            board.units.remove(reinforcements);

        }


    }



    /*
    check game over
     */
    public boolean checkGameOver() {
        if (current.map.get(player1.hqAddress).getBuildings().getOwner()==player2 ||
                current.map.get(player2.hqAddress).getBuildings().getOwner()==player1)
            return true;
        else return false;
    }

    public void updateIncome(Player player, Board board){
        player.income = 2000 + (1000 * board.getAllCities(player).size());
    }


    /*
    call this when switch turn
    need to:    switch current player
                add money
                make units of this player actionable
                turn count++
     */
    public void switchTurn(Board board){
        if (whoseTurn == 1) {
            for(String s : board.getAllCities(player1)){
                resupply(board.map.get(s).getBuildings());
            }
            for(String s : board.getAllUnits(player1)){
                _wait(board.map.get(s).getUnitHere());
            }
            this.whoseTurn++;
            this.turnCount++;
            updateIncome(player1, board);
            updateIncome(player2, board);
            player2.addMoney();
            for(String s : board.getAllUnits(player2)){
                _activate(board.map.get(s).getUnitHere());
            }


        } else {
            for(String s : board.getAllCities(player2)){
                resupply(board.map.get(s).getBuildings());
            }
            for(String s : board.getAllUnits(player2)){
                _wait(board.map.get(s).getUnitHere());
            }

            this.whoseTurn--;
            this.turnCount++;
            updateIncome(player2, board);
            updateIncome(player1, board);
            player1.addMoney();
            for(String s : board.getAllUnits(player1)){
                _activate(board.map.get(s).getUnitHere());
            }
        }

    }

    // not sure how to do these yet:
    //cancelMove();
    //calculateScore();
    //these will be in gui:
    //showPossibleActions();
    public static void main(String[] args) {
        MainGame game = new MainGame("map2");
        Player player1 = new Player();
        Player player2 = new Player();
        Board board = new Board("map2");
        Tank tank = new Tank(player1, "A1");
        Infantry infantry = new Infantry(player2, "A2");
        MediumTank mediumTank = new MediumTank(player2,"A3");
//        board.units.add(mediumTank);
//        board.units.add(tank);
//        board.units.add(infantry);
        summonUnit(tank, board, player1, "A1");
        summonUnit(infantry, board, player2, "A2");
        summonUnit(mediumTank, board, player2, "A3");
        System.out.println("player 1 tank HP:" + board.map.get("A1").getUnitHere().getHitpoints());
        System.out.println("player 1 tank DMG Rating:" + board.map.get("A1").getUnitHere().getTotaldamageRating());
        System.out.println("Player 1 tank terrain bonus:" + board.map.get("A1").getDefenceRating());
        System.out.println("player 2 infantry HP:" + board.map.get("A2").getUnitHere().getHitpoints());
        System.out.println("player 2 infantry DMG Rating:" + board.map.get("A2").getUnitHere().getTotaldamageRating());
        System.out.println("Player 2 infantry terrain bonus:" + board.map.get("A2").getDefenceRating());

        attack(board.map.get("A1").getUnitHere(), board.map.get("A2").getUnitHere(), board);

        System.out.println("Tank attacks infantry, infantry should suffer heavy damage and tank should take no damage on counter-attack");
        System.out.println("player 1 tank HP:" + board.map.get("A1").getUnitHere().getHitpoints());
        System.out.println("player 1 tank DMG Rating:" + board.map.get("A1").getUnitHere().getDamageRating() *
                board.map.get("A1").getUnitHere().getHitpoints());
        System.out.println("player 2 infantry HP:" + board.map.get("A2").getUnitHere().getHitpoints());
        System.out.println("player 2 infantry DMG Rating:" + board.map.get("A2").getUnitHere().getDamageRating() *
                board.map.get("A2").getUnitHere().getHitpoints());

        tank.setHitpoints(10);
        System.out.println("player 1 tank HP:" + board.map.get("A1").getUnitHere().getHitpoints());

        System.out.println("player 2 MDtank HP:" + board.map.get("A3").getUnitHere().getHitpoints());
        System.out.println("player 2 MDtank DMG Rating:" + board.map.get("A3").getUnitHere().getTotaldamageRating());
        System.out.println("Player 2 MDtank terrain bonus:" + board.map.get("A3").getDefenceRating());

        attack(board.map.get("A1").getUnitHere(), board.map.get("A3").getUnitHere(), board);
        System.out.println("Player 1 tank attacks Player 2 MdTank - MDTank should take minimal damage and Tank should suffer heavy counterattack damage");
        System.out.println("player 2 MDtank HP:" + board.map.get("A3").getUnitHere().getHitpoints());
        System.out.println("player 2 MDtank DMG Rating:" + board.map.get("A3").getUnitHere().getDamageRating() *
                board.map.get("A3").getUnitHere().getHitpoints());

        System.out.println("player 1 tank HP:" + board.map.get("A1").getUnitHere().getHitpoints());
        System.out.println("player 1 tank DMG Rating:" + board.map.get("A1").getUnitHere().getDamageRating() *
                board.map.get("A1").getUnitHere().getHitpoints());






    }


}
