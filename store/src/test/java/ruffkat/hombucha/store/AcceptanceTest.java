package ruffkat.hombucha.store;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Ingredient;
import ruffkat.hombucha.model.Processing;
import ruffkat.hombucha.model.Recipe;
import ruffkat.hombucha.money.Money;

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
import static ruffkat.hombucha.measure.MeasureAssert.assertMeasureEquals;

public class AcceptanceTest extends FunctionalTest {

    @Autowired
    private TimeSource timeSource;

    @Autowired
    private SourceMaker sourceMaker;

    @Autowired
    private ItemMaker itemMaker;

    @Autowired
    private VesselMaker vesselMaker;

    @Autowired
    private MotherMaker motherMaker;

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
        vesselMaker.make();
        motherMaker.make();
        recipeMaker.make();
        fermentMaker.make();
        entityManager.flush();
    }

    @Test
    @Transactional
    public void LookupStarterSolution()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Starter Solution");

        assertTrue(recipe.persisted());
        assertEquals(Measurements.volume("3.785 l"), recipe.getVolume());

        List<Ingredient<?>> ingredients = recipe.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<?> water = ingredients.get(0);
        assertEquals(water.getAmount(), Measurements.volume("2.84 l"));

        Ingredient<?> sugar = ingredients.get(1);
        assertEquals(sugar.getAmount(), Measurements.mass("250 g"));

        Ingredient<?> tea = ingredients.get(2);
        assertEquals(tea.getAmount(), Measurements.mass("5 g"));

        assertEquals(new Money("2.462"), recipe.price());
    }

    @Test
    @Transactional
    public void LookupFeederSolution()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Feeder Solution");

        assertTrue(recipe.persisted());
        assertEquals(Measurements.volume("1.0 l"), recipe.getVolume());

        List<Ingredient<?>> ingredients = recipe.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<?> water = ingredients.get(0);
        assertEquals(water.getAmount(), Measurements.volume("1.0 l"));

        Ingredient<?> sugar = ingredients.get(1);
        assertEquals(sugar.getAmount(), Measurements.mass("85 g"));

        Ingredient<?> tea = ingredients.get(2);
        assertEquals(tea.getAmount(), Measurements.mass("5 g"));

        assertEquals(new Money("1.485"), recipe.price());
    }

    @Test
    @Transactional
    public void DesignNewFerment()
            throws Exception {
        Vessels vessels = vesselMaker.repository();
        Mothers mothers = motherMaker.repository();
        Recipes recipes = recipeMaker.repository();

        Ferment batch = ferments.create();
        batch.setName("Pu-Erh 2006");
        batch.setProcessing(Processing.SECONDARY);
        batch.setMother(Searches.first(mothers, "Squiddy"));
        batch.setRecipe(Searches.first(recipes, "Starter Solution"));
        batch.setVolume(Measurements.volume("6.0 l"));
        batch.setVessel(vessels.pick(batch));
        ferments.save(batch);
    }

    @Test
    @Transactional
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

    @Test
    @Transactional
    public void ScaleUpFeederSolution()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Feeder Solution");

        Recipe scaled = recipe.scale(Measurements.volume("3.785 l"));
        assertFalse(scaled.persisted());
        assertMeasureEquals(Measurements.volume("3.785 l"), scaled.getVolume());

        List<Ingredient<?>> ingredients = scaled.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<Volume> water = scaled.ingredient("Distilled Water");
        assertMeasureEquals(Measurements.volume("3.785 l"), water.getAmount());

        Ingredient<Mass> sugar = scaled.ingredient("Sugar in the Raw");
        assertMeasureEquals(Measurements.mass("321.725 g"), sugar.getAmount());

        Ingredient<Mass> tea = scaled.ingredient("Organic Ancient Emerald Lily");
        assertMeasureEquals(Measurements.mass("18.925 g"), tea.getAmount());

        assertEquals(new Money("5.623"), scaled.price());
    }

    @Test
    @Transactional
    public void ScaleDownFeederSolution()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Feeder Solution");
        assertEquals(new Money("1.485"), recipe.price());

        Recipe scaled = recipe.scale(Measurements.volume("0.5 l"));
        assertFalse(scaled.persisted());
        assertMeasureEquals(Measurements.volume("0.500 l"), scaled.getVolume());

        List<Ingredient<?>> ingredients = scaled.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<Volume> water = scaled.ingredient("Distilled Water");
        assertMeasureEquals(Measurements.volume("0.500 l"), water.getAmount());

        Ingredient<Mass> sugar = scaled.ingredient("Sugar in the Raw");
        assertMeasureEquals(Measurements.mass("42.500 g"), sugar.getAmount());

        Ingredient<Mass> tea = scaled.ingredient("Organic Ancient Emerald Lily");
        assertMeasureEquals(Measurements.mass("2.500 g"), tea.getAmount());

        assertEquals(new Money("0.7427"), scaled.price());
    }

    @Test
    @Transactional
    public void ScaleDownFeederSolutionByIngredient()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Feeder Solution");

        Recipe scaled = recipe.scale("Sugar in the Raw", Measurements.mass("103 g"));
        assertFalse(scaled.persisted());
        assertMeasureEquals(Measurements.volume("1.211 l"), scaled.getVolume());

        List<Ingredient<?>> ingredients = scaled.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<Volume> water = scaled.ingredient("Distilled Water");
        assertMeasureEquals(Measurements.volume("1.211 l"), water.getAmount());

        Ingredient<Mass> sugar = scaled.ingredient("Sugar in the Raw");
        assertMeasureEquals(Measurements.mass("103.000 g"), sugar.getAmount());

        Ingredient<Mass> tea = scaled.ingredient("Organic Ancient Emerald Lily");
        assertMeasureEquals(Measurements.mass("6.058 g"), tea.getAmount());

        assertEquals(new Money("1.800"), scaled.price());
    }
}
