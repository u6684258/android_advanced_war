package com.example.assignapp2019s1;

import com.example.assignapp2019s1.maps.MapMaker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public class MovementTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);

    public void test(String startpos, double remainedMovePoints, String des, Board currentBoard, boolean expect){
        boolean out = MainGame.canMoveThere(startpos,remainedMovePoints,des,currentBoard);
        assertTrue("Input was move from '" + startpos + "' to '" + des + "' and the remain move points is '" + remainedMovePoints + "' but got " + out, expect == out);
    }

    @Test
    public void passroad(){
        test("K0",2,"K3", new Board("map1"),false);
        test("B4",2,"C4",new Board("map1"), true);
    }

    @Test
    public void passwater(){
        test("B1",2,"C2", new Board("map1"),true);
        test("L4",3,"L5", new Board("map1"),true);
    }

    @Test
    public void passmountain(){
        test("B3",3,"D3", new Board("map1"),true);
        test("P5",3,"N5", new Board("map1"),false);
    }

    @Test
    public void passvariety(){
        test("O6",7,"L5", new Board("map1"),true);
        test("H0",7,"H4", new Board("map1"),true);
        test("G5",5,"K4", new Board("map1"),true);
        test("B2",4,"A6", new Board("map1"),false);
        test("F1",4,"K1", new Board("map1"),false);
    }
}