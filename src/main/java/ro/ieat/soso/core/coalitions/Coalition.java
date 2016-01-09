package ro.ieat.soso.core.coalitions;


import ro.ieat.soso.core.jobs.Job;
import ro.ieat.soso.core.jobs.ScheduledJob;
import ro.ieat.soso.core.prediction.Prediction;
import ro.ieat.soso.core.time.LongInterval;
import ro.ieat.soso.core.time.LongIntervalNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by adrian on 25.11.2015.
 */
public class Coalition implements Comparable<Coalition>{
    private long id;
    private long rackId;
    private String logicJobName;
    private long scheduleClass;
    private List<Machine> machines;
    private Map<String, Long> jobs;
    private Prediction<Long> currentETA;
    private List<ScheduledJob> scheduledJobs;

    private List<LongIntervalNode> intervalNodesList=new ArrayList<>();

    public String getLogicJobName() {
        return logicJobName;
    }

    public void setLogicJobName(String logicJobName) {
        this.logicJobName = logicJobName;
    }

    public long getScheduleClass() {
        return scheduleClass;
    }

    public void setScheduleClass(long scheduleClass) {
        this.scheduleClass = scheduleClass;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public Map<String, Long> getJobs() {
        return jobs;
    }

    public void setJobs(Map<String, Long> jobs) {
        this.jobs = jobs;
    }

    public Prediction<Long> getCurrentETA() {
        return currentETA;
    }

    public void setCurrentETA(Prediction<Long> currentETA) {
        this.currentETA = currentETA;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRackId() {
        return rackId;
    }

    public void setRackId(long rackId) {
        this.rackId = rackId;
    }

    public List<ScheduledJob> getScheduledJobs() {
        return scheduledJobs;
    }

    public void setScheduledJobs(List<ScheduledJob> scheduledJobs) {
        this.scheduledJobs = scheduledJobs;
    }

    @Override
    public int compareTo(Coalition c) {
        return this.machines.size()-c.getMachines().size();
    }


    public Long getJobHistoryFinishTime(String logicJobName){
        return jobs.get(logicJobName);
    }



    public void addIntervalNode(LongIntervalNode in){
        this.intervalNodesList.add(in);
    }

    public void removeIntervalNode(LongIntervalNode i){

        intervalNodesList.remove(i);
    }

    
}
