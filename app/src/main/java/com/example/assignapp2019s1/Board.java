package com.example.assignapp2019s1;

import com.example.assignapp2019s1.maps.MapMaker;
import com.example.assignapp2019s1.terrains.*;
import com.example.assignapp2019s1.units.Unit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    HashMap<String, Terrain> map;
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
                    out = new Grass();
                    break;
                case "Road":
                    out = new Grass();
                    break;
                case "Forest":
                    out = new Grass();
                    break;
                case "Pipe":
                    out = new Grass();
                    break;
                case "Water":
                    out = new Grass();
                    break;
                case "City":
                    out = new Grass();
                    break;
                case "Workshop":
                    out = new Grass();
                    break;
                default:
                    out = new Grass();
                    break;
            }
            map.put(entry.getKey(), out);
        }
    }


}
