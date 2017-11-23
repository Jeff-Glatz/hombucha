package ruffkat.hombucha.store;

import org.springframework.stereotype.Repository;
import ruffkat.hombucha.model.Mother;

@Repository
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
