package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResellerTest {

    Town t = new Town(2,3);
    Reseller r = new Reseller(t, 2, 2);

    //Test to see if it returns Reseller
    @Test
    void testCase() {
        assertEquals(r.who(), State.RESELLER);
    }
}
