package com.example.assignapp2019s1;

import com.example.assignapp2019s1.maps.MapMaker;
import com.example.assignapp2019s1.terrains.City;
import com.example.assignapp2019s1.terrains.Forest;
import com.example.assignapp2019s1.terrains.Grass;
import com.example.assignapp2019s1.terrains.Mountain;
import com.example.assignapp2019s1.terrains.Road;
import com.example.assignapp2019s1.terrains.Terrain;
import com.example.assignapp2019s1.terrains.TerrainType;
import com.example.assignapp2019s1.terrains.Water;
import com.example.assignapp2019s1.terrains.WorkShop;
import com.example.assignapp2019s1.units.Unit;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    int rowSize = 0;
    int columnSize = 0;
    HashMap<String, Terrain> map = new HashMap<>();
    ArrayList<Unit> units;

    public Board(String mapName) {
        units = new ArrayList<>();
        HashMap<String, String> mapTemp = MapMaker.load(MapMaker.PATH + mapName + ".xml").getMap();
        for (Map.Entry<String, String> entry: mapTemp.entrySet()) {
            String ter = entry.getValue();
            Terrain out;
            switch (ter){
                case "Grass":
                    out = new Grass();
                    break;
                case "Mountain":
                    out = new Mountain();
                    break;
                case "Road":
                    out = new Road();
                    break;
                case "Forest":
                    out = new Forest();
                    break;
                case "Water":
                    out = new Water();
                    break;
                case "City":
                    out = new City();
                    break;
                case "Workshop":
                    out = new WorkShop();
                    break;
                default:
                    out = new Grass();
                    break;
            }
            map.put(entry.getKey(), out);
            if (getRow(entry.getKey()) - 64 > this.rowSize)
                this.rowSize = getRow(entry.getKey()) - 64;
            if (getColunm(entry.getKey()) > this.columnSize)
                this.columnSize = getColunm(entry.getKey());
        }
        this.columnSize++;
    }

    public Board(HashMap<String, String> map) {
        units = new ArrayList<>();
        HashMap<String, String> mapTemp = map;
        for (Map.Entry<String, String> entry: mapTemp.entrySet()) {
            String ter = entry.getValue();
            Terrain out;
            switch (ter){
                case "Grass":
                    out = new Grass();
                    break;
                case "Mountain":
                    out = new Mountain();
                    break;
                case "Road":
                    out = new Road();
                    break;
                case "Forest":
                    out = new Forest();
                    break;
                case "Water":
                    out = new Water();
                    break;
                case "City":
                    out = new City();
                    break;
                case "Workshop":
                    out = new WorkShop();
                    break;
                default:
                    out = new Grass();
                    break;
            }
            this.map.put(entry.getKey(), out);
            if (getRow(entry.getKey()) - 64 > this.rowSize)
                this.rowSize = getRow(entry.getKey()) - 64;
            if (getColunm(entry.getKey()) > this.columnSize)
                this.columnSize = getColunm(entry.getKey());
        }
        this.columnSize++;
    }


    public static Character getRow(String position) {
        return position.charAt(0);
    }

    public static int getColunm(String position) {
        return Integer.parseInt(position.substring(1));
    }

    public String moveUp(String position) {
        char row = getRow(position);
        int column = getColunm(position);
        if (row > 65)
            row-=1;
        return "" + row + column;
    }

    public ArrayList<String> getAllCities(Player player){
        ArrayList<String> allCities = new ArrayList<>();
        for(Map.Entry<String, Terrain> entry: map.entrySet()){
            if(entry.getValue().getTerrainType() == TerrainType.City && entry.getValue().getBuildings().getOwner() == player){
                allCities.add(entry.getKey());
            }
        }
        return allCities;
    }

    public int getTotalIncome(Player player){
        ArrayList<String> allCities = getAllCities(player);
        int income = 0;

        for(int i = 0; i <= allCities.size(); i++){
            income += map.get(allCities.get(i)).getBuildings().getIncomeGain();

        }
        return income;
    }

    public ArrayList<String> getAllUnits(Player player){
        ArrayList<String> allUnits = new ArrayList<>();
        for(Map.Entry<String, Terrain> entry : map.entrySet()){
         if (entry.getValue().getUnitHere().getOwner() == player){
             allUnits.add(entry.getKey());
         }

        }
        return allUnits;

    }



    public String moveDown(String position) {
        char row = getRow(position);
        int column = getColunm(position);
        if ((row - 64) < this.rowSize)
            row+=1;
        return "" + row + column;
    }

    public String moveLeft(String position) {
        char row = getRow(position);
        int column = getColunm(position);
        if (column > 0)
            column--;
        return "" + row + column;
    }

    public String moveRight(String position) {
        char row = getRow(position);
        int column = getColunm(position);
        if (column < this.columnSize-1)
            column++;
        return "" + row + column;
    }

    public static int distance(String pos, String des) {
        char rowp = getRow(pos);
        int columnp = getColunm(pos);
        char rowd = getRow(des);
        int columnd = getColunm(des);

        int dr = Math.abs(rowp - rowd);
        int dc = Math.abs(columnp - columnd);

        return dr + dc;

    }

    public ArrayList<String> neighbors(String startPos) {
        ArrayList<String> out = new ArrayList<>();

        if (!startPos.equals(moveUp(startPos)))
            out.add(moveUp(startPos));
        if (!startPos.equals(moveDown(startPos)) && !out.contains(moveDown(startPos)))
            out.add(moveDown(startPos));
        if (!startPos.equals(moveLeft(startPos)) && !out.contains(moveLeft(startPos)))
            out.add(moveLeft(startPos));
        if (!startPos.equals(moveRight(startPos)) && !out.contains(moveRight(startPos)))
            out.add(moveRight(startPos));
        return out;
    }

    public ArrayList<String> neighborsInRange(String startPos, int range) {
        ArrayList<String> outcome = new ArrayList<>();
        outcome.add(startPos);
        while (range > 0) {
            ArrayList<String> toadd = new ArrayList<>();
            for (String pos : outcome) {
                for (String nei : neighbors(pos)) {
                    if (!outcome.contains(nei) && !toadd.contains(nei))
                        toadd.add(nei);
                }
            }
            outcome.addAll(toadd);
            range--;
        }
        outcome.remove(startPos);
        return outcome;
    }

    public static int[] calculatePos(String pos) {
        int row = pos.charAt(0) - 65;
        int col = Integer.parseInt(pos.substring(1));
        int[] outcome = {row, col};
        return outcome;
    }

    public static String calculateCur(int[] pos) {
        char row = (char) (pos[0] + 65);
        return "" + row + pos[1];
    }

    public static void main(String[] args) {
        Board x  = new Board("map2");
    }


}
