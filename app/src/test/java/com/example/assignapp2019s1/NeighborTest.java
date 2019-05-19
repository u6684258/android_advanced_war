package com.example.assignapp2019s1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;

import static org.junit.Assert.*;

/*
According to the code in Board:
    the definition for 'Neighbor' is all positions getting from moving up, down, left or right
    if 'Neighbor' is still on board.
Then,
    lengthtest: testing how mant 'Neighbors' it has. On the corner is 2, on the side is 3 and in the middle is 4
    distance_test: testing whether the distance between these 'Neighbors' and the position is 1
 */
public class NeighborTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);

    private void length(String pos) {
        Board map = new Board("map1");
        int expect;
        int out = map.neighbors(pos).size();
        if (pos.charAt(0)> 65 && pos.charAt(0) < 80 && pos.charAt(1)>'0' && pos.charAt(1)<'7'){
            expect = 4;
        }
        else if (pos.charAt(0) == 65 && pos.charAt(1) == '0'){
            expect = 2;
        }
        else if (pos.charAt(0) == 80 && pos.charAt(1) == '7'){
            expect = 2;
        }
        else if (pos.charAt(0) == 65 && pos.charAt(1) == '7'){
            expect = 2;
        }
        else if (pos.charAt(0) == 80 && pos.charAt(1) == '0'){
            expect = 2;
        }
        else{
            expect = 3;
        }

        assertTrue("Input was '" + pos + "', expected " + expect + ", but got " + out, expect == out);
    }

    @Test
    public void lengthtest(){
        Board map = new Board("map1");

        for (String pos : map.map.keySet()){
            length(pos);
        }
    }

    private void distance_(String neigh, String pos){
        Board map = new Board("map1");
        int expect = 1;

        int out = Board.distance(neigh,pos);
        assertTrue("The input neighbor position was '" + neigh + "', expected distance is "+ expect + ", but got " + out, expect == out);
    }

    @Test
    public void distance_test(){
        Board map = new Board("map1");
        for(String pos: map.map.keySet()){
            for(String neigh : map.neighbors(pos)){
                distance_(neigh, pos);
            }
        }
    }

}