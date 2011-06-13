package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Molarity;
import ruffkat.hombucha.model.Sample;

import javax.measure.quantity.Dimensionless;
import java.util.Calendar;

public class SamplesImpl implements Samples {

    public Sample<Molarity> ph(Ferment ferment) {
        Sample<Molarity> ph = new Sample<Molarity>();
        ph.setFerment(ferment);
        ph.setTakenAt(Calendar.getInstance());
        return ph;
    }

    public Sample<Dimensionless> specificGravity(Ferment ferment) {
        Sample<Dimensionless> sg = new Sample<Dimensionless>();
        sg.setFerment(ferment);
        sg.setTakenAt(Calendar.getInstance());
        return sg;
    }
}
