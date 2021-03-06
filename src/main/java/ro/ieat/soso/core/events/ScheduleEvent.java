package ro.ieat.soso.core.events;

import ro.ieat.soso.core.coalitions.Coalition;
import ro.ieat.soso.core.jobs.Job;

/**
 * Created by adrian on 11.12.2015.
 */
public class ScheduleEvent {

    private String logicJobName;
    private long jobId;
    private long coalitionId;
    private long start;
    private long end;

    private Coalition coalition;
    private Job job;

    public void setCoalition(Coalition coalition){
        this.coalition=coalition;
    }

    public Coalition getCoalition(){
        return coalition;
    }

    public void setJob(Job job){
        this.job=job;
    }

    public Job getJob(){
        return job;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getCoalitionId() {
        return coalitionId;
    }

    public void setCoalitionId(long coalitionId) {
        this.coalitionId = coalitionId;
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
}
