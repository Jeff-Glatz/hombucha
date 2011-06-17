package ruffkat.hombucha.store;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.model.Ingredient;
import ruffkat.hombucha.model.Processing;
import ruffkat.hombucha.model.Recipe;
import ruffkat.hombucha.money.Money;

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
    private VesselMaker containerMaker;

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
    }

    @Test
    public void ScaleUpFeederSolution()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Feeder Solution");

        Recipe scaled = recipe.scale(Measurements.volume("2.0 l"));
        assertFalse(scaled.persisted());
        assertEquals(Measurements.volume("2.0 l"), scaled.getVolume());

        List<Ingredient<?>> ingredients = scaled.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<?> water = ingredients.get(0);
        assertEquals(water.getAmount().getValue().floatValue(),
                Measurements.volume("2.0 l").getValue().floatValue());

        Ingredient<?> sugar = ingredients.get(1);
        assertEquals(sugar.getAmount().getValue().floatValue(),
                Measurements.mass("170 g").getValue().floatValue());

        Ingredient<?> tea = ingredients.get(2);
        assertEquals(tea.getAmount().getValue().floatValue(),
                Measurements.mass("10 g").getValue().floatValue());

        System.out.println("Recipe: " + scaled.getName());
        System.out.println("Yield: " + scaled.getVolume());
        System.out.println();
        System.out.println("Ingredient List");
        for (Ingredient<?> ingredient : scaled.getIngredients()) {
            System.out.print("    * ");
            System.out.print(ingredient.getItem().getName());
            System.out.print(" - ");
            System.out.println(ingredient.getAmount());
        }
        System.out.println();
        System.out.println("Instructions");
        System.out.println(scaled.getInstructions());
    }

    @Test
    public void ScaleDownFeederSolution()
            throws Exception {
        Recipes recipes = recipeMaker.repository();
        Recipe recipe = Searches.first(recipes, "Feeder Solution");

        Recipe scaled = recipe.scale(Measurements.volume("0.5 l"));
        assertFalse(scaled.persisted());
        assertEquals(Measurements.volume("0.5 l"), scaled.getVolume());

        List<Ingredient<?>> ingredients = scaled.getIngredients();
        assertEquals(3, ingredients.size());

        Ingredient<?> water = ingredients.get(0);
        assertEquals(water.getAmount().getValue().floatValue(),
                Measurements.volume("0.5 l").getValue().floatValue());
        Ingredient<?> sugar = ingredients.get(1);
        assertEquals(sugar.getAmount().getValue().floatValue(),
                Measurements.mass("42.5 g").getValue().floatValue());
        Ingredient<?> tea = ingredients.get(2);
        assertEquals(tea.getAmount().getValue().floatValue(),
                Measurements.mass("2.5 g").getValue().floatValue());
    }

    @Test
    public void DesignNewFerment()
            throws Exception {
        Vessels vessels = containerMaker.repository();
        Mothers mothers = mushroomMaker.repository();
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
