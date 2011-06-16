package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

public abstract class AbstractMaker<P extends Persistent>
        implements Maker<P> {

    protected final Class<P> type;

    protected AbstractMaker(Class<P> type) {
        this.type = type;
    }
}
