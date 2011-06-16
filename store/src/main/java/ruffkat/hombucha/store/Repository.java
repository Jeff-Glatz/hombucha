package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

import java.util.List;

public interface Repository<P extends Persistent> {
    void save(P item);
    P load(Long oid);
    List<P> search(String criteria);
    void delete(P item);
    void flush();
}
