@TypeDef(name = "instant",
        defaultForType = Instant.class,
        typeClass = InstantType.class)
package ruffkat.hombucha.time;

import org.hibernate.annotations.TypeDef;

import java.time.Instant;