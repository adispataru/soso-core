package ro.ieat.soso.core.prediction;

/**
 * Created by adrian on 25.11.2015.
 */
public class MachinePrediction {

    private long startTime;
    private long endTime;
    private Double maxMemory;
    private Double maxCPU;
    private Double minMemory;
    private Double minCPU;
    private Double averageMemory;
    private Double averageCPU;
    private Double histogramMemory;
    private Double histogramCPU;

    public MachinePrediction(){

    }

    public Double getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(Double maxMemory) {
        this.maxMemory = maxMemory;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }


    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Double getMaxCPU() {
        return maxCPU;
    }

    public void setMaxCPU(Double maxCPU) {
        this.maxCPU = maxCPU;
    }

    public Double getMinMemory() {
        return minMemory;
    }

    public void setMinMemory(Double minMemory) {
        this.minMemory = minMemory;
    }

    public Double getMinCPU() {
        return minCPU;
    }

    public void setMinCPU(Double minCPU) {
        this.minCPU = minCPU;
    }

    public Double getAverageMemory() {
        return averageMemory;
    }

    public void setAverageMemory(Double averageMemory) {
        this.averageMemory = averageMemory;
    }

    public Double getAverageCPU() {
        return averageCPU;
    }

    public void setAverageCPU(Double averageCPU) {
        this.averageCPU = averageCPU;
    }

    public Double getHistogramMemory() {
        return histogramMemory;
    }

    public void setHistogramMemory(Double histogramMemory) {
        this.histogramMemory = histogramMemory;
    }

    public Double getHistogramCPU() {
        return histogramCPU;
    }

    public void setHistogramCPU(Double histogramCPU) {
        this.histogramCPU = histogramCPU;
    }
}
