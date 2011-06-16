package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Container;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContainersImpl
        extends AbstractRepository<Container>
        implements Containers {

    public ContainersImpl() {
        super(Container.class);
    }

    @Override
    public Container create() {
        return new Container();
    }

    @Override
    public Set<Container> available() {
        TypedQuery<Container> query = entityManager.
                createNamedQuery("Containers.available", Container.class);
        return new HashSet<Container>(query.getResultList());
    }

    @Override
    public Container pick(Measure<Volume> volume) {
        TypedQuery<Container> query = entityManager.
                createNamedQuery("Containers.pick", Container.class);
        query.setParameter("minimum", volume);
        List<Container> candidates = query.getResultList();
        return !candidates.isEmpty() ? candidates.get(0) : null;
    }
}
