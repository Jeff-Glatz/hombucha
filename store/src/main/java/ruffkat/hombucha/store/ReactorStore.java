package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Reactor;

public class ReactorStore extends AbstractStore
        implements Reactors {

    @Override
    public Reactor create() {
        return new Reactor();
    }

    @Override
    public Reactor load(Long id) {
        return entityManager.getReference(Reactor.class, id);
    }

    @Override
    public void delete(Reactor reactor) {
        entityManager.remove(reactor);
    }
}
