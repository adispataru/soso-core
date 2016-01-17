package ro.ieat.soso.core.coalitions;

/**
 * Created by adrian on 25.11.2015.
 * Abstract class usage can be extended to express usage of any kind.
 * Use freely.
 */

public abstract class Usage {

    protected Long startTime;
    protected Long endTime;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


}
