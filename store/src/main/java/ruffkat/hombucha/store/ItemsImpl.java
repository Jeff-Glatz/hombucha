package ruffkat.hombucha.store;

import org.springframework.stereotype.Repository;
import ruffkat.hombucha.model.Item;

import javax.measure.quantity.Quantity;

@Repository
public class ItemsImpl extends
        AbstractRepository<Item>
        implements Items {

    public ItemsImpl() {
        super(Item.class);
    }

    @Override
    public <Q extends Quantity> Item<Q> create(Class<Q> type) {
        return new Item<Q>();
    }
}
