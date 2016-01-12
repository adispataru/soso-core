package ro.ieat.soso.core.coalitions;

import ro.ieat.soso.core.jobs.TaskUsage;
import ro.ieat.soso.core.prediction.DurationPrediction;
import ro.ieat.soso.core.prediction.MachinePrediction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 25.11.2015.
 */
public class Machine implements Serializable{
    private static final long serialVersionUID = 5494991936696578350L;
    private Long id;
    private MachinePrediction prediction;
    private DurationPrediction ETA;
    private Double cpu;
    private Double memory;
    private List<TaskUsage> taskUsageList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MachinePrediction getPrediction() {
        return prediction;
    }

    public void setPrediction(MachinePrediction prediction) {
        this.prediction = prediction;
    }

    public DurationPrediction getETA() {
        return ETA;
    }

    public void setETA(DurationPrediction ETA) {
        this.ETA = ETA;
    }
}
