package ruffkat.hombucha.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Recipe implements Serializable, Sourced {
    private Long id;
    private String name;
    private Source source;
    private Date received;
    private List<Ingredient<?>> items;
    private String instructions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }

    public List<Ingredient<?>> getItems() {
        return items;
    }

    public void setItems(List<Ingredient<?>> items) {
        this.items = items;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (items != null ? !items.equals(recipe.items) : recipe.items != null) return false;
        if (instructions != null ? !instructions.equals(recipe.instructions) : recipe.instructions != null)
            return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (received != null ? !received.equals(recipe.received) : recipe.received != null) return false;
        if (source != null ? !source.equals(recipe.source) : recipe.source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (received != null ? received.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        return result;
    }
}
