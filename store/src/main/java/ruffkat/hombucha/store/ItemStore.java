package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Item;

public class ItemStore extends AbstractStore<Item>
        implements Items {

    public ItemStore() {
        super(Item.class);
    }

    @Override
    public Item create() {
        return new Item();
    }
}
