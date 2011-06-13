package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.model.CalendarUtils;
import ruffkat.hombucha.model.Friend;
import ruffkat.hombucha.model.Mushroom;
import ruffkat.hombucha.model.Online;
import ruffkat.hombucha.model.Reactor;

import java.net.URL;
import java.util.Calendar;

public class SamplesTest extends FunctionalTest {
    @Autowired
    private Samples samples;

    @Test
    @Rollback(false)
    public void SaveSample()
            throws Exception {
        // Create a mother
        Mushroom mother = new Mushroom();
        mother.setSource(new Friend("Christina Toyota"));
        mother.setName("Mama");
        mother.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));
        entityManager.persist(mother);

        // First baby
        Mushroom baby1 = new Mushroom();
        baby1.setSource(new Friend("Christina Toyota"));
        baby1.setName("Baby 1");
        baby1.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));
        baby1.setMother(mother);
        entityManager.persist(baby1);

        // Second baby
        Mushroom baby2 = new Mushroom();
        baby2.setSource(new Friend("Christina Toyota"));
        baby2.setName("Baby 2");
        baby2.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));
        baby2.setMother(mother);
        entityManager.persist(baby2);

        // Create a reactor to hold the brew
        Reactor reactorA = new Reactor();
        reactorA.setSource(new Online("Recycled Glass Dispenser",
                new URL("http://www.westelm.com")));
        reactorA.setName("A");
        reactorA.setReceived(CalendarUtils.date(Calendar.JUNE, 11, 2011));
        entityManager.persist(reactorA);

        // Create another reactor to hold the brew
        Reactor reactorB = new Reactor();
        reactorB.setSource(new Online("Recycled Glass Dispenser",
                new URL("http://www.westelm.com")));
        reactorB.setName("B");
        reactorB.setReceived(CalendarUtils.date(Calendar.JUNE, 11, 2011));
        entityManager.persist(reactorB);

//        Ferment ancientPuErh2006 = new Ferment();
//        ancientPuErh2006.setMethod(Method.BATCH);
//        ancientPuErh2006.setReactor(reactorA);
//        ancientPuErh2006.setStart(CalendarUtils.date(Calendar.JUNE, 12, 2011));
//        ancientPuErh2006.setEnd(CalendarUtils.date(Calendar.JUNE, 26, 2011));
//        entityManager.persist(ancientPuErh2006);
//
//        Ferment bloodOrangePuErh = new Ferment();
//        bloodOrangePuErh.setMethod(Method.CONTINUOUS);
//        bloodOrangePuErh.setReactor(reactorB);
//        bloodOrangePuErh.setStart(CalendarUtils.date(Calendar.JUNE, 12, 2011));
//        entityManager.persist(bloodOrangePuErh);
//
//        Sample<Molarity> ph = samples.ph(bloodOrangePuErh);
//        entityManager.persist(ph);

        entityManager.flush();
    }
}
