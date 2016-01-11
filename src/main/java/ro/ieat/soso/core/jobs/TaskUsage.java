package ro.ieat.soso.core.jobs;

import ro.ieat.soso.core.coalitions.Usage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 09.12.2015.
 */
public class TaskUsage {
    private long taskIndex;

    public TaskUsage(long taskIndex, long jobId, String logicJobName) {
        this.taskIndex = taskIndex;
        this.jobId = jobId;
        this.logicJobName = logicJobName;
    }

    public TaskUsage(){
        this.taskIndex = 0;
        this.jobId = 0;
        this.logicJobName = "";
    }


    private long jobId;
    private String logicJobName;
    private List<Usage> usageList = new ArrayList<Usage>();

    public List<Usage> getUsageList() {
        return usageList;
    }

    public void setUsageList(List<Usage> usageList) {
        this.usageList = usageList;
    }

    public long getTaskIndex() {
        return taskIndex;
    }

    public void setTaskIndex(long taskIndex) {
        this.taskIndex = taskIndex;
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

    public void combineUsage(TaskUsage t){
        for(Usage u : t.getUsageList()) {
            boolean found = false;
            for (int i = 0; !found && i < usageList.size(); i++) {
                if(usageList.get(i).getStartTime() == u.getStartTime())
                    break;
                if (usageList.get(i).getStartTime() > u.getStartTime()){
                    found = true;
                    usageList.add(i, u);
                }
            }
            if(!found)
                usageList.add(u);
        }
    }
}
