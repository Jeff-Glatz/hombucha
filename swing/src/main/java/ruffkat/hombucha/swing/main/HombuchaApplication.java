package ruffkat.hombucha.swing.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import ruffkat.hombucha.runtime.StoreWiring;

@Import(StoreWiring.class)
@ImportResource("classpath:swing-wiring.xml")
@SpringBootApplication(scanBasePackages = "ruffkat.hombucha.swing")
public class HombuchaApplication {
}
