package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Item;

import javax.measure.quantity.Quantity;

public class ItemsImpl extends Repository<Item>
        implements Items {

    public ItemsImpl() {
        super(Item.class);
    }

    @Override
    public <Q extends Quantity> Item<Q> create(Class<Q> type) {
        return new Item<Q>();
    }
}
