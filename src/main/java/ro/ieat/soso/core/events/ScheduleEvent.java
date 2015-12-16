package ro.ieat.soso.core.events;

/**
 * Created by adrian on 11.12.2015.
 */
public class ScheduleEvent {

    private String logicJobName;
    private long jobId;
    private long coalitionId;
    private long start;
    private long end;


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
