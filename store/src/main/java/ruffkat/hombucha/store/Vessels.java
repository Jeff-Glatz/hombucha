package ruffkat.hombucha.store;

import ruffkat.hombucha.measure.Volumetric;
import ruffkat.hombucha.model.Vessel;

import java.util.Set;

public interface Vessels
        extends Repository<Vessel> {
    Vessel create();
    Set<Vessel> available();
    Vessel pick(Volumetric volumetric);
}
