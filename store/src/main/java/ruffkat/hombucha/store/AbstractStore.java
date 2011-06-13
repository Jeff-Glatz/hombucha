package ruffkat.hombucha.store;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractStore {

    @PersistenceContext(name = "hombucha")
    protected EntityManager entityManager;
}
