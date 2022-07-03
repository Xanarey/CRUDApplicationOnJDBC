package com.tim.view;

import com.tim.repository.DeveloperRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class UserChoice {

    Scanner scanner = new Scanner(System.in);
    DeveloperRepository developerRepository = new DeveloperRepository();

    public UserChoice() throws SQLException {
    }

    public void start() throws SQLException {
        do {
            System.out.println(MENU);
            CHOICE_MENU = scanner.nextLong();

            if (CHOICE_MENU == 2) {
                developerRepository.getAllDevelopers();
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
