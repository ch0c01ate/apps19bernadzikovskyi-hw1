package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    public double[] temperatureSeries;
    public int length;
    public static double MINT = -273.0;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        this.length = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
        this.length = this.temperatureSeries.length;
        for (double item : this.temperatureSeries) {
            if (item < MINT) {
                throw new InputMismatchException();
            }
        }
    }

    public double average() {
        isEmpty();
        double average_value = 0;
        for (double item : this.temperatureSeries) {
            average_value += item;
        }
        return average_value / this.length;
    }

    public double deviation() {
        isEmpty();
        double mean = average();
        double standard_deviation = 0;
        for (double item : this.temperatureSeries) {
            standard_deviation += (item - mean) * (item - mean);
        }

        return Math.sqrt(standard_deviation / (this.length - 1));
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
        TempSummaryStatistics tss = new TempSummaryStatistics(average(), deviation(), max(), min());
        return tss;
    }

    public int addTemps(double... temps) {
        if (this.length + temps.length < this.temperatureSeries.length) {
            System.arraycopy(temps, 0, this.temperatureSeries, this.length, temps.length);
        } else {
            double[] newTemperatureSeries = new double[(this.length + temps.length) * 2];
            System.arraycopy(this.temperatureSeries, 0, newTemperatureSeries, 0, this.length);
            System.arraycopy(temps, 0, newTemperatureSeries, this.length, temps.length);
            this.temperatureSeries = newTemperatureSeries;
        }
        this.length += temps.length;
        return this.length;
    }
}