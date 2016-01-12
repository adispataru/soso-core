package ro.ieat.soso.core.time;

import ro.ieat.soso.core.coalitions.Coalition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by teodora on 09.01.2016.
 */
public class LongIntervalNode implements Comparable<LongIntervalNode> {

    //black-0, red-1;

    private LongInterval interval;
    private long max;
    private long min;

    private long key;

    private boolean color;
    private LongIntervalNode left, right, p;


    private List<Coalition> coalitionList=new ArrayList<>();

    public LongIntervalNode(LongInterval interval, Coalition c){

        this.key=interval.getLow();
        this.color=true;
        this.left=null;
        this.right=null;
        this.p=null;
        coalitionList.add(c);
        //  this.coalition=c;

        this.interval=interval;
        this.max=this.interval.getHigh();
        this.min=this.interval.getLow();
    }


    public void addCoalition(Coalition c){
        coalitionList.add(c);

        Collections.sort(coalitionList);
    }

    public Coalition getFirsCoalition(){
        return coalitionList.get(0);
    }

    public int getNrCoalitions(){
        return coalitionList.size();
    }

    public void removeCoalition(Coalition c){
        coalitionList.remove(c);
    }

    public List<Coalition> getCoalitionList(){
        return this.coalitionList;
    }

    public LongInterval getInterval() {
        return interval;
    }

    public long getMax(){
        return max;
    }

    public void setMax(long max){
        this.max=max;
    }

    public long getMin(){
        return min;
    }

    public void setMin(long min){
        this.min=min;
    }

    public void setInterval(LongInterval interval) {
        this.interval = interval;
    }

    @Override
    public String toString(){

        StringBuffer bf=new StringBuffer();

        if(this.getInterval().getLow()==0){
            if(this.getParent()!=null)
                bf.append("Key+"+this.getKey() +"-->interval="+this.interval+"--max="+this.max+"--min="+this.min/1000000+"----color=" + this.getColor() + "-parent="+this.getParent().getKey());
            else
                bf.append("Key+"+this.getKey() +"-->interval="+this.interval+"--max="+this.max+"--min="+this.min/1000000+"----color=" + this.getColor() + "-parent=none");

        }else {
            if (this.getParent() != null)
                bf.append("Key+"+this.getKey()  + "--val=" + (this.getKey() / 1000000) + "-->interval=" + this.interval + "--max=" + this.max + "--min=" + (this.min / 1000000) + "--color=" + this.getColor() + "-parent=" + this.getParent().getKey());
            else
                bf.append("Key+"+this.getKey()  + "--val=" + (this.getKey() / 1000000) + "-->interval=" + this.interval + "max=" + this.max + "--min=" + (this.min / 1000000) + "-color=" + this.getColor() + "-parent=none");

        }
        return bf.toString();
    }

    public boolean isColor() {
        return color;
    }

    public long getKey(){
        return this.key;
    }

    public boolean getColor(){
        return this.color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public LongIntervalNode getLeft() {
        return left;
    }

    public void setLeft(LongIntervalNode left) {
        this.left = left;
    }

    public LongIntervalNode getRight() {
        return right;
    }

    public void setRight(LongIntervalNode right) {
        this.right = right;
    }

    public LongIntervalNode getParent() {
        return p;
    }

    public void setParent(LongIntervalNode p) {
        this.p = p;
    }

    public Coalition binarySearch(long key){


        if(coalitionList.get(coalitionList.size()-1).getMachines().size()>=key) {
            int lo = 0;
            int hi = coalitionList.size() - 1;
            while (lo <= hi) {
                // Key is in a[lo..hi] or not present.
                int mid = lo + (hi - lo) / 2;
                if (key < coalitionList.get(mid).getMachines().size()) hi = mid - 1;
                else if (key > coalitionList.get(mid).getMachines().size()) lo = mid + 1;
                else return coalitionList.get(mid);
            }
            return coalitionList.get(lo);
        }else{
            return null;
        }
    }

    @Override
    public int compareTo(LongIntervalNode o) {

        if(this.getKey()<o.getKey())
            return -1;
        else if(this.getKey()==o.getKey())
            return 0;
        else return 1;

    }
}
