package ruffkat.swing.module;

import org.springframework.context.ConfigurableApplicationContext;

public abstract class SpringModule<T> implements Module<T> {
    protected final ConfigurableApplicationContext context;

    public SpringModule(ConfigurableApplicationContext context) {
        this.context = context;
    }

    public void release() {
        context.close();
    }
}
