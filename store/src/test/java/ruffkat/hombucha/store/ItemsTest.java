package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Item;
import ruffkat.hombucha.time.Dates;

import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ItemsTest extends FunctionalTest {

    @Autowired
    private Items items;

    @Autowired
    private SourceMaker sourceFactory;

    @Test
    @Rollback(false)
    public void testSaveAndLoad() {
        Item<Mass> item = items.create(Mass.class);
        item.setName("SCOBY DO");
        item.setReceived(Dates.date(Calendar.MAY, 12, 2011));
        item.setUnit(Measurements.mass("12 kg"));

        entityManager.persist(item);

        Long id = item.getOid();

        assertNotNull(id);
        assertEquals(item, items.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Item<Volume> item = items.create(Volume.class);
        item.setName("SCOBY DO");
        item.setReceived(Dates.date(Calendar.MAY, 12, 2011));
        item.setUnit(Measurements.volume("12 l"));

        entityManager.persist(item);

        Long id = item.getOid();

        item = items.load(id);
        items.delete(item);

        try {
            items.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }

    @Test
    @Rollback(false)
    public void testPersistItems()
            throws Exception {
        sourceFactory.make();

        Item<Volume> water = items.create(Volume.class);
        water.setName("Distilled Water");
        water.setSource(Searches.first(sourceFactory.repository(), "CVS"));
        water.setPrice(new BigDecimal("0.89"));
        water.setUnit(Measurements.volume("3.7 l"));

        assertFalse(water.persisted());
        items.save(water);
        assertTrue(water.persisted());

        Item<Mass> tea = items.create(Mass.class);
        tea.setName("Organic Ancient Emerald Lily");
        tea.setSource(Searches.first(sourceFactory.repository(), "Rishi Tea"));
        tea.setPrice(new BigDecimal("22.00"));
        tea.setUnit(Measurements.mass("113.4 g"));

        assertFalse(tea.persisted());
        items.save(tea);
        assertTrue(tea.persisted());

        Item<Mass> sugar = items.create(Mass.class);
        sugar.setName("Sugar in the Raw");
        sugar.setPrice(new BigDecimal("2.99"));
        sugar.setUnit(Measurements.mass("907 g"));

        assertFalse(sugar.persisted());
        items.save(sugar);
        assertTrue(sugar.persisted());
    }
}
