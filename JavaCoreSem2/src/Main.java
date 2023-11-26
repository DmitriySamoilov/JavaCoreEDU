import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';

    private static final int WIN_COUNT = 4;
    private static boolean realOrImitation = true; // real - true, imitation - false
    //переменная используеттся чтобы разделить реальный ход и имитацию хода при проверках победы
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;


    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                realOrImitation = true;//real
                if (checkGameState(DOT_AI, "Победил компьютер!"))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }

    }

    /**
     * Инициализация игрового поля
     */
    static void initialize() {
        fieldSizeY = 5;
        fieldSizeX = 5;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать текущего состояния игрового поля
     */
    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");

        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn() {
        int x;
        int y;

        do {
            System.out.print("Введите координаты хода X и Y (от 1 до 3)\nчерез пробел: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));

        field[y][x] = DOT_HUMAN;
    }

    /**
     * Ход игрока (компьютера)
     */
    static void aiTurn() {
        int x;
        int y;

        //ход компьютера - поиск своих выигрышных комбинаций
        int [] xy = findWinPoint(DOT_AI);//массив используем, чтобы вернуть два значения
        if (xy[0] != -1 ) {field[xy[1]][xy[0]] = DOT_AI; return;} // если точка найдена делаем ход. -1 - не найдена


        //ход компьютера - поиск у человека предвыигрышных комбинаций
        xy = findWinPoint(DOT_HUMAN);//массив используем, чтобы вернуть два значения
        if (xy[0] != -1 ) {field[xy[1]][xy[0]] = DOT_AI; return;} // если точка найдена делаем ход. -1 - не найдена


        // ход компьютера - случайным образом
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));

        field[y][x] = DOT_AI;
    }

    /**
     * Проверка, является ли ячейка игрового поля пустой
     */
    static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    /**
     * Проверка доступности ячейки игрового поля
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }


    /**
     * Метод проверки состояния игры
     */
    static boolean checkGameState(char dot, String s) {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (checkWin(x, y, dot, WIN_COUNT)) {
                    if (realOrImitation) {
                        System.out.println(s);
                    }
                    return true;
                }
            }
        }
        if (checkDraw()) {
            if (realOrImitation) {
                System.out.println("Ничья!");
            }
            return true;
        }
        return false; // Игра продолжается
    }

    /**
     * Проверка на ничью
     */
    static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(x, y))
                    return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы игрока
     *
     * @param dot фишка игрока
     * @return признак победы
     */
    static boolean checkWin(int x, int y, char dot, int winCount) {
        if (check1(x, y, dot, winCount) | check2(x, y, dot, winCount) |
                check3(x, y, dot, winCount) | check4(x, y, dot, winCount)) {
            return true;
        }
        return false;
    }

    /**
     * Проверка по горизонтали вправо
     */
    static boolean check1(int x, int y, char dot, int winCount) {
        for (int i = 0; i < winCount; i++) {
            if (!(isCellValid(x + i, y)) || field[x + i][y] != dot) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверка по вертикали вниз
     */
    static boolean check2(int x, int y, char dot, int winCount) {
        for (int i = 0; i < winCount; i++) {
            if (!(isCellValid(x, y + i)) || field[x][y + i] != dot) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверка по диагонали вправо вниз
     */
    static boolean check3(int x, int y, char dot, int winCount) {
        for (int i = 0; i < winCount; i++) {
            if (!(isCellValid(x + i, y + i)) || field[x + i][y + i] != dot) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверка по диагонали вправо вверх
     */
    static boolean check4(int x, int y, char dot, int winCount) {
        for (int i = 0; i < winCount; i++) {
            if (!(isCellValid(x + i, y - i)) || field[x + i][y - i] != dot) {
                return false;
            }
        }
        return true;
    }

    static int[] findWinPoint(char dot) {
        int x;
        int y;
        for (y = 0; y < fieldSizeY; y++) {
            for (x = 0; x < fieldSizeX; x++) {
                // ищем только пустые клетки
                if (!isCellEmpty(x, y)) {continue;}
                realOrImitation = false;// imitation
                //имитируем ход
                field[y][x] = dot;
                // проверяем ведет ли ход к победе
                if (checkGameState(dot, "Test:error")) {
                    field[y][x] = DOT_EMPTY;// возвращаем клетке пустое значение
                    return new int[]{x, y}; //возвращаем искомые x,y и завершаем
                }
                field[y][x] = DOT_EMPTY;// возвращаем клетке пустое значение
            }
        } return new int[]{-1, -1}; //возвращаем отрицательный результат и завершаем
    }

}