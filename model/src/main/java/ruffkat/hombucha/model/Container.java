package ruffkat.hombucha.model;

import org.hibernate.annotations.Type;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Container
        extends Item<Volume> {

    @Type(type = "measure")
    private Measure<Volume> volume;

    @OneToOne
    private Ferment ferment;

    public Measure<Volume> getVolume() {
        return volume;
    }

    public void setVolume(Measure<Volume> volume) {
        this.volume = volume;
    }

    public Ferment getFerment() {
        return ferment;
    }

    public boolean isFermenting() {
        return ferment != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Container)) return false;
        if (!super.equals(o)) return false;

        Container container = (Container) o;

        if (volume != null ? !volume.equals(container.volume) : container.volume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        return result;
    }

    // TODO: Events

    void fill(Ferment ferment) {
        this.ferment = ferment;
    }

    void drain() {
        this.ferment = null;
    }
}
