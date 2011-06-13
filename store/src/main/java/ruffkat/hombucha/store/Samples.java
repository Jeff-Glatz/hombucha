package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Molarity;
import ruffkat.hombucha.model.Sample;

import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Quantity;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Volume;

public interface Samples {
    Sample<Molarity> ph(Ferment ferment);
    Sample<Dimensionless> specificGravity(Ferment ferment);
    Sample<Volume> volume(Ferment ferment);
    Sample<Temperature> temperature(Ferment ferment);
    <Q extends Quantity> Sample<Q> load(Long id);
    void delete(Long id);
}
