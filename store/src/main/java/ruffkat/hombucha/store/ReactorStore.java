package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Reactor;

public class ReactorStore
        extends AbstractStore<Reactor>
        implements Reactors {

    public ReactorStore() {
        super(Reactor.class);
    }

    @Override
    public Reactor create() {
        return new Reactor();
    }
}
