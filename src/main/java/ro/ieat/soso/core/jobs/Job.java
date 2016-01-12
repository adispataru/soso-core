package ro.ieat.soso.core.jobs;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by adrian on 23.11.2015.
 */
public class Job {

    private long jobId;
    private String logicJobName;
    @JsonSerialize(as=Map.class)
    private Map<Long, TaskHistory> taskHistory;

    private long submitTime;
    private long scheduleTime;
    private long finishTime;
    private String status;
    private long taskSize;

    public Job(long jobId, String logicJobName, long submitTime, long scheduleTime, long finishTime, String status) {
        this.jobId = jobId;
        this.logicJobName = logicJobName;
        this.submitTime = submitTime;
        this.scheduleTime = scheduleTime;
        this.finishTime = finishTime;
        this.status = status;
        this.taskHistory = new TreeMap<>();
    }

    public Job(Job job){
        this.jobId = job.jobId;
        this.logicJobName = job.logicJobName;
        this.submitTime = job.submitTime;
        this.scheduleTime = job.scheduleTime;
        this.finishTime = job.finishTime;
        this.status = job.status;
        this.taskHistory = new TreeMap<>();
    }

    public Job(Job job, boolean noUsage){
        this.jobId = job.jobId;
        this.logicJobName = job.logicJobName;
        this.submitTime = job.submitTime;
        this.scheduleTime = job.scheduleTime;
        this.finishTime = job.finishTime;
        this.status = job.status;
        this.taskHistory = new TreeMap<>();
        if(noUsage == true) {
            for (TaskHistory th : job.getTaskHistory().values()) {
                this.taskHistory.put(th.getTaskIndex(), new TaskHistory(th));
            }
        }else{
            this.taskHistory = job.getTaskHistory();
        }
    }

    private long scheduleClass;

    public Job(){
        this.jobId = 0L;
        this.logicJobName = "0";
        this.submitTime = 0L;
        this.scheduleTime = 0L;
        this.finishTime = 0L;
        this.status = " ";
        this.scheduleClass = -1L;
    }

    public Job(String[] tokens) {

        this.setJobId(Long.parseLong(tokens[0]));
        this.setLogicJobName(tokens[1]);
        this.setSubmitTime(Long.parseLong(tokens[2]));
        this.setScheduleTime(Long.parseLong(tokens[3]));
        this.setFinishTime(Long.parseLong(tokens[4]));
        this.setStatus(tokens[5]);
        TaskHistory task = new TaskHistory();
        task.setTaskIndex(Long.parseLong(tokens[6]));
        task.setSubmitTime(Long.parseLong(tokens[7]));
        task.setScheduleTime(Long.parseLong(tokens[8]));
        task.setFinishTime(Long.parseLong(tokens[9]));
        task.setStatus(tokens[10]);

    }


    @Override
    public String toString(){

        return String.valueOf(jobId) + "," + logicJobName + "," + submitTime + "," + scheduleTime + "," + finishTime + "," + status + "," + taskHistory.size();
    }

    public long getScheduleClass() {
        return scheduleClass;
    }

    public void setScheduleClass(long scheduleClass) {
        this.scheduleClass = scheduleClass;
    }

    public void setLogicJobName(String logicJobName) {
        this.logicJobName = logicJobName;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public void setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
    }

    public void setScheduleTime(long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getJobId() {
        return jobId;
    }

    public String getLogicJobName() {
        return logicJobName;
    }


    public long getSubmitTime() {
        return submitTime;
    }

    public long getScheduleTime() {
        return scheduleTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public String getStatus() {
        return status;
    }


    public Map<Long, TaskHistory> getTaskHistory() {
        return taskHistory;
    }

    public void setTaskHistory(Map<Long, TaskHistory> taskHistory) {
        this.taskHistory = taskHistory;
    }

    public long getTaskSize() {
        return taskSize;
    }

    public void setTaskSize(long taskSize) {
        this.taskSize = taskSize;
    }
}
