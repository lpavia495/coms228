package edu.iastate.cs228.hw1;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CasualTest {
    Town t = new Town(3,3);
    Casual c = new Casual(t, 3, 2);

//Tests to see if it returns casual which it should
    @Test
    void testCase() {
            assertEquals(c.who(), State.CASUAL);
    }
}
