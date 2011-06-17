package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Mother;

public class MothersImpl
        extends AbstractRepository<Mother>
        implements Mothers {

    public MothersImpl() {
        super(Mother.class);
    }

    @Override
    public Mother create() {
        return new Mother();
    }
}
