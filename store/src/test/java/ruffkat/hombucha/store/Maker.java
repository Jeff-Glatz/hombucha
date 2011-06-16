package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

public interface Maker<P extends Persistent> {
    Repository<P> repository();
    void make()
            throws Exception;
}
