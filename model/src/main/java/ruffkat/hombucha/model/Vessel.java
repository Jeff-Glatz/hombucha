package ruffkat.hombucha.model;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import ruffkat.hombucha.measure.MeasureBridge;
import ruffkat.hombucha.measure.Volumetric;
import ruffkat.hombucha.util.PropertyUtils;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@NamedQueries({
        @NamedQuery(
                name = "Vessels.available",
                query = "select vessel " +
                        "from Vessel vessel " +
                        "where vessel.ferment is null " +
                        "order by vessel.volume"),
        @NamedQuery(
                name = "Vessels.pick",
                query = "select vessel " +
                        "from Vessel vessel " +
                        "where vessel.ferment is null " +
                        "and vessel.volume >= :minimum " +
                        "order by vessel.volume")})
@Entity
@Indexed
public class Vessel
        extends Item<Volume>
        implements Volumetric {

    @Basic
    @Field
    @FieldBridge(impl = MeasureBridge.class)
    @Type(type = "ruffkat.hombucha.measure.MeasureType")
    private Measure<Volume> volume;

    @OneToOne(mappedBy = "vessel")
    @IndexedEmbedded(depth = 1)
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
        if (!(o instanceof Vessel)) return false;
        if (!super.equals(o)) return false;

        Vessel vessel = (Vessel) o;

        if (volume != null ? !volume.equals(vessel.volume) : vessel.volume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        return result;
    }

    // TODO: Events/Workflow

    void utilize(Ferment ferment) {
        this.ferment = ferment;
    }

    void release(Ferment ferment) {
        if (PropertyUtils.same(this.ferment, ferment)) {
            this.ferment = null;
        }
    }
}
