package ro.ieat.soso.core.prediction;

/**
 * Created by adrian on 18.01.2016.
 * Duration is used to implement Predictable to be aligned with PredictionMethod.
 */
public class Duration extends Number implements Predictable {

    private static final long serialVersionUID = -377078655969390362L;
    private long value;

    public Duration(long value){
        this.value = value;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}
