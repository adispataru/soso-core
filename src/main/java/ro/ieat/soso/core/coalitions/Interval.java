package ro.ieat.soso.core.coalitions;

/**
 * Created by adispataru on 23-Jun-16.
 */
public class Interval {
    private long start;
    private long end;

    public Interval(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    private boolean contains(long l){
        return start < l && l < end;
    }
}
