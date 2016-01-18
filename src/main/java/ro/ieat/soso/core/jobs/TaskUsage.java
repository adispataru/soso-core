package ro.ieat.soso.core.jobs;

import org.springframework.data.annotation.Id;
import ro.ieat.soso.core.coalitions.Usage;
import ro.ieat.soso.core.prediction.Predictable;

/**
 * Created by adrian on 09.12.2015.
 * TaskUsage represents data regarding usage traces from google-cluster-data-2011-2.
 */
public class TaskUsage extends Usage implements Predictable{
    private long jobId;
    private long taskIndex;
    @Id
    private long id;
    private Long machineId;
    private String logicJobName;
    private double cpu;
    private double memory;
    private double disk;
    private double maxCpu;
    private double maxMemory;
    private double maxDisk;
    private Long assignedMachineId;

    public TaskUsage(long taskIndex, long jobId, String logicJobName) {
        this.taskIndex = taskIndex;
        this.jobId = jobId;
        this.logicJobName = logicJobName;
    }

    public TaskUsage() {
        this.startTime = 0L;
        this.endTime = 0L;
        this.taskIndex = 0;
        this.jobId = 0;
        this.logicJobName = "";
        this.cpu = .0;
        this.maxCpu = .0;
        this.memory = .0;
        this.maxMemory = .0;
        this.disk = .0;
        this.maxDisk = .0;
        assignedMachineId = 0L;
    }

    public void addTaskUsage(TaskUsage taskUsage){
        this.cpu += taskUsage.getCpu();
        this.maxCpu += taskUsage.getMaxCpu();
        this.memory += taskUsage.getMemory();
        this.maxMemory += taskUsage.getMaxMemory();
        this.disk += taskUsage.getDisk();
        this.maxDisk += taskUsage.getMaxDisk();
    }

    public void substractTaskUsage(TaskUsage taskUsage){
        this.cpu -= taskUsage.getCpu();
        this.maxCpu -= taskUsage.getMaxCpu();
        this.memory -= taskUsage.getMemory();
        this.maxMemory -= taskUsage.getMaxMemory();
        this.disk -= taskUsage.getDisk();
        this.maxDisk -= taskUsage.getMaxDisk();
    }

    public TaskUsage(Long startTime, Long endTime, double cpu, double mem, double disk) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cpu = cpu;
        this.memory = mem;
        this.disk = disk;
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

    public long getTaskIndex() {
        return taskIndex;
    }

    public void setTaskIndex(long taskIndex) {
        this.taskIndex = taskIndex;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getLogicJobName() {
        return logicJobName;
    }

    public void setLogicJobName(String logicJobName) {
        this.logicJobName = logicJobName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getAssignedMachineId() {
        return assignedMachineId;
    }

    public void setAssignedMachineId(Long assignedMachineId) {
        this.assignedMachineId = assignedMachineId;
    }

    public String loadForPlot(){
        return "" + cpu + " " + maxCpu + " " + memory + " " + maxMemory + " " + disk  + " " + maxDisk;
    }

    public void divide(int l){
        this.cpu /= l;
        this.maxCpu /= l;
        this.memory /= l;
        this.maxMemory /= l;
        this.disk /= l;
        this.maxDisk /= l;
    }
}
