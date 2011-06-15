package ruffkat.hombucha.type;

import org.junit.Before;
import org.junit.Test;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.measure.Units;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.measure.unit.Unit;
import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class MeasureTypeTest {
    private MeasureType type;

    @Before
    public void setUp()
            throws Exception {
        type = new MeasureType();
    }

    @Test
    public void testFormatting()
            throws Exception {
        Unit<Volume> unit = Units.volume("L");
        Measure<Volume> volume = Measurements.volume("6.0 l");

        assertEquals(new BigDecimal("6.0"), volume.getValue());
        assertEquals(unit, volume.getUnit());
        assertEquals("6.0 l", volume.toString());
    }
}
