package org.example.list;

import org.example.list.MyPerfectList;

import java.util.Arrays;

public class PerfectList<T> implements MyPerfectList {
    private final int defaultSize = 8;
    private final int sizeChangeRate = 2;
    Object[] array;
    private int lastElementIndex = 0;

    public PerfectList() {
        array = new Object[defaultSize];
    }

    public PerfectList(int newDefaultSize) {
        array = new Object[newDefaultSize];
    }

    public PerfectList(T[] someArray) {
        array = someArray.clone();
        lastElementIndex = array.length;
    }

    // добавляет элемент в массив и увеличивает lastElementIndex на 1
    @Override
    public void add(Object obj) {
        if (lastElementIndex == array.length - 1) extend(lastElementIndex);
        array[lastElementIndex++] = obj;
    }

    // 1. все элементы с конца перемещаются на шаг вправо
    // 2. элемент по переданному индексу получает новое значение
    @Override
    public void add(int index, Object o) {
        checkIndex(index);

        if (lastElementIndex == array.length - 1) extend(lastElementIndex);
        for (int i = lastElementIndex - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = array;
        lastElementIndex++;
    }

    //Возвращает элемент из списка array по индексу
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    // 1. удаляет элемент по индексу
    // 2. перемещает все элементы, находящиеся справа от удаляемого, на шаг влево
    // 3. проверяет размер массива, если 60% от массива пусты, то вызывает метод reduce
    @Override
    public void remove(int index) {
        checkIndex(index);

        for (int i = index; i < lastElementIndex-1 ; i++)  array[i] = array[i + 1];

        array[lastElementIndex-1] = null;
        lastElementIndex--;

        // честно не придумал нормальное условия для срабатывания, пока оставил так
        if (lastElementIndex < array.length / 100 *40) reduce(lastElementIndex);
    }

    // создаем новый пустой массив и заменяем им старый
    @Override
    public void removeAll() {
        lastElementIndex = 0;
        array = new Object[defaultSize];
    }

    // возвращает количество элементов в массиве
    @Override
    public int size() {
        return lastElementIndex;
    }

    // Создав новый больший массив, перезаписываем в него элементы из старого
    @Override
    public void extend(int oldSize) {
        int newSize = array.length * sizeChangeRate;
        array = Arrays.copyOf(array, newSize);

    }

    // Создав новый меньший массив, перезаписываем в него элементы из старого
    @Override
    public void reduce(int oldSize) {
        int newSize = array.length / sizeChangeRate;
        array = Arrays.copyOf(array, newSize);
    }

    // если индекс меньше чем 0 или больше чем последний элемент массива, выбрасывает исключение
    public void checkIndex(int index) {
        if (index > lastElementIndex || index < 0) throw new IndexOutOfBoundsException(index + " is out of range");
    }


}
