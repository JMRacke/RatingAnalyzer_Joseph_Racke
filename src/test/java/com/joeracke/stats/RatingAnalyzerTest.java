/*
 * Author: Joseph M Racke
 * Program: This is the implementation class tester for
 * rating-analyzer-1.2.jar created for TLG Learning
 * Cohort 22.10BL AmazonSDE10
 * Date: 08 NOV 2022
 */

package com.joeracke.stats;

import org.junit.Before;
import org.junit.Test;
import org.stats.AnalyzerConfigurationException;
import org.stats.RatingAnalyzer;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RatingAnalyzerTest {

    int[] ints, ints2, ints3, ints4,
            negativeInts, posAndNegInts, nullIntArray,
            emptyIntArray;
    RatingAnalyzer analyzer, analyzer2, analyzer3, analyzer4,
            negIntAnalyzer, posAndNegAnalyzer, nullAnalyzer, emptyAnalyzer;

    @Before
    public void setUp() {
        ints = new int[]{2, 3, 4, 5, 6, 7, 8, 234, 392, 29, 50, 3579, 273, 28}; // Even number of numbers
        ints2 = new int[]{2, 2, 2}; // All the same
        ints3 = new int[]{1, 1, 2, 3, 3, 3, 4, 5, 5, 5}; // More than 1 number for the mode but not all
        ints4 = new int[]{23, 45, 23, 89, 34, 87435, 276, 7435, 823, 345, 2}; // Odd number of numbers
        negativeInts = new int[]{-234, -547, -23, -567, -2, -86, -876, -234, -12, -7}; // All negative numbers
        posAndNegInts = new int[]{0, 3, 3, 4, 5, 5, 5, -234, -547, -23, -567, -2, -86, -876, -234}; // Both Positive and Negative numbers
        emptyIntArray = new int[0]; // Empty Array
        analyzer = RatingAnalyzer.newInstance(ints);
        analyzer2 = RatingAnalyzer.newInstance(ints2);
        analyzer3 = RatingAnalyzer.newInstance(ints3);
        analyzer4 = RatingAnalyzer.newInstance(ints4);
        negIntAnalyzer = RatingAnalyzer.newInstance(negativeInts);
        posAndNegAnalyzer = RatingAnalyzer.newInstance(posAndNegInts);
    }

    @Test
    public void testIfTheCorrectMeanIsCalculatedWithVariousDifferentArrays() {
        assertEquals(330.0, analyzer.mean(), 0.01);
        assertEquals(2.0, analyzer2.mean(), 0.01);
        assertEquals(3.2, analyzer3.mean(), 0.01);
        assertEquals(8775.4545454545, analyzer4.mean(), 0.01);
        assertEquals(-258.8, negIntAnalyzer.mean(), 0.01);
        assertEquals(-169.6, posAndNegAnalyzer.mean(), 0.01);

    }

    @Test
    public void testIfMedianCalculatesCorrectlyWithVariousDifferentArrays() {
        assertEquals(18, analyzer.median(), 0.0);
        assertEquals(2.0, analyzer2.median(), 0.0);
        assertEquals(3.0, analyzer3.median(), 0.0);
        assertEquals(-160.0, negIntAnalyzer.median(), 0.0);
        assertEquals(-2.0, posAndNegAnalyzer.median(), 0.0);
    }

    @Test
    public void testIfMedianCalculatesCorrectlyWithAnOddNumberOfIntegersInTheArray() {
        assertEquals(89, analyzer4.median(), 0.0);
    }

    @Test
    public void testIfModeWorksWithTheModeBeingEveryNumber() {
        int[] expectedArray = new int[]{2, 3, 4, 5, 6, 7, 8, 392, 234, 273, 50, 3579, 28, 29};
        Arrays.sort(expectedArray);
        assertArrayEquals(expectedArray, analyzer.mode());
    }

    @Test
    public void testIfModeWorksWithTheModeBeingOneNumber() {
        int[] expectedArray = new int[]{2};
        assertArrayEquals(expectedArray, analyzer2.mode());
    }

    @Test
    public void testIfModeWorksWithTheModeMoreThanOneNumberButNotAll() {
        int[] expectedArray = new int[]{3, 5};
        assertArrayEquals(expectedArray, analyzer3.mode());
    }

    @Test
    public void testMeanMedianModeWithAllNegativeNumbers() {
        int[] expectedArray = new int[]{-234};
        assertArrayEquals(expectedArray, negIntAnalyzer.mode());
    }

    @Test
    public void testMeanMedianModeWithSomeNegativeNumbers() {
        int[] expectedArray = new int[]{5};
        assertArrayEquals(expectedArray, posAndNegAnalyzer.mode());
    }

    @Test(expected = AnalyzerConfigurationException.class)
    public void testForAnalyzerConfigurationExceptionForANullArray() {
        nullAnalyzer = RatingAnalyzer.newInstance(nullIntArray);
    }

    @Test(expected = AnalyzerConfigurationException.class)
    public void testForAnalyzerConfigurationExceptionForAnEmptyArray() {
        emptyAnalyzer = RatingAnalyzer.newInstance(emptyIntArray);
    }
}