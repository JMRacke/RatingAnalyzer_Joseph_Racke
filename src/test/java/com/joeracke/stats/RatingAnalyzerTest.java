package com.joeracke.stats;

import org.junit.Before;
import org.junit.Test;
import org.stats.RatingAnalyzer;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RatingAnalyzerTest {

    int[] ints, ints2, ints3, ints4;
    RatingAnalyzer analyzer, analyzer2, analyzer3, analyzer4;

    @Before
    public void setUp() throws Exception {
        ints = new int[]{2, 3, 4, 5, 6, 7, 8, 234, 392, 29, 50, 3579, 273, 28}; // Even number of numbers
        ints2 = new int[]{2, 2, 2}; // All the same
        ints3 = new int[]{1, 1, 2, 3, 3, 3, 4, 5, 5, 5}; // More than 1 number for the mode but not all
        ints4 = new int[]{23, 45, 23, 89, 34, 87435, 276, 7435, 823, 345, 2}; // Odd number of numbers
        analyzer = RatingAnalyzer.newInstance(ints);
        analyzer2 = RatingAnalyzer.newInstance(ints2);
        analyzer3 = RatingAnalyzer.newInstance(ints3);
        analyzer4 = RatingAnalyzer.newInstance(ints4);
    }

    @Test
    public void mean() {
        assertEquals(329.92857142857, analyzer.mean(), 0.001);
    }

    @Test
    public void testIfMedianCalculatesCorrectlyWithAnEvenNumberOfIntegersInTheArray() {
        assertEquals(18, analyzer.median(), 0.0);
    }

    @Test
    public void testIfMedianCalculatesCorrectlyWithAnOddNumberOfIntegersInTheArray() {
        assertEquals(89, analyzer4.median(), 0.0);
    }

    @Test
    public void testIfModeWorksWithTheModeBeingEveryNumber() {
        int[] expectedArray = new int[]{2, 3, 4, 5, 6, 7, 8, 234, 392, 29, 50, 3579, 273, 28};
        assertTrue(Arrays.equals(expectedArray, analyzer.mode()));
    }

    @Test
    public void testIfModeWorksWithTheModeBeingOneNumber() {
        int[] expectedArray = new int[]{2};
        assertTrue(Arrays.equals(expectedArray, analyzer2.mode()));
    }

    @Test
    public void testIfModeWorksWithTheModeMoreThanOneNumberButNotAll() {
        int[] expectedArray = new int[]{3, 5};
        assertTrue(Arrays.equals(expectedArray, analyzer3.mode()));
    }
}