package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Item;

public interface Items {
    Item create();
    Item load(Long id);
    void delete(Long id);
}
