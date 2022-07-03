package com.tim.view;

import java.util.Scanner;

public class UserChoice {

    Scanner scanner = new Scanner(System.in);

    public void start() {
        do {
            System.out.println(MENU);
            CHOICE_MENU = scanner.nextLong();

            if (CHOICE_MENU == 2) {
                System.out.println("НАША БД");
            }

            if (CHOICE_MENU == 3) {
                System.out.println("Вводим данные РАЗРАБА");
            }

            if (CHOICE_MENU == 4) {
                System.out.println("Вводим имя и фамилию разраба , и удаляем его");
            }
        } while (CHOICE_MENU != 1);
        System.out.println("Вы вышли из приложения");
    }

    public static String MENU = """
            __________MENU__________
            1.  Выход
            2.  Вывести базу данных
            3.  Добавить разработчика
            4.  Удалить разработчика
            5.  (Редактирование потом)
            """;

    public static Long CHOICE_MENU;
}
