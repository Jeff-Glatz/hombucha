package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Processing;
import ruffkat.hombucha.time.Dates;

import javax.persistence.EntityNotFoundException;
import javax.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static javax.time.Duration.standardDays;
import static junit.framework.Assert.assertFalse;
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
        ferment.setVolume(Measurements.volume("1.5 L"));
        ferment.setProcessing(Processing.SECONDARY);
        ferment.setStart(Dates.date(Calendar.MAY, 12, 2011));
        ferment.setStop(Dates.date(Calendar.MAY, 24, 2011));

        entityManager.persist(ferment);

        Long id = ferment.getOid();

        assertNotNull(id);
        assertEquals(ferment, ferments.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Ferment ferment = ferments.create();
        ferment.setVolume(Measurements.volume("6.0 l"));
        ferment.setProcessing(Processing.CONTINUOUS);
        ferment.setStart(Dates.date(Calendar.MAY, 12, 2011));
        ferment.setStop(Dates.date(Calendar.MAY, 24, 2011));

        entityManager.persist(ferment);

        Long id = ferment.getOid();

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
        Instant now = timeSource.instant();

        Ferment ferment = ferments.create();
        ferment.setProcessing(Processing.BATCH);
        ferment.setStart(new Date(now.minus(standardDays(7)).toEpochMillisLong()));
        ferment.setStop(new Date(now.plus(standardDays(7)).toEpochMillisLong()));

        Set<Ferment> active = ferments.brewing();
        assertFalse(active.contains(ferment));

        entityManager.persist(ferment);

        active = ferments.brewing();
        assertTrue(active.contains(ferment));
    }
}
