package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Reactor;

public class ReactorsImpl
        extends AbstractStore<Reactor>
        implements Reactors {

    public ReactorsImpl() {
        super(Reactor.class);
    }

    @Override
    public Reactor create() {
        return new Reactor();
    }
}
