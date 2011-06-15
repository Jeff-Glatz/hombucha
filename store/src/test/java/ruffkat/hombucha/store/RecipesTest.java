package ruffkat.hombucha.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.model.Ingredient;
import ruffkat.hombucha.model.Item;
import ruffkat.hombucha.model.Recipe;
import ruffkat.hombucha.util.CalendarUtils;

import javax.measure.quantity.Mass;
import javax.persistence.EntityNotFoundException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class RecipesTest extends FunctionalTest {
    @Autowired
    private Recipes recipes;

    @Test
    @Rollback(false)
    public void testSaveAndLoad() {
        Item item = new Item();
        item.setName("Ancient Emerald Lilly");

        entityManager.persist(item);

        Recipe recipe = recipes.create();
        recipe.setName("SCOBY DO");
        recipe.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));
        recipe.setYields(Measurements.volume("4.0 L"));

        Ingredient<Mass> tea = new Ingredient<Mass>();
        tea.setAmount(Measurements.mass("5.0 g"));
        tea.setInstructions("Let stand for 5 minutes");
        tea.setItem(item);

        recipe.addIngredient(tea);

        entityManager.persist(recipe);

        Long id = recipe.getId();

        assertNotNull(id);
        assertEquals(recipe, recipes.load(id));
    }

    @Test
    @Rollback(false)
    public void testSaveAndDelete() {
        Recipe recipe = recipes.create();
        recipe.setName("SCOBY DO");
        recipe.setReceived(CalendarUtils.date(Calendar.MAY, 12, 2011));
        recipe.setYields(Measurements.volume("2.0 L"));

        entityManager.persist(recipe);

        Long id = recipe.getId();

        recipe = recipes.load(id);
        recipes.delete(recipe);

        try {
            recipes.load(id);
            fail("expected an exception");
        } catch (EntityNotFoundException e) {
        }
    }
}
