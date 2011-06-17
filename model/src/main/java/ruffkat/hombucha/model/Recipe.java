package ruffkat.hombucha.model;

import org.hibernate.annotations.Type;

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
public class Recipe
        extends Sourced {

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Ingredient<?>> ingredients = new LinkedList<Ingredient<?>>();

    @Basic
    private String instructions;

    @Basic
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

        Recipe scaled = new Recipe();
        scaled.setInstructions(getInstructions());
        scaled.setName(getName());
        scaled.setVolume(newVolume);
        scaled.setReceived(getReceived());
        scaled.setSource(getSource());
        List<Ingredient<?>> ingredients = new ArrayList<Ingredient<?>>(ingredientCount());
        for (Ingredient<?> ingredient : getIngredients()) {
            ingredients.add(ingredient.scale(converter));
        }
        scaled.setIngredients(ingredients);
        return scaled;
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
