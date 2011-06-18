package ruffkat.hombucha.model;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Indexed
public class Mother
        extends Sourced {

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH})
    @IndexedEmbedded(depth = 1)
    private Mother mother;

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mother)) return false;
        if (!super.equals(o)) return false;

        Mother mother = (Mother) o;

        if (mother != null ? !mother.equals(mother.mother) : mother.mother != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mother != null ? mother.hashCode() : 0);
        return result;
    }
}
