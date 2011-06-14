package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.model.CalendarUtils;
import ruffkat.hombucha.model.Container;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ContainersTest extends FunctionalTest {
    @Autowired
    private Containers containers;

    @Test
    @Rollback(false)
    public void testSaveAndLoad() {
        Container container = containers.create();
        container.setName("SCOBY DO");
        container.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));

        entityManager.persist(container);

        Long id = container.getId();

        assertNotNull(id);
        assertEquals(container, containers.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Container container = containers.create();
        container.setName("SCOBY DO");
        container.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));

        entityManager.persist(container);

        Long id = container.getId();

        container = containers.load(id);
        containers.delete(container);

        try {
            containers.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }
}
