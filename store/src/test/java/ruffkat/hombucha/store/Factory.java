package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

public interface Factory<P extends Persistent> {
    Repository<P> repository();
    void make()
            throws Exception;
}
