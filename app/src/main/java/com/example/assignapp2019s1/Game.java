package com.example.assignapp2019s1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignapp2019s1.terrains.City;
import com.example.assignapp2019s1.terrains.Terrain;
import com.example.assignapp2019s1.terrains.TerrainType;
import com.example.assignapp2019s1.terrains.WorkShop;
import com.example.assignapp2019s1.units.Artillery;
import com.example.assignapp2019s1.units.Infantry;
import com.example.assignapp2019s1.units.MediumTank;
import com.example.assignapp2019s1.units.Recon;
import com.example.assignapp2019s1.units.Tank;
import com.example.assignapp2019s1.units.Unit;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Game extends AppCompatActivity {

    int cursorLevel = 0;
    int unit_cursor = 0;
    protected TextView last_preview;
    ArrayList<String> units = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        MapView mapView = findViewById(R.id.mapView2);
        Bundle extra = getIntent().getExtras();
        mapView.showMap(extra.getString("Map"));
        units.add("Infantry");
        units.add("Tank");
        units.add("Recon");
        units.add("MEdian tank");
        units.add("Arttilery");
    }

    public void button_up(View v){
        if (cursorLevel == 1)
            return;
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveLeft(mapView.cursor);
        buttonClickHandler();
        mapView.invalidate();
    }

    public void button_down(View v){
        if (cursorLevel == 1)
            return;
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveRight(mapView.cursor);
        buttonClickHandler();
        mapView.invalidate();
    }
    public void button_left(View v){
        if (cursorLevel == 1)
            return;
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveUp(mapView.cursor);
        buttonClickHandler();
        mapView.invalidate();
    }
    public void button_right(View v){
        if (cursorLevel == 1)
            return;
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveDown(mapView.cursor);
        buttonClickHandler();
        mapView.invalidate();
    }

    public void button_A(View v){
        MapView mapView = findViewById(R.id.mapView2);
        String cursor = mapView.cursor;
        Terrain t = mapView.game.current.map.get(cursor);

        if (t.getTerrainType() == TerrainType.Workshop && cursorLevel == 0 && !t.isOccupied()) {
            LinearLayout l = findViewById(R.id.build_unit);
            l.setVisibility(View.VISIBLE);
            for (String x : units) {
                TextView txt = new TextView(this);
                txt.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                txt.setText(x);
                l.addView(txt);
            }
            cursorLevel++;
            Button b = findViewById(R.id.above);
            b.setVisibility(View.VISIBLE);
            b = findViewById(R.id.below);
            b.setVisibility(View.VISIBLE);
            b.performClick();
            b = findViewById(R.id.up);
            b.setVisibility(View.GONE);
            b = findViewById(R.id.down);
            b.setVisibility(View.GONE);
            b = findViewById(R.id.left);
            b.setVisibility(View.GONE);
            b = findViewById(R.id.right);
            b.setVisibility(View.GONE);
        }
        // TODO: 2019-05-17 change player
        else if (cursorLevel == 1) {
            Unit x;
            switch (unit_cursor) {
                case 0:
                    x = new Infantry(mapView.game.player1, cursor);
                    break;
                case 1:
                    x = new Tank(mapView.game.player1, cursor);
                    break;
                case 2:
                    x = new MediumTank(mapView.game.player1, cursor);
                    break;
                case 3:
                    x = new Recon(mapView.game.player1, cursor);
                    break;
                case 4:
                    x = new Artillery(mapView.game.player1, cursor);
                    break;
                default:
                    x = new Infantry(mapView.game.player1, cursor);
                    break;
            }
            MainGame.deployUnit(x, mapView.game.current, mapView.game.player1, (WorkShop) mapView.game.current.map.get(cursor));
            mapView.invalidate();
            Button b = findViewById(R.id.button_B);
            b.performClick();
        }

        else if (cursorLevel == 0 && t.isOccupied() && t.getUnitHere().isCan_move()) {
            cursorLevel = 2;
            mapView.showMoveRange(t.getUnitHere());
            mapView.invalidate();
            Toast.makeText(getApplicationContext(), "showing move range", Toast.LENGTH_SHORT).show();
        }

        else if (cursorLevel == 2){
            if (MainGame.move(mapView.selected, cursor, mapView.game.current)) {
                mapView.finishShowMoveRange();
                cursorLevel = 0;
                mapView.invalidate();
            }
        }

    }
    public void button_B(View v){
        MapView mapView = findViewById(R.id.mapView2);
        String cursor = mapView.cursor;
        Terrain t = mapView.game.current.map.get(cursor);

        if (t.getTerrainType() == TerrainType.Workshop && cursorLevel == 1) {
            LinearLayout l = findViewById(R.id.build_unit);
            l.removeAllViewsInLayout();
            l.setVisibility(View.GONE);
            cursorLevel--;
            Button b = findViewById(R.id.above);
            b.setVisibility(View.GONE);
            b = findViewById(R.id.below);
            b.setVisibility(View.GONE);
            b = findViewById(R.id.up);
            b.setVisibility(View.VISIBLE);
            b = findViewById(R.id.down);
            b.setVisibility(View.VISIBLE);
            b = findViewById(R.id.left);
            b.setVisibility(View.VISIBLE);
            b = findViewById(R.id.right);
            b.setVisibility(View.VISIBLE);
        }
        else if (cursorLevel == 2) {
            cursorLevel = 0;
            mapView.finishShowMoveRange();
            mapView.invalidate();
        }

        else if (cursorLevel == 0 && t.isOccupied() && t.getUnitHere().isCan_fire()) {
            cursorLevel = 3;
            mapView.showAttackRange(t.getUnitHere());
            mapView.invalidate();
            Toast.makeText(getApplicationContext(), "showing attack range", Toast.LENGTH_SHORT).show();
        }
        else if (cursorLevel == 3) {
            cursorLevel = 0;
            mapView.finishShowAttackRange();
            mapView.invalidate();
        }
    }

    public void button_above(View v) {
        if (unit_cursor <=0)
            return;
        LinearLayout l = findViewById(R.id.build_unit);
        TextView cur = (TextView) l.getChildAt(unit_cursor);
        cur.setBackgroundResource(0);
        unit_cursor--;
        cur = (TextView) l.getChildAt(unit_cursor);
        cur.setBackgroundColor(Color.parseColor("#555555"));

    }

    public void button_below(View v) {
        LinearLayout l = findViewById(R.id.build_unit);
        if (unit_cursor >= l.getChildCount()-1)
            return;
        TextView cur = (TextView) l.getChildAt(unit_cursor);
        cur.setBackgroundResource(0);
        unit_cursor++;
        cur = (TextView) l.getChildAt(unit_cursor);
        cur.setBackgroundColor(Color.parseColor("#555555"));
    }

    public void Button_end_turn(View v) {
        MapView mapView = findViewById(R.id.mapView2);
        //todo: make sure this is right
        mapView.game.switchTurn();
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
        if (mapView.game.current.map.get(mapView.cursor).getTerrainType() == TerrainType.City){
            City city = (City) mapView.game.current.map.get(mapView.cursor);
            t += "capture remained: " + ((City) mapView.game.current.map.get(mapView.cursor)).getCapturescore();
        }
        if (mapView.game.current.map.get(mapView.cursor).getUnitHere() != null) {
            Unit u = mapView.game.current.map.get(mapView.cursor).getUnitHere();
            t += "unit: " + u.getUnitType() + " movable:" + u.isCan_move() + " mobility:" +
                    u.getMobility() + " attack range:" + u.getAttackRange();
        }
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
