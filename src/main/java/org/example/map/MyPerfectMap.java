package org.example.map;

import java.util.HashMap;

public interface MyPerfectMap<K , V> {

    void add(K key, V value);
    V get(K key);
    void delete(K key);
    int size();

}
