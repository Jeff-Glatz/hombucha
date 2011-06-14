package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.model.CalendarUtils;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Processing;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FermentsTest extends FunctionalTest {
    @Autowired
    private Ferments ferments;

    @Test
    @Rollback(false)
    public void testSaveAndLoad() {
        Ferment ferment = ferments.create();
        ferment.setProcessing(Processing.BATCH);
        ferment.setStartTime(CalendarUtils.date(Calendar.MAY, 12, 2011));
        ferment.setEndTime(CalendarUtils.date(Calendar.MAY, 24, 2011));

        entityManager.persist(ferment);

        Long id = ferment.getId();

        assertNotNull(id);
        assertEquals(ferment, ferments.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Ferment ferment = ferments.create();
        ferment.setProcessing(Processing.BATCH);
        ferment.setStartTime(CalendarUtils.date(Calendar.MAY, 12, 2011));
        ferment.setEndTime(CalendarUtils.date(Calendar.MAY, 24, 2011));

        entityManager.persist(ferment);

        Long id = ferment.getId();

        ferment = ferments.load(id);
        ferments.delete(ferment);

        try {
            ferments.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }

    @Test
    @Rollback(false)
    public void testActive() {
        Set<Ferment> active = ferments.active();
        assertEquals(0, active.size());

        Ferment ferment = ferments.create();
        ferment.setProcessing(Processing.BATCH);
        ferment.setStartTime(CalendarUtils.date(Calendar.MAY, 12, 2011));
        ferment.setEndTime(CalendarUtils.date(Calendar.MAY, 24, 2012));

        entityManager.persist(ferment);

        active = ferments.active();
        assertEquals(1, active.size());
        assertTrue(active.contains(ferment));
    }
}
