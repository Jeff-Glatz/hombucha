package ruffkat.hombucha.model;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import ruffkat.hombucha.measure.MeasureBridge;
import ruffkat.hombucha.measure.Volumetric;
import ruffkat.hombucha.money.Money;
import ruffkat.hombucha.money.Priced;

import javax.measure.Measure;
import javax.measure.converter.MultiplyConverter;
import javax.measure.quantity.Volume;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Indexed
public class Recipe
        extends Sourced
        implements Volumetric, Priced {

    @ElementCollection(fetch = FetchType.EAGER)
    @IndexedEmbedded
    private List<Ingredient<?>> ingredients = new LinkedList<Ingredient<?>>();

    @Basic
    @Field
    private String instructions;

    @Basic
    @Field
    @FieldBridge(impl = MeasureBridge.class)
    @Type(type = "measure")
    private Measure<Volume> volume;

    public List<Ingredient<?>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient<?>> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient<?> ingredient) {
        ingredients.add(ingredient);
    }

    public int ingredientCount() {
        return ingredients.size();
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Measure<Volume> getVolume() {
        return volume;
    }

    public void setVolume(Measure<Volume> volume) {
        this.volume = volume;
    }

    public Recipe scale(Measure<Volume> newVolume) {
        float requested = newVolume.floatValue(volume.getUnit());
        float current = volume.floatValue(volume.getUnit());
        float scale = 1.0f + ((requested - current) / current);
        MultiplyConverter converter = new MultiplyConverter(scale);

        Recipe recipe = new Recipe();
        recipe.setInstructions(getInstructions());
        recipe.setName(getName());
        recipe.setVolume(newVolume);
        recipe.setReceived(getReceived());
        recipe.setSource(getSource());
        List<Ingredient<?>> scaled = new ArrayList<Ingredient<?>>(ingredientCount());
        for (Ingredient<?> ingredient : ingredients) {
            scaled.add(ingredient.scale(converter));
        }
        recipe.setIngredients(scaled);
        return recipe;
    }

    public Money price() {
        Money price = Money.ZERO;
        for (Ingredient<?> ingredient : ingredients) {
            price = price.add(ingredient.price());
        }
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        if (!super.equals(o)) return false;

        Recipe recipe = (Recipe) o;

        if (ingredients != null ? !ingredients.equals(recipe.ingredients) : recipe.ingredients != null) return false;
        if (instructions != null ? !instructions.equals(recipe.instructions) : recipe.instructions != null)
            return false;
        if (volume != null ? !volume.equals(recipe.volume) : recipe.volume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ingredients=" + ingredients +
                ", instructions='" + instructions + '\'' +
                ", volume=" + volume +
                '}';
    }
}
