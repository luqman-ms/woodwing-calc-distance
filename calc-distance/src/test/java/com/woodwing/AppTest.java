package com.woodwing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.woodwing.services.DistanceService;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testGetDistanceSumInMeters() {
        DistanceService service = new DistanceService();
        String result = service.getDistanceSum("yards", 3, "meters", 5, "meters");
        assertEquals("7.74 meters", result);
    }

    @Test
    public void testGetDistanceSumInYards() {
        DistanceService service = new DistanceService();
        String result = service.getDistanceSum("yards", 3, "meters", 5, "yards");
        assertEquals("8.47 yards", result);
    }
}
