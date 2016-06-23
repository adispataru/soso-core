package ro.ieat.soso.core.coalitions;

import org.springframework.data.annotation.Id;
import ro.ieat.soso.core.jobs.TaskUsage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adrian on 25.11.2015.
 * Used to represent machine data from google traces.
 */
public class Machine implements Serializable{
    private static final long serialVersionUID = 5494991936696578350L;
    @Id
    private Long id;
    private TaskUsage usagePrediction;
    private Long ETA;
    private Double cpu;
    private Double memory;
    private List<Long> taskUsageList;
    private Map<String, String> attributes;
    private List<Interval> availabilityInterval;

    public Machine(){

    }

    @Deprecated
    public Machine(long id, double cpu, double mem){
        this.id = id;
        this.cpu = cpu;
        this.memory = mem;
        availabilityInterval = new ArrayList<>();
    }

    public Machine(long machineId, Double cpu, Double mem, long timestamp){
        this.id = id;
        this.cpu = cpu;
        this.memory = mem;
        Interval i = new Interval(timestamp, Long.MAX_VALUE);
        availabilityInterval = new ArrayList<>();

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


    public Long getETA() {
        return ETA;
    }

    public void setETA(Long ETA) {
        this.ETA = ETA;
    }


    public TaskUsage getUsagePrediction() {
        return usagePrediction;
    }

    public void setUsagePrediction(TaskUsage usagePrediction) {
        this.usagePrediction = usagePrediction;
    }

    public Map<String, String> getAttributes() {
        if(attributes == null)
            attributes = new HashMap<>(42);
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(String attr, String value){

        getAttributes().put(attr, value);
    }

    public void turnOff(long timestamp) {
        //get last interval and set its end to the timestamp
        if(availabilityInterval.size() == 0)
            return;
        Interval i = availabilityInterval.get(availabilityInterval.size() - 1);
        i.setEnd(timestamp);
    }

    public void turnOn(long timestamp){
        Interval i = new Interval(timestamp, Long.MAX_VALUE);
        availabilityInterval.add(i);
    }

    public List<Interval> getAvailabilityInterval() {
        return availabilityInterval;
    }

    public void setAvailabilityInterval(List<Interval> availabilityInterval) {
        this.availabilityInterval = availabilityInterval;
    }
}
