package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Container;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import java.util.List;

public interface Containers
        extends Repository<Container> {
    Container create();
    List<Container> available();
    Container pick(Measure<Volume> volume);
}
