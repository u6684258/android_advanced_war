package com.example.assignapp2019s1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NeighborsInRangeTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);

    public void test(String pos, int range, ArrayList<String> expect){
        Board map = new Board("map1");
        ArrayList<String> out = map.neighborsInRange(pos, range);
        assertTrue("Input was '" + pos + "', expected " + expect + ", but got " + out, expect.equals(out));
    }

    @Test
    public void neighborsInRangeTest1(){
        ArrayList<String> ex = new ArrayList<>();
        ex.add("A3");
        ex.add("C3");
        ex.add("B2");
        ex.add("B4");

        test("B3",1,ex);
    }

    @Test
    public void neighborsInRangeTest2(){
        ArrayList<String> ex = new ArrayList<>();
        ex.add("E7");
        ex.add("G7");
        ex.add("F6");
        ex.add("D7");
        ex.add("E6");
        ex.add("H7");
        ex.add("G6");
        ex.add("F5");

        test("F7",2,ex);
    }

    @Test
    public void neighborsInRangeTest3(){
        ArrayList<String> ex = new ArrayList<>();
        ex.add("O0");
        ex.add("P1");
        ex.add("N0");
        ex.add("O1");
        ex.add("P2");
        ex.add("M0");
        ex.add("N1");
        ex.add("O2");
        ex.add("P3");

        test("P0",3,ex);
    }

}