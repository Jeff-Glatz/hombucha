package ruffkat.hombucha.store;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Ingredient;
import ruffkat.hombucha.model.Processing;
import ruffkat.hombucha.model.Recipe;

import javax.measure.Measure;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;
import javax.time.Duration;
import javax.time.Instant;
import javax.time.TimeSource;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

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
        Measure<Volume> kettle = Measurements.volume("2.0 l");

        Recipe recipe = Searches.first(recipes, "Starter Solution");
        assertTrue(recipe.persisted());
        assertEquals(Measurements.volume("6.0 l"), recipe.getVolume());
        List<Ingredient<?>> ingredients = recipe.getIngredients();
        assertEquals(2, ingredients.size());
        Ingredient<Mass> sugar = (Ingredient<Mass>) ingredients.get(0);
        Ingredient<Mass> tea = (Ingredient<Mass>) ingredients.get(1);
        assertEquals(sugar.getAmount(), Measurements.mass("500 g"));
        assertEquals(tea.getAmount(), Measurements.mass("10 g"));

        Recipe scaled = recipe.scale(kettle);
        assertFalse(scaled.persisted());
        assertEquals(Measurements.volume("2.0 l"), scaled.getVolume());
        List<Ingredient<?>> scaledIngredients = scaled.getIngredients();
        assertEquals(2, scaledIngredients.size());

        Ingredient<Mass> scaledSugar = (Ingredient<Mass>) scaledIngredients.get(0);
        Ingredient<Mass> scaledTea = (Ingredient<Mass>) scaledIngredients.get(1);
        assertEquals(scaledSugar.getAmount().getValue().floatValue(),
                Measurements.mass("166.6666567325592 g").getValue().floatValue());
        assertEquals(scaledTea.getAmount().getValue().floatValue(),
                Measurements.mass("3.333333134651184 g").getValue().floatValue());

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
