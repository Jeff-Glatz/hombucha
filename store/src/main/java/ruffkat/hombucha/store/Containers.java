package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Container;

public interface Containers {
    Container create();
    Container load(Long id);
    void delete(Container container);
}
