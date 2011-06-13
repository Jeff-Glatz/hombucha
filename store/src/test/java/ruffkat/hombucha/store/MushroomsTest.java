package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.model.CalendarUtils;
import ruffkat.hombucha.model.Mushroom;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class MushroomsTest extends FunctionalTest {

    @Autowired
    private Mushrooms mushrooms;

    @Test
    @Rollback(false)
    public void testSaveAndLoadMushroom() {
        Mushroom mushroom = mushrooms.create();
        mushroom.setName("SCOBY DO");
        mushroom.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));

        entityManager.persist(mushroom);

        Long id = mushroom.getId();

        assertNotNull(id);
        assertEquals(mushroom, mushrooms.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDeleteMushroom() {
        Mushroom mushroom = mushrooms.create();
        mushroom.setName("SCOBY DO");
        mushroom.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));

        entityManager.persist(mushroom);

        Long id = mushroom.getId();

        mushroom = mushrooms.load(id);
        mushrooms.delete(mushroom);

        try {
            mushrooms.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }
}
