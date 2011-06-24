package ruffkat.hombucha.measure;

import org.junit.Test;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.measure.unit.NonSI;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class RangeTest {

    @Test
    public void testTo() {
        Range<Volume> rangeSI = new Range<Volume>(Measurements.volume("0.47 l"), Measurements.volume("0.94 l"));
        Range<Volume> rangeEU = rangeSI.to(NonSI.OUNCE_LIQUID_US);

        Measure<Volume> lowEU = rangeEU.getLow();
        assertEquals(new BigDecimal("15.892590669866209"), Measurements.decimalValue(lowEU));

        Measure<Volume> highEU = rangeEU.getHigh();
        assertEquals(new BigDecimal("31.785181339732418"), Measurements.decimalValue(highEU));
    }
}
