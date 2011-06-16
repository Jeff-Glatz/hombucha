package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

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
    protected final Class<P> type;

    @PersistenceContext(name = "hombucha")
    protected EntityManager entityManager;

    protected AbstractRepository(Class<P> type) {
        this.type = type;
    }

    @Override
    public void save(P persistent) {
        if (persistent.persisted()) {
            entityManager.merge(persistent);
        } else {
            entityManager.persist(persistent);
        }
    }

    @Override
    public P load(Long id) {
        return entityManager.getReference(type, id);
    }

    // TODO: lucene
    @Override
    public Set<P> search(String criteria) {
        // Build the criteria query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<P> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<P> from = criteriaQuery.from(type);
        criteriaQuery = criteriaQuery.where(criteriaBuilder.equal(from.get("name"), criteria));
        // Create a "real" query from it
        TypedQuery<P> query = entityManager.createQuery(criteriaQuery);
        return new HashSet<P>(query.getResultList());
    }

    @Override
    public void delete(P persistent) {
        entityManager.remove(persistent);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }
}
