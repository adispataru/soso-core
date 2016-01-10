package ro.ieat.soso.core.prediction;

/**
 * Created by adrian on 25.11.2015.
 */
public class DurationPrediction {

    private long timestamp;
    private Long max;
    private Long min;
    private Double average;
    private Long histogram;

    public DurationPrediction(){

    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Long getHistogram() {
        return histogram;
    }

    public void setHistogram(Long histogram) {
        this.histogram = histogram;
    }
}
