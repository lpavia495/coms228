package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ISPBusinessTest {

    Town t = new Town(4,4);


//Checks if the profit return matches the actual
    @Test
    void testCase() {
        t.randomInit(5);
        assertEquals(ISPBusiness.getProfit(t),4);
    }
}
