package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Container;

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
                createQuery("from Container", Container.class);
        return query.getResultList();
    }
}
