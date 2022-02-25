package com.weareadaptive.auctionhouse.model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ModelPool<T extends Model> {
    private int nextID;
    private Map<Integer, T> models;

    public ModelPool (){
        this.nextID = 1;
        this.models = new HashMap<Integer, T>();
    }

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


    public Map<Integer, T> models(){
        return this.models;
    }
}
