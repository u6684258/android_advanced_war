package com.example.assignapp2019s1;

import com.example.assignapp2019s1.maps.MapMaker;
import com.example.assignapp2019s1.terrains.*;
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
        if (column < this.columnSize)
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

    public static void main(String[] args) {
        Board x = new Board("map2");
        System.out.println(x.rowSize + " " + x.columnSize);
        System.out.println(distance("B3", "A0"));
        System.out.println(x.neighborsInRange("C3",1));
    }

}
