package ruffkat.hombucha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Item;
import ruffkat.hombucha.money.Money;

import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;

@Component
public class ItemMaker
        extends AbstractMaker<Item> {

    @Autowired
    private Items items;

    @Autowired
    private Sources sources;

    public ItemMaker() {
        super(Item.class);
    }

    @Override
    public Items repository() {
        return items;
    }

    @Override
    public void make()
            throws Exception {
        Item<Volume> water = items.create(Volume.class);
        water.setName("Distilled Water");
        water.setPrice(new Money("0.89"));
        water.setUnit(Measurements.volume("3.785 l"));
        water.setSource(Searches.first(sources, "CVS"));
        items.save(water);

        Item<Mass> tea = items.create(Mass.class);
        tea.setName("Organic Ancient Emerald Lily");
        tea.setSource(Searches.first(sources, "Rishi Tea"));
        tea.setUnit(Measurements.mass("113.4 g"));
        tea.setPrice(new Money("22.00"));
        items.save(tea);

        Item<Mass> sugar = items.create(Mass.class);
        sugar.setName("Sugar in the Raw");
        sugar.setPrice(new Money("2.99"));
        sugar.setUnit(Measurements.mass("907 g"));
        items.save(sugar);
    }
}
