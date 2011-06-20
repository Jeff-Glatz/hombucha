package ruffkat.hombucha.measure;

import javax.measure.Measure;
import javax.measure.converter.MultiplyConverter;
import javax.measure.converter.UnitConverter;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Quantity;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Volume;

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

    public static <Q extends Quantity> Measure<Q> convert(Measure<Q> measure, UnitConverter converter) {
        double value = converter.convert(measure.getValue().doubleValue());
        return Measure.valueOf(value, measure.getUnit());
    }

    public static <Q extends Quantity> Measure<Q> scale(Measure<Q> measure, double factor) {
        return convert(measure, new MultiplyConverter(factor));
    }
}
