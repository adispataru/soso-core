package ro.ieat.soso.core.jobs;


/**
 * Created by adrian on 23.11.2015.
 */
public class TaskHistory {

    private long taskIndex;
    private long submitTime;
    private long scheduleTime;
    private long finishTime;
    private String status;

    private long machineId;
    private double requestedCPU;
    private double requestedMemory;
    private double requestedDisk;


    public TaskHistory(){
        this.taskIndex = 0;
        this.submitTime = 0;
        this.scheduleTime = 0;
        this.finishTime = 0;
        this.status = " ";
    }

    public TaskHistory(long taskIndex, long submitTime, long scheduleTime, long finishTime, String status) {
        this.taskIndex = taskIndex;
        this.submitTime = submitTime;
        this.scheduleTime = scheduleTime;
        this.finishTime = finishTime;
        this.status = status;
    }


    public long getTaskIndex() {
        return taskIndex;
    }

    public void setTaskIndex(long taskIndex) {
        this.taskIndex = taskIndex;
    }

    public long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
    }

    public long getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String toString(){
        return String.valueOf(taskIndex) + "," + submitTime + "," + scheduleTime + "," + finishTime + "," + status;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public double getRequestedCPU() {
        return requestedCPU;
    }

    public void setRequestedCPU(double requestedCPU) {
        this.requestedCPU = requestedCPU;
    }

    public double getRequestedMemory() {
        return requestedMemory;
    }

    public void setRequestedMemory(double requestedMemory) {
        this.requestedMemory = requestedMemory;
    }

    public double getRequestedDisk() {
        return requestedDisk;
    }

    public void setRequestedDisk(double requestedDisk) {
        this.requestedDisk = requestedDisk;
    }
}
