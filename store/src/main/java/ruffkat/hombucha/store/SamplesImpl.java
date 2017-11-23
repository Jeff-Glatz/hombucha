package ruffkat.hombucha.store;

import org.springframework.stereotype.Repository;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Sample;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import java.time.Clock;

@Repository
public class SamplesImpl
        extends AbstractRepository<Sample>
        implements Samples {
    private final Clock clock;

    public SamplesImpl(Clock clock) {
        super(Sample.class);
        this.clock = clock;
    }

    @Override
    public <Q extends Quantity> Sample<Q> create(Ferment ferment, Measure<Q> measurement) {
        Sample<Q> sample = new Sample<Q>();
        sample.setFerment(ferment);
        sample.setTaken(clock.instant());
        sample.setMeasurement(measurement);
        return sample;
    }
}
