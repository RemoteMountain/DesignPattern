package com.dp.bowling;

import com.dp.bowling.Frame;
import junit.framework.TestCase;


public class TestFrame extends TestCase {

    public TestFrame(String name){
        super(name);
    }

    public void testScoreNoThrows(){
        Frame frame = new Frame();
        assertEquals(0,frame.getScore());
    }

    public void testAddOneThrow(){
        Frame frame = new Frame();
        frame.add(5);
        assertEquals(5,frame.getScore());

    }

}
