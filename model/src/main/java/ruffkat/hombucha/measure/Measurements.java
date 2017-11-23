package ruffkat.hombucha.measure;

import javax.measure.Measure;
import javax.measure.converter.UnitConverter;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Quantity;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Volume;
import javax.measure.unit.Unit;
import java.math.BigDecimal;

import static javax.measure.Measure.valueOf;
import static ruffkat.hombucha.util.MathUtils.context;

public final class Measurements {

    public static Measure<Volume> volume(String value) {
        return valueOf(value).
                asType(Volume.class);
    }

    public static Measure<Temperature> temperature(String value) {
        return valueOf(value).
                asType(Temperature.class);
    }

    public static Measure<Molarity> molarity(String value) {
        return valueOf(value).
                asType(Molarity.class);
    }

    public static Measure<Mass> mass(String value) {
        return valueOf(value).
                asType(Mass.class);
    }

    public static <Q extends Quantity> BigDecimal decimalValue(Measure<Q> measure) {
        return measure.decimalValue(measure.getUnit(), context());
    }

    public static <Q extends Quantity> BigDecimal decimalValue(Measure<Q> measure, Unit<Q> unit) {
        return measure.decimalValue(unit, context());
    }

    public static <Q extends Quantity> Measure<Q> convert(Measure<Q> measure, UnitConverter converter) {
        BigDecimal converted = converter.convert(decimalValue(measure), context());
        return valueOf(converted, measure.getUnit());
    }
}
