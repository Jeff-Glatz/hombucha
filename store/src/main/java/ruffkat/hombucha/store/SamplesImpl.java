package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Molarity;
import ruffkat.hombucha.model.Sample;

import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Volume;
import java.util.Calendar;

public class SamplesImpl implements Samples {

    @Override
    public Sample<Molarity> ph(Ferment ferment) {
        Sample<Molarity> ph = new Sample<Molarity>();
        ph.setFerment(ferment);
        ph.setTakenAt(Calendar.getInstance());
        return ph;
    }

    @Override
    public Sample<Dimensionless> specificGravity(Ferment ferment) {
        Sample<Dimensionless> sg = new Sample<Dimensionless>();
        sg.setFerment(ferment);
        sg.setTakenAt(Calendar.getInstance());
        return sg;
    }

    @Override
    public Sample<Volume> volume(Ferment ferment) {
        Sample<Volume> volume = new Sample<Volume>();
        volume.setFerment(ferment);
        volume.setTakenAt(Calendar.getInstance());
        return volume;
    }
}
