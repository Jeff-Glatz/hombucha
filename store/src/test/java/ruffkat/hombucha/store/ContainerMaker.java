package ruffkat.hombucha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Container;
import ruffkat.hombucha.time.Dates;

import java.util.Calendar;

@Component
public class ContainerMaker
        extends AbstractMaker<Container> {

    @Autowired
    private Containers containers;

    @Autowired
    private Sources sources;

    public ContainerMaker() {
        super(Container.class);
    }

    @Override
    public Containers repository() {
        return containers;
    }

    @Override
    public void make()
            throws Exception {
        Container containerA = containers.create();
        containerA.setVolume(Measurements.volume("10.0 l"));
        containerA.setName("Ceramic Container with Blue Stripes");
        containerA.setReceived(Dates.date(Calendar.MAY, 15, 2011));
        containerA.setSource(Searches.first(sources, "Natural Grocers"));
        containers.save(containerA);

        Container containerB = containers.create();
        containerB.setVolume(Measurements.volume("6.0 l"));
        containerB.setName("Recycled Glass Container");
        containerB.setReceived(Dates.date(Calendar.JUNE, 11, 2011));
        containerB.setSource(Searches.first(sources, "West Elm"));
        containers.save(containerB);

        Container containerC = containers.create();
        containerC.setVolume(Measurements.volume("6.0 l"));
        containerC.setName("Recycled Glass Container");
        containerC.setReceived(Dates.date(Calendar.JUNE, 11, 2011));
        containerC.setSource(Searches.first(sources, "West Elm"));
        containers.save(containerC);
    }
}
