package ruffkat.hombucha.model;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Sample<Q extends Quantity>
        implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Ferment ferment;
    private Calendar takenAt;
    @Transient
    private Measure<Q> measurement;

    public Ferment getFerment() {
        return ferment;
    }

    public void setFerment(Ferment ferment) {
        this.ferment = ferment;
    }

    public Calendar getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Calendar takenAt) {
        this.takenAt = takenAt;
    }

    public Measure<Q> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measure<Q> measurement) {
        this.measurement = measurement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sample sample = (Sample) o;

        if (ferment != null ? !ferment.equals(sample.ferment) : sample.ferment != null) return false;
        if (id != null ? !id.equals(sample.id) : sample.id != null) return false;
        if (measurement != null ? !measurement.equals(sample.measurement) : sample.measurement != null) return false;
        if (takenAt != null ? !takenAt.equals(sample.takenAt) : sample.takenAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ferment != null ? ferment.hashCode() : 0);
        result = 31 * result + (takenAt != null ? takenAt.hashCode() : 0);
        result = 31 * result + (measurement != null ? measurement.hashCode() : 0);
        return result;
    }
}
