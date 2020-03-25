package com.fitechsource;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/*Main solution. Average gain (100 repetitions) = 3616.65ms*/
public class CustomThreadTestImpl implements Test {
    private volatile boolean flag = true;
    private volatile boolean error = false;
    private CountDownLatch latch = new CountDownLatch(TestConsts.MAX_THREADS);
    private Queue<Integer> queue = new ConcurrentLinkedQueue<>();
    private Set<Double> result = Collections.synchronizedSet(new HashSet<>());

    public CustomThreadTestImpl() {
        IntStream.range(0, TestConsts.N)
                .forEachOrdered(i -> queue.add(i));
    }

    @Override
    public void calculate() {
        try {
            for (int i = 0; i < TestConsts.MAX_THREADS; i++) {
                new Thread(new CustomThread()).start();
            }
            latch.await();
            if (!error) {
                System.out.println(result);
            }
        } catch (InterruptedException e) {
            System.out.println("Unexpected interruption");
        }
    }

    private class CustomThread implements Runnable {
        @Override
        public void run() {
            try {
                while (flag) {
                    Integer i = queue.remove();
                    Set<Double> s = TestCalc.calculate(i);
                    result.addAll(s);
                }
            } catch (NoSuchElementException e) {
                flag = false;
            } catch (TestException e) {
                System.out.println(e.getMessage());
                flag = false;
                error = true;
            } finally {
                latch.countDown();
            }
        }
    }

}
