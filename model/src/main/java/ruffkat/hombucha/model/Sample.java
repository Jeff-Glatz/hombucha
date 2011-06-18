package ruffkat.hombucha.model;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import ruffkat.hombucha.measure.MeasureBridge;
import ruffkat.hombucha.time.InstantBridge;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.time.Instant;

@Entity
@Indexed
public class Sample<Q extends Quantity>
        implements Persistent, Viewable {

    @Id
    @GeneratedValue
    @DocumentId
    private Long oid;

    @OneToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH})
    @IndexedEmbedded
    private Ferment ferment;

    @Basic
    @Field
    @FieldBridge(impl = InstantBridge.class)
    @Type(type = "instant")
    private Instant taken;

    @Basic
    @Field
    @FieldBridge(impl = MeasureBridge.class)
    @Type(type = "measure")
    private Measure<Q> measurement;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] image;


    public Long getOid() {
        return oid;
    }

    public boolean persisted() {
        return oid != null;
    }

    public Ferment getFerment() {
        return ferment;
    }

    public void setFerment(Ferment ferment) {
        this.ferment = ferment;
    }

    public Instant getTaken() {
        return taken;
    }

    public void setTaken(Instant taken) {
        this.taken = taken;
    }

    public Measure<Q> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measure<Q> measurement) {
        this.measurement = measurement;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sample)) return false;

        Sample sample = (Sample) o;

        if (ferment != null ? !ferment.equals(sample.ferment) : sample.ferment != null) return false;
        if (measurement != null ? !measurement.equals(sample.measurement) : sample.measurement != null) return false;
        if (taken != null ? !taken.equals(sample.taken) : sample.taken != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ferment != null ? ferment.hashCode() : 0;
        result = 31 * result + (taken != null ? taken.hashCode() : 0);
        result = 31 * result + (measurement != null ? measurement.hashCode() : 0);
        return result;
    }
}
