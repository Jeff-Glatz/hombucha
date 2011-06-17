package ruffkat.hombucha.money;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

public interface Econometric<Q extends Quantity> {
    Measure<Q> getUnit();
    Money getPrice();
    Money unitPrice();
}
