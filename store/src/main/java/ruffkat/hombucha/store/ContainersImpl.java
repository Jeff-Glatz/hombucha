package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Container;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.persistence.TypedQuery;
import java.util.List;

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
    public List<Container> available() {
        TypedQuery<Container> query = entityManager.
                createNamedQuery("Containers.available", Container.class);
        return query.getResultList();
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
