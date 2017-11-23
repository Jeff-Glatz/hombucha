package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.model.Friend;
import ruffkat.hombucha.model.Local;
import ruffkat.hombucha.model.Website;
import ruffkat.hombucha.model.Source;

import java.net.URL;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SourcesIT
        extends IntegrationTest {

    @Autowired
    private Sources sources;

    @Test
    @Rollback(false)
    public void testSaveAndLoad()
            throws Exception {
        Local local = sources.create(Local.class);
        local.setName("Local place");
        local.setDirections("go here");
        local.setEmail("foo@bar.com");
        local.setPhone("111-111-1111");
        local.setUrl(new URL("http://www.www.com"));

        sources.save(local);

        Long id = local.getOid();

        assertNotNull(id);
        assertEquals(local, sources.load(id));
    }

    @Test
    @Rollback
    public void testSaveAndDelete()
            throws Exception {
        Website website = sources.create(Website.class);
        website.setName("SCOBY DO");
        website.setUrl(new URL("http://www.www.com"));

        entityManager.persist(website);

        Long id = website.getOid();

        Source source = sources.load(id);
        sources.delete(source);

        try {
            sources.load(id);
            fail("expected an exception");
        } catch (JpaObjectRetrievalFailureException e) {
        }
    }

    @Test
    @Rollback(false)
    public void testPersistItems()
            throws Exception {
        Local cvs = new Local();
        cvs.setName("CVS");
        cvs.setUrl(new URL("http://www.cvs.com"));
        cvs.setPhone("111-111-1111");
        cvs.setDirections("hsdhasjdhajs");

        assertFalse(cvs.persisted());
        sources.save(cvs);
        assertTrue(cvs.persisted());

        Website rishi = new Website();
        rishi.setName("Rishi Tea");
        rishi.setUrl(new URL("http://www.rishi-tea.com"));

        assertFalse(rishi.persisted());
        sources.save(rishi);
        assertTrue(rishi.persisted());

        Friend friend = new Friend();
        friend.setName("My friend");
        friend.setEmail("friend@bar.com");
        friend.setPhone("111-111-1111");

        assertFalse(friend.persisted());
        sources.save(friend);
        assertTrue(friend.persisted());
    }
}
