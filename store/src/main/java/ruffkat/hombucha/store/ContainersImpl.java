package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Container;

public class ContainersImpl
        extends Repository<Container>
        implements Containers {

    public ContainersImpl() {
        super(Container.class);
    }

    @Override
    public Container create() {
        return new Container();
    }
}
