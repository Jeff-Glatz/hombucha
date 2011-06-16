package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Mushroom;

public class MushroomsImpl
        extends AbstractRepository<Mushroom>
        implements Mushrooms {

    public MushroomsImpl() {
        super(Mushroom.class);
    }

    @Override
    public Mushroom create() {
        return new Mushroom();
    }
}
