package ro.ieat.soso.core.coalitions;

import org.springframework.data.annotation.Id;
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
    @Id
    private Long id;
    private MachinePrediction prediction;
    private DurationPrediction ETA;
    private Double cpu;
    private Double memory;
    private List<Long> taskUsageList;

    public Machine(long id, double cpu, double mem){
        this.id = id;
        this.cpu = cpu;
        this.memory = mem;
    }

    public Machine(){

    }

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

    public List<Long> getTaskUsageList() {
        if(taskUsageList == null)
            taskUsageList = new ArrayList<Long>();
        return taskUsageList;
    }

    public void setTaskUsageList(List<Long> taskUsageList) {
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
