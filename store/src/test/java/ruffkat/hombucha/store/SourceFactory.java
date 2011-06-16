package ruffkat.hombucha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.model.Friend;
import ruffkat.hombucha.model.Local;
import ruffkat.hombucha.model.Online;
import ruffkat.hombucha.model.Source;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class SourceFactory
        extends AbstractFactory<Source> {

    @Autowired
    private Sources sources;

    public SourceFactory() {
        super(Source.class);
    }

    @Override
    public Sources repository() {
        return sources;
    }

    public void make()
            throws Exception {
        makeFriendSources();
        makeLocalSources();
        makeOnlineSources();
    }

    private void makeFriendSources() {
        Friend friend = new Friend();
        friend.setName("My friend");
        friend.setEmail("friend@bar.com");
        friend.setPhone("111-111-1111");
        sources.save(friend);
    }

    private void makeLocalSources()
            throws MalformedURLException {
        Local cvs = new Local();
        cvs.setName("CVS");
        cvs.setUrl(new URL("http://www.cvs.com"));
        cvs.setPhone("111-111-1111");
        cvs.setDirections("Beltline/35");
        sources.save(cvs);
    }

    private void makeOnlineSources()
            throws MalformedURLException {
        Online rishi = new Online();
        rishi.setName("Rishi Tea");
        rishi.setUrl(new URL("http://www.rishi-tea.com"));
        sources.save(rishi);

        Online westelm = sources.create(Online.class);
        westelm.setName("West Elm");
        westelm.setUrl(new URL("http://www.westelm.com"));
        sources.save(westelm);

        Online naturalGrocers = sources.create(Online.class);
        naturalGrocers.setName("Natural Grocers");
        naturalGrocers.setUrl(new URL("http://www.naturalgrocers.com"));
        sources.save(naturalGrocers);
    }
}
