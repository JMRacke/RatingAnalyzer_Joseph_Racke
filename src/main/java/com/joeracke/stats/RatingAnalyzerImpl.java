/*
 * Author: Joseph M Racke
 * Program: This is the implementation class for rating-analyzer-1.2.jar
 * created for TLG Learning Cohort 22.10BL AmazonSDE10
 * Date: 08 NOV 2022
 */

package com.joeracke.stats;

import org.stats.AnalyzerConfigurationException;
import org.stats.RatingAnalyzer;

import java.util.*;

public class RatingAnalyzerImpl implements RatingAnalyzer {

    // Fields
    private int[] intArray;

    // Constructor
    public RatingAnalyzerImpl(int[] ratings)
            throws IllegalArgumentException {

        if (ratings.length == 0) {
            throw new AnalyzerConfigurationException(
                    "Array can't be empty or null!", new IllegalArgumentException());
        } else {
            Arrays.sort(ratings);
            setIntArray(ratings);
        }
    }

    //Business Methods

    /*
     *  The mean() takes the intArray field and adds up all the numbers
     * then it divides that total by the # of integers in the array to
     * get the average (or mean) and returns it.
     */
    @Override
    public double mean() {
        int sum = 0;
        double result;
        int[] intArray = getIntArray();
        for (int i : intArray) {
            sum += i;
        }
        result = (double) sum / intArray.length;
        return result;
    }

    /*
     * The median() takes the intArray field and then determines the median
     * number, or the middle most index. If the array has an even amount of
     * numbers then it will get the average of those two numbers and return
     * that as the median.
     */
    @Override
    public double median() {

        int[] intArray = getIntArray();
        double median;
        if (intArray.length % 2 == 0) {
            int m1, m2;

            // Highest number in the lowest half
            m1 = intArray[(intArray.length / 2) - 1];
            // Lowest number in the highest half
            m2 = intArray[(intArray.length / 2)];

            median = (double) (m1 + m2) / 2;
        } else {
            median = intArray[intArray.length / 2];
        }
        return median;
    }

    /*
     * The mode() method takes the intArray field and passes it into a hashMap
     * to get the set of unique numbers in the array and stores values to each
     * for each time that number occurs in the array. Each step in the process
     * is described before the steps below.
     */
    @Override
    public int[] mode() {
        HashMap<Integer, Integer> mapOfIntegers = new HashMap<>();
        int count = 0;

        /*
         * Read through the intArray field and add each number as a key
         * to the hashmap "mapOfIntegers" and set the value to 1 (for 1 occurrence).
         * If the key is already in the hashmap, then it increments the value of that
         * key by 1 more. Immediately following the for-loop, if all keys were unique,
         * it sets the count to 1. The count keeps track of the highest value of all
         * the keys (which any key with that value will be the mode).
         */
        for (int j : getIntArray()) {
            if (mapOfIntegers.get(j) != null) {
                int temp = mapOfIntegers.get(j);
                temp++;
                mapOfIntegers.put(j, temp);

                if (temp > count) {
                    count = temp;
                }
            } else {
                mapOfIntegers.put(j, 1);
            }
        }
        if (count == 0) {
            count = 1;
        }

        /*
         * This List and for-loop goes through the hashmap and takes each entrySet
         * and checks the value as compared to the highest count. If that key's value
         * is equal to the highest count it adds it to the list.
         */
        List<Integer> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : mapOfIntegers.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value == count) {
                resultList.add(key);
            }
        }

        /*
         * This portion takes the List from above and converts it to an int array to
         * return to the calling method. At this point the only items that should be
         * in the list are the one or more numbers that make up the mode.
         */
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }

        /*
         * Added sort because I found that when there are many numbers to add to the
         * Hashmap, Java wasn't always placing them in the order received.
         */
        Arrays.sort(result);
        return result;
    }

    // Accessors
    public int[] getIntArray() {
        return intArray;
    }

    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    // toString()
    @Override
    public String toString() {
        return Object.class.getSimpleName() +
                " contains the intArray=" + Arrays.toString(intArray) +
                '}';
    }
}
