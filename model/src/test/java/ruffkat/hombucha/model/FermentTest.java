package ruffkat.hombucha.model;

import org.junit.Before;
import org.junit.Test;
import ruffkat.hombucha.measure.Measurements;

import static ruffkat.hombucha.measure.MeasureAssert.assertMeasureEquals;

public class FermentTest {
    private Ferment ferment;

    @Before
    public void setUp()
            throws Exception {
        ferment = new Ferment();
    }

    @Test
    public void testDailyDraw()
            throws Exception {
        ferment.setVolume(Measurements.volume("4.50 l"));
        assertMeasureEquals(Measurements.volume("0.900 l"), ferment.dailyDraw());
    }
}
