package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;
import ruffkat.hombucha.util.ListenerList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractRepository<P extends Persistent>
        implements Repository<P> {
    protected final ListenerList<RepositoryListener> listeners =
            new ListenerList<RepositoryListener>(RepositoryListener.class);
    protected final Class<P> type;

    @PersistenceContext(name = "hombucha")
    protected EntityManager manager;

    protected AbstractRepository(Class<P> type) {
        this.type = type;
    }

    @Override
    public void addRepositoryListener(RepositoryListener<P> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeRepositoryListener(RepositoryListener<P> listener) {
        listeners.remove(listener);
    }

    @Override
    public void save(P persistent) {
        if (persistent.persisted()) {
            manager.merge(persistent);
        } else {
            manager.persist(persistent);
        }
        fireSaved(persistent);
    }

    @Override
    public P load(Long id) {
        return manager.getReference(type, id);
    }

    // TODO: lucene
    @Override
    public Set<P> search(String criteria) {
        // Build the criteria query
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<P> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<P> from = criteriaQuery.from(type);
        criteriaQuery = criteriaQuery.where(criteriaBuilder.equal(from.get("name"), criteria));
        // Create a "real" query from it
        TypedQuery<P> query = manager.createQuery(criteriaQuery);
        return new HashSet<P>(query.getResultList());
    }

    @Override
    public Set<P> all() {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<P> criteriaQuery = criteriaBuilder.createQuery(type);
        criteriaQuery.from(type);
        TypedQuery<P> query = manager.createQuery(criteriaQuery);
        return new HashSet<P>(query.getResultList());
    }

    @Override
    public long count() {
        CriteriaBuilder qb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(type)));
        return manager.createQuery(cq).getSingleResult();
    }

    @Override
    public void delete(P persistent) {
        manager.remove(persistent);
        fireDeleted(persistent);
    }

    @Override
    public void flush() {
        manager.flush();
    }

    protected void fireSaved(P persistent) {
        if (!listeners.isEmpty()) {
            RepositoryEvent<P> event = new RepositoryEvent<P>(this, persistent);
            for (RepositoryListener<P> listener : listeners.listeners()) {
                listener.saved(event);
            }
        }
    }

    protected void fireDeleted(P persistent) {
        if (!listeners.isEmpty()) {
            RepositoryEvent<P> event = new RepositoryEvent<P>(this, persistent);
            for (RepositoryListener<P> listener : listeners.listeners()) {
                listener.deleted(event);
            }
        }
    }
}
