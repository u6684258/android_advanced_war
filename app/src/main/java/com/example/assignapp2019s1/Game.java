package com.example.assignapp2019s1;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
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
import com.example.assignapp2019s1.units.UnitSubType;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Game extends AppCompatActivity {

    int cursorLevel = 0;
    int unit_cursor = 0;
    boolean menuOn = false;
    protected TextView last_preview;
    ArrayList<String> units = new ArrayList<>();
    MediaPlayer bgm;

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
        units.add("Median tank");
        units.add("Arttilery");

        bgm = MediaPlayer.create(Game.this, R.raw.bgm1);
        bgm.setLooping(true);
        bgm.start();
    }

    public void button_up(View v){
        if (cursorLevel == 1)
            return;
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveLeft(mapView.cursor);
        Button b = findViewById(R.id.capture);
        if (b != null)
            b.setVisibility(View.GONE);
        buttonClickHandler();
        mapView.invalidate();
    }

    public void button_down(View v){
        if (cursorLevel == 1)
            return;
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveRight(mapView.cursor);
        Button b = findViewById(R.id.capture);
        if (b != null)
            b.setVisibility(View.GONE);
        buttonClickHandler();
        mapView.invalidate();
    }
    public void button_left(View v){
        if (cursorLevel == 1)
            return;
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveUp(mapView.cursor);
        Button b = findViewById(R.id.capture);
        if (b != null)
            b.setVisibility(View.GONE);
        buttonClickHandler();
        mapView.invalidate();
    }
    public void button_right(View v){
        if (cursorLevel == 1)
            return;
        MapView mapView = findViewById(R.id.mapView2);
        mapView.cursor = mapView.game.current.moveDown(mapView.cursor);
        Button b = findViewById(R.id.capture);
        if (b != null)
            b.setVisibility(View.GONE);
        buttonClickHandler();
        mapView.invalidate();
    }

    public void button_A(View v){
        MapView mapView = findViewById(R.id.mapView2);
        String cursor = mapView.cursor;
        Terrain t = mapView.game.current.map.get(cursor);
        Player p = mapView.game.whoseTurn == 1?mapView.game.player1:mapView.game.player2;

        if (t.getTerrainType() == TerrainType.Workshop && cursorLevel == 0 && !t.isOccupied()) {
            WorkShop w = (WorkShop) t;
            if (w.getOwner() != p)
                return;
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

        else if (cursorLevel == 1) {
            Unit x;
            Player cur = mapView.game.whoseTurn == 1?mapView.game.player1:mapView.game.player2;
            switch (unit_cursor) {
                case 0:
                    x = new Infantry(cur, cursor);
                    break;
                case 1:
                    x = new Tank(cur, cursor);
                    break;
                case 2:
                    x = new MediumTank(cur, cursor);
                    break;
                case 3:
                    x = new Recon(cur, cursor);
                    break;
                case 4:
                    x = new Artillery(cur, cursor);
                    break;
                default:
                    x = new Infantry(cur, cursor);
                    break;
            }
            if(!MainGame.deployUnit(x, cur, (WorkShop) mapView.game.current.map.get(cursor))){
                Toast.makeText(getApplicationContext(), "no enough money!", Toast.LENGTH_SHORT).show();
            }
            else {
            mapView.invalidate();
            Button b = findViewById(R.id.button_B);
            b.performClick();
            unit_cursor = 0;
        }}

        else if (cursorLevel == 0 && t.isOccupied() && t.getUnitHere().isCan_move()) {
            cursorLevel = 2;
            mapView.showMoveRange(t.getUnitHere());
            mapView.invalidate();
            Toast.makeText(getApplicationContext(), "showing move range", Toast.LENGTH_SHORT).show();
        }

        else if (cursorLevel == 2){
            if (MainGame.move(mapView.selected, cursor, mapView.game.current)) {
                cursorLevel = 0;
                mapView.finishShowMoveRange();
                mapView.invalidate();
            }
        }

        else if (cursorLevel == 3 && mapView.game.current.map.get(cursor).isOccupied()) {
            if (MainGame.getAttackRange(mapView.selected,mapView.game.current).contains(cursor)) {
                MainGame.attack(mapView.selected, mapView.game.current.map.get(cursor).getUnitHere(),mapView.game.current);
                Toast.makeText(getApplicationContext(), "fire!", Toast.LENGTH_SHORT).show();
                mapView.finishShowAttackRange();
                mapView.invalidate();
            }
            cursorLevel = 0;
        }
        if (t.isOccupied() && (t.getTerrainType() == TerrainType.City || t.getTerrainType() == TerrainType.HeadQuarters||
                t.getTerrainType() == TerrainType.Workshop)){
            City cur = (City) t;
            if (t.getUnitHere().getUnitSubType() == UnitSubType.Infantry &&
                    (((City) t).getOwner() == null || t.getUnitHere().getOwner() != ((City) t).getOwner())){
                Button b = findViewById(R.id.capture);
                b.setVisibility(View.VISIBLE);
            }
        }
        buttonClickHandler();

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
        Button b = findViewById(R.id.capture);
        if (b != null)
            b.setVisibility(View.GONE);
        buttonClickHandler();
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

    public void buttonEndTurn(View v) {
        if (cursorLevel != 0) {
            Toast.makeText(getApplicationContext(), "please finish current action first!", Toast.LENGTH_SHORT).show();
            return;
        }
        MapView mapView = findViewById(R.id.mapView2);
        mapView.game.switchTurn(mapView.game.current);
        Button b = findViewById(R.id.capture);
        if (b != null)
            b.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "player " + mapView.game.whoseTurn + "'s turn!", Toast.LENGTH_SHORT).show();
        mapView.invalidate();
    }

    public void button_capture(View v) {
        MapView mapView = findViewById(R.id.mapView2);
        mapView.game.capture(mapView.game.current.map.get(mapView.cursor).getUnitHere(),(City) mapView.game.current.map.get(mapView.cursor));
        mapView.invalidate();
        Button b = findViewById(R.id.capture);
        if (b != null)
            b.setVisibility(View.GONE);
        mapView.finishShowMoveRange();
        Toast.makeText(getApplicationContext(), "capture!", Toast.LENGTH_SHORT).show();
        mapView.game.checkGameOver();
        buttonClickHandler();
    }


    public void buttonClickHandler() {

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.outside_frame);
        if (last_preview != null){
            last_preview.setVisibility(View.GONE);
        }
        MapView mapView = findViewById(R.id.mapView2);
        TextView textView = new TextView(this);
        String t = mapView.game.current.map.get(mapView.cursor).getTerrainType().toString();
        if (mapView.game.current.map.get(mapView.cursor).getTerrainType() == TerrainType.City ||
                mapView.game.current.map.get(mapView.cursor).getTerrainType() == TerrainType.Workshop ||
                mapView.game.current.map.get(mapView.cursor).getTerrainType() == TerrainType.HeadQuarters){
            City city = (City) mapView.game.current.map.get(mapView.cursor);
            t += "capture remained: " + city.getCapturescore();
        }
        if (mapView.game.current.map.get(mapView.cursor).getUnitHere() != null) {
            Unit u = mapView.game.current.map.get(mapView.cursor).getUnitHere();
            t += "unit: " + u.getUnitType() + " HP:" + u.getHitpoints()+ " movable:" + u.isCan_fire() + " mobility:" +
                    u.getMovePoint() + " attack range:" + u.getAttackRange();
        }
        if (cursorLevel == 1){
            t = "current money:" + (mapView.game.whoseTurn == 1?mapView.game.player1.money:mapView.game.player2.money);
        }
        textView.setText(t);
        textView.setBackgroundColor(Color.parseColor("#bdbdbd"));
        textView.setPadding(10,10,10,10);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 910, 5, 0);
        params.alignWithParent = true;
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        relativeLayout.addView(textView);
        last_preview = textView;

        textView.setVisibility(View.VISIBLE);

        if (menuOn) {
            Button b = findViewById(R.id.back);
            b.performClick();
        }


        if (!mapView.game.gameStart) {
            TextView gameover = findViewById(R.id.gameOver);
            t = "PLAYER " + mapView.game.whoseTurn + " WIN!";
            gameover.setText(t);
            gameover.setVisibility(View.VISIBLE);
            Button b = findViewById(R.id.purse);
            mapView.setAlpha(0.5f);
            b.performClick();
            textView.setVisibility(View.GONE);
        }
    }

    public void button_MainMenu(View v) {
        bgm.release();
        bgm = null;
        Intent intent = new Intent(this, FullscreenActivity.class);
        startActivity(intent);
    }

    public void button_mute(View v) {
        if (bgm.isPlaying())
            bgm.pause();
        else
            bgm.start();
    }

    public void button_purse(View v) {
        LinearLayout l = findViewById(R.id.menu);
        menuOn = true;
        l.setVisibility(View.VISIBLE);
    }
    public void button_back(View v) {
        LinearLayout l = findViewById(R.id.menu);
        menuOn = false;
        l.setVisibility(View.GONE);
    }


}
