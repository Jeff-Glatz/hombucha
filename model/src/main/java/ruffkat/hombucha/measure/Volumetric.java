package ruffkat.hombucha.measure;

import javax.measure.Measure;
import javax.measure.quantity.Volume;

public interface Volumetric {
    Measure<Volume> getVolume();
}
