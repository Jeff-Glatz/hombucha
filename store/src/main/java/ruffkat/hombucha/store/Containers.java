package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Container;

public interface Containers
        extends Repository<Container> {
    Container create();
}
