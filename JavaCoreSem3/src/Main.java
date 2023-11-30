import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Создать на базе абстрактного класса массив/коллекцию сотрудников и заполнить его.
        Random rnd = new Random();
        BaseEmployee[] collection = new BaseEmployee[10];
        for (int i = 0; i < 10; i++) {
            String name;
            if (rnd.nextInt(2) == 0) {
                name = "Мария Петрова";
            } else {
                name = "Василий Иванов";
            }
            if (rnd.nextInt(2) == 0) {
                collection[i] = new Freelancer(name, rnd.nextInt(500, 1000));
            } else {
                collection[i] = new Worker(name, rnd.nextInt(50000, 85000));
            }
        }
        Arrays.sort(collection);

        for (BaseEmployee employee : collection) {
            System.out.println(employee);
        }
        System.out.println("---------------------");


        //Создать класс, содержащий массив или коллекцию сотрудников (как Worker так и Freelancer), и реализовать возможность
        // вывода данных с использованием foreach (подсказка: вам потребуется поработать с интерфейсом Iterable).

        StaffSheet sheet = new StaffSheet();
        sheet.add(new Freelancer("Вася",750));
        sheet.add(new Worker("Коля",56000));
        for (BaseEmployee e: sheet){
            System.out.println(e);
        }
    }
}

