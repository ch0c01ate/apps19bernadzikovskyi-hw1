package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double avgTemp;
    public double devTemp;
    public double minTemp;
    public double maxTemp;

    public TempSummaryStatistics(double avgTem, double devTem, double maxTem, double minTem) {
        this.avgTemp = avgTem;
        this.devTemp = devTem;
        this.maxTemp = maxTem;
        this.minTemp = minTem;
    }
}
