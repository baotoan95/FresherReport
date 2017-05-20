package com.fresher.report.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TraineeUtilsTest {
    @Test
    public void testComputeRank() {
	assertEquals("A", TraineeUtils.computeRank(8.4));
    }

}
