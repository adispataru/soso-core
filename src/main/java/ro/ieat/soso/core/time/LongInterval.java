package ro.ieat.soso.core.time;

/**
 * Created by teodora on 09.01.2016.
 */
public class LongInterval {

    private long low;
    private long high;

    private static long infinite=Long.MAX_VALUE;

    public  LongInterval(long low, long high){

        this.low=low;

        this.high=high;
    }

    public LongInterval(){

    }


    public static long getInfinite(){
        return Long.MAX_VALUE;
    }

    public long getLow() {
        return low;
    }

    public void setLow(long low) {
        this.low = low;
    }

    public long getHigh() {
        return high;
    }

    public void setHigh(long high) {
        this.high = high;
    }

    public String toString(){

        StringBuffer bf=new StringBuffer();

        bf.append("["+low+";"+high+"]");

        return bf.toString();
    }
}
