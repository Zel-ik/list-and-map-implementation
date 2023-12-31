package org.example.map;

import java.util.AbstractMap;

public class PerfectHashMap<K, V>  implements  MyPerfectMap<K, V> {
    private int allowedSize = 16;
    private Entry<K, V>[] entry;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public PerfectHashMap() {
        entry = new Entry[allowedSize];
    }

    @SuppressWarnings("unchecked")
    public PerfectHashMap(int newOriginSize) {
        this.allowedSize = newOriginSize;
        entry = new Entry[allowedSize];
    }


    // вычисляем хэш код ключа; если по данному хэш коду нет entry, добавляем значения key и value
    // если entry с таким хэшкодом есть, обновляем значение value
    @Override
    public void add(Object key, Object value) {
        if(key == null) throw new IllegalArgumentException("Can't add new Entry, because key is null");
        int index = hash(key);
        Entry newEntry = new Entry(key, value, null);
        if(entry[index] == null){
            entry[index] = newEntry;
            size++;
        }else {
            Entry<K, V> previousNode = null;
            Entry<K, V> currentNode = entry[index];
            while(currentNode != null){
                if(currentNode.getKey().equals(key)){
                    currentNode.setValue((V)value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if(previousNode != null)
                previousNode.setNext(newEntry);
        }
    }

    // вычисляем хэш код ключа, обходим entry пока не найдем ключ с искомым хэшкодом
    @Override
    public V get(K key) {
        V value = null;
        int index = hash(key);
        Entry<K, V> newEntry = entry[index];
        while (newEntry != null){
            if(newEntry.getKey().equals(key)) {
                value = newEntry.getValue();
                break;
            }
            newEntry = newEntry.getNext();
        }
        return value;
    }


    @Override
    public void delete(Object key) {
        // вычисляем индекс элемента через хэш код методом hash
        int index = hash(key);
        Entry previous = null;
        Entry newEntry = entry[index];
        while (newEntry != null){

            //обходим все элементы массива entry пока не найдем искомый индекс
            // когда находим устанавливаем ему значение следующего entry
            if(newEntry.getKey().equals(key)){
                if(previous == null){
                    newEntry = newEntry.getNext();
                    entry[index] = newEntry;
                    size--;
                    return;
                }else {
                    previous.setNext(newEntry.getNext());
                    return;
                }
            }
            previous = newEntry;
            newEntry = newEntry.getNext();
        }
    }

    @Override
    public int size() {
        return size;
    }

    private int hash(Object key) {
        if(key == null){
            return 0;
        }
        return Math.abs(key.hashCode()) % allowedSize;
    }

}
