package ro.ieat.soso.core.coalitions;

/**
 * Created by adrian on 25.11.2015.
 */

public class Usage {
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

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }
    public void addCpu(double cpu) {
        this.cpu += cpu;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }
    public void addMemory(double memory) {
        this.memory += memory;
    }

    public double getDisk() {
        return disk;
    }

    public void setDisk(double disk) {
        this.disk = disk;
    }

    public double getMaxCpu() {
        return maxCpu;
    }

    public void setMaxCpu(double maxCpu) {
        this.maxCpu = maxCpu;
    }
    public void addMaxCpu(double maxCpu) {
        this.maxCpu += maxCpu;
    }

    public double getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(double maxMemory) {
        this.maxMemory = maxMemory;
    }
    public void addMaxMemory(double maxMemory) {
        this.maxMemory += maxMemory;
    }

    public double getMaxDisk() {
        return maxDisk;
    }

    public void setMaxDisk(double maxDisk) {
        this.maxDisk = maxDisk;
    }


    private long startTime;
    private long endTime;
    private double cpu;
    private double memory;
    private double disk;
    private double maxCpu;
    private double maxMemory;
    private double maxDisk;

    public Usage(){
        this.startTime = 0;
        this.endTime = 0;
        this.cpu = .0;
        this.memory = .0;
        this.disk = .0;
        this.maxCpu = .0;
        this.maxMemory = .0;
        this.maxDisk = .0;
    }

    public Usage(long startTime, long endTime, double cpu, double memory, double disk){
        this.startTime = startTime;
        this.endTime = endTime;
        this.cpu = cpu;
        this.memory = memory;
        this.disk = disk;
    }


    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(cpu);
        result.append(",");

        result.append(memory);
        result.append(",");

        result.append(disk);

        return result.toString();
    }

    public String allToString(){
        return String.valueOf(startTime) + "," + endTime + "," + cpu + "," + maxCpu + "," + memory
                + "," + maxMemory + "," + disk + "," +maxDisk;
    }


}
