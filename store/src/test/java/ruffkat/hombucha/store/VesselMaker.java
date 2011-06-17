package ruffkat.hombucha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Vessel;
import ruffkat.hombucha.time.Dates;

import java.util.Calendar;

@Component
public class VesselMaker
        extends AbstractMaker<Vessel> {

    @Autowired
    private Vessels vessels;

    @Autowired
    private Sources sources;

    public VesselMaker() {
        super(Vessel.class);
    }

    @Override
    public Vessels repository() {
        return vessels;
    }

    @Override
    public void make()
            throws Exception {
        Vessel vesselA = vessels.create();
        vesselA.setVolume(Measurements.volume("10.0 l"));
        vesselA.setName("Ceramic Container with Blue Stripes");
        vesselA.setReceived(Dates.date(Calendar.MAY, 15, 2011));
        vesselA.setSource(Searches.first(sources, "Natural Grocers"));
        vessels.save(vesselA);

        Vessel vesselB = vessels.create();
        vesselB.setVolume(Measurements.volume("6.0 l"));
        vesselB.setName("Recycled Glass Container");
        vesselB.setReceived(Dates.date(Calendar.JUNE, 11, 2011));
        vesselB.setSource(Searches.first(sources, "West Elm"));
        vessels.save(vesselB);

        Vessel vesselC = vessels.create();
        vesselC.setVolume(Measurements.volume("6.0 l"));
        vesselC.setName("Recycled Glass Container");
        vesselC.setReceived(Dates.date(Calendar.JUNE, 11, 2011));
        vesselC.setSource(Searches.first(sources, "West Elm"));
        vessels.save(vesselC);
    }
}
