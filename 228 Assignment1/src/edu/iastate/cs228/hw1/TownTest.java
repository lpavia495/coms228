package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TownTest {

   
        Town t = new Town(5,5);

//checks the length and width
    @Test
    void testCase() {
    assertEquals(t.getWidth(),5);
    assertEquals(t.getLength(),5);
    }
}
