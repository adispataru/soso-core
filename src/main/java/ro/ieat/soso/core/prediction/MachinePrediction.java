package ro.ieat.soso.core.prediction;

/**
 * Created by adrian on 25.11.2015.
 */
public class MachinePrediction {

    private long startTime;
    private long endTime;
    private Double maxMemory;
    private Double maxCPU;
    private Double memory;
    private Double cpu;
    private Double maxDisk;
    private Double disk;

    public MachinePrediction() {

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

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Double getMaxDisk() {
        return maxDisk;
    }

    public void setMaxDisk(Double maxDisk) {
        this.maxDisk = maxDisk;
    }

    public Double getDisk() {
        return disk;
    }

    public void setDisk(Double disk) {
        this.disk = disk;
    }
}

