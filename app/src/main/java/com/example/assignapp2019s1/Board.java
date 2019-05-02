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
        }
    }


}
