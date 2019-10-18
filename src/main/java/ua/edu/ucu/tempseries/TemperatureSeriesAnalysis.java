package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final double MINT = -273.0;
    private double[] temperatureSeries;
    private int length;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        this.length = 0;
    }

    public TemperatureSeriesAnalysis(double[] tempSeries) {
        this.temperatureSeries = new double[tempSeries.length];
        System.arraycopy(tempSeries, 0,
                this.temperatureSeries, 0, tempSeries.length);
        this.length = this.temperatureSeries.length;
        for (double item : this.temperatureSeries) {
            if (item < MINT) {
                throw new InputMismatchException();
            }
        }
    }

    public double average() {
        isEmpty();
        double averageValue = 0;
        for (double item : this.temperatureSeries) {
            averageValue += item;
        }
        return averageValue / this.length;
    }

    public double deviation() {
        isEmpty();
        double mean = average();
        double standardDeviation = 0;
        for (double item : this.temperatureSeries) {
            standardDeviation += (item - mean) * (item - mean);
        }

        return Math.sqrt(standardDeviation / (this.length - 1));
    }

    public double min() {
        isEmpty();
        double min = this.temperatureSeries[0];
        for (int i = 1; i < this.length; i++) {
            if (min > this.temperatureSeries[i]) {
                min = this.temperatureSeries[i];
            }
        }
        return min;
    }

    public double max() {
        isEmpty();
        double max = this.temperatureSeries[0];
        for (int i = 1; i < this.length; i++) {
            if (max < this.temperatureSeries[i]) {
                max = this.temperatureSeries[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        isEmpty();
        double closestToValue = this.temperatureSeries[0];
        for (int i = 1; i < this.length; i++) {
            if (Math.abs(closestToValue - tempValue) > Math.abs(
                    this.temperatureSeries[i] - tempValue)) {
                closestToValue = this.temperatureSeries[i];
            }
        }
        return closestToValue;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] tempsLessThan = new double[this.length + 1];
        int j = 0;
        for (int i = 0; i < this.length; i++) {
            if (this.temperatureSeries[i] < tempValue) {
                tempsLessThan[j] = this.temperatureSeries[i];
                j++;
            }
        }
        return tempsLessThan;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] tempsGreaterThan = new double[this.length + 1];
        int j = 0;
        for (int i = 0; i < this.length; i++) {
            if (this.temperatureSeries[i] > tempValue) {
                tempsGreaterThan[j] = this.temperatureSeries[i];
                j++;
            }
        }
        return tempsGreaterThan;
    }

    public void isEmpty() {
        if (this.length == 0) {
            throw new IllegalArgumentException();
        }
    }


    public TempSummaryStatistics summaryStatistics() {
        isEmpty();
        TempSummaryStatistics tss = new TempSummaryStatistics(
                average(), deviation(), max(), min());
        return tss;
    }

    public int addTemps(double... temps) {
        if (this.length + temps.length < this.temperatureSeries.length) {
            System.arraycopy(temps, 0,
                    this.temperatureSeries, this.length, temps.length);
        } else {
            double[] newTemperatureSeries = new double[(
                    this.length + temps.length) * 2];
            System.arraycopy(this.temperatureSeries, 0,
                    newTemperatureSeries, 0, this.length);
            System.arraycopy(temps, 0,
                    newTemperatureSeries, this.length, temps.length);
            this.temperatureSeries = newTemperatureSeries;
        }
        this.length += temps.length;
        return this.length;
    }
}