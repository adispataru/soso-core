package ro.ieat.soso.core.jobs;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by adrian on 08.12.2015.
 */
public class ScheduledJob implements Comparable<ScheduledJob> {

    private long jobId;
    private long finishTime;
    private long submitTime;
    private long timeToStart;
    private Map<Long, Long> taskMachineMapping = new TreeMap<Long, Long>();

    private Job job;



    public long getTimeToStart() {
        return timeToStart;
    }

    public void setTimeToStart(long timeToStart) {
        this.timeToStart = timeToStart;
    }

    public long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public Map<Long, Long> getTaskMachineMapping() {
        return taskMachineMapping;
    }

    public void setTaskMachineMapping(Map<Long, Long> taskMachineMapping) {
        this.taskMachineMapping = taskMachineMapping;
    }

    @Override
    public int compareTo(ScheduledJob sj) {
        if(this.getTimeToStart()<sj.getTimeToStart())
            return -1;
        else if(this.getTimeToStart()==sj.getTimeToStart()){
            return 0;
        }else return 1;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
