package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

import java.util.Set;

public interface Repository<P extends Persistent> {
    void addRepositoryListener(RepositoryListener<P> listener);
    void removeRepositoryListener(RepositoryListener<P> listener);
    void save(P persistent);
    P load(Long oid);
    Set<P> search(String criteria);
    void delete(P persistent);
    Set<P> all();
    long count();
    void flush();
}
