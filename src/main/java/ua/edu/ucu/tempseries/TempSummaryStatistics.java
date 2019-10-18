package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private double avgTemp;
    private double devTemp;
    private double minTemp;
    private double maxTemp;

    public TempSummaryStatistics(double avgTem,
                                 double devTem,
                                 double maxTem,
                                 double minTem) {
        this.avgTemp = avgTem;
        this.devTemp = devTem;
        this.maxTemp = maxTem;
        this.minTemp = minTem;
    }

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

}
