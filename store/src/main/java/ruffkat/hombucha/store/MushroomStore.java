package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Mushroom;

public class MushroomStore extends AbstractStore
        implements Mushrooms {

    @Override
    public Mushroom create() {
        return new Mushroom();
    }

    @Override
    public Mushroom load(Long id) {
        return entityManager.getReference(Mushroom.class, id);
    }

    @Override
    public void delete(Mushroom mushroom) {
        entityManager.remove(mushroom);
    }
}
