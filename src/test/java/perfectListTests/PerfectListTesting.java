package perfectListTests;

import org.example.list.PerfectList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PerfectListTesting {


    // проверяем работу метода add и метода get
    @Test
    void addOneElement(){
        PerfectList<String> noArgsPerfectList = new PerfectList<>();
        noArgsPerfectList.add("car");
        Assertions.assertEquals("car", noArgsPerfectList.get(0));
    }

    // проверяем работает ли конструктор с параметром в виде массива
    @Test
    void addArrayInList(){
        String[] someArray = {"one", "two", "three"};
        PerfectList<String> arrayAddedPerfectList = new PerfectList<>(someArray);

        // пробовал проводить проверку каждого элемента через цикл for, но не уверен что работает
        // при использовании System.out.println он не выводил ничего. Пока оставлю так
        Assertions.assertEquals(someArray[0], arrayAddedPerfectList.get(0));
        Assertions.assertEquals(someArray[1], arrayAddedPerfectList.get(1));
        Assertions.assertEquals(someArray[2], arrayAddedPerfectList.get(2));
    }

    // тестируем работу метода size
    @Test
    void size(){
        String[] someArray = {"one", "two", "three"};
        PerfectList<String> arrayAddedPerfectList = new PerfectList<>(someArray);

        Assertions.assertEquals(3, arrayAddedPerfectList.size());
    }

    // тестируем как работает удаление единственного элемента
    @Test
    void removeElement(){
        PerfectList<String> noArgsPerfectList = new PerfectList<>();
        noArgsPerfectList.add("car");
        noArgsPerfectList.remove(0);
        Assertions.assertNull(noArgsPerfectList.get(0));
    }

    // тестируем удаление всех элементов
    @Test
    void removeAllElements(){
        String[] someArray = {"one", "two", "three"};
        PerfectList<String> arrayAddedPerfectList = new PerfectList<>(someArray);

        arrayAddedPerfectList.removeAll();
        Assertions.assertEquals(0, arrayAddedPerfectList.size());
    }

    //проверяем работу метода remove при удалении n элемента массива
    @Test
    void removeSecondOfThreeElement(){
        String[] someArray = {"one", "two", "three"};
        PerfectList<String> arrayAddedPerfectList = new PerfectList<>(someArray);

        arrayAddedPerfectList.remove(1);
        Assertions.assertEquals("three", arrayAddedPerfectList.get(1));
    }


    // проверяем будет ли список автоматически расширяться при добавлении элементов сверх размера массива
    @Test
    void extend(){
        PerfectList<String> noArgsPerfectList = new PerfectList<>(3);
        noArgsPerfectList.add("car1");
        noArgsPerfectList.add("car2");
        noArgsPerfectList.add("car2");
        noArgsPerfectList.add("car3");

        Assertions.assertEquals(4, noArgsPerfectList.size());
    }
}
