package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Source;

public class SourcesImpl
        extends Repository<Source>
        implements Sources {

    public SourcesImpl() {
        super(Source.class);
    }

    @Override
    public <T extends Source> T create(Class<T> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
