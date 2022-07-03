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
                } while (Objects.equals(STATUS, ""));

                System.out.println("Введите Specialty:");
                do {
                    SPECIALTY = scanner.nextLine();
                } while (Objects.equals(SPECIALTY, ""));

                System.out.println("Введите Skill:");
                do {
                    SKILL = scanner.nextLine();
                } while (Objects.equals(SKILL, ""));

                developerRepository.insertDeveloper(FIRST_NAME, LAST_NAME, STATUS, SPECIALTY, SKILL);
                System.out.println("TEST: ");
                System.out.println(FIRST_NAME);
                System.out.println(LAST_NAME);
                System.out.println(STATUS);
                System.out.println(SPECIALTY);
                System.out.println(SKILL);
                System.out.println("Разработчик: [" + FIRST_NAME + " " + LAST_NAME + "] успешно добавлен");
            }

            if (CHOICE_MENU == 4) {
                System.out.println("Вводим имя и фамилию разраба , и удаляем его");
                System.out.println("Введите First_Name:");
                do {
                    FIRST_NAME = scanner.nextLine();
                } while (Objects.equals(FIRST_NAME, ""));

                System.out.println("Введите Last_Name:");
                do {
                    LAST_NAME = scanner.nextLine();
                } while (Objects.equals(LAST_NAME, ""));
                developerRepository.deleteDeveloper(FIRST_NAME, LAST_NAME);
            }

            if (CHOICE_MENU == 5) {
                System.out.println("Введите имя редактируемого разраба");
                System.out.println("Введите First_Name:");
                do {
                    FIRST_NAME = scanner.nextLine();
                } while (Objects.equals(FIRST_NAME, ""));

                System.out.println("Введите Last_Name:");
                do {
                    LAST_NAME = scanner.nextLine();
                } while (Objects.equals(LAST_NAME, ""));

                System.out.println("Введите новое First_Name:");
                do {
                    CHANGE_FIRST_NAME = scanner.nextLine();
                } while (Objects.equals(FIRST_NAME, ""));

                System.out.println("Введите новое Last_Name:");
                do {
                    CHANGE_LAST_NAME = scanner.nextLine();
                } while (Objects.equals(LAST_NAME, ""));

                developerRepository.updateDeveloper(FIRST_NAME, LAST_NAME, CHANGE_FIRST_NAME, CHANGE_LAST_NAME);
            }

        } while (CHOICE_MENU != 1);
        developerRepository.saveChange();
        System.out.println("Вы вышли из приложения");
    }

    public static String MENU = """
            __________MENU__________
            1.  Сохранить изменения и выйти
            2.  Вывести базу данных
            3.  Добавить разработчика
            4.  Удалить разработчика
            5.  Редактировать данные о разработчике
            """;

    public static Long CHOICE_MENU;
    public static String FIRST_NAME;
    public static String LAST_NAME;
    public static String CHANGE_FIRST_NAME;
    public static String CHANGE_LAST_NAME;
    public static String STATUS;
    public static String SPECIALTY;
    public static String SKILL;
}
