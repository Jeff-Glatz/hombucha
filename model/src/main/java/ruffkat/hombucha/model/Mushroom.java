package ruffkat.hombucha.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Mushroom
        extends Sourced {

    @ManyToOne
    private Mushroom mother;

    public Mushroom getMother() {
        return mother;
    }

    public void setMother(Mushroom mother) {
        this.mother = mother;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mushroom)) return false;
        if (!super.equals(o)) return false;

        Mushroom mushroom = (Mushroom) o;

        if (mother != null ? !mother.equals(mushroom.mother) : mushroom.mother != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mother != null ? mother.hashCode() : 0);
        return result;
    }
}
