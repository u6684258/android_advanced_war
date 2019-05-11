package com.example.assignapp2019s1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class MapView extends View {

    MainGame game;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void showMap(String map) {
        game = new MainGame(map);

    }
}
