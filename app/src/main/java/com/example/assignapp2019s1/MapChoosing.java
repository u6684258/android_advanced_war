package com.example.assignapp2019s1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MapChoosing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_choosing);
        View v = findViewById(R.id.map1Button);
        v.bringToFront();
    }

    public void onPress(View v) {
        Intent intent = new Intent(this, Game.class);
        intent.putExtra("Map", "map2");
        startActivity(intent);
    }
}
