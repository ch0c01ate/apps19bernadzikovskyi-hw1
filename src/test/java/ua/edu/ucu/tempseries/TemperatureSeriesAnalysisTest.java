package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

public class TemperatureSeriesAnalysisTest {

    public double[] arr;
    public TemperatureSeriesAnalysis seriesAnalysis;
    public double[] arrEmpty;
    public TemperatureSeriesAnalysis seriesAnalysisEmpty;

    @Before
    public void init() {
        this.arr = new double[]{100.0, -100.0, 123.0, 0.0, -123.0, -5.0, 5.0};
        this.arrEmpty = new double[]{};
        this.seriesAnalysis = new TemperatureSeriesAnalysis(this.arr);
        this.seriesAnalysisEmpty = new TemperatureSeriesAnalysis(this.arrEmpty);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        seriesAnalysisEmpty.average();
    }

    @Test
    public void testAverage() {
        double expResult = 0.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double expResult = 91.5678254992804;
        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double expResult = -123.0;
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double expResult = 123.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(122.0);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double expResult = 0.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] expResult = new double[8];
        expResult[0] = -100.0;
        expResult[1] = -123.0;
        expResult[2] = -5.0;
        double[] actualResult = seriesAnalysis.findTempsLessThen(0.0);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] expResult = new double[8];
        expResult[0] = 100.0;
        expResult[1] = 123.0;
        expResult[2] = 5.0;
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(0.0);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        double[] expResult = new double[]{0.0, 91.5678254992804, 123.0, -123.0};
        TempSummaryStatistics tsum = seriesAnalysis.summaryStatistics();
        double[] actualResult = {tsum.getAvgTemp(), tsum.getDevTemp(), tsum.getMaxTemp(), tsum.getMinTemp()};
        assertArrayEquals(expResult, actualResult, 0.00001);
    }
}
