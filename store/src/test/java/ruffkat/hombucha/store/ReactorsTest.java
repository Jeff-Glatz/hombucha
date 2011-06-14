package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.model.CalendarUtils;
import ruffkat.hombucha.model.Reactor;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ReactorsTest extends FunctionalTest {
    @Autowired
    private Reactors reactors;

    @Test
    @Rollback(false)
    public void testSaveAndLoad() {
        Reactor reactor = reactors.create();
        reactor.setName("SCOBY DO");
        reactor.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));

        entityManager.persist(reactor);

        Long id = reactor.getId();

        assertNotNull(id);
        assertEquals(reactor, reactors.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Reactor reactor = reactors.create();
        reactor.setName("SCOBY DO");
        reactor.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));

        entityManager.persist(reactor);

        Long id = reactor.getId();

        reactor = reactors.load(id);
        reactors.delete(reactor);

        try {
            reactors.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }
}
