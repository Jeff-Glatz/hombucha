package ruffkat.hombucha.model;

import java.util.Calendar;

public interface Sourced {
    String getName();
    Source getSource();
    Calendar getReceived();
}
