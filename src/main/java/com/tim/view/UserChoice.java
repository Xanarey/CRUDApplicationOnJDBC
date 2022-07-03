package com.tim.view;

import com.tim.model.Status;
import com.tim.repository.DeveloperRepository;

import java.sql.SQLException;
import java.util.Objects;
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
                int id = developerRepository.getNewIdDeveloper();

                System.out.println("Введите First_Name:");
                do {
                    FIRST_NAME = scanner.nextLine();
                } while (Objects.equals(FIRST_NAME, ""));

                System.out.println("Введите Last_Name:");
                do {
                    LAST_NAME = scanner.nextLine();
                } while (Objects.equals(LAST_NAME, ""));

                do {
                    System.out.println("Введите STATUS (ACTIVE):");
                    STATUS = scanner.nextLine();
                } while (!STATUS.equals("ACTIVE"));

                System.out.println("Введите Specialty:");
                do {
                    SPECIALTY = scanner.nextLine();
                } while (Objects.equals(SPECIALTY, ""));

                System.out.println("Введите Skill:");
                do {
                    SKILL = scanner.nextLine();
                } while (Objects.equals(SKILL, ""));

                developerRepository.insertDeveloper(FIRST_NAME, LAST_NAME, STATUS, SPECIALTY, SKILL);

                System.out.println("Разработчик: [" + FIRST_NAME + " " + LAST_NAME + "] успешно добавлен");
            }

            if (CHOICE_MENU == 4) {
                System.out.println("Вводим имя и фамилию разраба , и удаляем его");
            }

            if (CHOICE_MENU == 5) {
                System.out.println(developerRepository.getNewIdDeveloper());
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
            5.  (Вывести новый id)
            """;

    public static Long CHOICE_MENU;
    public static String FIRST_NAME;
    public static String LAST_NAME;
    public static String STATUS;
    public static String SPECIALTY;
    public static String SKILL;
}