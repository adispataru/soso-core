package ro.ieat.soso.core.coalitions;


import org.springframework.data.annotation.Id;
import ro.ieat.soso.core.jobs.ScheduledJob;
import ro.ieat.soso.core.time.LongIntervalNode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by adrian on 25.11.2015.
 */
public class Coalition implements Comparable<Coalition>{
    @Id
    private long id;
    private long rackId;
    private String logicJobName;
    private long scheduleClass;
    private List<Machine> machines=new ArrayList<>();
    private Map<String, Long> jobs;
    private List<ScheduledJob> scheduledJobs=new ArrayList<>();
    private List<LongIntervalNode> intervalNodesList=new ArrayList<>();
    private Long currentETA;
    private Double confidenceLevel;


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

    public void addScheduledJob(ScheduledJob sJob){
        this.scheduledJobs.add(sJob);
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


   // public List<LongIntervalNode> getIntervalNodesList(){
   //   return this.intervalNodesList;
  //  }

    public void printIntervalNodesList(){

        PrintWriter out = null;

        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("./previousScheduled.out", true)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(LongIntervalNode l:intervalNodesList){

            out.println(l.getInterval());
            out.flush();
        }
        out.close();
    }


    public void sortIntervalNodesList(){

        Collections.sort(this.intervalNodesList);
    }
    public void setIntervalNodesList(List<LongIntervalNode> intervalNodesList){
        this.intervalNodesList=intervalNodesList;
    }


    public Double getConfidenceLevel() {
        return confidenceLevel;
    }

    public void setConfidenceLevel(Double confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }

    public LongIntervalNode searchByLow(Long value){

        for(LongIntervalNode l:this.intervalNodesList){
            if(l.getInterval().getLow()==value)
                return l;
        }

        return null;
    }

    public LongIntervalNode searchByHigh(Long value){

        for(LongIntervalNode l:this.intervalNodesList){
            if(l.getInterval().getHigh()==value)
                return l;
        }

        return null;
    }

    public Long getCurrentETA() {
        return currentETA;
    }

    public void setCurrentETA(Long currentETA) {
        this.currentETA = currentETA;
    }
}
