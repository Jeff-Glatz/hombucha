package ruffkat.hombucha.measure;

import org.junit.Test;

import javax.measure.Measure;
import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class MeasurementsTest {

    @Test
    public void testMolarity()
            throws Exception {
        Measure<Molarity> acidity = Measurements.molarity("7.0 mol/m3");
        assertEquals(new BigDecimal("7.0"), acidity.getValue());
        assertEquals(Molarity.UNIT, acidity.getUnit());
    }
}
