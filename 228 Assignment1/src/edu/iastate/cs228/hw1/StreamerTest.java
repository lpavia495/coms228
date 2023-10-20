package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamerTest {

    Town t = new Town(4,5);
    Streamer s = new Streamer(t, 3, 4);

    //Tests if who() returns Streamer
    @Test
    void testCase() {
        assertEquals(s.who(), State.STREAMER);
    }
}
