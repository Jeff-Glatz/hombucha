package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Persistent;
import ruffkat.hombucha.money.Econometric;

public final class Predicates {

    public static <P extends Persistent> Predicate<P> any() {
        return new Predicate<P>() {
            @Override
            public boolean include(P persistent) {
                return true;
            }
        };
    }

    public static <P extends Persistent> Predicate<P> withUnitPrice() {
        return new Predicate<P>() {
            @Override
            public boolean include(P persistent) {
                return (persistent instanceof Econometric)
                        && ((Econometric) persistent).getPrice() != null
                        && ((Econometric) persistent).getUnit() != null;
            }
        };
    }
}
