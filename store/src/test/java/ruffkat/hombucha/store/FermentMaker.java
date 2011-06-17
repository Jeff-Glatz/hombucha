package ruffkat.hombucha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Processing;

@Component
public class FermentMaker
        extends AbstractMaker<Ferment> {

    @Autowired
    private Ferments ferments;

    @Autowired
    private Mothers mothers;

    @Autowired
    private Recipes recipes;

    @Autowired
    private Vessels vessels;

    public FermentMaker() {
        super(Ferment.class);
    }

    @Override
    public Ferments repository() {
        return ferments;
    }

    @Override
    public void make()
            throws Exception {
        Ferment daily = ferments.create();
        daily.setName("Daily Elixir");
        daily.setProcessing(Processing.CONTINUOUS);
        daily.setMother(Searches.first(mothers, "Squiddy"));
        daily.setRecipe(Searches.first(recipes, "Starter Solution"));
        daily.setVolume(Measurements.volume("6.0 l"));
        daily.setVessel(vessels.pick(daily));
        ferments.save(daily);

        Ferment batch = ferments.create();
        batch.setName("MaltBrewCha Run 1");
        batch.setProcessing(Processing.BATCH);
        batch.setMother(Searches.first(mothers, "Squiddy"));
        batch.setRecipe(Searches.first(recipes, "Starter Solution"));
        batch.setVolume(Measurements.volume("6.0 l"));
        batch.setVessel(vessels.pick(batch));
        ferments.save(batch);
    }
}
