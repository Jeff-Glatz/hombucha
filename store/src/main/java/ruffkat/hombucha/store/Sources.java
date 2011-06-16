package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Source;

public interface Sources {
    <T extends Source> T create(Class<T> type);
    Source load(Long id);
    void delete(Source sample);
}
