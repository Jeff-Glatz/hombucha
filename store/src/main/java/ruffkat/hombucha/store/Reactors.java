package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Reactor;

public interface Reactors {
    Reactor create();
    Reactor load(Long id);
    void delete(Reactor id);
}
