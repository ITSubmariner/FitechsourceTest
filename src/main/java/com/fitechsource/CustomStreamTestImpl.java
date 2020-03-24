package com.fitechsource;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class CustomStreamTestImpl implements Test {
    private CopyOnWriteArraySet<Double> result = new CopyOnWriteArraySet<>();

    public void calculate() {
        Set<Double> res = new HashSet<>();
        IntConsumer consumer = i -> {
            try {
                result.addAll(TestCalc.calculate(i));
            } catch (TestException e) {
                e.printStackTrace();
            }
        };
        IntStream.range(0, TestConsts.N)
                .parallel()
                .forEach(consumer);
    }

    private class CustomThread implements Runnable {
        @Override
        public void run() {

        }
    }
}
