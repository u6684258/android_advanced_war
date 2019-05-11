package com.example.assignapp2019s1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable d = ResourcesCompat.getDrawable(getResources(),R.drawable.city_blue, null);
        d.setBounds(10,10,110,110);
        d.draw(canvas);
        Drawable dc = ResourcesCompat.getDrawable(getResources(),R.drawable.city_red, null);
        dc.setBounds(110,10,210,110);
        dc.draw(canvas);
    }


}
