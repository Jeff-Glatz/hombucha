package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Processing;

import javax.time.Duration;
import javax.time.Instant;
import javax.time.TimeSource;
import java.util.Date;

public class PackageTest extends FunctionalTest {

    @Autowired
    private TimeSource timeSource;

    @Autowired
    private SourceMaker sourceMaker;

    @Autowired
    private ItemMaker itemMaker;

    @Autowired
    private ContainerMaker containerMaker;

    @Autowired
    private MushroomMaker mushroomMaker;

    @Autowired
    private RecipeMaker recipeMaker;

    @Autowired
    private Ferments ferments;

    @Test
    public void testNewFerment()
            throws Exception {
        sourceMaker.make();
        itemMaker.make();
        containerMaker.make();
        mushroomMaker.make();
        recipeMaker.make();

        Containers containers = containerMaker.repository();
        Mushrooms mushrooms = mushroomMaker.repository();
        Recipes recipes = recipeMaker.repository();

        // "Design" the ferment
        Ferment batch = ferments.create();
        batch.setName("MaltBrewCha Run 1");
        batch.setProcessing(Processing.CONTINUOUS);
        batch.setMushroom(Searches.first(mushrooms, "Squiddy"));
        batch.setRecipe(Searches.first(recipes, "Starter Solution"));
        batch.setVolume(Measurements.volume("6.0 l"));
        batch.setContainer(containers.pick(batch));
        ferments.save(batch);

        // Begin fermentation
        Instant now = timeSource.instant();
        Instant later = now.plus(Duration.standardDays(10));
        batch.setStart(new Date(now.toEpochMillisLong()));
        batch.setStop(new Date(later.toEpochMillisLong()));
        ferments.save(batch);
    }
}
