package ruffkat.hombucha.store;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Ingredient;
import ruffkat.hombucha.model.Processing;
import ruffkat.hombucha.model.Recipe;
import ruffkat.hombucha.money.Money;

import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;
import java.time.Duration;
import java.time.Instant;
import java.time.Clock;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static ruffkat.hombucha.measure.MeasureAssert.assertMeasureEquals;

@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class AcceptanceIT
        extends IntegrationTest {

    @Autowired
    private Clock clock;

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
        assertMeasureEquals(Measurements.volume("3.785 l"), recipe.getVolume());

        List<Ingredient<?>> ingredients = recipe.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<Volume> water = recipe.ingredient("Distilled Water");
        assertMeasureEquals(water.getAmount(), Measurements.volume("2.84 l"));

        Ingredient<Mass> sugar = recipe.ingredient("Sugar in the Raw");
        assertMeasureEquals(sugar.getAmount(), Measurements.mass("250 g"));

        Ingredient<Mass> tea = recipe.ingredient("Organic Ancient Emerald Lily");
        assertMeasureEquals(tea.getAmount(), Measurements.mass("5 g"));

        assertEquals(new Money("2.461956"), recipe.price());
    }

    @Test
    @Transactional
    public void LookupFeederSolution()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Feeder Solution");

        assertTrue(recipe.persisted());
        assertMeasureEquals(Measurements.volume("1.0 l"), recipe.getVolume());

        List<Ingredient<?>> ingredients = recipe.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<Volume> water = recipe.ingredient("Distilled Water");
        assertMeasureEquals(water.getAmount(), Measurements.volume("1.0 l"));

        Ingredient<Mass> sugar = recipe.ingredient("Sugar in the Raw");
        assertMeasureEquals(sugar.getAmount(), Measurements.mass("85 g"));

        Ingredient<Mass> tea = recipe.ingredient("Organic Ancient Emerald Lily");
        assertMeasureEquals(tea.getAmount(), Measurements.mass("5 g"));

        assertEquals(new Money("1.485366"), recipe.price());
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
        Instant now = clock.instant();
        Instant later = now.plus(Duration.ofDays(10));
        batch.setStart(new Date(now.toEpochMilli()));
        batch.setStop(new Date(later.toEpochMilli()));
        ferments.save(batch);
    }

    @Test
    @Transactional
    public void ScaleUpFeederSolution()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Feeder Solution");

        Recipe scaled = recipe.scale(Measurements.volume("3.7850 l"));
        assertFalse(scaled.persisted());
        assertMeasureEquals(Measurements.volume("3.7850 l"), scaled.getVolume());

        List<Ingredient<?>> ingredients = scaled.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<Volume> water = scaled.ingredient("Distilled Water");
        assertMeasureEquals(Measurements.volume("3.7850 l"), water.getAmount());

        Ingredient<Mass> sugar = scaled.ingredient("Sugar in the Raw");
        assertMeasureEquals(Measurements.mass("321.725 g"), sugar.getAmount());

        Ingredient<Mass> tea = scaled.ingredient("Organic Ancient Emerald Lily");
        assertMeasureEquals(Measurements.mass("18.925 g"), tea.getAmount());

        assertEquals(new Money("5.622109"), scaled.price());
    }

    @Test
    @Transactional
    public void ScaleDownFeederSolution()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Feeder Solution");
        assertEquals(new Money("1.485366"), recipe.price());

        Recipe scaled = recipe.scale(Measurements.volume("0.5 l"));
        assertFalse(scaled.persisted());
        assertMeasureEquals(Measurements.volume("0.50 l"), scaled.getVolume());

        List<Ingredient<?>> ingredients = scaled.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<Volume> water = scaled.ingredient("Distilled Water");
        assertMeasureEquals(Measurements.volume("0.50 l"), water.getAmount());

        Ingredient<Mass> sugar = scaled.ingredient("Sugar in the Raw");
        assertMeasureEquals(Measurements.mass("42.5 g"), sugar.getAmount());

        Ingredient<Mass> tea = scaled.ingredient("Organic Ancient Emerald Lily");
        assertMeasureEquals(Measurements.mass("2.5 g"), tea.getAmount());

        assertEquals(new Money("0.7426829"), scaled.price());
    }

    @Test
    @Transactional
    public void ScaleDownFeederSolutionByIngredient()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Feeder Solution");

        Recipe scaled = recipe.scale("Sugar in the Raw", Measurements.mass("103.00 g"));
        assertFalse(scaled.persisted());
        assertMeasureEquals(Measurements.volume("1.211765 l"), scaled.getVolume());

        List<Ingredient<?>> ingredients = scaled.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<Volume> water = scaled.ingredient("Distilled Water");
        assertMeasureEquals(Measurements.volume("1.211765 l"), water.getAmount());

        Ingredient<Mass> sugar = scaled.ingredient("Sugar in the Raw");
        assertMeasureEquals(Measurements.mass("103.0000 g"), sugar.getAmount());

        Ingredient<Mass> tea = scaled.ingredient("Organic Ancient Emerald Lily");
        assertMeasureEquals(Measurements.mass("6.058825 g"), tea.getAmount());

        assertEquals(new Money("1.799914"), scaled.price());
    }
}
