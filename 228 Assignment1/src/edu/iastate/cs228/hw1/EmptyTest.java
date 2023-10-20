package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyTest {

    Town t = new Town(3,4);
    Empty e = new Empty(t, 1, 1);

    //Tests to see if it returns Empty
    @Test
    void testCase() {
        assertEquals(e.who(), State.EMPTY);
    }
}
