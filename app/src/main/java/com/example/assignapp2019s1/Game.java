package com.example.assignapp2019s1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Game extends AppCompatActivity {

    protected TextView last_preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        MapView mapView = findViewById(R.id.mapView2);
        Bundle extra = getIntent().getExtras();
        mapView.showMap(extra.getString("Map"));
    }

    public void button_left(View v){
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveLeft(mapView.cursor);
        buttonClickHandler();
        mapView.invalidate();
    }
    public void button_right(View v){
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveRight(mapView.cursor);
        buttonClickHandler();
        mapView.invalidate();
    }
    public void button_up(View v){
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveUp(mapView.cursor);
        buttonClickHandler();
        mapView.invalidate();
    }
    public void button_down(View v){
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveDown(mapView.cursor);
        buttonClickHandler();
        mapView.invalidate();
    }

    public void button_A(View v){
        MapView mapView = findViewById(R.id.mapView2);

    }
    public void button_B(View v){
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveDown(mapView.cursor);
        mapView.invalidate();
    }

    public void buttonClickHandler() {

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.outside_frame);
        if (last_preview != null){
            last_preview.setVisibility(View.GONE);
        }
        MapView mapView = findViewById(R.id.mapView2);
        TextView textView = new TextView(this);
        String t = "defence: " + mapView.game.current.map.get(mapView.cursor).getDefenceRating();
        textView.setText(t);
        textView.setBackgroundColor(Color.parseColor("#bdbdbd"));
        textView.setPadding(10,10,10,10);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 15, 5, 0);
        params.alignWithParent = true;
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        relativeLayout.addView(textView);
        last_preview = textView;
    }



}
