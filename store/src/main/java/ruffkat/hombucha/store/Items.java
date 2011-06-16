package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Item;

import javax.measure.quantity.Quantity;

public interface Items {
    <Q extends Quantity> Item<Q> create(Class<Q> type);
    Item load(Long id);
    void delete(Item item);
}
