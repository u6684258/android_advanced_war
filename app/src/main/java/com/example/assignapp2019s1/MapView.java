package com.example.assignapp2019s1;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.assignapp2019s1.terrains.Terrain;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapView extends View {

    MainGame game;
    String cursor = "";


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
        this.invalidate();
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
                d.setBounds(100 + pos[0]*100,50 + pos[1]*100,200 + pos[0]*100,150 + pos[1]*100);
                d.draw(canvas);


            }
            Drawable d = ResourcesCompat.getDrawable(getResources(),R.drawable.cursor, null);
            int[] pos = Board.calculatePos(cursor);
            d.setBounds(100 + pos[0]*100,50 + pos[1]*100,200 + pos[0]*100,150 + pos[1]*100);
            d.draw(canvas);
        }
    }


}
