package ruffkat.hombucha.model;

import java.util.Date;

public interface Sourced {
    String getName();
    Source getSource();
    Date getReceived();
}
