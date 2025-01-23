package com.nhnacademy.stopwatch;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest implements PerformanceTestable {

    @Override
    public void test() {
        List<Integer> integerList = new ArrayList<>();
        for(int i=0; i<100000000; i++){
            integerList.add(i);
        }
    }
}
