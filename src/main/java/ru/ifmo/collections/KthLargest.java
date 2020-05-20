package ru.ifmo.collections;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Design a class to find the kth largest element in a stream. k is from 1 to numbers.length.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Constructor accepts an integer k and an integer array numbers, which contains initial elements from the stream.
 * For each call to the method KthLargest.add(), return the element representing the kth largest element in the stream.
 */
public class KthLargest {
    private ArrayList<Integer> sortedList;
    private Integer serialNumber;
    public KthLargest(int k, int[] numbers) {
        serialNumber = --k;
        sortedList = new ArrayList<>();
        for(int tempNumber : numbers) {
            sortedList.add(tempNumber);
        }
    }

    public int add(int val) {
        sortedList.add(val);
        sortedList.sort(Collections.reverseOrder());
        return sortedList.get(serialNumber);
    }
}