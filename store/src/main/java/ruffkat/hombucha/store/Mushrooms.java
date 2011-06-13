package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Mushroom;

public interface Mushrooms {
    Mushroom create();
    Mushroom load(Long id);
    void delete(Mushroom mushroom);
}
