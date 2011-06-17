package ruffkat.hombucha.store;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Processing;
import ruffkat.hombucha.model.Recipe;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.time.Duration;
import javax.time.Instant;
import javax.time.TimeSource;
import java.util.Date;

public class AcceptanceTest extends FunctionalTest {

    @Autowired
    private TimeSource timeSource;

    @Autowired
    private SourceMaker sourceMaker;

    @Autowired
    private ItemMaker itemMaker;

    @Autowired
    private ContainerMaker containerMaker;

    @Autowired
    private MotherMaker mushroomMaker;

    @Autowired
    private RecipeMaker recipeMaker;

    @Autowired
    private FermentMaker fermentMaker;

    @Autowired
    private Ferments ferments;

    @Before
    public void setUp()
            throws Exception {
        sourceMaker.make();
        itemMaker.make();
        containerMaker.make();
        mushroomMaker.make();
        recipeMaker.make();
        fermentMaker.make();
    }

    @Test
    public void CalculateCustomStarterSolution()
            throws Exception {
        Recipes recipes = recipeMaker.repository();

        Recipe recipe = Searches.first(recipes, "Starter Solution");
        System.out.println(recipe);

        Measure<Volume> kettle = Measurements.volume("2.0 l");
        Recipe scaled = recipe.scale(kettle);
        System.out.println(scaled);
    }

    @Test
    public void DesignNewFerment()
            throws Exception {
        Containers containers = containerMaker.repository();
        Mothers mothers = mushroomMaker.repository();
        Recipes recipes = recipeMaker.repository();

        Ferment batch = ferments.create();
        batch.setName("Pu-Erh 2006");
        batch.setProcessing(Processing.SECONDARY);
        batch.setMother(Searches.first(mothers, "Squiddy"));
        batch.setRecipe(Searches.first(recipes, "Starter Solution"));
        batch.setVolume(Measurements.volume("6.0 l"));
        batch.setContainer(containers.pick(batch));
        ferments.save(batch);
    }

    @Test
    public void StartFermentation()
            throws Exception {
        Ferment batch = Searches.first(ferments, "MaltBrewCha Run 1");

        // TODO: Begin fermentation
        Instant now = timeSource.instant();
        Instant later = now.plus(Duration.standardDays(10));
        batch.setStart(new Date(now.toEpochMillisLong()));
        batch.setStop(new Date(later.toEpochMillisLong()));
        ferments.save(batch);
    }
}
