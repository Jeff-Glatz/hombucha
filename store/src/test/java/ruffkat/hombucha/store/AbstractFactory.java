package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

public abstract class AbstractFactory<P extends Persistent>
        implements Factory<P> {

    protected final Class<P> type;

    protected AbstractFactory(Class<P> type) {
        this.type = type;
    }

    public P find(String criteria) {
        return Searches.single(repository(), criteria);
    }
}
