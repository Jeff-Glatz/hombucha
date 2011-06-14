package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Mushroom;

public class MushroomStore
        extends AbstractStore<Mushroom>
        implements Mushrooms {

    public MushroomStore() {
        super(Mushroom.class);
    }

    @Override
    public Mushroom create() {
        return new Mushroom();
    }
}
