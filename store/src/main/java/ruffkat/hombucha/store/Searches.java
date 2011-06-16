package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

public final class Searches {

    public static <P extends Persistent> P single(Repository<P> repository, String criteria) {
        List<P> results = repository.search(criteria);
        if (results.size() != 1) {
            throw new NonUniqueResultException("Search criteria returned " +
                    results.size() + " results");
        }
        return results.get(0);
    }

    public static <P extends Persistent> P first(Repository<P> repository, String criteria) {
        List<P> results = repository.search(criteria);
        if (results.size() > 0) {
            return results.get(0);

        }
        throw new NoResultException("Search criteria returned no results");
    }
}
