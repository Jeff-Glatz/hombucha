package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Container;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Processing;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
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

        // "Design" the ferment
        Ferment batch = ferments.create();
        batch.setName("MaltBrewCha Run 1");
        batch.setProcessing(Processing.CONTINUOUS);
        batch.setMushroom(Searches.first(mushroomMaker.repository(), "Squiddy"));
        batch.setRecipe(Searches.first(recipeMaker.repository(), "Starter Solution"));
        Measure<Volume> volume = Measurements.volume("6.0 l");
        batch.setVolume(volume);
        Container container = containers.pick(volume);
        batch.setContainer(container);
        ferments.save(batch);

        // Begin fermentation
        Instant now = timeSource.instant();
        Instant later = now.plus(Duration.standardDays(10));
        batch.setStart(new Date(now.toEpochMillisLong()));
        batch.setStop(new Date(later.toEpochMillisLong()));
        ferments.save(batch);
    }
}
