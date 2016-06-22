package ro.ieat.soso.core.jobs;

import org.springframework.data.annotation.Id;
import ro.ieat.soso.core.coalitions.MachineAttribute;

/**
 * Created by adrian on 18.01.2016.
 * Class to represent task constraint from google cluster data.
 */
public class TaskConstraint {
    @Id
    private Long id;
    private Long timestamp;
    private Long jobId;
    private Long taskIndex;
    private String attributeName;
    private String attributeValue;
    private int comparisonOperator;

    private boolean satisfiesConstraint(MachineAttribute value){
        switch (comparisonOperator){
            case 0:
                return attributeValue.equals(value.getValue());
            case 1:
                return !attributeValue.equals(value.getValue());
            case 2:
                return Long.parseLong(value.getValue())  < Long.parseLong(attributeValue);
            case 3:
                return Long.parseLong(value.getValue())  > Long.parseLong(attributeValue);
        }
        return false;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public int getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(int comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public Long getTaskIndex() {
        return taskIndex;
    }

    public void setTaskIndex(Long taskIndex) {
        this.taskIndex = taskIndex;
    }
}
