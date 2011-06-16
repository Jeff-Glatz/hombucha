package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

import java.util.Set;

public interface Repository<P extends Persistent> {
    void save(P item);
    P load(Long oid);
    Set<P> search(String criteria);
    void delete(P item);
    void flush();
}
