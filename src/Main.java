import java.util.Random;
import java.util.Scanner;

public class Main {

    static char[][] map;
    static final int SIZE = 3;
    static final char DOT_EMPTY = '.';
    static final int WIN = 3;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();


    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (chekWinHorAndVert(DOT_X))
            {
                System.out.println("Победил человек");
                break;
            }
            else if (checkWinDig(DOT_X))
            {
                System.out.println("Победил человек");
                break;
            }
            else if (checkDraw())
            {
                System.out.println("Ничья");
                break;
            }
            cpuTurn();
            printMap();
            if (chekWinHorAndVert(DOT_O)) {
                System.out.println("Победил компьютер");
                break;
            }
            else if (checkWinDig(DOT_O)){
                System.out.println("Победил компьютер");
                break;
            }
            else if (checkDraw()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра окончена");
    }

    static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }

            System.out.println();
        }
    }

    static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }
        while (!isCellValid(x, y));

        map[y][x] = DOT_X;
    }

    static void cpuTurn() {
        int x;
        int y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        }
        while (!isCellValid(x, y));

        System.out.println("Компьютер сходил в точку " + (x + 1) + " " + (y + 1));

        map[y][x] = DOT_O;
    }

    public static boolean isCellValid(int x, int y)
    {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (map[y][x] == DOT_EMPTY) {
            return true;
        }
        return false;
    }

    static boolean chekWinHorAndVert(char symb) {

        int hor = 0, vert = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

//                    Проверка горизонталей
                if (map[i][j] == symb) {
                    hor++;
                } else {
                    hor = 0;
                }
//                    Проверка вертикалей

                if (map[j][i] == symb) {
                    vert++;
                } else {
                    vert = 0;
                }
                if (vert == WIN || hor == WIN) {
                    return true;
                }
            }
        }
        return false;
    }
    static boolean checkWinDig(char symb)
    {
//              проерка диагоналей
        int dig1 = 0, dig2 = 0;

        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == symb) {
                dig1++;
                } else {
                dig1 = 0;
            }
            if (map[i][2 - i] == symb) {
                dig2++;
            } else {
                dig2 = 0;
            }
            if (dig1 == WIN || dig2 == WIN) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}