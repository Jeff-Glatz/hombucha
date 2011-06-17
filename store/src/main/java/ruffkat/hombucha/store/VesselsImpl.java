package ruffkat.hombucha.store;

import ruffkat.hombucha.measure.Volumetric;
import ruffkat.hombucha.model.Vessel;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VesselsImpl
        extends AbstractRepository<Vessel>
        implements Vessels {

    public VesselsImpl() {
        super(Vessel.class);
    }

    @Override
    public Vessel create() {
        return new Vessel();
    }

    @Override
    public Set<Vessel> available() {
        TypedQuery<Vessel> query = manager.createNamedQuery(
                "Vessels.available", Vessel.class);
        return new HashSet<Vessel>(query.getResultList());
    }

    @Override
    public Vessel pick(Volumetric volumetric) {
        TypedQuery<Vessel> query = manager.createNamedQuery(
                "Vessels.pick", Vessel.class);
        query.setParameter("minimum", volumetric.getVolume());
        List<Vessel> candidates = query.getResultList();
        return !candidates.isEmpty() ? candidates.get(0) : null;
    }
}
