package ro.ieat.soso.core.jobs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 23.11.2015.
 */
public class Job {

    private long jobId;
    private String logicJobName;
    private List<TaskUsage> tasks;
    private List<TaskHistory> taskHistory;

    private long submitTime;
    private long scheduleTime;
    private long finishTime;
    private String status;
    private long taskSize;

    public Job(long jobId, String logicJobName, List<TaskUsage> task, long submitTime, long scheduleTime, long finishTime, String status) {
        this.jobId = jobId;
        this.logicJobName = logicJobName;
        this.tasks = task;
        this.submitTime = submitTime;
        this.scheduleTime = scheduleTime;
        this.finishTime = finishTime;
        this.status = status;
    }

    public Job(Job job){
        this.jobId = job.jobId;
        this.logicJobName = job.logicJobName;
        this.tasks = job.tasks;
        this.submitTime = job.submitTime;
        this.scheduleTime = job.scheduleTime;
        this.finishTime = job.finishTime;
        this.status = job.status;
        this.tasks = new ArrayList<TaskUsage>();
        this.taskHistory = new ArrayList<TaskHistory>();
    }

    private long scheduleClass;

    public Job(){
        this.jobId = 0L;
        this.logicJobName = "0";
        this.tasks = new ArrayList<TaskUsage>();
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
        this.getTaskHistory().add(task);

    }


    @Override
    public String toString(){

        return String.valueOf(jobId) + "," + logicJobName + "," + submitTime + "," + scheduleTime + "," + finishTime + "," + status + "," + tasks.size();
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


    public List<TaskHistory> getTaskHistory() {
        return taskHistory;
    }

    public void setTaskHistory(List<TaskHistory> taskHistory) {
        this.taskHistory = taskHistory;
    }

    public List<TaskUsage> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskUsage> tasks) {
        this.tasks = tasks;
    }

    public long getTaskSize() {
        return taskSize;
    }

    public void setTaskSize(long taskSize) {
        this.taskSize = taskSize;
    }
}
