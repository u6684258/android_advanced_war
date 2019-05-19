package com.example.assignapp2019s1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        View v = findViewById(R.id.imageButton2);
        v.bringToFront();

    }
    public void pressStart(View v){
        Intent intent = new Intent(this, MapChoosing.class);
        startActivity(intent);
    }





}
