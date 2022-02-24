package model;

import java.util.Map;
import java.util.stream.Stream;

public abstract class ModelPool<T extends Model> {
    protected int nextID;
    protected Map<Integer, T> models;


    public int nextID() {
        return this.nextID;
    }

    public void add(Integer ID, T model) {
        this.models.put(ID, model);
        this.nextID++;
    }

    public Stream<T> stream() {
        return models.values().stream();
    }

    public boolean keyExists(String key) {
        return models.containsKey(key);
    }
}
