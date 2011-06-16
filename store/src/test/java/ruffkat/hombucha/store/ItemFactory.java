package ruffkat.hombucha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Item;

import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;
import java.math.BigDecimal;

@Component
public class ItemFactory
        extends AbstractFactory<Item> {

    @Autowired
    private Items items;

    @Autowired
    private Sources sources;

    public ItemFactory() {
        super(Item.class);
    }

    @Override
    public Items repository() {
        return items;
    }

    public void make()
            throws Exception {
        Item<Volume> water = items.create(Volume.class);
        water.setName("Distilled Water");
        water.setPrice(new BigDecimal("0.89"));
        water.setUnit(Measurements.volume("3.7 l"));
        water.setSource(Searches.single(sources, "CVS"));
        items.save(water);

        Item<Mass> tea = items.create(Mass.class);
        tea.setName("Organic Ancient Emerald Lily");
        tea.setSource(Searches.single(sources, "Rishi Tea"));
        tea.setUnit(Measurements.mass("113.4 g"));
        tea.setPrice(new BigDecimal("22.00"));
        items.save(tea);

        Item<Mass> sugar = items.create(Mass.class);
        sugar.setName("Sugar in the Raw");
        sugar.setPrice(new BigDecimal("2.99"));
        sugar.setUnit(Measurements.mass("907 g"));
        items.save(sugar);
    }
}
