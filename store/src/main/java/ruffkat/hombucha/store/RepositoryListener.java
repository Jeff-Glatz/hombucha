package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

import java.util.EventListener;

public interface RepositoryListener<P extends Persistent>
        extends EventListener {
    void saved(RepositoryEvent<P> event);
    void deleted(RepositoryEvent<P> event);
}
