package org.example.list;

public interface MyPerfectList<T> {
    public void add(T t);
    public void add(int index, T t);
    public T get(int index);
    public void remove(int index);
    public  void removeAll();
    public int size();
    public void extend(int oldSize);
    public void reduce(int oldSize);
}
