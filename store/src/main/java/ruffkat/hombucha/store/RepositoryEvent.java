package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

import java.util.EventObject;

public class RepositoryEvent<P extends Persistent>
        extends EventObject {
    private final P persistent;

    public RepositoryEvent(Repository<P> repository, P persistent) {
        super(repository);
        this.persistent = persistent;
    }

    public Repository<P> getRepository() {
        return (Repository<P>) source;
    }

    public P getPersistent() {
        return persistent;
    }
}
