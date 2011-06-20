package ruffkat.hombucha.measure;

import ruffkat.hombucha.util.MathUtils;

import javax.measure.Measure;
import javax.measure.converter.UnitConverter;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Quantity;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Volume;
import java.math.BigDecimal;

public final class Measurements {

    public static Measure<Volume> volume(String value) {
        return Measure.valueOf(value).
                asType(Volume.class);
    }

    public static Measure<Temperature> temperature(String value) {
        return Measure.valueOf(value).
                asType(Temperature.class);
    }

    public static Measure<Molarity> molarity(String value) {
        return Measure.valueOf(value).
                asType(Molarity.class);
    }

    public static Measure<Mass> mass(String value) {
        return Measure.valueOf(value).
                asType(Mass.class);
    }

    public static <Q extends Quantity> BigDecimal decimalValue(Measure<Q> measure) {
        return measure.decimalValue(measure.getUnit(), MathUtils.context());
    }

    public static <Q extends Quantity> Measure<Q> convert(Measure<Q> measure, UnitConverter converter) {
        BigDecimal converted = converter.convert(decimalValue(measure), MathUtils.context());
        return Measure.valueOf(converted, measure.getUnit());
    }
}
