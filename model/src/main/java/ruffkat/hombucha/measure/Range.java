package ruffkat.hombucha.measure;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

public class Range<Q extends Quantity> {
    private final Measure<Q> high;
    private final Measure<Q> low;

    public Range(Measure<Q> low, Measure<Q> high) {
        this.high = high;
        this.low = low;
    }

    public Measure<Q> getLow() {
        return low;
    }

    public Measure<Q> getHigh() {
        return high;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Range range = (Range) o;

        if (high != null ? !high.equals(range.high) : range.high != null) return false;
        if (low != null ? !low.equals(range.low) : range.low != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = high != null ? high.hashCode() : 0;
        result = 31 * result + (low != null ? low.hashCode() : 0);
        return result;
    }
}
