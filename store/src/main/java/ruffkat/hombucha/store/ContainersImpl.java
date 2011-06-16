package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Container;

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
}
