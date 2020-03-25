package com.fitechsource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.LongSummaryStatistics;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FitechsourceTest {
    private static final int repeat = 100;
    private ArrayList<Long> list = new ArrayList<>();

    @RepeatedTest(repeat)
    void timeout() throws TestException {
        long sourceTestTimeout = getTimeout(new TestImpl());
        long customTestTimeout = getTimeout(new CustomThreadTestImpl());
        long diff = sourceTestTimeout - customTestTimeout;
        list.add(diff);
        assertTrue(diff > 0);
    }

    @AfterAll
    void getDiffAverage() {
        LongSummaryStatistics statistics = list.stream().mapToLong(x -> x).summaryStatistics();
        System.out.println("Average gain: " + statistics.getAverage()+"ms");
    }

    private long getTimeout(Test test) throws TestException {
        long before = System.currentTimeMillis();
        test.calculate();
        long after = System.currentTimeMillis();
        return after - before;
    }
}