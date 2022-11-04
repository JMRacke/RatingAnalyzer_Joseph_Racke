package com.joeracke.stats;

import org.stats.AnalyzerConfigurationException;
import org.stats.RatingAnalyzer;

import java.util.*;

public class RatingAnalyzerImpl implements RatingAnalyzer {

    private int[] intArray;

    public RatingAnalyzerImpl(int[] ratings)
        /*throws IllegalArgumentException*/ {
        // Add code here

        if (ratings.equals(null) || ratings.length == 0) {
            throw new AnalyzerConfigurationException("Array can't be empty or null!", new IllegalArgumentException());
        } else {
            setIntArray(ratings);
        }
    }

    //Business Methods
    @Override
    public double mean() {
        int sum = 0;
        double result = 0.0;
        for (int i : intArray) {
            sum += i;
        }
        result = (double) sum / intArray.length;
        return result;
    }

    @Override
    public double median() {
        Arrays.sort(intArray);
        double median;
        if(intArray.length % 2 == 0) {
            int m1,m2;
            m1 = intArray[(intArray.length / 2) - 1]; // Highest number in the lowest half
            m2 = intArray[(intArray.length / 2)]; // Lowest number in the highest half
            median = (double) (m1 + m2) / 2;
        } else {
            median = intArray[intArray.length / 2];
        }
        return median;
    }

    @Override
    public int[] mode() {
        HashMap<Integer,Integer> mapOfIntegers = new HashMap<>();
        int count = 0;
        int keyNumber = 0;
        for(int i = 0; i < intArray.length; i++) {
            if(mapOfIntegers.get(intArray[i]) != null) {
                int temp = mapOfIntegers.get(intArray[i]);
                temp++;
                mapOfIntegers.put(intArray[i], temp);

                if(temp > count) {
                    count = temp;
                    keyNumber = intArray[i];
                }
            } else {
                mapOfIntegers.put(intArray[i],1);
            }
        }
        if(count == 0) { count = 1; };

        List<Integer> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : mapOfIntegers.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if(value == count) {
                resultList.add(key);
            }
        }
//        for(int i = 0; i < mapOfIntegers.size(); i++) {
//            if(mapOfIntegers.get(intArray[i]) == count) {
//                resultList.add();
//            }
//        }

        int[] result = new int[resultList.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }


        return result;
    }

    // Accessors

    public int[] getIntArray() {
        return intArray;
    }

    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }
}
