package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;

public interface Predicate<P extends Persistent> {
    boolean include(P persistent);
}
