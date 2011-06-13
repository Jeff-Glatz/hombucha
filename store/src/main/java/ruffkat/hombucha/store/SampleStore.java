package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Molarity;
import ruffkat.hombucha.model.Sample;

import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Quantity;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Volume;
import java.util.Calendar;

public class SampleStore extends AbstractStore implements Samples {

    @Override
    public Sample<Molarity> ph(Ferment ferment) {
        Sample<Molarity> ph = new Sample<Molarity>();
        ph.setFerment(ferment);
        ph.setTakenAt(Calendar.getInstance());
        return ph;
    }

    @Override
    public Sample<Dimensionless> specificGravity(Ferment ferment) {
        Sample<Dimensionless> specificGravity = new Sample<Dimensionless>();
        specificGravity.setFerment(ferment);
        specificGravity.setTakenAt(Calendar.getInstance());
        return specificGravity;
    }

    @Override
    public Sample<Volume> volume(Ferment ferment) {
        Sample<Volume> volume = new Sample<Volume>();
        volume.setFerment(ferment);
        volume.setTakenAt(Calendar.getInstance());
        return volume;
    }

    @Override
    public Sample<Temperature> temperature(Ferment ferment) {
        Sample<Temperature> temperature = new Sample<Temperature>();
        temperature.setFerment(ferment);
        temperature.setTakenAt(Calendar.getInstance());
        return temperature;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Q extends Quantity> Sample<Q> load(Long id) {
        return (Sample<Q>) entityManager.getReference(Sample.class, id);
    }

    @Override
    public <Q extends Quantity> void delete(Sample<Q> sample) {
        entityManager.remove(sample);
    }
}
