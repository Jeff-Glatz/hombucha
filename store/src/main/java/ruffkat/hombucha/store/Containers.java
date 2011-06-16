package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Container;

import java.util.List;

public interface Containers
        extends Repository<Container> {
    Container create();
    List<Container> available();
}
