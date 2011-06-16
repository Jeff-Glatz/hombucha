package ruffkat.hombucha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.model.Mushroom;
import ruffkat.hombucha.time.Dates;

import java.util.Calendar;

@Component
public class MushroomMaker
        extends AbstractMaker<Mushroom> {

    @Autowired
    private Mushrooms mushrooms;

    @Autowired
    private Sources sources;

    public MushroomMaker() {
        super(Mushroom.class);
    }

    @Override
    public Mushrooms repository() {
        return mushrooms;
    }

    @Override
    public void make()
            throws Exception {
        // Add a mother
        Mushroom mother = mushrooms.create();
        mother.setName("Squiddy");
        mother.setReceived(Dates.date(Calendar.MAY, 7, 2011));
        mother.setSource(Searches.first(sources, "My friend"));
        mushrooms.save(mother);

        // Add the babies
        Mushroom baby = mushrooms.create();
        baby.setName("Eggshell");
        baby.setMother(mother);
        baby.setReceived(Dates.date(Calendar.MAY, 7, 2011));
        baby.setSource(Searches.first(sources, "My friend"));
        mushrooms.save(baby);
    }
}
