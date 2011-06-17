package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.time.Dates;
import ruffkat.hombucha.model.Mother;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class MothersTest extends FunctionalTest {
    @Autowired
    private Mothers mothers;

    @Test
    @Rollback(false)
    public void testSaveAndLoad() {
        Mother mother = mothers.create();
        mother.setName("SCOBY DO");
        mother.setReceived(Dates.date(Calendar.MAY, 12, 2011));

        entityManager.persist(mother);

        Long id = mother.getOid();

        assertNotNull(id);
        assertEquals(mother, mothers.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Mother mother = mothers.create();
        mother.setName("SCOBY DO");
        mother.setReceived(Dates.date(Calendar.MAY, 12, 2011));

        entityManager.persist(mother);

        Long id = mother.getOid();

        mother = mothers.load(id);
        mothers.delete(mother);

        try {
            mothers.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }
}
