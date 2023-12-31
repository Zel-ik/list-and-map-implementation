package PerfectHashMapTest;

import org.example.map.PerfectHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PerfectHashMapTesting {

    // Тестируем работу метода add с добавлением одной пары ключ-значение и последующим ее изменением
    // Также тестируем работу метода get
    @Test
    void addOneEntry(){
        PerfectHashMap<String, Integer> myMap = new PerfectHashMap<>();
        myMap.add("Car", 14);
        myMap.add("Car", 145);

        Assertions.assertEquals(14, myMap.get("Car"));
    }

    // тестируем удаление объектов из мапы
    @Test
    void delete(){
        PerfectHashMap<String, Integer> myMap = new PerfectHashMap<>();
        myMap.add("Car", 14);
        myMap.add("Cars", 16);
        myMap.delete("Car");
        myMap.delete("Cars");

        Assertions.assertEquals(0, myMap.size());
    }
}
