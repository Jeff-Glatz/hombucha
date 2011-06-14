package ruffkat.hombucha.model;

import java.util.Date;

public interface Sourced {
    Long getId();
    String getName();
    Source getSource();
    Date getReceived();
}
