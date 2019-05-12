package com.example.assignapp2019s1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public class DistanceTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);

    public void test(String inpos, String indes, int expect){
        int out = Board.distance(inpos,indes);
        assertTrue("Input was '" + inpos +"," + indes + "', expected "+ expect + " but got "+out, expect == out);
    }

    @Test
    public void distanceupperlefttolowerright(){
        test("A2","D7",8);
        test("E0","N5",14);
        test("I5","L6",4);
    }

    @Test
    public void distanceupperrighttolowerleft(){
        test("B4","H2",8);
        test("G7","P0",16);
        test("K5","M4",3);
    }

    @Test
    public void distancelowerlefttoupperright(){
        test("O1","A5",18);
        test("F3","C6",6);
        test("J5","I7",3);
    }

    @Test
    public void distancesamerow(){
        test("G3","G6",3);
        test("J7","J2",5);
    }

    @Test
    public void distancesamecolumn(){
        test("B2","M2",11);
        test("E5","A5",4);
    }

}