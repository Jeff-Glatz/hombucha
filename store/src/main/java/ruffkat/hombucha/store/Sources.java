package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Source;

public interface Sources
        extends Repository<Source> {
    <T extends Source> T create(Class<T> type);
}
