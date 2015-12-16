package ro.ieat.soso.core.prediction;

import ro.ieat.soso.core.coalitions.Usage;

import java.util.*;

/**
 * Created by adrian on 25.11.2015.
 */
public class Prediction<T> {

    private long startTime;
    private long endTime;
    private T max;
    private T min;
    private Double average;
    private T histogram;

    public Prediction(T max, T min, Double average, T histogram){
        this.max = max;
        this.min = min;
        this.average = average;
        this.histogram = histogram;
    }

    public Prediction(){

    }

    public static Double pickFromHistogram(Map<Double, Long> histogram){
        Random random = new Random();
        Double d = random.nextDouble();
        Long total = 0L;
        if(histogram.size() == 1)
            return histogram.keySet().iterator().next();
        for(Long l : histogram.values()) {
            total += l;
        }
        for(Double key : histogram.keySet()){
            if(d > histogram.get(key)/total)
                return key;
        }
        return .0;

    }

    public static Long pickFromHistogramLong(Map<Long, Long> histogram){
        Random random = new Random();
        Double d = random.nextDouble();
        Long total = 0L;
        if(histogram.size() == 1)
            return histogram.keySet().iterator().next();
        for(Long l : histogram.values())
            total += l;
        for(Map.Entry<Long, Long> entry : histogram.entrySet()){
            if(d > entry.getValue()/total)
                return entry.getKey();
        }
        return 0L;

    }

    public static Prediction<Double> predictCPU(List<Usage> list){
        Prediction<Double> result = new Prediction<Double>(Double.MIN_VALUE, Double.MAX_VALUE, .0, .0);
        double sum = 0;
        Map<Double, Long> histogram = new TreeMap<Double, Long>();
        for(Usage usage : list){
            //or max cpu?
            Double max = usage.getCpu();
            Double avg = usage.getCpu();
            if(max > result.getMax()){
                result.setMax(max);
            }
            if(max < result.getMin())
                result.setMin(max);
            sum += avg;
            if (histogram.containsKey(avg))
                histogram.put(avg, histogram.get(avg) + 1);
            else
                histogram.put(avg, 1L);
        }
        if(list.size() > 0){
            result.setAverage(sum/list.size());
            result.setHistogram(pickFromHistogram(histogram));
        }else{
            result.setAverage(.0);
            result.setHistogram(.0);
        }
        return result;
    }

    public static Prediction<Double> predictMemory(List<Usage> list){
        Prediction<Double> result = new Prediction<Double>(Double.MIN_VALUE, Double.MAX_VALUE, .0, .0);
        double sum = 0;
        Map<Double, Long> histogram = new TreeMap<Double, Long>();
        for(Usage usage : list){
            //or max memory?
            Double max = usage.getMemory();
            Double avg = usage.getMemory();
            if(max > result.getMax()){
                result.setMax(max);
            }
            if(max < result.getMin())
                result.setMin(max);
            sum += avg;
            if (histogram.containsKey(avg))
                histogram.put(avg, histogram.get(avg) + 1);
            else
                histogram.put(avg, 1L);
        }
        if(list.size() > 0){
            result.setAverage(sum/list.size());
            result.setHistogram(pickFromHistogram(histogram));
        }else{
            result.setAverage(.0);
            result.setHistogram(.0);
        }
        return result;
    }

    public static Prediction<Double> predictDisk(List<Usage> list){
        Prediction<Double> result = new Prediction<Double>(Double.MIN_VALUE, Double.MAX_VALUE, .0, .0);
        double sum = 0;
        Map<Double, Long> histogram = new TreeMap<Double, Long>();
        for(Usage usage : list){
            Double max = usage.getMaxDisk();
            Double avg = usage.getDisk();
            if(max > result.getMax()){
                result.setMax(max);
            }
            if(max < result.getMin())
                result.setMin(max);
            sum += avg;
            if (histogram.containsKey(avg))
                histogram.put(avg, histogram.get(avg) + 1);
        }
        if(list.size() > 0){
            result.setAverage(sum/list.size());
            result.setHistogram(pickFromHistogram(histogram));
        }else{
            result.setAverage(.0);
            result.setHistogram(.0);
        }
        return result;
    }


    public static Prediction<Long> predictTime(List<Long> list){
        Prediction<Long> result = new Prediction<Long>(Long.MIN_VALUE, Long.MAX_VALUE, .0, 0L);
        double sum = 0;
        Map<Long, Long> histogram = new TreeMap<Long, Long>();
        for(Long t : list){
            if(t > result.getMax()){
                result.setMax(t);
            }
            if(t < result.getMin())
                result.setMin(t);
            sum += t;
            if (histogram.containsKey(t))
                histogram.put(t, histogram.get(t) + 1);
        }
        if(list.size() > 0){
            result.setAverage(sum/list.size());
            result.setHistogram(pickFromHistogramLong(histogram));
        }else{
            result.setAverage(.0);
            result.setHistogram(0L);
        }
        return result;
    }

    public static Prediction<Double> predictDouble(List<Double> list){
        Prediction<Double> result = new Prediction<Double>(Double.MIN_VALUE, Double.MAX_VALUE, .0, .0);
        double sum = 0;
        Map<Double, Long> histogram = new TreeMap<Double, Long>();
        for(Double t : list){
            if(t > result.getMax()){
                result.setMax(t);
            }
            if(t < result.getMin())
                result.setMin(t);
            sum += t;
            if (histogram.containsKey(t))
                histogram.put(t, histogram.get(t) + 1);
        }
        if(list.size() > 0){
            result.setAverage(sum/list.size());
            result.setHistogram(pickFromHistogram(histogram));
        }else{
            result.setAverage(.0);
            result.setHistogram(.0);
        }
        return result;
    }

    //TODO Add prediction which minimizes importance as time passes
    //TODO Add linear regression prediction


    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public T getHistogram() {
        return histogram;
    }

    public void setHistogram(T histogram) {
        this.histogram = histogram;
    }

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
