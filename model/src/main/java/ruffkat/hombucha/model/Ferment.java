package ruffkat.hombucha.model;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Ferment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Recipe recipe;
    @OneToOne
    private Mushroom mushroom;
    @OneToOne
    private Reactor reactor;
    private Measure<Volume> volume;
    private Method method;
    private Calendar start;
    private Calendar end;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Mushroom getMushroom() {
        return mushroom;
    }

    public void setMushroom(Mushroom mushroom) {
        this.mushroom = mushroom;
    }

    public Reactor getReactor() {
        return reactor;
    }

    public void setReactor(Reactor reactor) {
        this.reactor = reactor;
    }

    public Measure<Volume> getVolume() {
        return volume;
    }

    public void setVolume(Measure<Volume> volume) {
        this.volume = volume;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ferment ferment = (Ferment) o;

        if (end != null ? !end.equals(ferment.end) : ferment.end != null) return false;
        if (method != ferment.method) return false;
        if (mushroom != null ? !mushroom.equals(ferment.mushroom) : ferment.mushroom != null) return false;
        if (reactor != null ? !reactor.equals(ferment.reactor) : ferment.reactor != null) return false;
        if (recipe != null ? !recipe.equals(ferment.recipe) : ferment.recipe != null) return false;
        if (start != null ? !start.equals(ferment.start) : ferment.start != null) return false;
        if (volume != null ? !volume.equals(ferment.volume) : ferment.volume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recipe != null ? recipe.hashCode() : 0;
        result = 31 * result + (mushroom != null ? mushroom.hashCode() : 0);
        result = 31 * result + (reactor != null ? reactor.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
