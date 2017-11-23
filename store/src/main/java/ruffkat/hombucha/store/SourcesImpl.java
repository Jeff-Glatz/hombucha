package ruffkat.hombucha.store;

import org.springframework.stereotype.Repository;
import ruffkat.hombucha.model.Source;

@Repository
public class SourcesImpl
        extends AbstractRepository<Source>
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
