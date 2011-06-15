package ruffkat.hombucha.measure;

import javax.measure.Measure;
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
}
