package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Item;

public class ItemsImpl extends Repository<Item>
        implements Items {

    public ItemsImpl() {
        super(Item.class);
    }

    @Override
    public Item create() {
        return new Item();
    }
}
