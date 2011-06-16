package ruffkat.hombucha.store;

import ruffkat.hombucha.measure.Volumetric;
import ruffkat.hombucha.model.Container;

import java.util.Set;

public interface Containers
        extends Repository<Container> {
    Container create();
    Set<Container> available();
    Container pick(Volumetric volumetric);
}
