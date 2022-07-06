package com.tim.view;
import com.tim.controller.DeveloperController;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class UserChoice {

    Scanner scanner = new Scanner(System.in);
    DeveloperController developerController = new DeveloperController();

    public UserChoice() throws SQLException {
    }

    public void start() throws SQLException {
        do {
            System.out.println(MENU);
            CHOICE_MENU = scanner.nextLong();

            if (CHOICE_MENU == 2) {
                developerController.getAllDevelopers();
            }

            if (CHOICE_MENU == 3) {
                System.out.println("Введите First_Name:");
                do {
                    FIRST_NAME = scanner.nextLine();
                } while (Objects.equals(FIRST_NAME, ""));

                System.out.println("Введите Last_Name:");
                do {
                    LAST_NAME = scanner.nextLine();
                } while (Objects.equals(LAST_NAME, ""));

                STATUS = "ACTIVE";

                System.out.println("Введите id Specialty:");
                do {
                    developerController.getAllSpecialtyDeveloper();
                    SPECIALTY = scanner.nextInt();
                } while (SPECIALTY <= 0);

                System.out.println("Введите id Skill:");
                do {
                    developerController.getAllSkillsDeveloper();
                    SKILL = scanner.nextInt();
                } while (SKILL <= 0);

                developerController.insertDeveloper(FIRST_NAME, LAST_NAME, STATUS, SPECIALTY, SKILL);
                System.out.println("TEST: ");
                System.out.println(FIRST_NAME);
                System.out.println(LAST_NAME);
                System.out.println(STATUS);
                System.out.println(SPECIALTY);
                System.out.println(SKILL);
                System.out.println("Разработчик: [" + FIRST_NAME + " " + LAST_NAME + "] успешно добавлен");
            }

            if (CHOICE_MENU == 4) {
                System.out.println("Введите id для удаления:");
                do {
                    ID = scanner.nextLong();
                } while (ID <= 0);

                developerController.deleteById(ID);
            }

            if (CHOICE_MENU == 5) {
                System.out.println("Введите id редактируемого разработчика");
                do {
                    ID = scanner.nextLong();
                } while (ID <= 0);
                System.out.println("Введите новое First_Name:");
                do {
                    FIRST_NAME = scanner.nextLine();
                } while (Objects.equals(FIRST_NAME, ""));

                System.out.println("Введите новое Last_Name:");
                do {
                    LAST_NAME = scanner.nextLine();
                } while (Objects.equals(LAST_NAME, ""));

                developerController.updateDeveloper(ID, FIRST_NAME, LAST_NAME);
            }

        } while (CHOICE_MENU != 1);
        developerController.saveDevelopers();
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

    public static int SKILL;
    public static int SPECIALTY;
    public static Long ID = 0L;
    public static Long CHOICE_MENU;
    public static String FIRST_NAME;
    public static String LAST_NAME;
    public static String STATUS;
}
