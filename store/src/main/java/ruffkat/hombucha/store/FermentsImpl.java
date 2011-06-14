package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Ferment;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

public class FermentsImpl
        extends Repository<Ferment>
        implements Ferments {

    protected FermentsImpl() {
        super(Ferment.class);
    }

    @Override
    public Ferment create() {
        return new Ferment();
    }

    @Override
    public Set<Ferment> active() {
        TypedQuery<Ferment> query = entityManager.
                createNamedQuery("Ferments.active", Ferment.class);
        return new HashSet<Ferment>(query.getResultList());
    }
}
