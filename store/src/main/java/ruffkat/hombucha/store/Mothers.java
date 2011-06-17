package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Mother;

public interface Mothers
        extends Repository<Mother> {
    Mother create();
}
