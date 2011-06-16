package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Mushroom;

public interface Mushrooms
        extends Repository<Mushroom> {
    Mushroom create();
}
