package ro.ieat.soso.core.coalitions;

import ro.ieat.soso.core.jobs.TaskUsage;
import ro.ieat.soso.core.prediction.Prediction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 25.11.2015.
 */
public class Machine {
    private Long id;
    private Prediction<Double> estimatedCPULoad;
    private Prediction<Double> estimatedMemoryLoad;
    private Prediction<Double> estimatedDiskLoad;
    private Prediction<Long> ETA;
    private Double cpu;
    private Double memory;
    private List<TaskUsage> taskUsageList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prediction<Double> getEstimatedCPULoad() {
        return estimatedCPULoad;
    }

    public void setEstimatedCPULoad(Prediction<Double> estimatedCPULoad) {
        this.estimatedCPULoad = estimatedCPULoad;
    }

    public Prediction<Double> getEstimatedMemoryLoad() {
        return estimatedMemoryLoad;
    }

    public void setEstimatedMemoryLoad(Prediction<Double> estimatedMemoryLoad) {
        this.estimatedMemoryLoad = estimatedMemoryLoad;
    }

    public Prediction<Double> getEstimatedDiskLoad() {
        return estimatedDiskLoad;
    }

    public void setEstimatedDiskLoad(Prediction<Double> estimatedDiskLoad) {
        this.estimatedDiskLoad = estimatedDiskLoad;
    }

    public Prediction<Long> getETA() {
        return ETA;
    }

    public void setETA(Prediction<Long> ETA) {
        this.ETA = ETA;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public List<TaskUsage> getTaskUsageList() {
        if(taskUsageList == null)
            taskUsageList = new ArrayList<TaskUsage>();
        return taskUsageList;
    }

    public void setTaskUsageList(List<TaskUsage> taskUsageList) {
        this.taskUsageList = taskUsageList;
    }
}