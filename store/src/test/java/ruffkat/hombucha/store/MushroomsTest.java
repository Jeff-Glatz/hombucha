package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.time.Dates;
import ruffkat.hombucha.model.Mushroom;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class MushroomsTest extends FunctionalTest {
    @Autowired
    private Mushrooms mushrooms;

    @Test
    @Rollback(false)
    public void testSaveAndLoad() {
        Mushroom mushroom = mushrooms.create();
        mushroom.setName("SCOBY DO");
        mushroom.setReceived(Dates.date(Calendar.MAY, 12, 2011));

        entityManager.persist(mushroom);

        Long id = mushroom.getOid();

        assertNotNull(id);
        assertEquals(mushroom, mushrooms.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Mushroom mushroom = mushrooms.create();
        mushroom.setName("SCOBY DO");
        mushroom.setReceived(Dates.date(Calendar.MAY, 12, 2011));

        entityManager.persist(mushroom);

        Long id = mushroom.getOid();

        mushroom = mushrooms.load(id);
        mushrooms.delete(mushroom);

        try {
            mushrooms.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }
}
