package ro.ieat.soso.core.jobs;


import ro.ieat.soso.core.coalitions.Usage;

import java.util.ArrayList;

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
    private TaskUsage taskUsage;


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

    public static TaskHistory combineTasks(TaskHistory t1, TaskHistory t2){
        ArrayList<Usage> sorted = new ArrayList<Usage>();

        //sorted.addAll(t1.getUsageMap());
        for(Usage u : t1.getTaskUsage().getUsageList()) {
            boolean found = false;
            for (int i = 0; !found && i < sorted.size(); i++) {
                if(sorted.get(i).getStartTime() == u.getStartTime())
                    break;
                if (sorted.get(i).getStartTime() > u.getStartTime()){
                    found = true;
                    sorted.add(i, u);
                }
            }
            if(!found)
                sorted.add(u);
        }
        for(Usage u : t2.getTaskUsage().getUsageList()) {
            boolean found = false;
            for (int i = 0; !found && i < sorted.size(); i++) {
                if(sorted.get(i).getStartTime() == u.getStartTime())
                    break;
                if (sorted.get(i).getStartTime() > u.getStartTime()){
                    found = true;
                    sorted.add(i, u);
                }
            }
            if(!found)
                sorted.add(u);
        }

        if(t1.getMachineId() == 0 && t2.getMachineId() != 0)
            //System.out.print("Tasks are combined " + t1.getMachineId() + ", " + t2.getMachineId() + "=");
            t1.setMachineId(t2.getMachineId());

        TaskHistory result = new TaskHistory();
        result.setFinishTime(t1.getFinishTime() > t2.getFinishTime() ? t1.getFinishTime() : t2.getFinishTime());
        result.setSubmitTime(t1.getSubmitTime() > t2.getSubmitTime() ? t1.getSubmitTime() : t2.getSubmitTime());
        result.setScheduleTime(t1.getScheduleTime() > t2.getScheduleTime() ? t1.getScheduleTime() : t2.getScheduleTime());
        result.setMachineId(t1.getMachineId() > t2.getMachineId() ? t1.getMachineId() : t2.getMachineId());
        result.setRequestedCPU(t1.getRequestedCPU() > t2.getRequestedCPU() ? t1.getRequestedCPU() : t2.getRequestedCPU());
        result.setRequestedMemory(t1.getRequestedMemory() > t2.getRequestedMemory() ? t1.getRequestedMemory() : t2.getRequestedMemory());
        result.setRequestedDisk(t1.getRequestedDisk() > t2.getRequestedDisk() ? t1.getRequestedDisk() : t2.getRequestedDisk());
        result.setStatus(t1.getStatus().length() > t2.getStatus().length() ? t1.getStatus() : t2.getStatus());
        result.setTaskIndex(t1.getTaskIndex());

        TaskUsage taskUsage1 = new TaskUsage();
        taskUsage1.setUsageList(sorted);

        result.setTaskUsage(taskUsage1);

        if (result.getMachineId() == 0)
            System.out.print(result.getMachineId() + "\n");

        return result;
    }

    public TaskUsage getTaskUsage() {
        return taskUsage;
    }

    public void setTaskUsage(TaskUsage taskUsage) {
        this.taskUsage = taskUsage;
    }
}
