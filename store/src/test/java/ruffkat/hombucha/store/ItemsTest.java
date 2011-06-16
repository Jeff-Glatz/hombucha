package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.time.Dates;
import ruffkat.hombucha.model.Item;

import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;
import javax.persistence.EntityNotFoundException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ItemsTest extends FunctionalTest {
    @Autowired
    private Items items;

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
}
