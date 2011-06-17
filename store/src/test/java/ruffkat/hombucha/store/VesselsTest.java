package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.model.Vessel;
import ruffkat.hombucha.time.Dates;
import ruffkat.hombucha.measure.Measurements;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class VesselsTest extends FunctionalTest {
    @Autowired
    private Vessels vessels;

    @Test
    @Rollback(false)
    public void testSaveAndLoad() {
        Vessel vessel = vessels.create();
        vessel.setName("SCOBY DO");
        vessel.setReceived(Dates.date(Calendar.MAY, 12, 2011));
        vessel.setVolume(Measurements.volume("6.0 l"));

        entityManager.persist(vessel);

        Long id = vessel.getOid();

        assertNotNull(id);
        assertEquals(vessel, vessels.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Vessel vessel = vessels.create();
        vessel.setName("SCOBY DO");
        vessel.setReceived(Dates.date(Calendar.MAY, 12, 2011));
        vessel.setVolume(Measurements.volume("6.0 l"));

        entityManager.persist(vessel);

        Long id = vessel.getOid();

        vessel = vessels.load(id);
        vessels.delete(vessel);

        try {
            vessels.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }
}
