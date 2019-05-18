package com.example.assignapp2019s1;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.assignapp2019s1.terrains.Terrain;
import com.example.assignapp2019s1.terrains.WorkShop;
import com.example.assignapp2019s1.units.Infantry;
import com.example.assignapp2019s1.units.Unit;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapView extends View {

    private static final int PIECE_SIZE = 100;

    MainGame game;
    String cursor = "";
    Unit selected = null;


    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void showMap(String map) {
        HashMap<String, String> m = new HashMap<>();
        XmlResourceParser assurances;
        switch (map) {
            case "map1":{
                assurances = getResources().getXml(R.xml.map1);
                break;
            }
            case "map2":{
                assurances = getResources().getXml(R.xml.map2);
                break;
            }
            default:{
                assurances = getResources().getXml(R.xml.map2);
                break;
            }
        }
        try {
            String buffer = "";
            int eventType = assurances.getEventType();
            while(eventType != XmlResourceParser.END_DOCUMENT)
            {

                if (assurances.getEventType() == XmlResourceParser.START_TAG) {
                    String s = assurances.getName();
                    if (s.equals("pos")){
                        assurances.next();
                        buffer += assurances.getText();
                        System.out.println(assurances);
                    }
                    if (s.equals("type") && !buffer.isEmpty()){
                        assurances.next();
                        m.put(buffer, assurances.getText());
                        buffer = "";
                    }
                }
                assurances.next();
                eventType = assurances.getEventType();
            }} catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//
        game = new MainGame(m);
        cursor = "A0";
        //todo: remove this
        Infantry x = new Infantry(game.player1, "A6");
        x.setCan_fire(true);
        x.setCan_move(true);
        x.setMovePoint(x.getMobility());
        MainGame.deployUnit(x, game.current, game.player1, (WorkShop) game.current.map.get("A6"));

        Infantry y = new Infantry(game.player2, "B5");
        MainGame.summonUnit(y, game.current, game.player2, "B5");

        Infantry z = new Infantry(game.player1, "B6");
        MainGame.summonUnit(z, game.current, game.player1, "B6");

        this.invalidate();
    }

    public void showMoveRange(Unit unit) {
        selected = unit;
        ArrayList<String> x = MainGame.getMovementRange(game.current.map.get(cursor).getUnitHere(), game.current);
        for (Map.Entry<String, Terrain> entry : game.current.map.entrySet()) {
            if (x.contains(entry.getKey())) {
                entry.getValue().alpha = 150;
            }
        }
    }

    public void finishShowMoveRange() {
        selected = null;
        for (Map.Entry<String, Terrain> entry : game.current.map.entrySet()) {
            entry.getValue().alpha = 255;
        }
    }

    //todo: these two attack functions
    // my idea: use button_b to attack
    public void showAttackRange(Unit unit) {
        selected = unit;
        ArrayList<String> x = MainGame.getAttackRange(game.current.map.get(cursor).getUnitHere(), game.current);
        for (Map.Entry<String, Terrain> entry : game.current.map.entrySet()) {
            if (entry.getValue().isOccupied() && !x.contains(entry.getKey()))
                entry.getValue().getUnitHere().alpha = 150;
        }
    }

    public void finishShowAttackRange() {
        selected = null;
        for (Map.Entry<String, Terrain> entry : game.current.map.entrySet()) {
            if (entry.getValue().isOccupied())
                entry.getValue().getUnitHere().alpha = 255;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (game == null) {

        }

        else {
            for (Map.Entry<String, Terrain> entry : game.current.map.entrySet()) {
                Drawable d = ResourcesCompat.getDrawable(getResources(),entry.getValue().pic, null);
                int[] pos = Board.calculatePos(entry.getKey());
                d.setBounds(100 + pos[0]*PIECE_SIZE,50 + pos[1]*PIECE_SIZE,200 + pos[0]*PIECE_SIZE,150 + pos[1]*PIECE_SIZE);
                d.setAlpha(entry.getValue().alpha);
                d.draw(canvas);
                if (entry.getValue().getUnitHere() != null) {
                d = ResourcesCompat.getDrawable(getResources(),entry.getValue().getUnitHere().pic, null);
                d.setBounds(100 + pos[0]*PIECE_SIZE,50 + pos[1]*PIECE_SIZE,200 + pos[0]*PIECE_SIZE,150 + pos[1]*PIECE_SIZE);
                d.setAlpha(entry.getValue().getUnitHere().alpha);
                d.draw(canvas);}
            }
            Drawable d = ResourcesCompat.getDrawable(getResources(),R.drawable.cursor, null);
            int[] pos = Board.calculatePos(cursor);
            d.setBounds(100 + pos[0]*100,50 + pos[1]*100,200 + pos[0]*100,150 + pos[1]*100);
            d.draw(canvas);
        }
    }


}
