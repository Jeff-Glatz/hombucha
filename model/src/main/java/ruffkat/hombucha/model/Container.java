package ruffkat.hombucha.model;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Container
        extends Component {

    @Transient
    private Measure<Volume> volume;

    public Measure<Volume> getVolume() {
        return volume;
    }

    public void setVolume(Measure<Volume> volume) {
        this.volume = volume;
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
}
