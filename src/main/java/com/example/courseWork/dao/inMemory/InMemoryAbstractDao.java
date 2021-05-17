package com.example.courseWork.dao.inMemory;

import com.example.courseWork.dao.AbstractDao;
import java.util.*;
import java.util.function.*;

class InMemoryAbstractDao<T> implements AbstractDao<T> {
    protected Map<String, T> entities;
    protected Function<T, Integer> idGetter;
    protected BiConsumer<T, Integer> idSetter;
    protected InMemoryDatabase database;

    InMemoryAbstractDao(Map<String, T> entities,
                        InMemoryDatabase database) {
        this.entities = entities;
        this.database = database;
    }

    @Override
    public T get(String name) {
        return entities.get(name);
    }

    @Override
    public Collection<T> findAll() {
        return entities.values();
    }

}
