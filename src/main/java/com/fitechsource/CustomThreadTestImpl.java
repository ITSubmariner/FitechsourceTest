package com.fitechsource;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;

public class CustomThreadTestImpl implements Test {
    private ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue();
    private CopyOnWriteArraySet<Double> result = new CopyOnWriteArraySet<>();

    public CustomThreadTestImpl() {
        IntStream.range(0, TestConsts.N)
                .forEachOrdered(i -> queue.add(i));
    }

    @Override
    public void calculate() throws TestException {

    }

    private class CustomThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                Integer i = queue.poll();
                if (i == null) {
                    result.addAll(TestCalc.calculate(i));
                } else {
                    return;
                }
            }
        }
    }

}
