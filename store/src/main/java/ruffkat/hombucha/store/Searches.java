package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.Set;

public final class Searches {

    public static <P extends Persistent> P single(Repository<P> repository, String criteria) {
        Set<P> results = repository.search(criteria);
        if (results.size() != 1) {
            throw new NonUniqueResultException("Search criteria returned " +
                    results.size() + " results");
        }
        return results.iterator().next();
    }

    public static <P extends Persistent> P first(Repository<P> repository, String criteria,
                                                 Predicate<P> predicate) {
        Set<P> results = repository.search(criteria);
        for (P result : results) {
            if (predicate.include(result)) {
                return result;
            }
        }
        throw new NoResultException("Search criteria returned no results");
    }

    public static <P extends Persistent> P first(Repository<P> repository, String criteria) {
        return first(repository, criteria, Predicates.<P>any());
    }
}
