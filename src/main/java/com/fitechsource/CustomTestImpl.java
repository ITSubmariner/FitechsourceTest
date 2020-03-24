package com.fitechsource;

import java.util.HashSet;
import java.util.Set;

public class CustomTestImpl implements Test {
    public void calculate() throws TestException {
        Set<Double> res = new HashSet<>();

        for (int i = 0; i < TestConsts.N; i++) {
            res.addAll(TestCalc.calculate(i));
        }

    }
}
