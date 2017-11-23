package ruffkat.hombucha.store;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ruffkat.hombucha.model.Ferment;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Repository
public class FermentsImpl
        extends AbstractRepository<Ferment>
        implements Ferments {

    protected FermentsImpl() {
        super(Ferment.class);
    }

    @Override
    public Ferment create() {
        return new Ferment();
    }

    @Override
    public Set<Ferment> brewing() {
        TypedQuery<Ferment> query = manager.createNamedQuery(
                "Ferments.brewing", Ferment.class);
        return new HashSet<Ferment>(query.getResultList());
    }
}
