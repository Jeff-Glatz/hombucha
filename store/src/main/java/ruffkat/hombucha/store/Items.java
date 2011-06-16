package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Item;

import javax.measure.quantity.Quantity;

public interface Items
        extends Repository<Item> {
    <Q extends Quantity> Item<Q> create(Class<Q> type);
}
