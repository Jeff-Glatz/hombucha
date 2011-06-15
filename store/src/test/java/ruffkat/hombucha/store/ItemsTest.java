package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.util.Dates;
import ruffkat.hombucha.model.Item;

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
        Item item = items.create();
        item.setName("SCOBY DO");
        item.setReceived(Dates.date(Calendar.MAY, 12, 2011));

        entityManager.persist(item);

        Long id = item.getId();

        assertNotNull(id);
        assertEquals(item, items.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Item item = items.create();
        item.setName("SCOBY DO");
        item.setReceived(Dates.date(Calendar.MAY, 12, 2011));

        entityManager.persist(item);

        Long id = item.getId();

        item = items.load(id);
        items.delete(item);

        try {
            items.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }
}
