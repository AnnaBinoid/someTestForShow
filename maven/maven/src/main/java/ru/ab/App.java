package ru.ab;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла
 (Парадокс Монти Холла — Википедия ) и наглядно убедиться в верности парадокса
 (запустить игру в цикле на 1000 и вывести итоговый счет).
 Сохранить результат игр в одну из коллекций или в какой то библиотечный класс
 Вывести на экран статистику по победам и поражениям.
 */

public class App {
    static Random rand;
    static Map<Integer, Boolean> dontChangeChoise;
    static Map<Integer, Boolean> changeChoise;
    static int doors;
    static int attemp;

    public static void main(String[] args) {
        rand = new Random();
        dontChangeChoise = new HashMap<>();
        changeChoise = new HashMap<>();
        doors = 3;
        attemp = 1000;

        for (int i = 0; i < attemp; i++) {
            choosingAttemp(i);
        }

        int win = 0;
        for (Map.Entry<Integer, Boolean> entry: dontChangeChoise.entrySet()){
            if (entry.getValue()){
                win++;
            }
        }

        System.out.printf("If player don't change the door, he wins %d times from %d attemps.\n", win, attemp);

        win = 0;                                  // Статистика для второго игрока, изменяющего свой выбор.
        for (Map.Entry<Integer, Boolean> entry: changeChoise.entrySet()){
            if (entry.getValue()){
                win++;
            }
        }
        System.out.printf("If player change the door, he wins %d times from %d attemps.\n", win, attemp);
    }

    private static void choosingAttemp(int round) {
        int itIs = rand.nextInt(doors);
        int firstChoice = rand.nextInt(doors);
        int freeOpenDoor = -1;
        int secondChoice = -1;

        for (int i = 0; i < doors; i++) {
            if (i != itIs && i != firstChoice){
                freeOpenDoor = i;
            }
        }

        for (int i = 0; i < doors; i++) {
            if (i != freeOpenDoor && i != firstChoice){
                secondChoice = firstChoice;
            }
        }
        dontChangeChoise.put(round, itIs == secondChoice);

        for (int i = 0; i < doors; i++) {
            if (i != freeOpenDoor && i != firstChoice){
                secondChoice = i;
            }
        }
        changeChoise.put(round, itIs == secondChoice);
    }
}
