package ruffkat.hombucha.measure;

import javax.measure.quantity.Temperature;
import javax.measure.quantity.Volume;
import javax.measure.unit.Unit;

public final class Units {

    public static Unit<Volume> volume(String value) {
        return Unit.valueOf(value).
                asType(Volume.class);
    }

    public static Unit<Temperature> temperature(String value) {
        return Unit.valueOf(value).
                asType(Temperature.class);
    }

    public static Unit<Molarity> molarity(String value) {
        return Unit.valueOf(value).
                asType(Molarity.class);
    }
}
