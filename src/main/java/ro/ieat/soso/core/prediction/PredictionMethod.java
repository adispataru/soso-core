package ro.ieat.soso.core.prediction;

import java.util.List;

/**
 * Created by adrian on 18.01.2016.
 * interface to be implemented by prediction methods.
 */
public interface PredictionMethod {
    Predictable predict(List<Predictable> data);
}
