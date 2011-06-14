package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Sample;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

public interface Samples {
    <Q extends Quantity> Sample<Q> create(Ferment ferment, Measure<Q> measurement);
    Sample load(Long id);
    void delete(Sample sample);
}
