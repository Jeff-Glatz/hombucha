package ruffkat.hombucha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.model.Mother;
import ruffkat.hombucha.time.Dates;

import java.util.Calendar;

@Component
public class MotherMaker
        extends AbstractMaker<Mother> {

    @Autowired
    private Mothers mothers;

    @Autowired
    private Sources sources;

    public MotherMaker() {
        super(Mother.class);
    }

    @Override
    public Mothers repository() {
        return mothers;
    }

    @Override
    public void make()
            throws Exception {
        // Add a mother
        Mother mother = mothers.create();
        mother.setName("Squiddy");
        mother.setReceived(Dates.date(Calendar.MAY, 7, 2011));
        mother.setSource(Searches.first(sources, "My friend"));
        mothers.save(mother);

        // Add the babies
        Mother baby = mothers.create();
        baby.setName("Eggshell");
        baby.setMother(mother);
        baby.setReceived(Dates.date(Calendar.MAY, 7, 2011));
        baby.setSource(Searches.first(sources, "My friend"));
        mothers.save(baby);
    }
}
