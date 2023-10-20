package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutageTest {

    Town t = new Town(2,2);
    Outage o = new Outage(t, 0, 1);

    //Test to see if who() returns Outage
    @Test
    void testCase() {
        assertEquals(o.who(), State.OUTAGE);
    }
}
