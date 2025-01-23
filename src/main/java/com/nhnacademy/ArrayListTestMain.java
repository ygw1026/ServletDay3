package com.nhnacademy;

import com.nhnacademy.ArrayListTest;
import com.nhnacademy.ArrayListTestProxy;
import com.nhnacademy.PerformanceTestable;

public class ArrayListTestMain {
    public static void main(String[] args) {
        PerformanceTestable performanceTestable = new ArrayListTest();
        ArrayListTestProxy arrayListTestProxy = new ArrayListTestProxy(performanceTestable);
        arrayListTestProxy.test();
    }
}
