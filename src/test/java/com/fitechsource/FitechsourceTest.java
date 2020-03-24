package com.fitechsource;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FitechsourceTest {
    private static final int repeat = 1;
    private List<Long> list = new ArrayList<>();

    @RepeatedTest(repeat)
    @Order(1)
    void timeout() throws TestException {
        long sourceTestTimeout = getTimeout(new TestImpl());
        long customTestTimeout = getTimeout(new CustomTestImpl());
        long diff = customTestTimeout - sourceTestTimeout;
        list.add(diff);
        assertTrue(diff > 0);
    }

    @org.junit.jupiter.api.Test
    @Order(2)
    void getDiffAverage() {
        LongSummaryStatistics statistics = list.stream().mapToLong(x -> x).summaryStatistics();
        System.out.println(statistics);
    }

    private long getTimeout(Test test) throws TestException {
        long before = System.currentTimeMillis();
        test.calculate();
        long after = System.currentTimeMillis();
        return after - before;
    }
}