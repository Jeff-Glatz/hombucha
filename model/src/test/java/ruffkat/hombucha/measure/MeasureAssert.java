package ruffkat.hombucha.measure;

import org.junit.Assert;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

public class MeasureAssert extends Assert {

    public static <Q extends Quantity> void assertMeasureEquals(Measure<Q> expected, Measure<Q> observed) {
        assertEquals(expected.getUnit(), observed.getUnit());
        assertEquals(expected.getValue().doubleValue(), observed.getValue().doubleValue(), 0.001);
    }
}
